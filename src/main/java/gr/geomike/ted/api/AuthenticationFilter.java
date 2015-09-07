package gr.geomike.ted.api;

import gr.geomike.ted.api.db.EntityDao;
import gr.geomike.ted.api.db.entity.User;
import java.lang.reflect.Method;
import java.util.*;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.internal.util.Base64;

@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter
{
    @Context
    private ResourceInfo resourceInfo;

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
            .entity("You cannot access this resource").build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
            .entity("Access blocked for all users !!").build();

    public void filter(ContainerRequestContext requestContext)
    {
        Method method = resourceInfo.getResourceMethod();

        if(!method.isAnnotationPresent(PermitAll.class))
        {
            if(method.isAnnotationPresent(DenyAll.class))
            {
                requestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }

            final MultivaluedMap<String, String> headers = requestContext.getHeaders();

            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

            if(authorization == null || authorization.isEmpty())
            {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }

            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
            String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            System.out.println(username);
            System.out.println(password);

            if(method.isAnnotationPresent(RolesAllowed.class))
            {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

                if(!isUserAllowed(username, password, rolesSet))
                {
                    requestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            }
        }
    }
    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet)
    {
        /*List<QueryParameter> params = new ArrayList<QueryParameter>();
        params.add(new QueryParameter("username", username));
        params.add(new QueryParameter("password", password));*/

        Map<String, Object> params =new HashMap<String, Object>();
        params.put("username",username);
        params.put("password",password);
        List<User> users = EntityDao.Find("User.findByUsernameAndPassword",params);

        if (users.isEmpty()) {
            return false;
        }
        else {
            return rolesSet.contains(users.get(0).getRole());
        }
    }
}

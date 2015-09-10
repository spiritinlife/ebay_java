package gr.geomike.ted.api;

import gr.geomike.ted.api.db.EntityDao;
import gr.geomike.ted.api.db.entity.User;
import org.glassfish.jersey.internal.util.Base64;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.*;

@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter
{
    @Context
    private ResourceInfo resourceInfo;

    @Inject
    UriInfo uriInfo;

    public static final String AUTHORIZATION_PROPERTY = "Authorization";
    public static final String AUTHENTICATION_SCHEME = "Basic";
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
            final MultivaluedMap<String, String> pathParameters = uriInfo.getPathParameters();
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

            if(method.isAnnotationPresent(RolesAllowed.class))
            {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

               List<String> authIds = pathParameters.get("username");
               String authId = "";
               if (authIds != null && !authIds.isEmpty()){
                    authId = authIds.get(0);
               }

                if(!isUserAllowed(username, password, authId, rolesSet))
                {
                    requestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            }
        }
    }
    private boolean isUserAllowed(final String username, final String password, final String authId, final Set<String> rolesSet)
    {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("username",username);
        params.put("password",password);
        List<User> users = EntityDao.Find("User.findByUsernameAndPassword", params);

        if (users.isEmpty()) {
            return false;
        }
        else {
            String role = users.get(0).getRole();
            String userId = users.get(0).getUsername();

            if (rolesSet.contains("AUTH_USER")){
                return userId.equals(authId) || rolesSet.contains(role);
            }
            else {
                return rolesSet.contains(role);
            }
        }
    }
}

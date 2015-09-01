package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.api.db.dao.UserDao;
import gr.geomike.ted.api.db.entity.User;

//import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        return JSON.toJson(UserDao.findAll());
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getUser(@PathParam("id") int id) {
        return JSON.toJson(UserDao.find(id));
    }

   /* @POST
    @Path("/login")
    public String loginUser()
    {
        HttpServletRequest request;
        HttpServletResponse response;
        HttpSession session = request.getSession();
        session.setAttribute("user", "Pankaj");
        //setting session to expiry in 30 mins
        session.setMaxInactiveInterval(30*60);
        Cookie userName = new Cookie("user", user);
        userName.setMaxAge(30*60);
        response.addCookie(userName);
        response.sendRedirect("LoginSuccess.jsp");
    }*/

    @POST
    @Path("{id}")
    //@Produces("application/json")
    public void postUser(@PathParam("id") int id) {
        UserDao.insert(new User());
       // return JSON.toJson(UserDao.find(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        /*User userEntity = new User();

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setAddress(user.getAddress());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setSocialSecurityNumber(user.getSocialSecurityNumber());*/

        UserDao.insert(user);

        return  Response.status(201).build();
    }
}
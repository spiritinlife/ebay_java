package gr.geomike.ted.api;


import gr.geomike.ted.JSON;
import gr.geomike.ted.Views;
import gr.geomike.ted.api.db.EntityDao;
import gr.geomike.ted.api.db.entity.Message;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/messages")
public class MessageService {
    //get all incoming messages
    @RolesAllowed({"AUTH_USER"})
    @Path("/{username}/received")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getReceivedMessages(@PathParam("username") String username){
        System.err.println("GET: messages/"+ username + "/incoming");

        return JSON.toJson(EntityDao.Find("Message.findByToUsername", "username", username), Views.Message.class);
    }

    //get all sent messages
    @RolesAllowed({"AUTH_USER"})
    @Path("/{username}/sent")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSentMessages(@PathParam("username") String username){
        System.err.println("GET: messages/"+ username + "/sent");

        return JSON.toJson(EntityDao.Find("Message.findByFromUsername", "username", username), Views.Message.class);
    }

    //get all sent messages
    @RolesAllowed({"AUTH_USER"})
    @Path("/{username}/sent")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postSentMessage(Message message, @PathParam("username") String username){
        System.err.println("POST: messages/"+ username + "/sent");

        if (!message.getFromUsername().equals(username))
            return Response.status(Response.Status.FORBIDDEN).build();

        EntityDao.insert(message);
        return Response.status(201).build();
    }

    //update all sent messages
    @RolesAllowed({"AUTH_USER"})
    @Path("/{username}/sent/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSentMessages(Message message
            , @PathParam("username") String username
            , @PathParam("id") int id){
        System.err.println("PUT: messages/"+ username + "/sent/" + id);

        if (message.getFromUsername().equals(username)) {
                EntityDao.merge(message);
            return Response.status(201).build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }
    //update all received messages
    @RolesAllowed({"AUTH_USER"})
    @Path("/{username}/received/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReceivedMessages(Message message
            , @PathParam("username") String username
            , @PathParam("id") int id){
        System.err.println("PUT: messages/"+ username + "/received/" + id);

        if (message.getToUsername().equals(username)) {
            EntityDao.merge(message);
            return Response.status(201).build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }
}

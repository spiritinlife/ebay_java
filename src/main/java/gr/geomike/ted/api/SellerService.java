package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.Views;
import gr.geomike.ted.api.db.EntityDao;
import gr.geomike.ted.api.db.entity.Image;
import gr.geomike.ted.api.db.entity.Item;
import gr.geomike.ted.api.db.entity.Seller;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("sellers")
public class SellerService {
    //get all sellers
    @RolesAllowed("ADMIN")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSellers() {
        System.err.println("GET: /sellers");

        return JSON.toJson(EntityDao.Find("Seller.findAll"), Views.Seller.class);
    }

    //get single seller
    @RolesAllowed({"ADMIN", "AUTH_USER"})
    @Path("{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSeller(@PathParam("username") String username) {
        System.err.println("GET: /sellers/" + username);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);

        List<Seller> sellers = EntityDao.Find("Seller.findByUsername", params);

      //  System.err.println(sellers.get(0).getItems().get(0).getEnds());

        return JSON.toJson(sellers.get(0), Views.SellerInternal.class);
    }

    //update seller
    @RolesAllowed({"AUTH_USER"})
    //just for authorization
    @Path("{username}")
    //---
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSeller(Seller seller){
        System.err.println("PUT: /sellers/" + seller.getUsername());

        //handle bi-directional references inside Seller
        for (Item item : seller.getItems()){
            item.setSeller(seller);
            if (!item.getImages().isEmpty()){
                for (Image image: item.getImages()){
                    image.setItem(item);
                }
            }
        }
        EntityDao.merge(seller);

        return Response.status(201).build();
    }

    //get all seller items
    @RolesAllowed({"AUTH_USER"})
    //just for authorization
    @Path("{username}/items")
    //---
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSellerItems(@PathParam("username") String username, Item item){
        System.err.println("GET: " + username + "/items");

        return JSON.toJson(((Seller)EntityDao.Find("Seller.findByUsername", "username", username)
                .get(0)).getItems(), Views.SellerInternal.class);
    }

    //create item for seller
    @RolesAllowed({"AUTH_USER"})
    //just for authorization
    @Path("{username}/items")
    //---
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSeller(@PathParam("username") String username, Item item){
        System.err.println("POST: " + username + "/items");

        Seller seller = (Seller) EntityDao.Find("Seller.findByUsername","username",username).get(0);
        item.setSeller(seller);
        item.setStatus("BEGUN");
        item.setCurrently(item.getFirstBid());
        EntityDao.merge(item);

        seller = (Seller) EntityDao.Find("Seller.findByUsername","username",username).get(0);
        System.err.println(seller.getItems());

        return Response.status(201).build();
    }

    //returns single seller item
    //shouldnt be there except for authorization purposes i.e. return(seller(username).item(id))
    //when username is authorized
    @RolesAllowed({"AUTH_USER"})
    @Path("{username}/items/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String updateSeller(@PathParam("username") String username,
                               @PathParam("id") int id) {
        System.err.println("GET: " + username + "/items/" + id);

        return JSON.toJson(EntityDao.Find("Item.findById", "id", id).get(0), Views.ItemInternal.class);
    }

    //updates single seller item
    @RolesAllowed({"AUTH_USER"})
    @Path("{username}/items/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSeller(Item item,
                    @PathParam("username") String username,
                    @PathParam("id") int id) {
        System.err.println("PUT: " + username + "/items/" + id);

        Item oldItem = (Item) EntityDao.Find("Item.findById","id",id).get(0);
        for ( Image image : item.getImages()) {
            image.setItem(item);
        }

        if (oldItem.getSeller().getUsername().equals(username) && oldItem.getId() == item.getId() && oldItem.getStatus().equals("BEGUN")) {

            EntityDao.merge(item);
            return  Response.status(201).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();

    }
}

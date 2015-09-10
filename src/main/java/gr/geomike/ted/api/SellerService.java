package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.Views;
import gr.geomike.ted.api.db.EntityDao;
import gr.geomike.ted.api.db.entity.Image;
import gr.geomike.ted.api.db.entity.Item;
import gr.geomike.ted.api.db.entity.Seller;
import gr.geomike.ted.api.db.entity.User;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("sellers")
public class SellerService {
    @RolesAllowed("ADMIN")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSellers() {
        return JSON.toJson(EntityDao.Find("Seller.findAll"), Views.Seller.class);
    }

    @RolesAllowed({"ADMIN", "AUTH_USER"})
    @Path("{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSeller(@PathParam("username") String username) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);

        List<Seller> sellers = EntityDao.Find("Seller.findByUsername", params);

        System.err.println(sellers.get(0).getItems().get(0).getEnds());

        return JSON.toJson(sellers.get(0), Views.SellerInternal.class);
    }

    @RolesAllowed({"AUTH_USER"})
    //just for authorization
    @Path("{username}")
    //---
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSeller(Seller seller){
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

    //just returns item
    //shouldnt be there except for authorization purposes i.e. return(seller(username).item(id))
    //when username is authorized
    @RolesAllowed({"AUTH_USER"})
    @Path("{username}/items/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String updateSeller(@PathParam("username") String username,
                               @PathParam("id") int id) {
        return JSON.toJson(EntityDao.Find("Item.findById", "id", id).get(0), Views.ItemInternal.class);
    }

    @RolesAllowed({"AUTH_USER"})
    @Path("{username}/items/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSeller(Item item,
                    @PathParam("username") String username,
                    @PathParam("id") int id) {

        for (Item i : ((Seller)EntityDao.Find("Seller.findByUsername", "username", username).get(0)).getItems()){
            //validate ids
            if (i.getId() == item.getId() && i.getId() == id){
                //check if item status permits modification
                /*if (i.getStatus() != "BEGUN"){
                    return Response.status(Response.Status.FORBIDDEN).build();
                }*/

                System.out.println("OK..");
                EntityDao.merge(item);

                return  Response.status(201).build();
            }
        }

        return Response.status(201).build();
    }
}

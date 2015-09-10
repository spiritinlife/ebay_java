package gr.geomike.ted.api;

import gr.geomike.ted.api.db.EntityDao;
import gr.geomike.ted.api.db.entity.*;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Path("bids")
public class BidService {

    @RolesAllowed({"AUTH_USER"})
    @Path("{username}/{itemId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBid(Bid bid
            , @PathParam("username") String username, @PathParam("itemId") int itemId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        List<Bidder> bidders = EntityDao.Find("Bidder.findByUsername", params);

        params.clear();
        params.put("id", itemId);
        List<Item> items = EntityDao.Find("Item.findById", params);

        if (!bidders.isEmpty() && !items.isEmpty()){
            Bidder bidder = bidders.get(0);
            Item item = items.get(0);

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            System.out.println( sdf.format(cal.getTime()));

            bid.setTime(new Timestamp(new Date().getTime()));
            bid.setBidder(bidder);
            bid.setItem(item);
            item.getBids().add(bid);
            System.err.println(item.getBids().get(0).getAmount());
            bid.setItemId(item.getId());
            bidder.getBids().add(bid);

            EntityDao.merge(bidder);

            return Response.status(201).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}

package gr.geomike.ted.api;


import gr.geomike.ted.JSON;
import gr.geomike.ted.db.dao.UserDao;
import gr.geomike.ted.db.entity.User;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/adduser")
public class AddUser {
    @GET
    @Produces("application/json")
    public String postUser() {
        User user = new User();
        user.setId(15);
        user.setUsername("potis2");
        UserDao.insert(user);

        return JSON.toJson(user);
    }
}
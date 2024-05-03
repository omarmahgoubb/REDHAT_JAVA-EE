package controller;

import model.User;
import service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateful
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    @EJB
    private UserService userService;

    @POST
    @Path("register")
    public String registerUser(User user) {
        return userService.registerUser(user);
    }

    @GET
    @Path("get_user")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @POST
    @Path("update_profile")
    public String updateUserProfile(User updatedUser) {
        return userService.updateUserProfile(updatedUser);
    }
}

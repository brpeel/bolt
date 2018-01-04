package com.btn.bolt.resources;

import com.btn.bolt.data.Controller;
import com.btn.bolt.data.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("api/user")
public class UserResource implements Resource {

    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

    private final Controller controller;

    public UserResource(Controller controller) {
        this.controller = controller;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User get(@PathParam("id") long id ) {
        return controller.getUser(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(@Context HttpServletRequest request, User user) {

        try {
            long userId = controller.createUser(user);
            return Response.noContent().header("Location", new URI("api/user/" + userId)).build();
        } catch (Exception e) {
            logger.error("Could not create user", e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {

        try {
            controller.deleteUser(id);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Could not delete user " + id, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}

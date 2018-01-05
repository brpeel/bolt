package com.btn.bolt.resources;

import com.btn.bolt.data.Controller;
import com.btn.bolt.data.transfer.Transfer;
import com.btn.bolt.data.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

@Path("api/transfer")
public class TransferResource implements Resource {

    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

    private final Controller controller;


    public TransferResource(Controller controller) {
        this.controller = controller;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Transfer get(@PathParam("id") long id ) {
        return controller.getTransfer(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(@Context HttpServletRequest request, Transfer transfer) {

        try {
            User user = controller.getUser(transfer.getUserId());

            if (user == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("No user found for id "+transfer.getUserId()).build();
            }

            long transferId = controller.createTransfer(transfer);

            return Response.ok().header("Location", new URI("api/transfer/" + transferId)).build();
        } catch (Exception e) {
            logger.error("Could not create transfer", e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}

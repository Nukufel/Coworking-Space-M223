package ch.zli.m223.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.User;
import ch.zli.m223.service.BookingService;
import ch.zli.m223.service.UserService;

@Path("/booking")
@Tag(name = "Booking", description = "Handling of bookings")
public class BookingController {

    @Inject
    BookingService bookingService;

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all booking.", description = "Returns a list of all booking.")
    @Path("/list")
    @PermitAll
    public List<Booking> list() {
        return bookingService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new booking.", description = "Creates a new booking and returns the newly added booking.")
    @Path("/create")
    public Response create( Booking booking, @Context SecurityContext ctx) {
        User user = bookingService.findOne(booking.getId()).getUser();

        if (ctx.getUserPrincipal().getName().equals(user.getEmail()) ||  userService.findByEmail(ctx.getUserPrincipal().getName()).get().getRole()) {
            return Response.status(200).entity(bookingService.createBooking(booking)).build();
        } else {
            return Response.status(403).build();
        }
    }
  

    @Path("/delete/{id}")
    @DELETE
    @Operation(summary = "Deletes an booking.", description = "Deletes an booking by its id.")
    public Response delete(@PathParam("id") Long id, @Context SecurityContext ctx) {
        User user = bookingService.findOne(id).getUser();
    
        if (ctx.getUserPrincipal().getName().equals(user.getEmail()) ||  userService.findByEmail(ctx.getUserPrincipal().getName()).get().getRole()) {
            bookingService.deleteBooking(id);
            return Response.status(200).build();
        } else {
            return Response.status(403).build();
        }
    }

    @Path("/update/{id}")
    @PUT
    @RolesAllowed({"Admin"})
    @Operation(summary = "Updates an booking.", description = "Updates an booking by its id.")
    public Response update(@PathParam("id") Long id, Booking booking, @Context Principal principal) {
        
        if (((User) principal).getRole()) {
            return Response.status(200).entity(bookingService.updateBooking(id, booking)).build();
        } else {
            return Response.status(403).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get one booking.", description = "Returns a booking.")
    @Path("/{id}")
    public Booking oneBooking(@PathParam("id") Long id) {
        return bookingService.findOne(id);
    }
}

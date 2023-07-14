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

import ch.zli.m223.model.User;
import ch.zli.m223.service.UserService;


@Path("/user")
@Tag(name = "Users", description = "Handling of users")
public class UserController {
  
  @Inject
  UserService userService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(
      summary = "Index all users.", 
      description = "Returns a list of all users."
  )
  @Path("/all")
  public List<User> index() {
      return userService.findAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(
      summary = "Creates a new user. Also known as registration.", 
      description = "Creates a new user and returns the newly added user."
  )
  @PermitAll
  @Path("/create")
  public User create(User user) {
     return userService.createUser(user);
  }
  @Context SecurityContext c
  @Operation(
      summary = "Deletes an user.",
      description = "Deletes an user by its id."
  )
  @RolesAllowed("Admin")
  public Response delete(@PathParam("id") Long id, @Context SecurityContext ctx) {
      if (userService.findByEmail(ctx.getUserPrincipal().getName()).get().getRole()) {
            userService.deleteUser(id);
            return Response.status(200).build();
        } else {
            return Response.status(403).build();
        }
  }

  @Path("/update/{id}")
  @PUT
  @Operation(
      summary = "Updates an user.",
      description = "Updates an user by its id."
  )
  @RolesAllowed({"Admin"})
  public Response update(@PathParam("id") Long id, @Context SecurityContext ctx, User user) {
        if (userService.findByEmail(ctx.getUserPrincipal().getName()).get().getRole()) {
            return Response.status(200).entity(userService.updateUser(id, user)).build();
        } else {
            return Response.status(403).build();
        }
  }


  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(
      summary = "Get one users by id.", 
      description = "Returns a user."
  )
  @Path("/{id}")
  public User oneUser(@PathParam("id") Long id) {
      return userService.findOne(id);
  }
}

package ch.zli.m223.controller;

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
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.User;
import ch.zli.m223.service.UserService;


@Path("/user")
@Tag(name = "Users", description = "Handling of users")
@RolesAllowed({ "User", "Admin" })
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

  @Path("/delete/{id}")
  @DELETE
  @Operation(
      summary = "Deletes an user.",
      description = "Deletes an user by its id."
  )
  @RolesAllowed({"Admin"})
  public void delete(@PathParam("id") Long id) {
      userService.deleteUser(id);
  }

  @Path("/update/{id}")
  @PUT
  @Operation(
      summary = "Updates an user.",
      description = "Updates an user by its id."
  )
  @RolesAllowed({"Admin"})
  public User update(@PathParam("id") Long id, User user) {
      return userService.updateUser(id, user);
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

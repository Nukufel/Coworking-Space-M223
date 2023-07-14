package ch.zli.m223.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.mindrot.jbcrypt.BCrypt;

import ch.zli.m223.model.User;
import ch.zli.m223.model.Credential;
import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class SessionService {

  @Inject
  UserService userService;

  public Response authenticate(Credential credential) {
    Optional<User> principal = userService.findByEmail(credential.getEmail());
//BCrypt.hashpw(credential.getPassword(),principal.get().getSalt())
    try {
      if (principal.isPresent() && principal.get().getPassword().equals(credential.getPassword())) {
        String token = Jwt
            .issuer("https://zli.example.com/")
            .upn(credential.getEmail())
            .groups(principal.get().getRole() ? "Admin" : "User")
            .expiresIn(Duration.ofHours(24))
            .sign();
        return Response
            .ok(principal.get())
            .cookie(new NewCookie("coworkingspace", token))
            .header("Authorization", "Bearer " + token)
            .build();
      }
    } catch (Exception e) {
      System.err.println("Couldn't validate password.");
    }

    

    return Response.status(Response.Status.FORBIDDEN).build();
  }
}

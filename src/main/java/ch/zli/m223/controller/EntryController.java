package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
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

import ch.zli.m223.model.Buchung;
import ch.zli.m223.service.BuchungService;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
@RolesAllowed({ "User", "Admin" })
public class BuchungController {

    @Inject
    BuchungService buchungService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all entries.", description = "Returns a list of all entries.")
    public List<Buchung> index() {
        return buchungService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new buchung.", description = "Creates a new buchung and returns the newly added buchung.")
    public Buchung create(@Valid Buchung buchung) {
        return buchungService.createBuchung(buchung);
    }

    @Path("/{id}")
    @DELETE
    @Operation(summary = "Deletes an buchung.", description = "Deletes an buchung by its id.")
    public void delete(@PathParam("id") Long id) {
        buchungService.deleteBuchung(id);
    }

    @Path("/{id}")
    @PUT
    @Operation(summary = "Updates an buchung.", description = "Updates an buchung by its id.")
    public Buchung update(@PathParam("id") Long id, @Valid Buchung buchung) {
        return buchungService.updateBuchung(id, buchung);
    }

}

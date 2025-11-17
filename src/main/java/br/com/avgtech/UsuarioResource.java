package br.com.avgtech;

import br.com.avgtech.BO.UsuarioBO;
import br.com.avgtech.beans.Usuario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {

        try {
            UsuarioBO bo = new UsuarioBO();
            Usuario u = bo.buscarPorId(id);

            return Response.ok(u).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}

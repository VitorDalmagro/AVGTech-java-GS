package br.com.avgtech.resource;

import br.com.avgtech.BO.CadastroBO;
import br.com.avgtech.BO.LoginBO;
import br.com.avgtech.beans.Usuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/autenticacao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AutenticacaoResource {

    @POST
    @Path("/cadastrar")
    public Response cadastrar(Usuario usuario) {

        try {
            CadastroBO bo = new CadastroBO();
            String mensagem = bo.cadastrar(usuario);
            return Response.status(Response.Status.CREATED).entity(mensagem).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    public Response login(Usuario usuario) {

        try {
            LoginBO bo = new LoginBO();
            Usuario usuarioLogado = bo.logar(usuario.getEmail(), usuario.getSenha());
            return Response.ok(usuarioLogado).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
}

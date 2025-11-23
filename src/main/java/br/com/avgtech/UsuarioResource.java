package br.com.avgtech;

import br.com.avgtech.BO.UsuarioBO;
import br.com.avgtech.beans.Usuario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            UsuarioBO bo = new UsuarioBO();
            Usuario u = bo.buscarPorId(id);
            return Response.status(200).entity(u).build();
        } catch (Exception e) {
            switch (e.getMessage()) {
                case "DADOS_INVALIDOS":
                    return Response.status(400).entity("ID inválido.").build();
                case "USUARIO_NAO_ENCONTRADO":
                    return Response.status(404).entity("Usuário não encontrado.").build();
                default:
                    return Response.status(500).entity("Erro inesperado.").build();
            }
        }
    }
    @GET
    @Path("/email/{email}")
    public Response buscarPorEmail(@PathParam("email") String email) {
        try {
            UsuarioBO bo = new UsuarioBO();
            Usuario u = bo.buscarPorEmail(email);
            return Response.status(200).entity(u).build();
        } catch (Exception e) {
            switch (e.getMessage()) {
                case "DADOS_INVALIDOS":
                    return Response.status(400).entity("Email inválido.").build();
                case "USUARIO_NAO_ENCONTRADO":
                    return Response.status(404).entity("Usuário não encontrado.").build();
                default:
                    return Response.status(500).entity("Erro inesperado.").build();
            }
        }
    }
    @GET
    @Path("/cpf/{cpf}")
    public Response buscarPorCpf(@PathParam("cpf") String cpf) {

        try {
            UsuarioBO bo = new UsuarioBO();
            Usuario u = bo.buscarPorCpf(cpf);
            return Response.status(200).entity(u).build();

        } catch (Exception e) {
            switch (e.getMessage()) {
                case "DADOS_INVALIDOS":
                    return Response.status(400).entity("CPF inválido.").build();
                case "USUARIO_NAO_ENCONTRADO":
                    return Response.status(404).entity("Usuário não encontrado.").build();
                default:
                    return Response.status(500).entity("Erro inesperado.").build();
            }
        }
    }

    @GET
    public Response listar() {
        try {
            UsuarioBO bo = new UsuarioBO();
            List<Usuario> lista = bo.listarTodos();
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            switch (e.getMessage()) {
                case "LISTA_VAZIA":
                    return Response.status(404).entity("Nenhum usuário encontrado.").build();
                default:
                    return Response.status(500).entity("Erro inesperado.").build();
            }
        }
    }
    @PUT
    public Response atualizar(Usuario usuario) {
        try {
            UsuarioBO bo = new UsuarioBO();
            bo.atualizar(usuario);
            return Response.status(200).entity("Usuário atualizado com sucesso.").build();
        } catch (Exception e) {
            switch (e.getMessage()) {
                case "DADOS_INVALIDOS":
                    return Response.status(400).entity("Dados inválidos.").build();
                case "USUARIO_NAO_ENCONTRADO":
                    return Response.status(404).entity("Usuário não encontrado.").build();
                default:
                    return Response.status(500).entity("Erro inesperado.").build();
            }
        }
    }
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        try {
            UsuarioBO bo = new UsuarioBO();
            bo.deletar(id);
            return Response.status(200).entity("Usuário deletado com sucesso.").build();

        } catch (Exception e) {
            switch (e.getMessage()) {
                case "DADOS_INVALIDOS":
                    return Response.status(400).entity("ID inválido.").build();
                case "USUARIO_NAO_ENCONTRADO":
                    return Response.status(404).entity("Usuário não encontrado.").build();
                case "USUARIO_POSSUI_MATRICULAS":
                    return Response.status(409).entity("Não é possível deletar: usuário possui matrículas ativas.").build();
                default:
                    return Response.status(500).entity("Erro inesperado.").build();
            }
        }
    }


}

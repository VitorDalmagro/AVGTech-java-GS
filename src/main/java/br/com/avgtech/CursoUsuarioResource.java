package br.com.avgtech;

import br.com.avgtech.BO.CursoUsuarioBO;
import br.com.avgtech.beans.Curso;
import br.com.avgtech.beans.Usuario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/matricula")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CursoUsuarioResource {
    @POST
    @Path("/usuario/{idUsuario}/curso/{idCurso}")
    public Response matricular(@PathParam("idUsuario") int idUsuario, @PathParam("idCurso") int idCurso) {
        try {
            CursoUsuarioBO bo = new CursoUsuarioBO();
            bo.matricular(idUsuario, idCurso);
            return Response.status(200).entity("Usuário matriculado com sucesso.").build();
        } catch (Exception e) {
            switch (e.getMessage()) {
                case "DADOS_INVALIDOS":
                    return Response.status(400).entity("Dados inválidos.").build();
                case "USUARIO_NAO_ENCONTRADO":
                    return Response.status(404).entity("Usuário não encontrado.").build();
                case "CURSO_NAO_ENCONTRADO":
                    return Response.status(404).entity("Curso não encontrado.").build();
                case "JA_MATRICULADO":
                    return Response.status(409).entity("Usuário já está matriculado neste curso.").build();
                default:
                    return Response.status(500).entity("Erro inesperado.").build();
            }
        }
    }
    @DELETE
    @Path("/usuario/{idUsuario}/curso/{idCurso}")
    public Response desmatricular(@PathParam("idUsuario") int idUsuario, @PathParam("idCurso") int idCurso) {
        try {
            CursoUsuarioBO bo = new CursoUsuarioBO();
            bo.desmatricular(idUsuario, idCurso);
            return Response.status(200).entity("Usuário desmatriculado com sucesso.").build();

        } catch (Exception e) {
            return switch (e.getMessage()) {
                case "DADOS_INVALIDOS" -> Response.status(400).entity("Dados inválidos.").build();
                case "NAO_MATRICULADO" -> Response.status(404).entity("O usuário não estava matriculado neste curso.").build();
                default -> Response.status(500).entity("Erro inesperado.").build();
            };
        }
    }
    @GET
    @Path("/usuario/{idUsuario}/cursos")
    public Response listarCursos(@PathParam("idUsuario") int idUsuario) {
        try {
            CursoUsuarioBO bo = new CursoUsuarioBO();
            List<Curso> lista = bo.listarCursosDoUsuario(idUsuario);
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            switch (e.getMessage()) {
                case "DADOS_INVALIDOS":
                    return Response.status(400).entity("ID inválido.").build();
                case "LISTA_VAZIA":
                    return Response.status(404).entity("Usuário não está matriculado em nenhum curso.").build();
                default:
                    return Response.status(500).entity("Erro inesperado.").build();
            }
        }
    }
    @GET
    @Path("/curso/{idCurso}/usuarios")
    public Response listarUsuariosDoCurso(@PathParam("idCurso") int idCurso) {
        try {
            CursoUsuarioBO bo = new CursoUsuarioBO();
            List<Usuario> lista = bo.listarUsuariosDoCurso(idCurso);
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            return switch (e.getMessage()) {
                case "DADOS_INVALIDOS" -> Response.status(400).entity("ID inválido.").build();
                case "LISTA_VAZIA" -> Response.status(404).entity("Nenhum usuário matriculado neste curso.").build();
                default -> Response.status(500).entity("Erro inesperado.").build();
            };
        }
    }
    @GET
    public Response listarTodas() {
        try {
            CursoUsuarioBO bo = new CursoUsuarioBO();
            List<String> lista = bo.listarTodas();
            return Response.status(200).entity(lista).build();

        } catch (Exception e) {
            return switch (e.getMessage()) {
                case "LISTA_VAZIA" -> Response.status(404).entity("Nenhuma matrícula encontrada.").build();
                default -> Response.status(500).entity("Erro inesperado.").build();
            };
        }
    }
}

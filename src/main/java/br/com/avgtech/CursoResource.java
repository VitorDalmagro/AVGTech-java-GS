package br.com.avgtech;

import br.com.avgtech.BO.CursoBO;
import br.com.avgtech.beans.Curso;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/curso")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CursoResource {

    @POST
    @Path("/cadastrar")
    public Response cadastrar(Curso curso) {
        try {
            CursoBO bo = new CursoBO();
            bo.cadastrar(curso);
            return Response.status(201).entity("Curso cadastrado com sucesso.").build();
        } catch (Exception e) {
            switch (e.getMessage()) {
                case "DADOS_INVALIDOS":
                    return Response.status(400).entity("Dados inválidos.").build();
                default:
                    return Response.status(500).entity("Erro inesperado.").build();
            }
        }
    }
    @GET
    @Path("/id/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            CursoBO bo = new CursoBO();
            Curso c = bo.buscarPorId(id);
            return Response.status(200).entity(c).build();
        } catch (Exception e) {
            switch (e.getMessage()) {
                case "DADOS_INVALIDOS":
                    return Response.status(400).entity("ID inválido.").build();
                case "CURSO_NAO_ENCONTRADO":
                    return Response.status(404).entity("Curso não encontrado.").build();
                default:
                    return Response.status(500).entity("Erro inesperado.").build();
            }
        }
    }
    @GET
    @Path("/listar")
    public Response listar() {
        try {
            CursoBO bo = new CursoBO();
            List<Curso> lista = bo.listarTodos();
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            switch (e.getMessage()) {
                case "LISTA_VAZIA":
                    return Response.status(404).entity("Nenhum curso encontrado.").build();
                default:
                    return Response.status(500).entity("Erro inesperado.").build();
            }
        }
    }

    @PUT
    @Path("/atualizar")
    public Response atualizar(Curso curso) {

        try {
            CursoBO bo = new CursoBO();
            bo.atualizar(curso);
            return Response.status(200).entity("Curso atualizado com sucesso.").build();
        } catch (Exception e) {
            switch (e.getMessage()) {
                case "DADOS_INVALIDOS":
                    return Response.status(400).entity("Dados inválidos.").build();
                case "CURSO_NAO_ENCONTRADO":
                    return Response.status(404).entity("Curso não encontrado.").build();
                default:
                    return Response.status(500).entity("Erro inesperado.").build();
            }
        }
    }
    @DELETE
    @Path("/deletar/{id}")
    public Response deletar(@PathParam("id") int id) {
        try {
            CursoBO bo = new CursoBO();
            bo.deletar(id);
            return Response.status(200).entity("Curso deletado com sucesso.").build();
        } catch (Exception e) {
            switch (e.getMessage()) {
                case "DADOS_INVALIDOS":
                    return Response.status(400).entity("ID inválido.").build();
                case "CURSO_NAO_ENCONTRADO":
                    return Response.status(404).entity("Curso não encontrado.").build();
                default:
                    return Response.status(500).entity("Erro inesperado.").build();
            }
        }
    }
}

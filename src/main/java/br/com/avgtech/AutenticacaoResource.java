package br.com.avgtech;

import br.com.avgtech.BO.CadastroBO;
import br.com.avgtech.BO.LoginBO;
import br.com.avgtech.BO.UsuarioBO;
import br.com.avgtech.beans.Usuario;
import jakarta.ws.rs.*;
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
            return Response.status(201).entity(mensagem).build();
        } catch (Exception e) {
            switch (e.getMessage()) {
                case "EMAIL_JA_EXISTE":
                    return Response.status(409).entity("Email já existe.").build();
                case "CPF_JA_EXISTE":
                    return Response.status(409).entity("CPF já existe.").build();
                case "DADOS_INVALIDOS":
                    return Response.status(400).entity("Dados inválidos.").build();
                default:
                    return Response.status(500).entity("Erro inesperado.").build();
            }
        }
    }
    @POST
    @Path("/login")
    public Response login(Usuario usuario) {
        try {
            LoginBO bo = new LoginBO();
            Usuario usuarioLogado = bo.logar(usuario.getEmail(), usuario.getSenha());
            return Response.status(200).entity("Login realizado com sucesso.").build();

        } catch (Exception e) {
            switch (e.getMessage()) {
                case "EMAIL_INVALIDO":
                    return Response.status(400)
                            .entity("Email inválido. Preencha corretamente.")
                            .build();
                case "SENHA_INVALIDA":
                    return Response.status(400)
                            .entity("Senha inválida. Não deixe vazio.")
                            .build();
                case "CREDENCIAIS_INVALIDAS":
                    return Response.status(401)
                            .entity("Email ou senha incorretos.")
                            .build();
                default:
                    return Response.status(500)
                            .entity("Erro inesperado ao realizar login.")
                            .build();
            }
        }
    }

}

package br.com.avgtech.BO;

import br.com.avgtech.DAO.UsuarioDAO;
import br.com.avgtech.beans.Usuario;

public class LoginBO {

    public Usuario logar(String email, String senha) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Validações básicas
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("EMAIL_INVALIDO");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new Exception("SENHA_INVALIDA");
        }
        // Busca o usuário pelo DAO
        Usuario usuarioEncontrado = usuarioDAO.login(email, senha);
        if (usuarioEncontrado == null) {
            throw new Exception("CREDENCIAIS_INVALIDAS");
        }
        return usuarioEncontrado;
    }
}

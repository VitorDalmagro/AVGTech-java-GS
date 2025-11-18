package br.com.avgtech.BO;

import br.com.avgtech.DAO.UsuarioDAO;
import br.com.avgtech.beans.Usuario;

public class LoginBO {

    public Usuario logar(String email, String senha) throws Exception {
        UsuarioDAO dao = new UsuarioDAO();
        if (email == null) {
            throw new Exception("Email não pode ser nulo.");
        } else if (email.isEmpty()) {
            throw new Exception("Email não pode ser vazio.");
        }
        if (senha == null) {
            throw new Exception("Senha não pode ser nula.");
        } else if (senha.isEmpty()) {
            throw new Exception("Senha não pode ser vazia.");
        }
        Usuario usuario = dao.login(email, senha);
        if (usuario == null) {
            throw new Exception("Email ou senha incorretos.");
        } else {
            return usuario;
        }
    }
}

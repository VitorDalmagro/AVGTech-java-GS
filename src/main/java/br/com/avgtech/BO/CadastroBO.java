package br.com.avgtech.BO;

import br.com.avgtech.DAO.UsuarioDAO;
import br.com.avgtech.beans.Usuario;

public class CadastroBO {

    public String cadastrar(Usuario usuario) throws Exception {
        UsuarioDAO dao = new UsuarioDAO();

        if (usuario.getNome() == null) {
            throw new Exception("Nome não pode ser nulo.");
        } else if (usuario.getNome().length() == 0) {
            throw new Exception("Nome não pode ser vazio.");
        }

        if (usuario.getEmail() == null) {
            throw new Exception("Email não pode ser nulo.");
        } else if (usuario.getEmail().length() == 0) {
            throw new Exception("Email não pode ser vazio.");
        }

        if (usuario.getSenha() == null) {
            throw new Exception("Senha não pode ser nula.");
        } else if (usuario.getSenha().length() < 4) {
            throw new Exception("A senha deve ter pelo menos 4 caracteres.");
        }

        if (usuario.getNumeroTelefone() == null) {
            throw new Exception("Telefone não pode ser nulo.");
        } else if (usuario.getNumeroTelefone().length() == 0) {
            throw new Exception("Telefone não pode ser vazio.");
        }

        if (usuario.getCpf() == null) {
            throw new Exception("CPF não pode ser nulo.");
        } else if (usuario.getCpf().length() != 11) {
            throw new Exception("CPF inválido. Deve ter 11 dígitos.");
        }


        Usuario usuarioEmail = dao.selecionarPorEmail(usuario.getEmail());

        if (usuarioEmail != null) {
            throw new Exception("Este email já está cadastrado.");
        } else {


            Usuario usuarioCpf = dao.selecionarPorCpf(usuario.getCpf());

            if (usuarioCpf != null) {
                throw new Exception("Este CPF já está cadastrado.");
            } else {


                return dao.insert(usuario);
            }
        }
    }
}

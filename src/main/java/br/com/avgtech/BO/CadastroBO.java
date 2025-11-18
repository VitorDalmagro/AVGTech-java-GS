package br.com.avgtech.BO;

import br.com.avgtech.DAO.UsuarioDAO;
import br.com.avgtech.beans.Usuario;

public class CadastroBO {

    public String cadastrar(Usuario usuario) throws Exception {

        UsuarioDAO dao = new UsuarioDAO();

        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        if (usuario.getSenha() == null || usuario.getSenha().length() < 4) {
            throw new Exception("DADOS_INVALIDOS");
        }
        if (usuario.getNumeroTelefone() == null || usuario.getNumeroTelefone().isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        if (usuario.getCpf() == null || usuario.getCpf().length() != 11) {
            throw new Exception("DADOS_INVALIDOS");
        }
        // Email existe?
        Usuario emailExistente = dao.selecionarPorEmail(usuario.getEmail());
        if (emailExistente != null) {
            throw new Exception("EMAIL_JA_EXISTE");
        }
        // CPF existe?
        Usuario cpfExistente = dao.selecionarPorCpf(usuario.getCpf());
        if (cpfExistente != null) {
            throw new Exception("CPF_JA_EXISTE");
        }

        return dao.insert(usuario);
    }
}

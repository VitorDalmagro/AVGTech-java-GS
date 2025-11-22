package br.com.avgtech.BO;

import br.com.avgtech.DAO.UsuarioDAO;
import br.com.avgtech.beans.Usuario;

public class CadastroBO {

    public String cadastrar(Usuario usuario) throws Exception {
        UsuarioDAO usuarioDAO = null;

        try {
            usuarioDAO = new UsuarioDAO();

            // Validações de dados obrigatórios
            if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
                throw new Exception("DADOS_INVALIDOS");
            }
            if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
                throw new Exception("DADOS_INVALIDOS");
            }
            if (!usuario.getEmail().contains("@") || !usuario.getEmail().contains(".")) {
                throw new Exception("EMAIL_INVALIDO");
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

            // Verifica email duplicado
            Usuario usuarioComMesmoEmail = usuarioDAO.selecionarPorEmail(usuario.getEmail());
            if (usuarioComMesmoEmail != null) {
                throw new Exception("EMAIL_JA_EXISTE");
            }

            // Verifica CPF duplicado
            Usuario usuarioComMesmoCpf = usuarioDAO.selecionarPorCpf(usuario.getCpf());
            if (usuarioComMesmoCpf != null) {
                throw new Exception("CPF_JA_EXISTE");
            }

            return usuarioDAO.insert(usuario);

        } finally {
            if (usuarioDAO != null) {
                usuarioDAO.fecharConexao();
            }
        }
    }
}

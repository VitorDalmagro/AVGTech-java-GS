package br.com.avgtech.BO;

import br.com.avgtech.DAO.UsuarioDAO;
import br.com.avgtech.beans.Usuario;

import java.util.List;

public class UsuarioBO {

    public Usuario buscarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }

        UsuarioDAO usuarioDAO = null;

        try {
            usuarioDAO = new UsuarioDAO();
            Usuario usuarioEncontrado = usuarioDAO.selecionarPorId(id);

            if (usuarioEncontrado == null) {
                throw new Exception("USUARIO_NAO_ENCONTRADO");
            }

            return usuarioEncontrado;

        } finally {
            if (usuarioDAO != null) usuarioDAO.fecharConexao();
        }
    }

    public Usuario buscarPorEmail(String email) throws Exception {
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        UsuarioDAO usuarioDAO = null;
        try {
            usuarioDAO = new UsuarioDAO();
            Usuario usuarioEncontrado = usuarioDAO.selecionarPorEmail(email);
            if (usuarioEncontrado == null) {
                throw new Exception("USUARIO_NAO_ENCONTRADO");
            }
            return usuarioEncontrado;
        } finally {
            if (usuarioDAO != null) usuarioDAO.fecharConexao();
        }
    }
    public Usuario buscarPorCpf(String cpf) throws Exception {
        if (cpf == null || cpf.length() != 11) {
            throw new Exception("DADOS_INVALIDOS");
        }
        UsuarioDAO usuarioDAO = null;
        try {
            usuarioDAO = new UsuarioDAO();
            Usuario usuarioEncontrado = usuarioDAO.selecionarPorCpf(cpf);

            if (usuarioEncontrado == null) {
                throw new Exception("USUARIO_NAO_ENCONTRADO");
            }
            return usuarioEncontrado;
        } finally {
            if (usuarioDAO != null) usuarioDAO.fecharConexao();
        }
    }
    public List<Usuario> listarTodos() throws Exception {
        UsuarioDAO usuarioDAO = null;
        try {
            usuarioDAO = new UsuarioDAO();
            List<Usuario> lista = usuarioDAO.listarTodos();

            if (lista.isEmpty()) {
                throw new Exception("LISTA_VAZIA");
            }

            return lista;

        } finally {
            if (usuarioDAO != null) usuarioDAO.fecharConexao();
        }
    }
    public String atualizar(Usuario usuario) throws Exception {
        if (usuario.getIdUsuario() <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        UsuarioDAO usuarioDAO = null;
        try {
            usuarioDAO = new UsuarioDAO();
            String resultado = usuarioDAO.atualizar(usuario);

            if ("USUARIO_NAO_ENCONTRADO".equals(resultado)) {
                throw new Exception("USUARIO_NAO_ENCONTRADO");
            }
            return "OK";
        } finally {
            if (usuarioDAO != null) usuarioDAO.fecharConexao();
        }
    }
    public String deletar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }
        UsuarioDAO usuarioDAO = null;
        try {
            usuarioDAO = new UsuarioDAO();
            String resultado = usuarioDAO.deletar(id);
            if ("USUARIO_NAO_ENCONTRADO".equals(resultado)) {
                throw new Exception("USUARIO_NAO_ENCONTRADO");
            }
            return "OK";
        } finally {
            if (usuarioDAO != null) usuarioDAO.fecharConexao();
        }
    }
}

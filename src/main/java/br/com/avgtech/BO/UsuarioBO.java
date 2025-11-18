package br.com.avgtech.BO;

import br.com.avgtech.DAO.UsuarioDAO;
import br.com.avgtech.beans.Usuario;

import java.util.List;

public class UsuarioBO {

    public Usuario buscarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }
        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.selecionarPorId(id);
        if (u == null) {
            throw new Exception("USUARIO_NAO_ENCONTRADO");
        }
        return u;
    }
    public Usuario buscarPorEmail(String email) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.selecionarPorEmail(email);
        if (u == null) {
            throw new Exception("USUARIO_NAO_ENCONTRADO");
        }
        return u;
    }
    public Usuario buscarPorCpf(String cpf) throws Exception {
        if (cpf == null || cpf.length() != 11) {
            throw new Exception("DADOS_INVALIDOS");
        }
        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.selecionarPorCpf(cpf);
        if (u == null) {
            throw new Exception("USUARIO_NAO_ENCONTRADO");
        }
        return u;
    }
    public List<Usuario> listarTodos() throws Exception {
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> lista = dao.listarTodos();
        if (lista.isEmpty()) {
            throw new Exception("LISTA_VAZIA");
        }
        return lista;
    }
    public String atualizar(Usuario usuario) throws Exception {
        if (usuario.getIdUsuario() <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        UsuarioDAO dao = new UsuarioDAO();
        String resultado = dao.atualizar(usuario);
        if (resultado.equals("USUARIO_NAO_ENCONTRADO")) {
            throw new Exception("USUARIO_NAO_ENCONTRADO");
        }
        return "OK";
    }
    public String deletar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }
        UsuarioDAO dao = new UsuarioDAO();
        String resultado = dao.deletar(id);
        if (resultado.equals("USUARIO_NAO_ENCONTRADO")) {
            throw new Exception("USUARIO_NAO_ENCONTRADO");
        }
        return "OK";
    }

}

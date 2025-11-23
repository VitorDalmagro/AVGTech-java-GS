package br.com.avgtech.BO;

import br.com.avgtech.DAO.CursoDAO;
import br.com.avgtech.DAO.CursoUsuarioDAO;
import br.com.avgtech.DAO.UsuarioDAO;
import br.com.avgtech.beans.Curso;
import br.com.avgtech.beans.Matricula;
import br.com.avgtech.beans.Usuario;

import java.sql.SQLException;
import java.util.List;

public class CursoUsuarioBO {

    public void matricular(int idUsuario, int idCurso) throws Exception {
        if (idUsuario <= 0 || idCurso <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }

        UsuarioDAO usuarioDAO = null;
        CursoDAO cursoDAO = null;
        CursoUsuarioDAO cursoUsuarioDAO = null;

        try {
            // Verifica se o usuário existe
            usuarioDAO = new UsuarioDAO();
            Usuario usuarioEncontrado = usuarioDAO.selecionarPorId(idUsuario);
            if (usuarioEncontrado == null) {
                throw new Exception("USUARIO_NAO_ENCONTRADO");
            }

            // Verifica se o curso existe
            cursoDAO = new CursoDAO();
            Curso cursoEncontrado = cursoDAO.selecionarPorId(idCurso);
            if (cursoEncontrado == null) {
                throw new Exception("CURSO_NAO_ENCONTRADO");
            }

            // Realiza a matrícula
            cursoUsuarioDAO = new CursoUsuarioDAO();
            String resultado = cursoUsuarioDAO.inserirMatricula(idUsuario, idCurso);

            if (resultado.equals("JA_MATRICULADO")) {
                throw new Exception("JA_MATRICULADO");
            }
        } finally {
            if (usuarioDAO != null) usuarioDAO.fecharConexao();
            if (cursoDAO != null) cursoDAO.fecharConexao();
            if (cursoUsuarioDAO != null) cursoUsuarioDAO.fecharConexao();
        }
    }
    public List<Curso> listarCursosDoUsuario(int idUsuario) throws Exception {
        if (idUsuario <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }

        CursoUsuarioDAO cursoUsuarioDAO = null;

        try {
            cursoUsuarioDAO = new CursoUsuarioDAO();
            List<Curso> lista = cursoUsuarioDAO.listarCursosDoUsuario(idUsuario);

            if (lista.isEmpty()) {
                throw new Exception("LISTA_VAZIA");
            }
            return lista;

        } finally {
            if (cursoUsuarioDAO != null) cursoUsuarioDAO.fecharConexao();
        }
    }
    public void desmatricular(int idUsuario, int idCurso) throws Exception {
        if (idUsuario <= 0 || idCurso <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }
        CursoUsuarioDAO cursoUsuarioDAO = null;
        try {
            cursoUsuarioDAO = new CursoUsuarioDAO();
            cursoUsuarioDAO.desmatricular(idUsuario, idCurso);
        } catch (SQLException e) {
            if (e.getMessage().contains("NAO_MATRICULADO")) {
                throw new Exception("NAO_MATRICULADO");
            }
            throw e;
        } finally {
            if (cursoUsuarioDAO != null) cursoUsuarioDAO.fecharConexao();
        }
    }
    public List<Usuario> listarUsuariosDoCurso(int idCurso) throws Exception {
        if (idCurso <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }
        CursoUsuarioDAO cursoUsuarioDAO = null;
        try {
            cursoUsuarioDAO = new CursoUsuarioDAO();
            List<Usuario> lista = cursoUsuarioDAO.listarUsuariosDoCurso(idCurso);

            if (lista.isEmpty()) {
                throw new Exception("LISTA_VAZIA");
            }

            return lista;

        } finally {
            if (cursoUsuarioDAO != null) cursoUsuarioDAO.fecharConexao();
        }
    }
    public List<Matricula> listarTodas() throws Exception {
        CursoUsuarioDAO cursoUsuarioDAO = null;
        try {
            cursoUsuarioDAO = new CursoUsuarioDAO();
            List<Matricula> lista = cursoUsuarioDAO.listarTodasMatriculas();

            if (lista.isEmpty()) {
                throw new Exception("LISTA_VAZIA");
            }

            return lista;

        } finally {
            if (cursoUsuarioDAO != null) cursoUsuarioDAO.fecharConexao();
        }
    }

}

package br.com.avgtech.BO;

import br.com.avgtech.DAO.CursoDAO;
import br.com.avgtech.beans.Curso;

import java.util.List;

public class CursoBO {

    public void cadastrar(Curso curso) throws Exception {
        if (curso.getNome() == null || curso.getNome().trim().isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        if (curso.getDescricao() == null || curso.getDescricao().trim().isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        if (curso.getUrlVideo() == null || curso.getUrlVideo().trim().isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        CursoDAO cursoDAO = null;
        try {
            cursoDAO = new CursoDAO();
            cursoDAO.inserir(curso);
        } finally {
            if (cursoDAO != null) {
                cursoDAO.fecharConexao();
            }
        }
    }
    public Curso buscarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }
        CursoDAO cursoDAO = null;
        try {
            cursoDAO = new CursoDAO();
            Curso curso = cursoDAO.selecionarPorId(id);
            if (curso == null) {
                throw new Exception("CURSO_NAO_ENCONTRADO");
            }
            return curso;
        } finally {
            if (cursoDAO != null) {
                cursoDAO.fecharConexao();
            }
        }
    }
    public List<Curso> listarTodos() throws Exception {
        CursoDAO cursoDAO = null;
        try {
            cursoDAO = new CursoDAO();
            List<Curso> lista = cursoDAO.listarTodos();

            if (lista.isEmpty()) {
                throw new Exception("LISTA_VAZIA");
            }

            return lista;
        } finally {
            if (cursoDAO != null) {
                cursoDAO.fecharConexao();
            }
        }
    }
    public void atualizar(Curso curso) throws Exception {
        if (curso.getIdCurso() <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }
        CursoDAO cursoDAO = null;
        try {
            cursoDAO = new CursoDAO();
            String resultado = cursoDAO.atualizar(curso);

            if (resultado.equals("CURSO_NAO_ENCONTRADO")) {
                throw new Exception("CURSO_NAO_ENCONTRADO");
            }
        } finally {
            if (cursoDAO != null) {
                cursoDAO.fecharConexao();
            }
        }
    }

    public void deletar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }
        CursoDAO cursoDAO = null;
        try {
            cursoDAO = new CursoDAO();
            String resultado = cursoDAO.deletar(id);

            if (resultado.equals("CURSO_NAO_ENCONTRADO")) {
                throw new Exception("CURSO_NAO_ENCONTRADO");
            }
        } finally {
            if (cursoDAO != null) {
                cursoDAO.fecharConexao();
            }
        }
    }
}

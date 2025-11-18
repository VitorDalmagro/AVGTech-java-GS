package br.com.avgtech.BO;

import br.com.avgtech.DAO.CursoDAO;
import br.com.avgtech.beans.Curso;

import java.util.List;

public class CursoBO {

    public void cadastrar(Curso curso) throws Exception {
        if (curso.getNome() == null || curso.getNome().isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        if (curso.getDescricao() == null || curso.getDescricao().isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        if (curso.getUrlVideo() == null || curso.getUrlVideo().isEmpty()) {
            throw new Exception("DADOS_INVALIDOS");
        }
        CursoDAO dao = new CursoDAO();
        dao.inserir(curso);
    }
    public Curso buscarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }
        CursoDAO dao = new CursoDAO();
        Curso curso = dao.selecionarPorId(id);
        if (curso == null) {
            throw new Exception("CURSO_NAO_ENCONTRADO");
        }
        return curso;
    }

    public List<Curso> listarTodos() throws Exception {
        CursoDAO dao = new CursoDAO();
        List<Curso> lista = dao.listarTodos();

        if (lista.isEmpty()) {
            throw new Exception("LISTA_VAZIA");
        }
        return lista;
    }

    public void atualizar(Curso curso) throws Exception {
        if (curso.getIdCurso() <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }
        CursoDAO dao = new CursoDAO();
        String r = dao.atualizar(curso);

        if (r.equals("CURSO_NAO_ENCONTRADO")) {
            throw new Exception("CURSO_NAO_ENCONTRADO");
        }
    }

    public void deletar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("DADOS_INVALIDOS");
        }
        CursoDAO dao = new CursoDAO();
        String r = dao.deletar(id);
        if (r.equals("CURSO_NAO_ENCONTRADO")) {
            throw new Exception("CURSO_NAO_ENCONTRADO");
        }
    }
}

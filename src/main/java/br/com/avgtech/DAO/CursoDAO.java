package br.com.avgtech.DAO;

import br.com.avgtech.beans.Curso;
import br.com.avgtech.conexao.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    private Connection conexao;

    public CursoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }
    public void inserir(Curso curso) throws SQLException {
        String sql = "INSERT INTO CURSO (NOME, DESCRICAO, URL_VIDEO) VALUES (?, ?, ?)";
        PreparedStatement statement = conexao.prepareStatement(sql);

        statement.setString(1, curso.getNome());
        statement.setString(2, curso.getDescricao());
        statement.setString(3, curso.getUrlVideo());

        statement.execute();
        statement.close();
    }
    public Curso selecionarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM CURSO WHERE ID_CURSO=?";
        PreparedStatement statement = conexao.prepareStatement(sql);

        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();
        Curso cursoEncontrado = null;

        if (rs.next()) {
            cursoEncontrado = new Curso();
            cursoEncontrado.setIdCurso(rs.getInt(1));
            cursoEncontrado.setNome(rs.getString(2));
            cursoEncontrado.setDescricao(rs.getString(3));
            cursoEncontrado.setUrlVideo(rs.getString(4));
            cursoEncontrado.setDataCriacao(rs.getString(5));
        }
        rs.close();
        statement.close();
        return cursoEncontrado;
    }
    public List<Curso> listarTodos() throws SQLException {
        List<Curso> lista = new ArrayList<>();

        String sql = "SELECT * FROM CURSO";
        PreparedStatement statement = conexao.prepareStatement(sql);

        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Curso curso = new Curso();

            curso.setIdCurso(rs.getInt(1));
            curso.setNome(rs.getString(2));
            curso.setDescricao(rs.getString(3));
            curso.setUrlVideo(rs.getString(4));
            curso.setDataCriacao(rs.getString(5));

            lista.add(curso);
        }
        rs.close();
        statement.close();

        return lista;
    }
    public String atualizar(Curso curso) throws SQLException {
        String sql = "UPDATE CURSO SET NOME=?, DESCRICAO=?, URL_VIDEO=? WHERE ID_CURSO=?";
        PreparedStatement statement = conexao.prepareStatement(sql);

        statement.setString(1, curso.getNome());
        statement.setString(2, curso.getDescricao());
        statement.setString(3, curso.getUrlVideo());
        statement.setInt(4, curso.getIdCurso());

        int linhas = statement.executeUpdate();
        statement.close();

        if (linhas == 0) {
            return "CURSO_NAO_ENCONTRADO";
        }

        return "CURSO_ATUALIZADO";
    }
    public String deletar(int id) throws SQLException {
        String sql = "DELETE FROM CURSO WHERE ID_CURSO=?";
        PreparedStatement statement = conexao.prepareStatement(sql);

        statement.setInt(1, id);
        int linhas = statement.executeUpdate();
        statement.close();

        if (linhas == 0) {
            return "CURSO_NAO_ENCONTRADO";
        }
        return "CURSO_DELETADO";
    }
}

package br.com.avgtech.DAO;

import br.com.avgtech.beans.Curso;
import br.com.avgtech.conexao.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public Connection cn;

    public CursoDAO() throws SQLException, ClassNotFoundException {
        this.cn = new ConexaoFactory().conexao();
    }
    public void inserir(Curso curso) throws SQLException {
        String sql = "INSERT INTO CURSO (NOME, DESCRICAO, URL_VIDEO) VALUES (?, ?, ?)";
        PreparedStatement pstmt = cn.prepareStatement(sql);

        pstmt.setString(1, curso.getNome());
        pstmt.setString(2, curso.getDescricao());
        pstmt.setString(3, curso.getUrlVideo());

        pstmt.execute();
        pstmt.close();
    }

    public Curso selecionarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM CURSO WHERE ID_CURSO=?";
        PreparedStatement pstmt = cn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();
        Curso curso = null;
        if (rs.next()) {
            curso = new Curso();
            curso.setIdCurso(rs.getInt(1));
            curso.setNome(rs.getString(2));
            curso.setDescricao(rs.getString(3));
            curso.setUrlVideo(rs.getString(4));
            curso.setDataCriacao(rs.getString(5));
        }
        rs.close();
        pstmt.close();
        return curso;
    }
    public List<Curso> listarTodos() throws SQLException {

        List<Curso> lista = new ArrayList<>();

        String sql = "SELECT * FROM CURSO";
        PreparedStatement pstmt = cn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

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
        pstmt.close();

        return lista;
    }

    public String atualizar(Curso curso) throws SQLException {
        String sql = "UPDATE CURSO SET NOME=?, DESCRICAO=?, URL_VIDEO=? WHERE ID_CURSO=?";
        PreparedStatement pstmt = cn.prepareStatement(sql);
        pstmt.setString(1, curso.getNome());
        pstmt.setString(2, curso.getDescricao());
        pstmt.setString(3, curso.getUrlVideo());
        pstmt.setInt(4, curso.getIdCurso());
        int linhas = pstmt.executeUpdate();
        pstmt.close();
        if (linhas == 0) {
            return "CURSO_NAO_ENCONTRADO";
        }
        return "OK";
    }
    public String deletar(int id) throws SQLException {
        String sql = "DELETE FROM CURSO WHERE ID_CURSO=?";
        PreparedStatement pstmt = cn.prepareStatement(sql);
        pstmt.setInt(1, id);
        int linhas = pstmt.executeUpdate();
        pstmt.close();
        if (linhas == 0) {
            return "CURSO_NAO_ENCONTRADO";
        }
        return "OK";
    }
}

package br.com.avgtech.DAO;

import br.com.avgtech.beans.Curso;
import br.com.avgtech.beans.Usuario;
import br.com.avgtech.conexao.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoUsuarioDAO {
    private Connection conexao;

    public CursoUsuarioDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }
    public String inserirMatricula(int idUsuario, int idCurso) throws SQLException {
        String sql = "INSERT INTO CURSO_USUARIO (ID_USUARIO, ID_CURSO) VALUES (?, ?)";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, idUsuario);
        statement.setInt(2, idCurso);
        try {
            statement.execute();
        } catch (SQLException e) {
            if (e.getMessage().contains("PK_CURSO_USUARIO")) {
                return "JA_MATRICULADO";
            }
            throw e;
        }
        statement.close();
        return "MATRICULA_REALIZADA";
    }
    public void desmatricular(int idUsuario, int idCurso) throws SQLException {
        String sql = "DELETE FROM CURSO_USUARIO WHERE ID_USUARIO=? AND ID_CURSO=?";
        PreparedStatement statement = conexao.prepareStatement(sql);

        statement.setInt(1, idUsuario);
        statement.setInt(2, idCurso);

        int linhas = statement.executeUpdate();
        statement.close();

        if (linhas == 0) {
            throw new SQLException("NAO_MATRICULADO");
        }
    }
    public List<Usuario> listarUsuariosDoCurso(int idCurso) throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = """
            SELECT usuario.ID_USUARIO,
                   usuario.NOME,
                   usuario.EMAIL,
                   usuario.NUMERO_TELEFONE,
                   usuario.CPF,
                   usuario.DATA_CRIACAO
            FROM CURSO_USUARIO curso_usuario
            JOIN USUARIO usuario
                ON curso_usuario.ID_USUARIO = usuario.ID_USUARIO
            WHERE curso_usuario.ID_CURSO = ?
            """;

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, idCurso);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();

            usuario.setIdUsuario(rs.getInt(1));
            usuario.setNome(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setNumeroTelefone(rs.getString(4));
            usuario.setCpf(rs.getString(5));
            usuario.setDataCriacao(rs.getString(6));

            lista.add(usuario);
        }

        rs.close();
        statement.close();

        return lista;
    }
    public List<String> listarTodasMatriculas() throws SQLException {
        List<String> lista = new ArrayList<>();

        String sql = "SELECT ID_USUARIO, ID_CURSO FROM CURSO_USUARIO";

        PreparedStatement statement = conexao.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            lista.add("Usuario: " + rs.getInt(1) + " - Curso: " + rs.getInt(2));
        }

        rs.close();
        statement.close();

        return lista;
    }
    public List<Curso> listarCursosDoUsuario(int idUsuario) throws SQLException {
        List<Curso> lista = new ArrayList<>();

        String sql = """
            SELECT curso.ID_CURSO,
                   curso.NOME,
                   curso.DESCRICAO,
                   curso.URL_VIDEO,
                   curso.DATA_CRIACAO
            FROM CURSO_USUARIO curso_usuario
            JOIN CURSO curso
                ON curso_usuario.ID_CURSO = curso.ID_CURSO
            WHERE curso_usuario.ID_USUARIO = ?
            """;
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, idUsuario);
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
}

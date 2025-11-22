package br.com.avgtech.DAO;

import br.com.avgtech.beans.Usuario;
import br.com.avgtech.conexao.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection conexao;

    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }
    public String insert(Usuario usuario) throws SQLException {

        String sql = "INSERT INTO USUARIO (NOME, EMAIL, SENHA, NUMERO_TELEFONE, CPF) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = conexao.prepareStatement(sql);

        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getEmail());
        statement.setString(3, usuario.getSenha());
        statement.setString(4, usuario.getNumeroTelefone());
        statement.setString(5, usuario.getCpf());

        statement.execute();
        statement.close();

        return "USUARIO_CADASTRADO";
    }
    public Usuario selecionarPorEmail(String email) throws SQLException {

        String sql = "SELECT * FROM USUARIO WHERE EMAIL=?";
        PreparedStatement statement = conexao.prepareStatement(sql);

        statement.setString(1, email);

        ResultSet rs = statement.executeQuery();
        Usuario usuario = null;

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt(1));
            usuario.setNome(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setSenha(rs.getString(4));
            usuario.setNumeroTelefone(rs.getString(5));
            usuario.setCpf(rs.getString(6));
            usuario.setDataCriacao(rs.getString(7));
        }

        rs.close();
        statement.close();

        return usuario;
    }
    public Usuario selecionarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE CPF=?";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, cpf);

        ResultSet rs = statement.executeQuery();
        Usuario usuario = null;

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt(1));
            usuario.setNome(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setSenha(rs.getString(4));
            usuario.setNumeroTelefone(rs.getString(5));
            usuario.setCpf(rs.getString(6));
            usuario.setDataCriacao(rs.getString(7));
        }
        rs.close();
        statement.close();

        return usuario;
    }
    public Usuario selecionarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM USUARIO WHERE ID_USUARIO=?";
        PreparedStatement statement = conexao.prepareStatement(sql);

        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();
        Usuario usuario = null;

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt(1));
            usuario.setNome(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setSenha(rs.getString(4));
            usuario.setNumeroTelefone(rs.getString(5));
            usuario.setCpf(rs.getString(6));
            usuario.setDataCriacao(rs.getString(7));
        }

        rs.close();
        statement.close();

        return usuario;
    }
    public Usuario login(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE EMAIL=? AND SENHA=?";
        PreparedStatement statement = conexao.prepareStatement(sql);

        statement.setString(1, email);
        statement.setString(2, senha);

        ResultSet rs = statement.executeQuery();
        Usuario usuario = null;

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt(1));
            usuario.setNome(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setSenha(rs.getString(4));
            usuario.setNumeroTelefone(rs.getString(5));
            usuario.setCpf(rs.getString(6));
            usuario.setDataCriacao(rs.getString(7));
        }
        rs.close();
        statement.close();

        return usuario;
    }
    public List<Usuario> listarTodos() throws SQLException {

        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM USUARIO";
        PreparedStatement statement = conexao.prepareStatement(sql);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Usuario usuario = new Usuario();

            usuario.setIdUsuario(rs.getInt(1));
            usuario.setNome(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setSenha(rs.getString(4));
            usuario.setNumeroTelefone(rs.getString(5));
            usuario.setCpf(rs.getString(6));
            usuario.setDataCriacao(rs.getString(7));

            lista.add(usuario);
        }

        rs.close();
        statement.close();

        return lista;
    }

    public String atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE USUARIO SET NOME=?, EMAIL=?, SENHA=?, NUMERO_TELEFONE=?, CPF=? WHERE ID_USUARIO=?";
        PreparedStatement statement = conexao.prepareStatement(sql);

        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getEmail());
        statement.setString(3, usuario.getSenha());
        statement.setString(4, usuario.getNumeroTelefone());
        statement.setString(5, usuario.getCpf());
        statement.setInt(6, usuario.getIdUsuario());

        int linhas = statement.executeUpdate();
        statement.close();

        if (linhas == 0) {
            return "USUARIO_NAO_ENCONTRADO";
        }

        return "USUARIO_ATUALIZADO";
    }
    public String deletar(int id) throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE ID_USUARIO=?";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, id);
        try {
            int linhas = statement.executeUpdate();
            statement.close();
            if (linhas == 0) {
                return "USUARIO_NAO_ENCONTRADO";
            }
            return "USUARIO_DELETADO";
        } catch (SQLException e) {
            statement.close();
            if (e.getMessage().contains("FK_CURSO_USUARIO_USUARIO")) {
                return "USUARIO_POSSUI_MATRICULAS";
            }
            throw e;
        }
    }
    public void fecharConexao() {
        if (conexao != null) {
            try {
                if (!conexao.isClosed()) {
                    conexao.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

}

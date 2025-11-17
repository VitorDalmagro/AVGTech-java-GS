package br.com.avgtech.DAO;

import br.com.avgtech.beans.Usuario;
import br.com.avgtech.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Connection cn;

    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        this.cn = new ConexaoFactory().conexao();
    }

    public String insert(Usuario usuario) throws SQLException {

        String sql = "INSERT INTO USUARIO (NOME, EMAIL, SENHA, NUMERO_TELEFONE, CPF) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement pstmt = cn.prepareStatement(sql);

        pstmt.setString(1, usuario.getNome());
        pstmt.setString(2, usuario.getEmail());
        pstmt.setString(3, usuario.getSenha());
        pstmt.setString(4, usuario.getNumeroTelefone());
        pstmt.setString(5, usuario.getCpf());

        pstmt.execute();
        pstmt.close();


        return "Usuário cadastrado com sucesso!";
    }

    public Usuario selecionarPorEmail(String email) throws SQLException {

        String sql = "SELECT * FROM USUARIO WHERE EMAIL=?";
        PreparedStatement pstmt = cn.prepareStatement(sql);

        pstmt.setString(1, email);

        ResultSet rs = pstmt.executeQuery();

        Usuario u = null;

        if (rs.next()) {
            u = new Usuario();
            u.setIdUsuario(rs.getInt(1));
            u.setNome(rs.getString(2));
            u.setEmail(rs.getString(3));
            u.setSenha(rs.getString(4));
            u.setNumeroTelefone(rs.getString(5));
            u.setCpf(rs.getString(6));
            u.setDataCriacao(rs.getString(7));
        }

        rs.close();
        pstmt.close();


        return u;
    }
    public Usuario selecionarPorCpf(String cpf) throws SQLException {

        String sql = "SELECT * FROM USUARIO WHERE CPF=?";
        PreparedStatement pstmt = cn.prepareStatement(sql);

        pstmt.setString(1, cpf);

        ResultSet rs = pstmt.executeQuery();

        Usuario u = null;

        if (rs.next()) {
            u = new Usuario();
            u.setIdUsuario(rs.getInt(1));
            u.setNome(rs.getString(2));
            u.setEmail(rs.getString(3));
            u.setSenha(rs.getString(4));
            u.setNumeroTelefone(rs.getString(5));
            u.setCpf(rs.getString(6));
            u.setDataCriacao(rs.getString(7));
        }

        rs.close();
        pstmt.close();


        return u;
    }
    public Usuario selecionarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM USUARIO WHERE ID_USUARIO=?";
        PreparedStatement pstmt = cn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        Usuario u = null;

        if (rs.next()) {
            u = new Usuario();
            u.setIdUsuario(rs.getInt(1));
            u.setNome(rs.getString(2));
            u.setEmail(rs.getString(3));
            u.setSenha(rs.getString(4));
            u.setNumeroTelefone(rs.getString(5));
            u.setCpf(rs.getString(6));
            u.setDataCriacao(rs.getString(7));
        }

        rs.close();
        pstmt.close();


        return u;
    }
    public Usuario login(String email, String senha) throws SQLException {

        String sql = "SELECT * FROM USUARIO WHERE EMAIL=? AND SENHA=?";
        PreparedStatement pstmt = cn.prepareStatement(sql);

        pstmt.setString(1, email);
        pstmt.setString(2, senha);

        ResultSet rs = pstmt.executeQuery();

        Usuario u = null;

        if (rs.next()) {
            u = new Usuario();
            u.setIdUsuario(rs.getInt(1));
            u.setNome(rs.getString(2));
            u.setEmail(rs.getString(3));
            u.setSenha(rs.getString(4));
            u.setNumeroTelefone(rs.getString(5));
            u.setCpf(rs.getString(6));
            u.setDataCriacao(rs.getString(7));
        }

        rs.close();
        pstmt.close();


        return u;
    }
}

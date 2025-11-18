package br.com.avgtech.DAO;

import br.com.avgtech.beans.Usuario;
import br.com.avgtech.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        pstmt.close();

        return usuario;
    }
    public Usuario selecionarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE CPF=?";
        PreparedStatement pstmt = cn.prepareStatement(sql);

        pstmt.setString(1, cpf);

        ResultSet rs = pstmt.executeQuery();
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
        pstmt.close();

        return usuario;
    }
    public Usuario selecionarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM USUARIO WHERE ID_USUARIO=?";
        PreparedStatement pstmt = cn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();
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
        pstmt.close();

        return usuario;
    }
    public Usuario login(String email, String senha) throws SQLException {

        String sql = "SELECT * FROM USUARIO WHERE EMAIL=? AND SENHA=?";
        PreparedStatement pstmt = cn.prepareStatement(sql);

        pstmt.setString(1, email);
        pstmt.setString(2, senha);

        ResultSet rs = pstmt.executeQuery();

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
        pstmt.close();


        return usuario;
    }
    public List<Usuario> listarTodos() throws SQLException {

        List<Usuario> lista = new ArrayList<Usuario>();

        String sql = "SELECT * FROM USUARIO";
        PreparedStatement pstmt = cn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

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
        pstmt.close();
        return lista;
    }
    public String atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE USUARIO SET NOME=?, EMAIL=?, SENHA=?, NUMERO_TELEFONE=?, CPF=? WHERE ID_USUARIO=?";

        PreparedStatement pstmt = cn.prepareStatement(sql);
        pstmt.setString(1, usuario.getNome());
        pstmt.setString(2, usuario.getEmail());
        pstmt.setString(3, usuario.getSenha());
        pstmt.setString(4, usuario.getNumeroTelefone());
        pstmt.setString(5, usuario.getCpf());
        pstmt.setInt(6, usuario.getIdUsuario());
        int linhas = pstmt.executeUpdate();

        pstmt.close();
        if (linhas == 0) {
            return "USUARIO_NAO_ENCONTRADO";
        }
        return "Usuario atualizado com sucesso!";
    }
    public String deletar(int id) throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE ID_USUARIO=?";
        PreparedStatement pstmt = cn.prepareStatement(sql);

        pstmt.setInt(1, id);
        int linhas = pstmt.executeUpdate();
        pstmt.close();
        if (linhas == 0) {
            return "USUARIO_NAO_ENCONTRADO";
        }
        return "Usuário deletado com sucesso!";
    }

}

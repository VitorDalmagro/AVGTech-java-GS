package br.com.avgtech.BO;

import br.com.avgtech.DAO.UsuarioDAO;
import br.com.avgtech.beans.Usuario;

public class UsuarioBO {

    public Usuario buscarPorId(int id) throws Exception {

        if (id <= 0) {
            throw new Exception("ID inválido.");
        } else {

            UsuarioDAO dao = new UsuarioDAO();
            Usuario u = dao.selecionarPorId(id);

            if (u == null) {
                throw new Exception("Usuário não encontrado.");
            } else {
                return u;
            }
        }
    }
}

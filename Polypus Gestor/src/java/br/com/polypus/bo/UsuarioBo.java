package br.com.polypus.bo;

import br.com.polypus.dao.UsuarioDao;
import br.com.polypus.transfer.Usuario;
import java.sql.SQLException;

public class UsuarioBo {

    public Usuario logar(Usuario usuario) throws Exception{
        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            usuario = usuarioDao.logar(usuario);
            if(usuario == null)
                throw new SQLException("Usuário não Encontrado ou Senha Inválida!");
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
}
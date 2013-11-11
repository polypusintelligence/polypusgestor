package br.com.polypus.dao;

import br.com.polypus.conexao.ConnectionFactory;
import br.com.polypus.transfer.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public UsuarioDao() throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try {
            connection = connectionFactory.getConnectionFactory();
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }
    
    private List<Usuario> rsToObjeto(ResultSet rs) throws SQLException {
        List<Usuario> list = new ArrayList<Usuario>();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("usu_id_usuario"));
            usuario.setIdPerfil(rs.getInt("per_id_perfil"));
            usuario.setDescPerfil(rs.getString("descPerfil"));
            usuario.setNome(rs.getString("usu_tx_nome"));
            usuario.setEmail(rs.getString("usu_tx_email"));
            usuario.setTelefone(rs.getString("usu_tx_telefone"));
            list.add(usuario);
        }
        return list;
    }
    
    public Usuario logar(Usuario usuario) throws SQLException {
        try {
            StringBuilder sql = new StringBuilder("SELECT u.*, p.per_tx_descricao AS descPerfil FROM usuario u ");
            sql.append("INNER JOIN perfil p ON u.per_id_perfil = p.per_id_perfil ");
            sql.append("WHERE usu_tx_email = ? AND usu_tx_senha = CONVERT(VARCHAR(32), HashBytes('MD5', CAST(? AS VARCHAR)), 2) ");
            String strSql = sql.toString();
            ps = connection.prepareStatement(strSql);
            ps.setObject(1, usuario.getEmail());
            ps.setObject(2, usuario.getSenha());
            rs = ps.executeQuery();
            List<Usuario> list = rsToObjeto(rs);
            if (list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        } finally{
            ps.close();
            rs.close();
        }
        
    }
    
}

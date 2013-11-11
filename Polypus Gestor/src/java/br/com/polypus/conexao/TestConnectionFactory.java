package br.com.polypus.conexao;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnectionFactory {

    public static void main(String[] args) throws SQLException {
        
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection con = connectionFactory.getConnectionFactory();
        
    }
}

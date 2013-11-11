package br.com.polypus.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String drive = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=Polypus;";
    private static final String usuario = "sa";
    private static final String senha = "trish";

    public Connection getConnectionFactory() throws SQLException{
        try {
            Class.forName(drive);
            //System.out.println("CONECTANDO...");
            Connection connection = DriverManager.getConnection(url, usuario, senha);
            //System.out.println("CONECTADO.");
            return connection;
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

}

package es.progcipfpbatoi.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static Connection connection;

    public Connection getConnection() {

        if (connection == null){
            try {
                String dbURL = "jdbc:mysql://192.168.1.111/Restaurante_db";
                Connection connection = DriverManager.getConnection(dbURL,"batoi","1234");
                this.connection = connection;
                System.out.println("Conexión valida: " + connection.isValid(20));
            } catch (SQLException exception) {
                throw new RuntimeException(exception.getMessage());
            }
        }
        return this.connection;
    }

    public void closeConnection() {
        if (connection!= null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


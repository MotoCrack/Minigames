package br.com.cavalinhorx.thepit.database;

import lombok.Getter;

import java.sql.*;

public class Database {

    private String hostname;
    private String port;
    private String database;
    private String username;
    private String password;

    @Getter private Connection connection;

    public Database(String hostname, String port, String database, String username, String password) {
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    /**
     * Cria conexão com o banco de dados MySQL.
     * @return {@link Connection}
     */
    public Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return connection = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + database, username, password);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return connection;
    }

    /**
     * Fecha conexão com o banco de dados MySQL.
     */
    public void closeConnection() {
        try {
            if (connection.isClosed())
                connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Cria um prepared statement.
     * @return {@link PreparedStatement}
     */
    public PreparedStatement query(String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement;
    }

    /**
     * Cria as tabelas necessárias para o plugin.
     */
    public void createTables() {
        try {
            query("CREATE TABLE IF NOT EXISTS ThePit(id INT AUTO_INCREMENT NOT NULL, uuid VARCHAR(36), level INT, xp INT, gold DOUBLE, PRIMARY KEY(id));");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
package ru.job4j.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();

        try (FileReader fileReader = new FileReader("data/app.properties")) {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (TableEditor editor = new TableEditor(properties)) {
            editor.createTable("fak_table");
            System.out.println(editor.getTableScheme("fak_table"));
            editor.addColumn("fak_table", "ip", "serial primary key");
            System.out.println(editor.getTableScheme("fak_table"));
            editor.addColumn("fak_table", "fak", "VARCHAR(255)");
            System.out.println(editor.getTableScheme("fak_table"));
            editor.dropColumn("fak_table", "fak");
            System.out.println(editor.getTableScheme("fak_table"));
            editor.renameColumn("fak_table", "ip", "id");
            System.out.println(editor.getTableScheme("fak_table"));
            editor.dropTable("fak_table");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private void createStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        createStatement(String.format("CREATE TABLE IF NOT EXISTS %s();", tableName));
    }

    public void dropTable(String tableName) {
        createStatement(String.format("DROP TABLE %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) {
        createStatement(String.format("ALTER TABLE %s ADD %s %s NOT NULL;", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) {
        createStatement(String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        createStatement(String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName));
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}

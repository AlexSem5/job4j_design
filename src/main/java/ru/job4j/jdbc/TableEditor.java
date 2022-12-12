package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    
    private Connection connection;
    
    private Properties properties;
    
    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }
    
    private void initConnection() throws ClassNotFoundException, SQLException {
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver_class");
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
    }
    
    private void executeStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void createTable(String tableName) {
        executeStatement(String.format(
                "CREATE TABLE IF NOT EXISTS %s ();",
                tableName
        ));
    }
    
    public void dropTable(String tableName) {
        executeStatement(String.format(
                "DROP TABLE %s;",
                tableName
        ));
    }
    
    public void addColumn(String tableName, String columnName, String type) {
        executeStatement(String.format(
                "ALTER TABLE %s ADD COLUMN %s %s;",
                tableName, columnName, type
        ));
    }
    
    public void dropColumn(String tableName, String columnName) {
        executeStatement(String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName, columnName
        ));
    }
    
    public void renameColumn(String tableName, String columnName, String newColumnName) {
        executeStatement(String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName
        ));
    }
    
    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
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
    
    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        /*Writing a try-with-resources statement:
        Inside the parentheses, declare an object whose type implements Autocloseable*/
        try (TableEditor tableEditor = new TableEditor(config)) {
            /*Use the object you declared inside the try block*/
            tableEditor.createTable("Contacts");
            System.out.println(tableEditor.getTableScheme("Contacts"));
            tableEditor.addColumn("Contacts", "Valera", "TEXT");
            System.out.println(tableEditor.getTableScheme("Contacts"));
            tableEditor.renameColumn("Contacts", "Valera", "Names");
            System.out.println(tableEditor.getTableScheme("Contacts"));
            tableEditor.dropColumn("Contacts", "Names");
            System.out.println(tableEditor.getTableScheme("Contacts"));
            tableEditor.dropTable("Contacts");
        }
    }
    
    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}

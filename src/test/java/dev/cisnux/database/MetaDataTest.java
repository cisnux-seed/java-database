package dev.cisnux.database;

import dev.cisnux.database.utils.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Types;

public class MetaDataTest {
    @Test
    void testDatabaseMetaData() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        final var databaseMetaData = connection.getMetaData();

        System.out.println(databaseMetaData.getDatabaseProductName());
        System.out.println(databaseMetaData.getDatabaseProductVersion());

        final var resultSet = databaseMetaData.getTables("belajar_java_database", null, null, null);
        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");
            System.out.println("Table : " + tableName);
        }

        connection.close();
    }

    @Test
    void testParameterMetaData() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email_address, comment) VALUES (?, ?)";
        final var preparedStatement = connection.prepareStatement(sql);

        final var parameterMetaData = preparedStatement.getParameterMetaData();

        System.out.println(parameterMetaData.getParameterCount());
        System.out.println(parameterMetaData.getParameterTypeName(1));

        preparedStatement.close();
        connection.close();
    }

    @Test
    void testResultSetMetaData() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        final var statement = connection.createStatement();
        final var resultSet = statement.executeQuery("SELECT * FROM sample_time");

        final var resultSetMetaData = resultSet.getMetaData();

        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            System.out.println("Name : " + resultSetMetaData.getColumnName(i));
            System.out.println("Type : " + resultSetMetaData.getColumnType(i));
            System.out.println("Type Name : " + resultSetMetaData.getColumnTypeName(i));

            if (resultSetMetaData.getColumnType(i) == Types.INTEGER) {
                System.out.println("THIS IS INTEGER");
            }
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}

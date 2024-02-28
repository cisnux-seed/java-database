package dev.cisnux.database;

import dev.cisnux.database.utils.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class StatementTest {
    @Test
    void testCreateStatement() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        final var statement = connection.createStatement();

        statement.close();
        connection.close();
    }

    @Test
    void testExecuteUpdate() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        final var statement = connection.createStatement();

        String sql = """
                UPDATE customers
                SET name='Cisnux', email_address='cisnux@gmail.com', age=21
                WHERE id='sdjfohsdof-sdhfoihsodf-234fas0'
                """;
        int update = statement.executeUpdate(sql);
        System.out.println(update);

        statement.close();
        connection.close();
    }

    @Test
    void testExecuteInsert() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        final var statement = connection.createStatement();

        String sql = """
                INSERT INTO customers(id, name, email_address, age)
                VALUES ('sdjfohsdof-sdhfoihsodf-234fas0', 'Fajra Cisnux', 'fajra@gmail.com', 20)
                """;
        int update = statement.executeUpdate(sql);
        System.out.println(update);

        statement.close();
        connection.close();
    }

    @Test
    void testExecuteDelete() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        final var statement = connection.createStatement();

        String sql = """
                DELETE FROM customers;
                """;
        int update = statement.executeUpdate(sql);
        System.out.println(update);

        statement.close();
        connection.close();
    }

    @Test
    void testExecuteQuery() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        final var statement = connection.createStatement();

        String sql = """
        SELECT * FROM customers
        """;
        final var resultSet = statement.executeQuery(sql);

        resultSet.close();
        statement.close();
        connection.close();
    }
}

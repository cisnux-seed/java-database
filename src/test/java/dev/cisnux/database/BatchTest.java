package dev.cisnux.database;

import dev.cisnux.database.utils.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class BatchTest {
    @Test
    void testStatement() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        final var statement = connection.createStatement();

        String sql = "INSERT INTO comments(email_address, comment) VALUES ('fajra@gmail.com', 'hi')";

        for (int i = 0; i < 1000; i++) {
            statement.addBatch(sql);
        }

        statement.executeBatch();

        statement.close();
        connection.close();
    }

    @Test
    void testPreparedStatement() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email_address, comment) VALUES (?, ?)";
        final var preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < 1000; i++) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, "fajra@gmail.com");
            preparedStatement.setString(2, "hi");
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();
    }
}

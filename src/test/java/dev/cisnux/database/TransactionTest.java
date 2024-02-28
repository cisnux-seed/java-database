package dev.cisnux.database;

import dev.cisnux.database.utils.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TransactionTest {
    @Test
    void testCommit() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email_address, comment) VALUES (?, ?)";
        for (int i = 0; i < 100; i++) {
            final var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "fajra@gmail.com");
            preparedStatement.setString(2, "hi");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        connection.commit();
        connection.close();
    }

    @Test
    void testRollback() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email_address, comment) VALUES (?, ?)";
        for (int i = 0; i < 100; i++) {
            final var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "fajra@gmail.com");
            preparedStatement.setString(2, "hi");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        connection.rollback();
        connection.close();
    }

}

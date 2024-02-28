package dev.cisnux.database;

import dev.cisnux.database.utils.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;

public class AutoIncrementTest {
    @Test
    void testAutoIncrement() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email_address, comment) VALUES (?, ?)";
        final var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, "fajra@gmail.com");
        preparedStatement.setString(2, "hi");

        preparedStatement.executeUpdate();

        final var resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            System.out.println("Id Comment : " + resultSet.getInt(1));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}

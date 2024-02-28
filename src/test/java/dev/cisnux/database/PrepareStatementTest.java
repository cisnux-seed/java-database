package dev.cisnux.database;

import dev.cisnux.database.utils.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class PrepareStatementTest {
    @Test
    void testPrepareStatement() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        String username = "admin'; #";
        String password = "salah";

        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        final var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        final var resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            // success
            System.out.println("Successfullly login : " + resultSet.getString("username"));
        } else {
            // failed
            System.out.println("Failed to login");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}

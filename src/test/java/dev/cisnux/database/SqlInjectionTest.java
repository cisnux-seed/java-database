package dev.cisnux.database;

import dev.cisnux.database.utils.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class SqlInjectionTest {
    @Test
    void testSqlInjection() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        final var statement = connection.createStatement();

        String username = "admin'; #";
        String password = "salah";

        // haha it doesn't work in postgresql
        String sql = "SELECT * FROM admin WHERE username = '" + username +
                "' AND PASSWORD = '" + password + "'";

        System.out.println("SELECT * FROM admin WHERE username = 'admin'; #' AND PASSWORD = 'salah';");

        final var resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            // success
            System.out.println("Successfullly login : " + resultSet.getString("username"));
        } else {
            // failed
            System.out.println("Failed to login");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}

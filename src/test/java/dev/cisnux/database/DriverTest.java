package dev.cisnux.database;

import dev.cisnux.database.utils.DatabaseConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverTest {
    @Test
    void testRegister() {
        final var jdbcUrl = DatabaseConfig.getDbUrl();
        final var user = DatabaseConfig.getDbUsername();
        final var password = DatabaseConfig.getDbPassword();

        try {
            DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("Successfully connected");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}

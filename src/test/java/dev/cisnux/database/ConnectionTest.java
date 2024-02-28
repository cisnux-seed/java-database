package dev.cisnux.database;

import dev.cisnux.database.utils.DatabaseConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    @Test
    void testConnection() {
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

    @Test
    void testConnectionClose() {
        final var jdbcUrl = DatabaseConfig.getDbUrl();
        final var user = DatabaseConfig.getDbUsername();
        final var password = DatabaseConfig.getDbPassword();

        // without try with-resource
//        try  {
//            final var connection = DriverManager.getConnection(jdbcUrl, user, password);
//            System.out.println("Successfully connect to database");
//            connection.close();
//            System.out.println("Successfully close the database");
//        } catch (SQLException e) {
//            Assertions.fail(e);
//        }
        // with try with-resource
        try (final var ignored = DriverManager.getConnection(jdbcUrl, user, password)) {
            System.out.println("Successfully connected");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}

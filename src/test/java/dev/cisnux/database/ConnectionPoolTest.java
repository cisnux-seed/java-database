package dev.cisnux.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.cisnux.database.utils.ConnectionUtil;
import dev.cisnux.database.utils.DatabaseConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ConnectionPoolTest {

    @Test
    public void testHikariCP() {
        final var config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl(DatabaseConfig.getDbUrl());
        config.setUsername(DatabaseConfig.getDbUsername());
        config.setPassword(DatabaseConfig.getDbPassword());

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);

        try {
            final var dataSource = new HikariDataSource(config);
            final var connection = dataSource.getConnection();
            System.out.println("Successfully get the connection");
            connection.close();
            System.out.println("Successfully return the connection");
            dataSource.close();
            System.out.println("Successfully close the pool connection");
        } catch (SQLException exception) {
            Assertions.fail(exception);
        }
    }

    @Test
    public void testUtil() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
    }
}

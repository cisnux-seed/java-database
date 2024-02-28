package dev.cisnux.database;

import dev.cisnux.database.utils.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ResultSetTest {
    @Test
    void testExecuteQuery() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        final var statement = connection.createStatement();

        String sql = """
        SELECT * FROM customers
        """;
        final var resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            final var id = resultSet.getString("id");
            final var name = resultSet.getString("name");
            final var emailAddress = resultSet.getString("email_address");
            final var age = resultSet.getInt("age");

            System.out.println(
                    String.join(", ", id, name, emailAddress, String.valueOf(age))
            );
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}

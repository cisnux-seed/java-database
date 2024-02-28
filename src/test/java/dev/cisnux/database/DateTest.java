package dev.cisnux.database;

import dev.cisnux.database.utils.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Month;
import java.util.Calendar;

public class DateTest {
    @Test
    void testDate() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        String sql = """
                INSERT INTO sample_time(sample_time, sample_date, sample_timestamp)
                VALUES (?, ?, ?)
                """;
        final var preparedStatement = connection.prepareStatement(sql);
        final var time = new Calendar.Builder()
                .setFields(Calendar.YEAR, 2020)
                .setFields(Calendar.DATE, 12)
                .setFields(Calendar.MONTH, Calendar.JUNE)
                .setFields(Calendar.HOUR_OF_DAY, 23)
                .setFields(Calendar.MINUTE, 59)
                .setFields(Calendar.SECOND, 59)
                .build()
                .getTimeInMillis();

        preparedStatement.setTime(1, new Time(time));
        preparedStatement.setDate(2, new Date(time));
        preparedStatement.setTimestamp(3, new Timestamp(time));

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Test
    void testDateQuery() throws SQLException {
        final var connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "SELECT * FROM sample_time";
        final var preparedStatement = connection.prepareStatement(sql);
        final var resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Time time = resultSet.getTime("sample_time");
            System.out.println("Time = " + time);
            Date date = resultSet.getDate("sample_date");
            System.out.println("Date = " + date);
            Timestamp timestamp = resultSet.getTimestamp("sample_timestamp");
            System.out.println("Timestamp = " + timestamp);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}

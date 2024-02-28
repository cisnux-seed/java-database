package dev.cisnux.database.repository;

import dev.cisnux.database.entity.Comment;
import dev.cisnux.database.utils.ConnectionUtil;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository {
    @Override
    public Comment insert(Comment comment) {
        try (final var connection = ConnectionUtil.getDataSource().getConnection()) {
            final var sql = "INSERT INTO comments(email_address, comment) VALUES (?,?)";
            try (final var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, comment.emailAddress());
                statement.setString(2, comment.comment());
                statement.executeUpdate();

                try (final var resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next())
                        return comment.copy().setId(resultSet.getInt(1)).build();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Comment findById(int id) {
        try (final var connection = ConnectionUtil.getDataSource().getConnection()) {
            final var sql = "SELECT * FROM comments WHERE id = ?";
            try (final var statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (final var resultSet = statement.executeQuery()) {
                    if (resultSet.next())
                        return new Comment(
                                resultSet.getInt("id"),
                                resultSet.getString("email_address"),
                                resultSet.getString("comment")
                        );
                    else return null;
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

    }

    @Override
    public List<Comment> findAll() {
        try (final var connection = ConnectionUtil.getDataSource().getConnection()) {
            final var sql = "SELECT * FROM comments";
            try (final var statement = connection.createStatement()) {
                try (final var resultSet = statement.executeQuery(sql)) {
                    final var comments = new ArrayList<Comment>();
                    while (resultSet.next()) {
                        comments.add(new Comment(resultSet.getInt("id"), resultSet.getString("email_address"), resultSet.getString("comment")));
                    }
                    return Collections.unmodifiableList(comments);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> findAllByEmailAddress(String emailAddress) {
        try (final var connection = ConnectionUtil.getDataSource().getConnection()) {
            final var sql = "SELECT * FROM comments WHERE email_address = ?";
            try (final var statement = connection.prepareStatement(sql)) {
                statement.setString(1, emailAddress);
                try (final var resultSet = statement.executeQuery()) {
                    final var comments = new ArrayList<Comment>();
                    while (resultSet.next()) {
                        comments.add(new Comment(resultSet.getInt("id"), resultSet.getString("email_address"), resultSet.getString("comment")));
                    }
                    return Collections.unmodifiableList(comments);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

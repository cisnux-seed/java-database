package dev.cisnux.database.repository;

import dev.cisnux.database.entity.Comment;

import java.util.List;

public interface CommentRepository {
    Comment insert(Comment comment);

    Comment findById(int id);

    List<Comment> findAll();
    List<Comment> findAllByEmailAddress(String emailAddress);
}

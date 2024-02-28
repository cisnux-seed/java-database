package dev.cisnux.database.repository;

import dev.cisnux.database.entity.Comment;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class CommentRepositoryTest {
    private CommentRepository repository;

    @BeforeAll
    void beforeAll() {
        repository = new CommentRepositoryImpl();
    }

    @Test
    void testInsert() {
        final var comment = new Comment("fajra@gmail.com", "hi");
        final var result = repository.insert(comment);

        Assertions.assertNotNull(result);
        Assertions.assertNotEquals(-1, result.id());
        System.out.println(result.id());
    }

    @Test
    void testFindById_WhenFound() {
        Comment comment = repository.findById(2506);
        Assertions.assertNotNull(comment);
        System.out.println(comment.id());
        System.out.println(comment.emailAddress());
        System.out.println(comment.comment());
    }

    @Test
    void testFindById_WhenNotFound() {
        Comment notFound = repository.findById(10000000);
        Assertions.assertNull(notFound);
    }

    @Test
    void testFindAll() {
        final var comments = repository.findAll();
        System.out.println(comments.size());
        final List<Comment> expectedType = Collections.unmodifiableList(new ArrayList<>());
        Assertions.assertInstanceOf(expectedType.getClass(), comments);
    }

    @Test
    void testFindByEmail_WhenNotFound() {
        final var comments = repository.findAllByEmailAddress("salah@test.com");
        Assertions.assertEquals(0, comments.size());
    }

    @Test
    void testFindByEmail_WhenFound() {
        final var comments = repository.findAllByEmailAddress("fajra@gmail.com");
        Assertions.assertFalse(comments.isEmpty());
    }
}
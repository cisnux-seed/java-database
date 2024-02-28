package dev.cisnux.database.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {
    @Test
    void testCopyMethod_WhenEqual() {
        final var commentA = new Comment("fajra@gmail.com", "Hi");
        final var copiedComment = commentA.copy().build();
        System.out.println(commentA);
        System.out.println(copiedComment);
        assertEquals(commentA, copiedComment);
    }

    @Test
    void testCopyMethod_WhenNotEqual() {
        final var commentA = new Comment("fajra@gmail.com", "Hi");
        final var copiedComment = commentA.copy().setName("cisnux@gmail.com").build();
        System.out.println(commentA);
        System.out.println(copiedComment);
        assertNotEquals(commentA, copiedComment);
    }
}
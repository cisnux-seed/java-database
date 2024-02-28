package dev.cisnux.database.entity;

import java.util.Optional;

public record Comment(int id, String emailAddress, String comment) {
    public Comment(String emailAddress, String comment) {
        this(-1, emailAddress, comment);
    }

    public static class Builder {
        private int mutableId;
        private String mutableEmailAddress;
        private String mutableComment;

        public Builder setId(int id) {
            this.mutableId = id;
            return this;
        }

        public Builder setEmailAddress(String emailAddress) {
            this.mutableEmailAddress = emailAddress;
            return this;
        }

        public Builder setName(String name) {
            this.mutableComment = name;
            return this;
        }

        public Builder(Comment comment) {
            this.mutableId = comment.id;
            this.mutableEmailAddress = comment.emailAddress;
            this.mutableComment = comment.comment;
        }

        public Comment build() {
            return new Comment(mutableId, mutableEmailAddress, mutableComment);
        }
    }

    public Builder copy() {
        return new Builder(this);
    }
}

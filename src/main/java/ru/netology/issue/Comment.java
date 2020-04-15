package ru.netology.issue;

import ru.netology.person.Author;

import java.util.Date;

public class Comment {
    int id;
    Author author;
    String content;
    Date commentDate = new Date();

    public Comment(int id, Author author, String content, Date commentDate) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.commentDate = commentDate;

    }

    @Override
    public String toString() {
        return "Comment{" +
                "author=" + author +
                ", content='" + content + '\'' +
                '}';
    }
}

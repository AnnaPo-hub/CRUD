package ru.netology.issue;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.netology.person.Author;

import java.util.Date;

@AllArgsConstructor
@Data
public class Comment {
    private int id;
    private Author author;
    private String content;
    private Date commentDate = new Date();

}

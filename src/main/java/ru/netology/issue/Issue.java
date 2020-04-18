package ru.netology.issue;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.netology.person.Assignee;
import ru.netology.person.Author;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class Issue {
    private int id;
    private String name;
    private Author author;
    private Label label;
    private Status status;
    private Set<Tag> tagsSet;
    private Set<Assignee> assigneesSet;
    private Date createDate;
    private Date updateDate;
    private Set<Comment> commentSet = new HashSet<>();
    private Date commentDate;

    public Issue(int id, String name, Author author, Label label, Assignee assignee, Tag tag, Status status, Date createDate, Date updateDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.label = label;
        this.assigneesSet = new HashSet<>();
        this.assigneesSet.add(assignee);
        this.tagsSet = new HashSet<Tag>();
        this.tagsSet.add(tag);
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Issue(int id, String name, Author author, Label label, Assignee assignee, Status status, Date createDate, Date updateDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.label = label;
        this.assigneesSet = new HashSet<Assignee>();
        this.assigneesSet.add(assignee);
        this.tagsSet = new HashSet<Tag>();
        this.status = status;
        this.createDate = createDate;
        this.updateDate= updateDate;
    }

    public Issue(int id, String name, Author author, Label label, Assignee assignee, Status status, Date createDate, Comment comment) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.label = label;
        this.assigneesSet = new HashSet<Assignee>();
        this.assigneesSet.add(assignee);
        this.tagsSet = new HashSet<Tag>();
        this.status = status;
        this.createDate = createDate;
        this.commentSet.add(comment);
    }

    public Issue(int id, String name, Author author, Label label, Assignee assignee, Status status, Date createDate, Comment comment, Date updateDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.label = label;
        this.assigneesSet = new HashSet<Assignee>();
        this.assigneesSet.add(assignee);
        this.tagsSet = new HashSet<Tag>();
        this.status = status;
        this.createDate = createDate;
        this.commentSet.add(comment);
        this.updateDate= updateDate;
    }

    public Issue(int id, String name, Author author, Label label, Assignee assignee, Status status, Date createDate, Set<Comment> commentsSet) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.label = label;
        this.assigneesSet = new HashSet<Assignee>();
        this.assigneesSet.add(assignee);
        this.tagsSet = new HashSet<Tag>();
        this.status = status;
        this.createDate = createDate;
        this.commentSet = commentsSet;
    }

    public Issue(int id, String name, Author author, Label label, Assignee assignee, Status status, Date createDate, Set<Comment> commentsSet, Date updateDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.label = label;
        this.assigneesSet = new HashSet<Assignee>();
        this.assigneesSet.add(assignee);
        this.tagsSet = new HashSet<Tag>();
        this.status = status;
        this.createDate = createDate;
        this.commentSet = commentsSet;
        this.updateDate= updateDate;
    }

    public Issue(int id, String name, Author author, Label label, Assignee assignee,  Status status, Date createDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.label = label;
        this.assigneesSet = new HashSet<>();
        this.assigneesSet.add(assignee);
        this.tagsSet = new HashSet<Tag>();
        this.status = status;
        this.createDate = createDate;
    }

    public void setStatus(Status status) {
        this.status = status;
        updateDate = new Date();
    }
}

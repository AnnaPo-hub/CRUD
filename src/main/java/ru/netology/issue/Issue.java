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
    int id;
    String name;
    Author author;
    Label label;
    Status status;
    Set<Tag> tagsSet;
    Set<Assignee> assigneesSet;
    Date createDate = new Date();
    Date updateDate = new Date();
    Set<Comment> commentSet = new HashSet<>();
    Date commentDate = new Date();


    public Issue(int id, String name, Author author, Label label, Assignee assignee, Tag tag, Status status, Date createDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.label = label;
        this.assigneesSet = new HashSet<Assignee>();
        this.assigneesSet.add(assignee);
        this.tagsSet = new HashSet<Tag>();
        this.tagsSet.add(tag);
        this.status = status;
        this.createDate = createDate;
    }

    public Issue(int id, String name, Author author, Label label, Assignee assignee, Status status, Date createDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.label = label;
        this.assigneesSet = new HashSet<Assignee>();
        this.assigneesSet.add(assignee);
        this.tagsSet = new HashSet<Tag>();
        this.status = status;
        this.createDate = createDate;
    }

    public Issue(int id, String name, Author author, Label label, Assignee assignee, Status status, Date createDate, Comment comment) { //один коммент
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

    public Issue(int id, String name, Author author, Label label, Assignee assignee, Status status, Date createDate, Set<Comment> commentsSet) { //набор комментариев
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



    public boolean addAssignee(Assignee assignee) {
        if (assignee != null) {
            if (assigneesSet == null) {
                assigneesSet = new HashSet<>();
            }
            return assigneesSet.add(assignee);
        }
        return false;
    }

    public boolean addComment(Comment comment) {
        if (comment != null) {
            if (commentSet == null) {
                commentSet = new HashSet<>();
            }
            return commentSet.add(comment);
        }
        return false;
    }


    public void setStatus(Status status) {
        this.status = status;
        updateDate = new Date();
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", label=" + label +
                ", status=" + status +
                ", tagsSet=" + tagsSet +
                ", assigneesSet=" + assigneesSet +
                '}';
    }
}

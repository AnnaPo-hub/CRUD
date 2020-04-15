package ru.netology.domain;

import org.junit.jupiter.api.Test;
import ru.netology.domain.issue.Issue;
import ru.netology.domain.issue.Label;
import ru.netology.domain.issue.Tag;
import ru.netology.domain.person.Assignee;
import ru.netology.domain.person.Author;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.domain.issue.Status.CLOSED;
import static ru.netology.domain.issue.Status.OPEN;

class ManagerTest {
    Manager man = new Manager(new Repository());
    Repository repo = new Repository();



    @Test
    void shouldFindMatchByAuthor() {
        man.add(new Issue(8, "test", new Author(10, "Igor", "Popov"), Label.DOCUMENTATION, new Assignee(5, "Boris", "Aprelev"), new Tag(1, "test"), CLOSED,new GregorianCalendar(2020, Calendar.APRIL, 12, 15, 11, 11).getTime() ));
        List<Issue> actual = man.findMatch(issue -> issue.getAuthor().getSurname().equals("Popov"));
        System.out.println(actual);
        int expectedIssueId = 8;
        int actualIssueId = actual.get(0).getId();
        assertEquals(expectedIssueId, actualIssueId);
    }

    @Test
    void shouldFindMatchByLabel() {
        man.add(new Issue(8, "test", new Author(10, "Igor", "Popov"), Label.DOCUMENTATION, new Assignee(5, "Boris", "Aprelev"), new Tag(1, "test"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 12, 15, 11, 11).getTime()));
        List<Issue> actual = man.findMatch(issue -> issue.getLabel().equals(Label.DOCUMENTATION));
        System.out.println(actual);
        int expectedIssueId = 8;
        int actualIssueId = actual.get(0).getId();
        assertEquals(expectedIssueId, actualIssueId);
    }

    @Test
    void shouldFindMatchByAssignee() {
        man.addAssignee(1, new Assignee(7, "Vasya", "Pupkin"));
        List<Issue> actual = man.findByAssignee(new Assignee(7, "Vasya", "Pupkin"));
        int expectedIssueId = 1;
        int actualIssueId = actual.get(0).getId();
        assertEquals(expectedIssueId, actualIssueId);
    }


    @Test
    void shouldAddAssignee() {
        man.addAssignee(1, new Assignee(5, "Boris", "Aprelev"));
        Set<Assignee> assignees = man.showListOfAssignees(1);
        assertTrue(assignees.contains(new Assignee(5, "Boris", "Aprelev")));
    }

    @Test
    void shouldShowOpen() {
        List<Issue> actual = man.showOpen();
        List<Issue> expected = new ArrayList<>();
        for(Issue issue :actual){
            if(issue.getStatus()==OPEN){
                expected.add(issue);
            }
        }
        assertEquals(expected,actual);
    }

    @Test
    void shouldShowClosed() {
        List<Issue> actual = man.showClosed();
        List<Issue> expected = new ArrayList<>();
        for(Issue issue :actual){
            if(issue.getStatus()==CLOSED){
                expected.add(issue);
            }
        }
        assertEquals(expected,actual);
    }
}

package ru.netology.domain;

import org.junit.jupiter.api.Test;
import ru.netology.issue.*;
import ru.netology.person.Assignee;
import ru.netology.person.Author;
import ru.netology.manager.IssueManager;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static ru.netology.issue.Label.BUG;
import static ru.netology.issue.Status.CLOSED;
import static ru.netology.issue.Status.OPEN;

class IssueManagerTest {
    Comment[] commentsArray = {
            new Comment(3, new Author(4, "Nikolay", "Elagin"), "Я уже уточнял, что за приложение и делал эту задачу", new GregorianCalendar(2020, Calendar.APRIL, 12, 13, 11, 11).getTime()),
            new Comment(4, new Author(8, "Anna", "Popova"), "Оно снова падает", new GregorianCalendar(2020, Calendar.APRIL, 12, 15, 11, 11).getTime()),
            new Comment(4, new Author(4, "Nikolay", "Elagin"), "разбираемся", new GregorianCalendar(2020, Calendar.APRIL, 12, 15, 15, 16).getTime())};

    Set<Comment> commentSet = new HashSet<>(Arrays.asList(commentsArray));

    Issue[] list = {new Issue(1, "ничего не работает", new Author(1, "Irina", "Alexandrova"), BUG, new Assignee(2, "Vladimir", "Posnek"), new Tag(2, "invitroproject"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 12, 11, 11).getTime()),
            new Issue(2, "подсказка в д/з не помогает", new Author(3, "Vasiliy", "Vodovozov"), BUG, new Assignee(4, "Nilolay", "Elagin"), new Tag(3, "invitroprogect"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 18, 12, 11, 11).getTime()),
            new Issue(3, "я могу предложить больше методов для д/з", new Author(5, "Boris", "Aprelev"), Label.ENHANCEMENT, new Assignee(6, "Dmitriy", "Girs"), CLOSED, new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime(),new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime()),
            new Issue(4, "Файл с домашним заданием не загружается", new Author(7, "Savva", "Derunov"), BUG, new Assignee(5, "Boris", "Aprelev"), CLOSED, new GregorianCalendar(2020, Calendar.MARCH, 24, 12, 11, 11).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 24, 2, 11, 11).getTime()),
            new Issue(5, "Кнопка добавить товар должна быть больше", new Author(5, "Anyz", "Mavel"), Label.WANTFIX, new Assignee(1, "Irina", "Alexandrova"), Status.OPEN, new GregorianCalendar(2020, Calendar.APRIL, 8, 12, 11, 11).getTime(), new Comment(1, new Author(1, "Irina", "Alexandrova"), "уточните какого размера должна быть кнопка", new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()),new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()),
            new Issue(6, "приложение не запускается", new Author(8, "Anna", "Popova"), BUG, new Assignee(2, "Vladimir", "Poznek"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 10, 13, 11, 11).getTime(), commentSet,new GregorianCalendar(2020, Calendar.APRIL, 10, 17, 11, 11).getTime())};
    List<Issue> repoList = new ArrayList<>(Arrays.asList(list));

    Issue testIssue = new Issue(7, "ничего не работает", new Author(1, "Irina", "Alexandrova"),
            Label.BUG, new Assignee(2, "Vladimir", "Posnek"), Status.CLOSED,
            new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime());

    IssueManager issueManager = new IssueManager(new IssueRepository(repoList));

    @Test
    void shouldFindMatchByAuthor() {
        issueManager.add(new Issue(8, "test", new Author(10, "Igor", "Popov"), Label.DOCUMENTATION, new Assignee(5, "Boris", "Aprelev"), new Tag(1, "test"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 12, 15, 11, 11).getTime(), new GregorianCalendar(2020, Calendar.APRIL, 13, 15, 11, 11).getTime()));
        List<Issue> actual = issueManager.findMatch(issue -> issue.getAuthor().getSurname().equals("Popov"));
        int expectedIssueId = 8;
        int actualIssueId = actual.get(0).getId();
        assertEquals(expectedIssueId, actualIssueId);
    }

    @Test
    void shouldFilterByLabel() {
        issueManager.add(new Issue(8, "test", new Author(10, "Igor", "Popov"), Label.DOCUMENTATION, new Assignee(5, "Boris", "Aprelev"), new Tag(1, "test"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 12, 15, 11, 11).getTime(), new GregorianCalendar(2020, Calendar.APRIL, 13, 15, 11, 11).getTime()));
        List<Issue> actual = issueManager.findMatch(issue -> issue.getLabel().equals(Label.DOCUMENTATION));
        int expectedIssueId = 8;
        int actualIssueId = actual.get(0).getId();
        assertEquals(expectedIssueId, actualIssueId);
    }

    @Test
    void shouldFilterByAssignee() {
        List<Issue> actual = issueManager.filterByAssignee(new Assignee(4, "Nilolay", "Elagin"));
        System.out.println(actual);
        List<Issue> expected = new ArrayList<>();
        expected.add(new Issue(2, "подсказка в д/з не помогает", new Author(3, "Vasiliy", "Vodovozov"), BUG, new Assignee(4, "Nilolay", "Elagin"), new Tag(3, "invitroprogect"), Status.OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 18, 12, 11, 11).getTime()));
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowOpen() {
        List<Issue> actual = issueManager.showOpen();
        System.out.println(actual);
        List<Issue> expected = new ArrayList<>();
        for (Issue issue : actual) {
            if (issue.getStatus() == OPEN) {
                expected.add(issue);
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowClosed() {
        List<Issue> actual = issueManager.showClosed();
        List<Issue> expected = new ArrayList<>();
        for (Issue issue : actual) {
            if (issue.getStatus() == CLOSED) {
                expected.add(issue);
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByMostCommented() {
        List<Issue> actual = issueManager.sortByMostCommented();
        Issue prevIssue = null;
        for (Issue issue : actual) {
            if (prevIssue != null) {
                assertTrue(prevIssue.getCommentSet().size() >= issue.getCommentSet().size());
            }
            prevIssue = issue;
        }
    }

    @Test
    void shouldSortByLessCommented() {
        List<Issue> actual = issueManager.sortByLessCommented();
        Issue prevIssue = null;
        for (Issue issue : actual) {
            if (prevIssue != null) {
                assertTrue(prevIssue.getCommentSet().size() <= issue.getCommentSet().size());
            }
            prevIssue = issue;
        }
    }


    @Test
    void shouldSortFromOldest() {

        List<Issue> actual = issueManager.sortFromOldest();
        Issue prevIssue = null;
        for (Issue issue : actual) {
            if (prevIssue != null) {
                assertTrue(prevIssue.getCreateDate().before(issue.getCreateDate()));
            }
            prevIssue = issue;
        }
    }


    @Test
    void shouldSortFromNewest() {
        List<Issue> actual = issueManager.sortFromNewest();
        Issue prevIssue = null;
        for (Issue issue : actual) {
            if (prevIssue != null) {
                assertTrue(prevIssue.getCreateDate().after(issue.getCreateDate()));
            }
            prevIssue = issue;
        }
    }

    @Test
    void shouldSortFromRecentlyUpdated() {
        List<Issue> actual = issueManager.sortFromRecentlyUpdated();
        Issue prevIssue = null;
        for (Issue issue : actual) {
            if (prevIssue != null) {
                assertFalse(prevIssue.getUpdateDate().before(issue.getUpdateDate()));
            }
            prevIssue = issue;
        }
    }


    @Test
    void shouldSortFromLeastRecentlyUpdated() {
        List<Issue> actual = issueManager.sortFromLeastRecentlyUpdated();
        Issue prevIssue = null;
        for (Issue issue : actual) {
            if (prevIssue != null) {
                assertFalse(prevIssue.getUpdateDate().after(issue.getUpdateDate()));
            }
            prevIssue = issue;
        }
    }
}

package ru.netology.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.issue.Issue;
import ru.netology.domain.issue.Label;
import ru.netology.domain.issue.Status;
import ru.netology.domain.person.Assignee;
import ru.netology.domain.person.Author;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CRUDRepositoryTest {

    Repository repo = new Repository();


    Issue testIssue = new Issue(1, "ничего не работает", new Author(1, "Irina", "Alexandrova"),
            Label.BUG, new Assignee(2, "Vladimir", "Posnek"), Status.CLOSED,
            new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime());


    @Nested
    public class Empty {

        @Test
        void add() {
            repo.add(testIssue);
            List<Issue> actual = repo.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(testIssue);
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleItem {
        Repository repo = new Repository(testIssue);

        @Test
        void shouldOpen() {
            repo.open(1);
            assertTrue(testIssue.getStatus() == Status.OPEN);
        }

        @Test
        void shouldDeleteById() {
            repo.deleteById(1);
            assertEquals(null, repo.findById(1));
        }
    }

    @Nested
    public class MultipleItem {
        @Test
        void shouldSortByMostCommented() {
            List<Issue> actual = repo.sortByMostCommented();
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
                List<Issue> actual = repo.sortByLessCommented();
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
            List<Issue> actual = repo.sortFromOldest();
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
            List<Issue> actual = repo.sortFromNewest();
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
            List<Issue> actual = repo.sortFromRecentlyUpdated();
            Issue prevIssue = null;
            for (Issue issue : actual) {
                if (prevIssue != null) {
                    assertTrue(!prevIssue.getUpdateDate().before(issue.getUpdateDate()));
                }
                prevIssue = issue;
            }
        }


        @Test
        void shouldSortFromLeastRecentlyUpdated() {
            List<Issue> actual = repo.sortFromLeastRecentlyUpdated();
            Issue prevIssue = null;
            for (Issue issue : actual) {
                if (prevIssue != null) {
                    assertTrue(!prevIssue.getUpdateDate().after(issue.getUpdateDate()));
                }
                prevIssue = issue;
            }
        }
        }
    }

package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.issue.*;
import ru.netology.manager.IssueManager;
import ru.netology.person.Assignee;
import ru.netology.person.Author;
import ru.netology.repository.Repository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.issue.Label.BUG;
import static ru.netology.issue.Status.CLOSED;
import static ru.netology.issue.Status.OPEN;


class RepositoryTest {
    private Issue issue1 = new Issue(1, "ничего не работает", new Author(1, "Irina", "Alexandrova"), BUG, new Assignee(2, "Vladimir", "Posnek"), new Tag(2, "invitroproject"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 12, 11, 11).getTime());
    private Issue issue2 = new Issue(2, "подсказка в д/з не помогает", new Author(3, "Vasiliy", "Vodovozov"), BUG, new Assignee(4, "Nilolay", "Elagin"), new Tag(3, "invitroprogect"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 18, 12, 11, 11).getTime());
    private Issue issue3 = new Issue(3, "я могу предложить больше методов для д/з", new Author(5, "Boris", "Aprelev"), Label.ENHANCEMENT, new Assignee(6, "Dmitriy", "Girs"), CLOSED, new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime());
    private Issue issue4 = new Issue(4, "Файл с домашним заданием не загружается", new Author(7, "Savva", "Derunov"), BUG, new Assignee(5, "Boris", "Aprelev"), CLOSED, new GregorianCalendar(2020, Calendar.MARCH, 24, 12, 11, 11).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 24, 2, 11, 11).getTime());
    private Issue issue5 = new Issue(5, "Кнопка добавить товар должна быть больше", new Author(5, "Anyz", "Mavel"), Label.WANTFIX, new Assignee(1, "Irina", "Alexandrova"), Status.OPEN, new GregorianCalendar(2020, Calendar.APRIL, 8, 12, 11, 11).getTime(), new Comment(1, new Author(1, "Irina", "Alexandrova"), "уточните какого размера должна быть кнопка", new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()), new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime());
    private Issue issue6 = new Issue(6, "приложение не запускается", new Author(8, "Anna", "Popova"), BUG, new Assignee(2, "Vladimir", "Poznek"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 10, 13, 11, 11).getTime(), new GregorianCalendar(2020, Calendar.APRIL, 10, 17, 11, 11).getTime());

    private Comment comment1 = new Comment(3, new Author(4, "Nikolay", "Elagin"), "Я уже уточнял, что за приложение и делал эту задачу", new GregorianCalendar(2020, Calendar.APRIL, 12, 13, 11, 11).getTime());
    private Comment comment2 = new Comment(4, new Author(8, "Anna", "Popova"), "Оно снова падает", new GregorianCalendar(2020, Calendar.APRIL, 12, 15, 11, 11).getTime());
    private Comment comment3 = new Comment(4, new Author(4, "Nikolay", "Elagin"), "разбираемся", new GregorianCalendar(2020, Calendar.APRIL, 12, 15, 15, 16).getTime());

    Repository repo = new Repository();
    private IssueManager issueManager = new IssueManager(repo);

    @BeforeEach
    void setUp() {
        issueManager.add(issue1);
        issueManager.add(issue2);
        issueManager.add(issue3);
        issueManager.add(issue4);
        issueManager.add(issue5);
        issueManager.add(issue6);

        issueManager.addComment(6, comment1);
        issueManager.addComment(6, comment2);
        issueManager.addComment(6, comment3);
    }

    Issue testIssue = new Issue(7, "ничего не работает", new Author(1, "Irina", "Alexandrova"),
            Label.BUG, new Assignee(2, "Vladimir", "Posnek"), Status.CLOSED,
            new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime());

    @Test
    void shouldAdd() {
        repo.add(testIssue);
        Issue actual = repo.findById(7);
        List<Issue> expected = new ArrayList<>();
        expected.add(testIssue);
        assertEquals(testIssue, actual);
    }

    @Test
    void shouldOpen() {
        repo.open(1);
        Issue byId = issueManager.findById(1);
        assertSame(byId.getStatus(), Status.OPEN);
    }

    @Test
    void close() {
        repo.close(5);
        Issue byId = issueManager.findById(5);
        assertSame(byId.getStatus(), CLOSED);
    }


    @Test
    void deleteById() {
        repo.deleteById(6);
        assertNull(repo.findById(6));
    }
}

package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.issue.*;
import ru.netology.manager.IssueManager;
import ru.netology.person.Assignee;
import ru.netology.person.Author;
import ru.netology.repository.Repository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.issue.Label.BUG;
import static ru.netology.issue.Status.CLOSED;
import static ru.netology.issue.Status.OPEN;

class IssueManagerTest {
    private Issue issue1 = new Issue(1, "ничего не работает", new Author(1, "Irina", "Alexandrova"), BUG, new Assignee(2, "Vladimir", "Posnek"), new Tag(2, "invitroproject"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 12, 11, 11).getTime());
    private Issue issue2 = new Issue(2, "подсказка в д/з не помогает", new Author(3, "Vasiliy", "Vodovozov"), BUG, new Assignee(4, "Nilolay", "Elagin"), new Tag(3, "invitroprogect"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 18, 12, 11, 11).getTime());
    private Issue issue3 = new Issue(3, "я могу предложить больше методов для д/з", new Author(5, "Boris", "Aprelev"), Label.ENHANCEMENT, new Assignee(6, "Dmitriy", "Girs"), CLOSED, new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime());
    private Issue issue4 = new Issue(4, "Файл с домашним заданием не загружается", new Author(7, "Savva", "Derunov"), BUG, new Assignee(5, "Boris", "Aprelev"), CLOSED, new GregorianCalendar(2020, Calendar.MARCH, 24, 12, 11, 11).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 24, 2, 11, 11).getTime());
    private Issue issue5 = new Issue(5, "Кнопка добавить товар должна быть больше", new Author(5, "Anyz", "Mavel"), Label.WANTFIX, new Assignee(1, "Irina", "Alexandrova"), Status.OPEN, new GregorianCalendar(2020, Calendar.APRIL, 8, 12, 11, 11).getTime(), new Comment(1, new Author(1, "Irina", "Alexandrova"), "уточните какого размера должна быть кнопка", new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()), new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime());
    private Issue issue6 = new Issue(6, "приложение не запускается", new Author(8, "Anna", "Popova"), BUG, new Assignee(2, "Vladimir", "Poznek"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 10, 13, 11, 11).getTime(), new GregorianCalendar(2020, Calendar.APRIL, 10, 17, 11, 11).getTime());

    private Comment comment1 = new Comment(3, new Author(4, "Nikolay", "Elagin"), "Я уже уточнял, что за приложение и делал эту задачу", new GregorianCalendar(2020, Calendar.APRIL, 12, 13, 11, 11).getTime());
    private Comment comment2 = new Comment(4, new Author(8, "Anna", "Popova"), "Оно снова падает", new GregorianCalendar(2020, Calendar.APRIL, 12, 15, 11, 11).getTime());
    private Comment comment3 = new Comment(4, new Author(4, "Nikolay", "Elagin"), "разбираемся", new GregorianCalendar(2020, Calendar.APRIL, 12, 15, 15, 16).getTime());

    private IssueManager issueManager = new IssueManager(new Repository());

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
        List<Issue> expected = new ArrayList<>();
        expected.add(new Issue(2, "подсказка в д/з не помогает", new Author(3, "Vasiliy", "Vodovozov"), BUG, new Assignee(4, "Nilolay", "Elagin"), new Tag(3, "invitroprogect"), Status.OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 18, 12, 11, 11).getTime()));
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowOpen() {
        List<Issue> actual = issueManager.showOpen();
        List<Issue> expected = new ArrayList<>();
        expected.add(new Issue(1, "ничего не работает", new Author(1, "Irina", "Alexandrova"), BUG, new Assignee(2, "Vladimir", "Posnek"), new Tag(2, "invitroproject"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 12, 11, 11).getTime()));
        expected.add(new Issue(2, "подсказка в д/з не помогает", new Author(3, "Vasiliy", "Vodovozov"), BUG, new Assignee(4, "Nilolay", "Elagin"), new Tag(3, "invitroprogect"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 18, 12, 11, 11).getTime()));
        expected.add(new Issue(5, "Кнопка добавить товар должна быть больше", new Author(5, "Anyz", "Mavel"), Label.WANTFIX, new Assignee(1, "Irina", "Alexandrova"), Status.OPEN, new GregorianCalendar(2020, Calendar.APRIL, 8, 12, 11, 11).getTime(), new Comment(1, new Author(1, "Irina", "Alexandrova"), "уточните какого размера должна быть кнопка", new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()), new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()));
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowClosed() {
        List<Issue> actual = issueManager.showClosed();
        List<Issue> expected = new ArrayList<>();
        expected.add(new Issue(3, "я могу предложить больше методов для д/з", new Author(5, "Boris", "Aprelev"), Label.ENHANCEMENT, new Assignee(6, "Dmitriy", "Girs"), CLOSED, new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime()));
        expected.add(new Issue(4, "Файл с домашним заданием не загружается", new Author(7, "Savva", "Derunov"), BUG, new Assignee(5, "Boris", "Aprelev"), CLOSED, new GregorianCalendar(2020, Calendar.MARCH, 24, 12, 11, 11).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 24, 2, 11, 11).getTime()));
        HashSet<Comment> commentsSet = new HashSet<>();
        commentsSet.add(comment1);
        commentsSet.add(comment2);
        commentsSet.add(comment3);
        expected.add(new Issue(6, "приложение не запускается", new Author(8, "Anna", "Popova"), BUG, new Assignee(2, "Vladimir", "Poznek"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 10, 13, 11, 11).getTime(), commentsSet, new GregorianCalendar(2020, Calendar.APRIL, 10, 17, 11, 11).getTime()));
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByMostCommented() {
        List<Issue> actual = issueManager.sortByMostCommented();
        List<Issue> expected = new ArrayList<>();
        HashSet<Comment> commentsSet = new HashSet<>();
        commentsSet.add(comment1);
        commentsSet.add(comment2);
        commentsSet.add(comment3);
        expected.add(new Issue(6, "приложение не запускается", new Author(8, "Anna", "Popova"), BUG, new Assignee(2, "Vladimir", "Poznek"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 10, 13, 11, 11).getTime(), commentsSet, new GregorianCalendar(2020, Calendar.APRIL, 10, 17, 11, 11).getTime()));
        expected.add(new Issue(5, "Кнопка добавить товар должна быть больше", new Author(5, "Anyz", "Mavel"), Label.WANTFIX, new Assignee(1, "Irina", "Alexandrova"), Status.OPEN, new GregorianCalendar(2020, Calendar.APRIL, 8, 12, 11, 11).getTime(), new Comment(1, new Author(1, "Irina", "Alexandrova"), "уточните какого размера должна быть кнопка", new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()), new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()));
        expected.add(new Issue(1, "ничего не работает", new Author(1, "Irina", "Alexandrova"), BUG, new Assignee(2, "Vladimir", "Posnek"), new Tag(2, "invitroproject"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 12, 11, 11).getTime()));
        expected.add(new Issue(2, "подсказка в д/з не помогает", new Author(3, "Vasiliy", "Vodovozov"), BUG, new Assignee(4, "Nilolay", "Elagin"), new Tag(3, "invitroprogect"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 18, 12, 11, 11).getTime()));
        expected.add(new Issue(3, "я могу предложить больше методов для д/з", new Author(5, "Boris", "Aprelev"), Label.ENHANCEMENT, new Assignee(6, "Dmitriy", "Girs"), CLOSED, new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime()));
        expected.add(new Issue(4, "Файл с домашним заданием не загружается", new Author(7, "Savva", "Derunov"), BUG, new Assignee(5, "Boris", "Aprelev"), CLOSED, new GregorianCalendar(2020, Calendar.MARCH, 24, 12, 11, 11).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 24, 2, 11, 11).getTime()));
        assertEquals(expected, actual);
    }


    @Test
    void shouldSortByLessCommented() {
        List<Issue> actual = issueManager.sortByLessCommented();
        List<Issue> expected = new ArrayList<>();
        expected.add(new Issue(1, "ничего не работает", new Author(1, "Irina", "Alexandrova"), BUG, new Assignee(2, "Vladimir", "Posnek"), new Tag(2, "invitroproject"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 12, 11, 11).getTime()));
        expected.add(new Issue(2, "подсказка в д/з не помогает", new Author(3, "Vasiliy", "Vodovozov"), BUG, new Assignee(4, "Nilolay", "Elagin"), new Tag(3, "invitroprogect"), Status.OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 18, 12, 11, 11).getTime()));
        expected.add(new Issue(3, "я могу предложить больше методов для д/з", new Author(5, "Boris", "Aprelev"), Label.ENHANCEMENT, new Assignee(6, "Dmitriy", "Girs"), CLOSED, new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime()));
        expected.add(new Issue(4, "Файл с домашним заданием не загружается", new Author(7, "Savva", "Derunov"), BUG, new Assignee(5, "Boris", "Aprelev"), CLOSED, new GregorianCalendar(2020, Calendar.MARCH, 24, 12, 11, 11).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 24, 2, 11, 11).getTime()));
        expected.add(new Issue(5, "Кнопка добавить товар должна быть больше", new Author(5, "Anyz", "Mavel"), Label.WANTFIX, new Assignee(1, "Irina", "Alexandrova"), Status.OPEN, new GregorianCalendar(2020, Calendar.APRIL, 8, 12, 11, 11).getTime(), new Comment(1, new Author(1, "Irina", "Alexandrova"), "уточните какого размера должна быть кнопка", new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()), new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()));
        HashSet<Comment> commentsSet = new HashSet<>();
        commentsSet.add(comment1);
        commentsSet.add(comment2);
        commentsSet.add(comment3);
        expected.add(new Issue(6, "приложение не запускается", new Author(8, "Anna", "Popova"), BUG, new Assignee(2, "Vladimir", "Poznek"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 10, 13, 11, 11).getTime(), commentsSet, new GregorianCalendar(2020, Calendar.APRIL, 10, 17, 11, 11).getTime()));
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortFromOldest() {
        List<Issue> actual = issueManager.sortFromOldest();
        List<Issue> expected = new ArrayList<>();
        expected.add(new Issue(1, "ничего не работает", new Author(1, "Irina", "Alexandrova"), BUG, new Assignee(2, "Vladimir", "Posnek"), new Tag(2, "invitroproject"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 12, 11, 11).getTime()));
        expected.add(new Issue(2, "подсказка в д/з не помогает", new Author(3, "Vasiliy", "Vodovozov"), BUG, new Assignee(4, "Nilolay", "Elagin"), new Tag(3, "invitroprogect"), Status.OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 18, 12, 11, 11).getTime()));
        expected.add(new Issue(3, "я могу предложить больше методов для д/з", new Author(5, "Boris", "Aprelev"), Label.ENHANCEMENT, new Assignee(6, "Dmitriy", "Girs"), CLOSED, new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime()));
        expected.add(new Issue(4, "Файл с домашним заданием не загружается", new Author(7, "Savva", "Derunov"), BUG, new Assignee(5, "Boris", "Aprelev"), CLOSED, new GregorianCalendar(2020, Calendar.MARCH, 24, 12, 11, 11).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 24, 2, 11, 11).getTime()));
        expected.add(new Issue(5, "Кнопка добавить товар должна быть больше", new Author(5, "Anyz", "Mavel"), Label.WANTFIX, new Assignee(1, "Irina", "Alexandrova"), Status.OPEN, new GregorianCalendar(2020, Calendar.APRIL, 8, 12, 11, 11).getTime(), new Comment(1, new Author(1, "Irina", "Alexandrova"), "уточните какого размера должна быть кнопка", new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()), new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()));
        HashSet<Comment> commentsSet = new HashSet<>();
        commentsSet.add(comment1);
        commentsSet.add(comment2);
        commentsSet.add(comment3);
        expected.add(new Issue(6, "приложение не запускается", new Author(8, "Anna", "Popova"), BUG, new Assignee(2, "Vladimir", "Poznek"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 10, 13, 11, 11).getTime(), commentsSet, new GregorianCalendar(2020, Calendar.APRIL, 10, 17, 11, 11).getTime()));
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortFromNewest() {
        List<Issue> actual = issueManager.sortFromNewest();
        List<Issue> expected = new ArrayList<>();
        HashSet<Comment> commentsSet = new HashSet<>();
        commentsSet.add(comment1);
        commentsSet.add(comment2);
        commentsSet.add(comment3);
        expected.add(new Issue(6, "приложение не запускается", new Author(8, "Anna", "Popova"), BUG, new Assignee(2, "Vladimir", "Poznek"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 10, 13, 11, 11).getTime(), commentsSet, new GregorianCalendar(2020, Calendar.APRIL, 10, 17, 11, 11).getTime()));
        expected.add(new Issue(5, "Кнопка добавить товар должна быть больше", new Author(5, "Anyz", "Mavel"), Label.WANTFIX, new Assignee(1, "Irina", "Alexandrova"), Status.OPEN, new GregorianCalendar(2020, Calendar.APRIL, 8, 12, 11, 11).getTime(), new Comment(1, new Author(1, "Irina", "Alexandrova"), "уточните какого размера должна быть кнопка", new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()), new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()));
        expected.add(new Issue(4, "Файл с домашним заданием не загружается", new Author(7, "Savva", "Derunov"), BUG, new Assignee(5, "Boris", "Aprelev"), CLOSED, new GregorianCalendar(2020, Calendar.MARCH, 24, 12, 11, 11).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 24, 2, 11, 11).getTime()));
        expected.add(new Issue(3, "я могу предложить больше методов для д/з", new Author(5, "Boris", "Aprelev"), Label.ENHANCEMENT, new Assignee(6, "Dmitriy", "Girs"), CLOSED, new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime()));
        expected.add(new Issue(2, "подсказка в д/з не помогает", new Author(3, "Vasiliy", "Vodovozov"), BUG, new Assignee(4, "Nilolay", "Elagin"), new Tag(3, "invitroprogect"), Status.OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 18, 12, 11, 11).getTime()));
        expected.add(new Issue(1, "ничего не работает", new Author(1, "Irina", "Alexandrova"), BUG, new Assignee(2, "Vladimir", "Posnek"), new Tag(2, "invitroproject"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 12, 11, 11).getTime()));
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortFromRecentlyUpdated() {
        List<Issue> actual = issueManager.sortFromRecentlyUpdated();
        List<Issue> expected = new ArrayList<>();
        expected.add(new Issue(5, "Кнопка добавить товар должна быть больше", new Author(5, "Anyz", "Mavel"), Label.WANTFIX, new Assignee(1, "Irina", "Alexandrova"), Status.OPEN, new GregorianCalendar(2020, Calendar.APRIL, 8, 12, 11, 11).getTime(), new Comment(1, new Author(1, "Irina", "Alexandrova"), "уточните какого размера должна быть кнопка", new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()), new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()));
        HashSet<Comment> commentsSet = new HashSet<>();
        commentsSet.add(comment1);
        commentsSet.add(comment2);
        commentsSet.add(comment3);
        expected.add(new Issue(6, "приложение не запускается", new Author(8, "Anna", "Popova"), BUG, new Assignee(2, "Vladimir", "Poznek"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 10, 13, 11, 11).getTime(), commentsSet, new GregorianCalendar(2020, Calendar.APRIL, 10, 17, 11, 11).getTime()));
        expected.add(new Issue(4, "Файл с домашним заданием не загружается", new Author(7, "Savva", "Derunov"), BUG, new Assignee(5, "Boris", "Aprelev"), CLOSED, new GregorianCalendar(2020, Calendar.MARCH, 24, 12, 11, 11).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 24, 2, 11, 11).getTime()));
        expected.add(new Issue(3, "я могу предложить больше методов для д/з", new Author(5, "Boris", "Aprelev"), Label.ENHANCEMENT, new Assignee(6, "Dmitriy", "Girs"), CLOSED, new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime()));
        expected.add(new Issue(1, "ничего не работает", new Author(1, "Irina", "Alexandrova"), BUG, new Assignee(2, "Vladimir", "Posnek"), new Tag(2, "invitroproject"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 12, 11, 11).getTime()));
        expected.add(new Issue(2, "подсказка в д/з не помогает", new Author(3, "Vasiliy", "Vodovozov"), BUG, new Assignee(4, "Nilolay", "Elagin"), new Tag(3, "invitroprogect"), Status.OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 18, 12, 11, 11).getTime()));
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortFromLeastRecentlyUpdated() {
        List<Issue> actual = issueManager.sortFromLeastRecentlyUpdated();
        List<Issue> expected = new ArrayList<>();
        expected.add(new Issue(2, "подсказка в д/з не помогает", new Author(3, "Vasiliy", "Vodovozov"), BUG, new Assignee(4, "Nilolay", "Elagin"), new Tag(3, "invitroprogect"), Status.OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 18, 12, 11, 11).getTime()));
        expected.add(new Issue(1, "ничего не работает", new Author(1, "Irina", "Alexandrova"), BUG, new Assignee(2, "Vladimir", "Posnek"), new Tag(2, "invitroproject"), OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 12, 11, 11).getTime()));
        expected.add(new Issue(3, "я могу предложить больше методов для д/з", new Author(5, "Boris", "Aprelev"), Label.ENHANCEMENT, new Assignee(6, "Dmitriy", "Girs"), CLOSED, new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime()));
        expected.add(new Issue(4, "Файл с домашним заданием не загружается", new Author(7, "Savva", "Derunov"), BUG, new Assignee(5, "Boris", "Aprelev"), CLOSED, new GregorianCalendar(2020, Calendar.MARCH, 24, 12, 11, 11).getTime(), new GregorianCalendar(2020, Calendar.MARCH, 24, 2, 11, 11).getTime()));
        HashSet<Comment> commentsSet = new HashSet<>();
        commentsSet.add(comment1);
        commentsSet.add(comment2);
        commentsSet.add(comment3);
        expected.add(new Issue(6, "приложение не запускается", new Author(8, "Anna", "Popova"), BUG, new Assignee(2, "Vladimir", "Poznek"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 10, 13, 11, 11).getTime(), commentsSet, new GregorianCalendar(2020, Calendar.APRIL, 10, 17, 11, 11).getTime()));
        expected.add(new Issue(5, "Кнопка добавить товар должна быть больше", new Author(5, "Anyz", "Mavel"), Label.WANTFIX, new Assignee(1, "Irina", "Alexandrova"), Status.OPEN, new GregorianCalendar(2020, Calendar.APRIL, 8, 12, 11, 11).getTime(), new Comment(1, new Author(1, "Irina", "Alexandrova"), "уточните какого размера должна быть кнопка", new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()), new GregorianCalendar(2020, Calendar.MAY, 8, 13, 15, 17).getTime()));
        assertEquals(expected, actual);
    }
}

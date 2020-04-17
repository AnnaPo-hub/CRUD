package ru.netology.domain;
import org.junit.jupiter.api.Test;
import ru.netology.issue.*;
import ru.netology.person.Assignee;
import ru.netology.person.Author;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.issue.Label.BUG;
import static ru.netology.issue.Status.CLOSED;


class IssueRepositoryTest {
    Comment [] commentsArray = {
            new Comment(3, new Author(4, "Nikolay", "Elagin"), "Я уже уточнял, что за приложение и делал эту задачу", new GregorianCalendar(2020, Calendar.APRIL, 12, 13, 11, 11).getTime()),
            new Comment(4, new Author(8, "Anna", "Popova"), "Оно снова падает", new GregorianCalendar(2020, Calendar.APRIL, 12, 15, 11, 11).getTime()),
            new Comment(4, new Author(4, "Nikolay", "Elagin"), "разбираемся", new GregorianCalendar(2020, Calendar.APRIL, 12, 15, 15, 16).getTime())};

    Set <Comment> commentSet = new HashSet<>(Arrays.asList(commentsArray));

    Issue[] list = {new Issue(1, "ничего не работает", new Author(1, "Irina", "Alexandrova"), BUG, new Assignee(2, "Vladimir", "Posnek"), new Tag(2, "invitroproject"), CLOSED, new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.DECEMBER, 16, 12, 11, 11).getTime()),
            new Issue(2, "подсказка в д/з не помогает", new Author(3, "Vasiliy", "Vodovozov"), BUG, new Assignee(4, "Nilolay", "Elagin"), new Tag(3, "invitroprogect"), Status.OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime()),
            new Issue(3, "я могу предложить больше методов для д/з", new Author(5, "Boris", "Aprelev"), Label.ENHANCEMENT, new Assignee(6, "Dmitriy", "Girs"), CLOSED, new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime()),
            new Issue(4, "Файл с домашним заданием не загружается", new Author(7, "Savva", "Derunov"), BUG, new Assignee(5, "Boris", "Aprelev"), CLOSED, new GregorianCalendar(2020, Calendar.MARCH, 24, 12, 11, 11).getTime()),
            new Issue(5, "Кнопка добавить товар должна быть больше", new Author(5, "Anyz", "Mavel"), Label.WANTFIX, new Assignee(1, "Irina", "Alexandrova"), Status.OPEN, new GregorianCalendar(2020, Calendar.APRIL, 8, 12, 11, 11).getTime(), new Comment(1, new Author(1, "Irina", "Alexandrova"), "уточните какого размера должна быть кнопка", new GregorianCalendar(2020, Calendar.APRIL, 8, 13, 15, 17).getTime())),
            new Issue(6, "приложение не запускается", new Author(8, "Anna", "Popova"), BUG, new Assignee(2, "Vladimir", "Poznek"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 10, 13, 11, 11).getTime(), commentSet)};
    List<Issue> repoList = new ArrayList<>(Arrays.asList(list));

   IssueRepository repo = new IssueRepository(repoList);


    Issue testIssue = new Issue(7, "ничего не работает", new Author(1, "Irina", "Alexandrova"),
            Label.BUG, new Assignee(2, "Vladimir", "Posnek"), Status.CLOSED,
            new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime());

//    @Test
//    void shouldAdd() {
//        repo.add(testIssue);
//        Issue byId = repo.findById(7);
//        List<Issue> expected = new ArrayList<>();
//        expected.add(testIssue);
//        assertEquals(expected, actual);
//    }

    @Test
    void shouldOpen() {
        repo.open(1);
        Issue byId = repo.findById(1);
        assertSame(byId.getStatus(), Status.OPEN);
    }
}

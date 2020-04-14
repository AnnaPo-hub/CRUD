package ru.netology.domain;

import ru.netology.domain.issue.*;
import ru.netology.domain.person.Assignee;
import ru.netology.domain.person.Author;

import java.util.*;

import static ru.netology.domain.issue.Label.BUG;
import static ru.netology.domain.issue.Status.CLOSED;

public class Repository {


    List<Issue> issues = new ArrayList<>();


    Repository(Issue issue) {
        issues.add(issue);
    }


    Repository() {
        Set<Comment> commentSet = new HashSet<>();
        commentSet.add(new Comment(3, new Author(4, "Nikolay", "Elagin"), "Я уже уточнял, что за приложение и делал эту задачу", new GregorianCalendar(2020, Calendar.APRIL, 12, 13, 11, 11).getTime()));
        commentSet.add(new Comment(4, new Author(8, "Anna", "Popova"), "Оно снова падает", new GregorianCalendar(2020, Calendar.APRIL, 12, 15, 11, 11).getTime()));
        commentSet.add(new Comment(4, new Author(4, "Nikolay", "Elagin"), "разбираемся", new GregorianCalendar(2020, Calendar.APRIL, 12, 15, 15, 16).getTime()));

        issues.add(new Issue(1, "ничего не работает", new Author(1, "Irina", "Alexandrova"), BUG, new Assignee(2, "Vladimir", "Posnek"), new Tag(2, "invitroproject"), CLOSED, new GregorianCalendar(2019, Calendar.NOVEMBER, 16, 12, 11, 11).getTime()));
        issues.add(new Issue(2, "подсказка в д/з не помогает", new Author(3, "Vasiliy", "Vodovozov"), BUG, new Assignee(4, "Nilolay", "Elagin"), new Tag(3, "invitroprogect"), Status.OPEN, new GregorianCalendar(2019, Calendar.NOVEMBER, 17, 12, 11, 11).getTime()));
        issues.add(new Issue(3, "я могу предложить больше методов для д/з", new Author(5, "Boris", "Aprelev"), Label.ENHANCEMENT, new Assignee(6, "Dmitriy", "Girs"), CLOSED, new GregorianCalendar(2019, Calendar.DECEMBER, 16, 13, 12, 14).getTime()));
        issues.add(new Issue(4, "Файл с домашним заданием не загружается", new Author(7, "Savva", "Derunov"), BUG, new Assignee(5, "Boris", "Aprelev"), CLOSED, new GregorianCalendar(2020, Calendar.MARCH, 24, 12, 11, 11).getTime()));
        issues.add(new Issue(5, "Кнопка добавить товар должна быть больше", new Author(5, "Anyz", "Mavel"), Label.WANTFIX, new Assignee(1, "Irina", "Alexandrova"), Status.OPEN, new GregorianCalendar(2020, Calendar.APRIL, 8, 12, 11, 11).getTime(), new Comment(1, new Author(1, "Irina", "Alexandrova"), "уточните какого размера должна быть кнопка", new GregorianCalendar(2020, Calendar.APRIL, 8, 13, 15, 17).getTime())));
        issues.add(new Issue(6, "приложение не запускается", new Author(8, "Anna", "Popova"), BUG, new Assignee(2, "Vladimir", "Poznek"), CLOSED, new GregorianCalendar(2020, Calendar.APRIL, 10, 13, 11, 11).getTime(), commentSet));
    }

    List<Issue> getAll() {
        return issues;
    }

    void add(Issue issue) {
        issues.add(issue);
    }

    void open(int issueId) {
        for (Issue issue : issues) {
            if (issue.getId() == issueId) {
                issue.setStatus(Status.OPEN);
            }
        }
    }

    void deleteById(int issueId) {
        for (Issue issue : issues) {
            if (issue.getId() == issueId) {
                issues.remove(issue);
                return;
            }
        }
    }

    void removeAll (){
        issues.clear();
    }

    Issue findById(int issueId) {
        for (Issue issue : issues) {
            if (issue.getId() == issueId) {
                return issue;
            }
        }
        return null;
    }


    public List<Issue> sortByMostCommented() {
        CommentsQuantComparator comparator = new CommentsQuantComparator(true);
        issues.sort(comparator);
        return issues;
    }

    public List<Issue> sortByLessCommented() {
        CommentsQuantComparator comparator = new CommentsQuantComparator(false);
        issues.sort(comparator);
        return issues;
    }

    public List<Issue> sortFromOldest() {
        DateComparator comparator = new DateComparator(false);
        issues.sort(comparator);
        return issues;
    }

    public List<Issue> sortFromNewest() {
        DateComparator comparator = new DateComparator(true);
        issues.sort(comparator);
        return issues;
    }

    public List<Issue> sortFromRecentlyUpdated() {
        UpdateComparator comparator = new UpdateComparator(true);
        issues.sort(comparator);
        return issues;
    }

    public List<Issue> sortFromLeastRecentlyUpdated() {
        UpdateComparator comparator = new UpdateComparator(false);
        issues.sort(comparator);
        return issues;
    }

    public static class DateComparator implements Comparator<Issue> {
        private boolean isDescending;

        public DateComparator(boolean isDescending) {
            this.isDescending = isDescending;
        }

        @Override
        public int compare(Issue issue1, Issue issue2) {
            if (isDescending) {
                return issue2.getCreateDate().compareTo(issue1.getCreateDate());
            } else {
                return issue1.getCreateDate().compareTo(issue2.getCreateDate());
            }
        }
    }

    public static class CommentsQuantComparator implements Comparator<Issue> {
        private boolean isDescending;

        public CommentsQuantComparator(boolean isDescending) {
            this.isDescending = isDescending;
        }

        @Override
        public int compare(Issue issue1, Issue issue2) {
            int size1 = issue2.getCommentSet().size();
            int size2 = issue1.getCommentSet().size();

            if (isDescending) {
                return size1 - size2;
            } else {
                return size2 - size1;
            }
        }
    }

    public static class UpdateComparator implements Comparator<Issue> {
        private boolean isDescending;

        public UpdateComparator(boolean isDescending) {
            this.isDescending = isDescending;
        }

        @Override
        public int compare(Issue issue1, Issue issue2) {
            if (isDescending) {
                return issue2.getUpdateDate().compareTo(issue1.getUpdateDate());
            } else {
                return issue1.getUpdateDate().compareTo(issue2.getUpdateDate());
            }
        }
    }
}

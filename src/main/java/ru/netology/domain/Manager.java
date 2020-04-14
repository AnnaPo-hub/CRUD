package ru.netology.domain;

import ru.netology.domain.issue.Issue;
import ru.netology.domain.issue.Status;
import ru.netology.domain.person.Assignee;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static ru.netology.domain.issue.Status.CLOSED;

public class Manager {
    private Repository issues;

    public Manager(Repository issues) {
        this.issues = issues;
    }


    public List<Issue> showOpen() { //    отображение списка открытых Issue
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : issues.getAll()) {
            if (issue.getStatus().equals(Status.OPEN)) {
                temp.add(issue);

            }
        }
        return temp;
    }

    public List<Issue> showClosed() {  //  отображение списка закрытых Issue
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : issues.getAll()) {
            if (issue.getStatus().equals(CLOSED)) {
                temp.add(issue);

            }
        }
        return temp;
    }

    protected List<Issue> findMatch(Predicate<Issue> predicate) { //  `  фильтрация по имени автора c  предикатом,
        // можно тоже самое запустить с помощью лямбда выражения для фильтрации по лэйблу
        // (кто создал)
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : issues.getAll()) {
            if (predicate.test(issue)) {
                temp.add(issue);
            }
        }
        return temp;
    }

    public Set<Assignee> showListOfAssignees(int issueId) {
        for (Issue issue : issues.getAll()) {
            if (issue.getId() == issueId) {
                return issue.getAssigneesSet();
            }
        }
        return null;
    }

    public void addAssignee(int issueId, Assignee assignee) {
        for (Issue issue : issues.getAll()) {
            if (issue.getId() == issueId) {
                issue.addAssignee(assignee);

            }
        }
    }

    public List<Issue> sortByMostCommented() {
        List<Issue> issues = this.issues.sortByMostCommented();
        return issues;

    }

    public List<Issue> sortByLessCommented() {
        List<Issue> issues = this.issues.sortByLessCommented();
        return issues;
    }

    public List<Issue> sortFromOldest() {
        List<Issue> issues = this.issues.sortFromOldest();
        return issues;
    }

    public List<Issue> sortFromNewest() {
        List<Issue> issues = this.issues.sortFromNewest();
        return issues;
    }

    public List<Issue> sortFromRecentlyUpdated() {
        List<Issue> issues = this.issues.sortFromRecentlyUpdated();
        return issues;
    }

    public List<Issue> sortFromLeastUpdated() {
        List<Issue> issues = this.issues.sortFromLeastRecentlyUpdated();
        return issues;
    }
}

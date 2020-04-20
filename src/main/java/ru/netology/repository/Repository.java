package ru.netology.repository;

import ru.netology.issue.Issue;
import ru.netology.issue.Status;

import java.util.ArrayList;
import java.util.List;

import static ru.netology.issue.Status.CLOSED;

public class Repository {
    List<Issue> issues = new ArrayList<>();

    public Repository() {
    }

    public List<Issue> getAll() {
        return issues;
    }

    public void add(Issue issue) {
        issues.add(issue);
    }

    public void open(int issueId) {
        for (Issue issue : issues) {
            if (issue.getId() == issueId) {
                issue.setStatus(Status.OPEN);
            }
        }
    }

    public void close(int issueId) {
        for (Issue issue : issues) {
            if (issue.getId() == issueId) {
                issue.setStatus(CLOSED);
            }
        }
    }

    public void deleteById(int issueId) {
        for (Issue issue : issues) {
            if (issue.getId() == issueId) {
                issues.remove(issue);
                return;
            }
        }
    }

    public Issue findById(int issueId) {
        for (Issue issue : issues) {
            if (issue.getId() == issueId) {
                return issue;
            }
        }
        return null;
    }
}

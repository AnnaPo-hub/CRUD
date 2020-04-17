package ru.netology.repository;

import ru.netology.issue.Issue;
import ru.netology.issue.Status;

import java.util.List;

import static ru.netology.issue.Status.CLOSED;

public class IssueRepository {

    List<Issue> issues;

    public IssueRepository(List<Issue> issues) {
        this.issues = issues;
    }


//    public IssueRepository(Issue issue) {
//        issues.add(issue);
//    }

//    public IssueRepository() {
//    }

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

//    public List<Issue> sortByMostCommented() {
//        CommentsQuantComparator comparator = new CommentsQuantComparator(true);
//        issues.sort(comparator);
//        return issues;
//    }
//
//    public List<Issue> sortByLessCommented() {
//        CommentsQuantComparator comparator = new CommentsQuantComparator(false);
//        issues.sort(comparator);
//        return issues;
//    }
//
//    public List<Issue> sortFromOldest() {
//        DateComparator comparator = new DateComparator(false);
//        issues.sort(comparator);
//        return issues;
//    }
//
//    public List<Issue> sortFromNewest() {
//        DateComparator comparator = new DateComparator(true);
//        issues.sort(comparator);
//        return issues;
//    }
//
//    public List<Issue> sortFromRecentlyUpdated() {
//        UpdateComparator comparator = new UpdateComparator(true);
//        issues.sort(comparator);
//        return issues;
//    }
//
//    public List<Issue> sortFromLeastRecentlyUpdated() {
//        UpdateComparator comparator = new UpdateComparator(false);
//        issues.sort(comparator);
//        return issues;
//    }
}

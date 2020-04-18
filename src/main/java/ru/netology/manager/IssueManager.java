package ru.netology.manager;

import ru.netology.issue.Comment;
import ru.netology.issue.Issue;
import ru.netology.issue.Label;
import ru.netology.issue.Status;
import ru.netology.person.Assignee;
import ru.netology.repository.CommentsQuantComparator;
import ru.netology.repository.DateComparator;
import ru.netology.repository.IssueRepository;
import ru.netology.repository.UpdateComparator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static ru.netology.issue.Status.CLOSED;

public class IssueManager {
    private IssueRepository issues;

    public IssueManager(IssueRepository issues) {
        this.issues = issues;
    }

    public void add(Issue issue) {
        issues.add(issue);
    }

    public void addComment(int issueId, Comment comment) {
        if (comment != null) {
            Issue issueById = findById(issueId);
            if (issueById != null) {
                Set<Comment> commentSet = issueById.getCommentSet();
                if (commentSet == null) {
                    commentSet = new HashSet<>();
                }
                commentSet.add(comment);
            }
        }
    }

    public List<Issue> showOpen() {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : issues.getAll()) {
            if (issue.getStatus().equals(Status.OPEN)) {
                temp.add(issue);

            }
        }
        return temp;
    }

    public List<Issue> showClosed() {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : issues.getAll()) {
            if (issue.getStatus().equals(CLOSED)) {
                temp.add(issue);

            }
        }
        return temp;
    }

    public List<Issue> findMatch(Predicate<Issue> predicate) {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : issues.getAll()) {
            if (predicate.test(issue)) {
                temp.add(issue);
            }
        }
        return temp;
    }

    public List<Issue> filterByAssignee(Assignee assignee) {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : issues.getAll()) {
            if (issue.getAssigneesSet().contains(assignee)) {
                temp.add(issue);
            }
        }
        return temp;
    }
    public List<Issue> filterByLabel(Label label) {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : issues.getAll()) {
            if (issue.getLabel().equals(label)) {
                temp.add(issue);
            }
        }
        return temp;
    }

    public List<Issue> sortByMostCommented() {
        CommentsQuantComparator comparator = new CommentsQuantComparator(true);
        List<Issue> issuesAll = issues.getAll();
        issuesAll.sort(comparator);
        return issuesAll;
    }

    public List<Issue> sortByLessCommented() {
        CommentsQuantComparator comparator = new CommentsQuantComparator(false);
        List<Issue> issuesAll = issues.getAll();
        issuesAll.sort(comparator);
        return issuesAll;
    }

    public List<Issue> sortFromOldest() {
        DateComparator comparator = new DateComparator(false);
        List<Issue> issuesAll = issues.getAll();
        issuesAll.sort(comparator);
        return issuesAll;
    }

    public List<Issue> sortFromNewest() {
        DateComparator comparator = new DateComparator(true);
        List<Issue> issuesAll = issues.getAll();
        issuesAll.sort(comparator);
        return issuesAll;
    }

    public List<Issue> sortFromRecentlyUpdated() {
        UpdateComparator comparator = new UpdateComparator(true);
        List<Issue> issuesAll = issues.getAll();
        issuesAll.sort(comparator);
        return issuesAll;
    }

    public List<Issue> sortFromLeastRecentlyUpdated() {
        UpdateComparator comparator = new UpdateComparator(false);
        List<Issue> issuesAll = issues.getAll();
        issuesAll.sort(comparator);
        return issuesAll;
    }

    Issue findById(int issueId) {
        for (Issue issue : issues.getAll()) {
            if (issue.getId() == issueId) {
                return issue;
            }
        }
        return null;
    }
}

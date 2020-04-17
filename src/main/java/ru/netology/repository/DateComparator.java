package ru.netology.repository;

import ru.netology.issue.Issue;

import java.util.Comparator;

public class DateComparator implements Comparator<Issue> {
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


package ru.netology.comparator;

import ru.netology.issue.Issue;

import java.util.Comparator;

public class UpdateComparator implements Comparator<Issue> {
    private boolean isDescending;

    public UpdateComparator(boolean isDescending) {
        this.isDescending = isDescending;
    }

    @Override
    public int compare(Issue issue1, Issue issue2) {

        if (issue1.getUpdateDate() == null && issue2.getUpdateDate()  == null) return 0;
        if (issue1.getUpdateDate()  == null) return 1;
        if (issue2.getUpdateDate()  == null) return -1;
        if (isDescending) {
            return issue2.getUpdateDate().compareTo(issue1.getUpdateDate());
        } else {
            return issue1.getUpdateDate().compareTo(issue2.getUpdateDate());
        }
    }
}

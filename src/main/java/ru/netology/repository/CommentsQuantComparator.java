package ru.netology.repository;

import ru.netology.issue.Issue;

import java.util.Comparator;

public class CommentsQuantComparator implements Comparator<Issue> {
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

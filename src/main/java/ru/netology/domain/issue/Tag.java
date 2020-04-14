package ru.netology.domain.issue;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class Tag {
    int id;
    String name;

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                '}';
    }
}

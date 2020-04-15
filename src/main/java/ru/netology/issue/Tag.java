package ru.netology.issue;

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

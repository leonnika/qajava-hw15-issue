package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Issue implements Comparable<Issue>{
    private int id;
    private String title;
    private String author;
    private String dateOpen;
    private boolean isOpen;
    private String dataClose;
    private HashSet<String> assignee;
    private HashSet<String> label;

    @Override
    public int compareTo(Issue issue) {
        return id - issue.id;
    }
}

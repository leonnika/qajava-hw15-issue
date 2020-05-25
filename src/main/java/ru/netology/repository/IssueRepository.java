package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    private List<Issue> items = new ArrayList<>();

    public List<Issue> getAll() {
        return items;
    }

    public Issue getById(int id) {
        for (Issue item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public boolean add(Issue item) {
        return items.add(item);
    }

    public void openIssue(int id) {
        for (Issue item : items) {
            if (item.getId() == id) {
                item.setOpen(true);
            }
        }
    }

    public void closeIssue(int id) {
        for (Issue item : items) {
            if (item.getId() == id) {
                item.setOpen(false);
            }
        }
    }
}


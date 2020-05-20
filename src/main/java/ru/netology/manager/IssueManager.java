package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;


public class IssueManager {
    private IssueRepository repository = new IssueRepository();

    private List<Issue> items = new ArrayList<>();

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void addIssue(Issue issue) {
        repository.add(issue);
    }

    public List<Issue> getOpen() {
        List<Issue> result = new ArrayList<>();
        for (Issue item : repository.getAll()) {
            if (item.isOpen()) {
                result.add(item);
            }
        }
        return result;
    }

    public List<Issue> getClose() {
        List<Issue> result = new ArrayList<>();
        for (Issue item : repository.getAll()) {
            if (item.isOpen() == false) {
                result.add(item);
            }
        }
        return result;
    }


    public List<Issue> filterByAuthor(String author) {
        List<Issue> result = new ArrayList<>();
        Predicate<String> predicate = x -> x == author;
        for (Issue item : repository.getAll()) {
            if (predicate.test(item.getAuthor())) {
                result.add(item);
            }
        }
        return result;
    }

    public List<Issue> filterByLabel(String label) {
        List<Issue> result = new ArrayList<>();
        Predicate<String> predicate = x -> x == label;

        for (Issue item : repository.getAll()) {
            for (String itemLabel : item.getLabel())
                if (predicate.test(itemLabel)) {
                    result.add(item);
                }
        }
        return result;
    }

    public List<Issue> filterByAssignee(String assignee) {
        List<Issue> result = new ArrayList<>();
        Predicate<String> predicate = x -> x == assignee;
        for (Issue item : repository.getAll()) {
            for (String itemAssignee : item.getAssignee())
                if (predicate.test(itemAssignee)) {
                    result.add(item);
                }
        }
        return result;
    }

    public void sortById() {
        Collections.sort(repository.getAll(), Collections.reverseOrder());
    }

    public List<Issue> getAllIssue() {
        Collections.sort(repository.getAll(), Collections.reverseOrder());
        return repository.getAll();
    }

    public void updateById(int id) {
        for (Issue item : repository.getAll()) {
            if ((item.getId() == id)) {
                if (item.isOpen()) {
                    item.setOpen(false);
                } else {
                    item.setOpen(true);
                }
            }
        }
    }
}

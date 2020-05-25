package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IssueRepositoryTest {
    private IssueRepository repository = new IssueRepository();
    private HashSet<String> fistAssigneeSet = new HashSet<>();
    private HashSet<String> fistLabel = new HashSet<>();
    private HashSet<String> secondAssigneeSet = new HashSet<>();
    private HashSet<String> secondLabel = new HashSet<>();
    private HashSet<String> thirdAssigneeSet = new HashSet<>();
    private HashSet<String> thirdLabel = new HashSet<>();
    private Issue issue1Open = new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel);
    private Issue issue1Close = new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", false, "26.04.2020", fistAssigneeSet, fistLabel);
    private Issue issue2 = new Issue(7, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel);
    private Issue issue3 = new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel);


    @BeforeEach
    void init() {
        fistAssigneeSet.add("Александрова Александра Александровна");
        fistLabel.add("bag");
        secondAssigneeSet.add("Александрова Александра Александровна");
        secondAssigneeSet.add("Иванов Иван Иванович");
        secondLabel.add("bag");
        secondLabel.add("documentation");
        thirdAssigneeSet.add("Александрова Александра Александровна");
        thirdAssigneeSet.add("Иванов Иван Иванович");
        thirdAssigneeSet.add("Петров Петр Петрович");
        thirdLabel.add("bag");
        thirdLabel.add("documentation");
        thirdLabel.add("dublicat");
        repository.add(issue1Open);
        repository.add(issue2);
        repository.add(issue3);
    }

    @Test
    void shouldGetByCorrectId() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = new ArrayList<>();
        expected.add(issue3);
        actual.add(repository.getById(3));
        assertEquals(expected, actual);
    }

    @Test
    void shouldGetByNotCorrectId() {
        Issue actual = repository.getById(30);
        assertNull(actual);
    }

    @Test
    void shouldGetAll() {
        List<Issue> expected = new ArrayList<>();
        expected.addAll(List.of(issue1Open, issue2, issue3));
        assertEquals(expected, repository.getAll());
    }

    @Test
    void shouldCloseIssueIfClose() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = new ArrayList<>();
        expected.add(issue2);
        repository.closeIssue(7);
        actual.add(repository.getById(7));
        assertEquals(expected, actual);
    }

    @Test
    void shouldCloseIssueIfOpen() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = new ArrayList<>();
        expected.add(issue1Close);
        repository.closeIssue(1);
        actual.add(repository.getById(1));
        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenIssueIfClose() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = new ArrayList<>();
        expected.add(issue2);
        repository.openIssue(7);
        actual.add(repository.getById(7));
        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenIssueIfOpen() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = new ArrayList<>();
        expected.add(issue3);
        repository.openIssue(3);
        actual.add(repository.getById(3));
        assertEquals(expected, actual);
    }
}
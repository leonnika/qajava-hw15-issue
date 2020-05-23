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
        repository.add(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        repository.add(new Issue(2, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel));
        repository.add(new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel));
    }

    @Test
    void shouldGetByCorrectId() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = new ArrayList<>();
        expected.add(new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel));
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
        expected.addAll(List.of(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel), new Issue(2, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel), new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel)));
        assertEquals(expected, repository.getAll());
    }

    @Test
    void shouldRemove() {
        List<Issue> expected = new ArrayList<>();
        expected.addAll(List.of(new Issue(2, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel), new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel)));
        repository.remove(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        assertEquals(expected, repository.getAll());
    }

    @Test
    void shouldAddAll() {
        List<Issue> expected = new ArrayList<>();
        expected.addAll(List.of(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel), new Issue(2, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel), new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel), new Issue(4, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel), new Issue(5, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel)));
        List<Issue> issues = new ArrayList<>();
        issues.add(new Issue(4, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        issues.add(new Issue(5, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel));
        repository.addAll(issues);
        assertEquals(expected, repository.getAll());
    }

    @Test
    void shouldRemoveAll() {
        List<Issue> expected = new ArrayList<>();
        repository.removeAll(List.of(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel), new Issue(2, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel), new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel)));
        assertEquals(expected, repository.getAll());
    }

    @Test
    void shouldCloseSetIfClose() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = new ArrayList<>();
        expected.add(new Issue(2, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel));
        repository.closeSet(2);
        actual.add(repository.getById(2));
        assertEquals(expected, actual);
    }

    @Test
    void shouldCloseSetIfOpen() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = new ArrayList<>();
        expected.add(new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", false, "20.05.2020", thirdAssigneeSet, thirdLabel));
        repository.closeSet(3);
        actual.add(repository.getById(3));
        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenSetIfClose() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = new ArrayList<>();
        expected.add(new Issue(2, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", true, "", secondAssigneeSet, secondLabel));
        repository.openSet(2);
        actual.add(repository.getById(2));
        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenSetIfOpen() {
        List<Issue> expected = new ArrayList<>();
        List<Issue> actual = new ArrayList<>();
        expected.add(new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel));
        repository.openSet(3);
        actual.add(repository.getById(3));
        assertEquals(expected, actual);
    }
}
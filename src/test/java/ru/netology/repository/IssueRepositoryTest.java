package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
    }

    @Test
    void shouldGetByCorrectId() {
        repository.add(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        repository.add(new Issue(2, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel));
        repository.add(new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel));
        System.out.println("Test for getByCorrectId, expected issue(id 3), actual: " + repository.getById(3));
    }

    @Test
    void shouldGetByNotCorrectId() {
        repository.add(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        repository.add(new Issue(2, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel));
        repository.add(new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel));
        System.out.println("Test for getByNotCorrectId, expected null, actual: " + repository.getById(30));
    }

    @Test
    void shouldGetAll() {
        repository.add(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        repository.add(new Issue(2, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel));
        repository.add(new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel));
        System.out.println("Test for getAll, expected issue(id 1), issue(id 2), issue(id 3), actual: " + repository.getAll());
    }

    @Test
    void shouldRemove() {
        repository.add(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        repository.add(new Issue(2, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel));
        repository.add(new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel));
        repository.remove(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        System.out.println("Test for remove, expected issue(id 2) issue(id 3), actual: " + repository.getAll());
    }

    @Test
    void shouldAddAll() {
        List<Issue> issues = new ArrayList<>();
        issues.add(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        issues.add(new Issue(2, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel));
        issues.add(new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel));
        repository.addAll(issues);
        System.out.println("Test for addAll, expected issue(id 1), issue(id 2), issue(id 3), actual: " + repository.getAll());
    }

    @Test
    void shouldRemoveAll() {
        List<Issue> issues = new ArrayList<>();
        issues.add(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        issues.add(new Issue(2, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel));
        issues.add(new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel));
        repository.removeAll(issues);
        System.out.println("Test for removeAll, expected null, actual: " + repository.getAll());
    }
}
package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;


import java.util.Comparator;
import java.util.HashSet;

class IssueManagerTest {
    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);
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
    void shouldAddIssue() {
        manager.addIssue(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        System.out.println("Test for addIssue, expected issue(id 1), actual: " + manager.getAllIssue());
    }

    @Test
    void shouldGetOpen() {
        manager.addIssue(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        manager.addIssue(new Issue(7, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel));
        manager.addIssue(new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel));
        System.out.println("Test for getOpen, expected issue(id 1), issue(id 3), actual: " + manager.getOpen());
    }

    @Test
    void shouldGetClose() {
        manager.addIssue(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        manager.addIssue(new Issue(7, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel));
        manager.addIssue(new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel));
        System.out.println("Test for getClose, expected issue(id 7), actual: " + manager.getClose());
    }

    @Test
    void shouldSortById() {
        manager.addIssue(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        manager.addIssue(new Issue(7, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel));
        manager.addIssue(new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel));
        manager.sortById();
        System.out.println("Test for SortById, expected issue(id 7), issue(id 3), issue(id 1), actual: " + manager.getAllIssue());
    }

    @Test
    void shouldupdateById() {
        manager.addIssue(new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel));
        manager.addIssue(new Issue(7, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel));
        manager.addIssue(new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel));
        manager.updateById(7);
        System.out.println("Test for updateById, expected issue(id 1), issue(id 7), issue(id 3), actual: " + manager.getAllIssue());
    }
}
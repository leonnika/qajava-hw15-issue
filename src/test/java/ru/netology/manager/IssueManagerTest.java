package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueManagerTest {
    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);
    private HashSet<String> fistAssigneeSet = new HashSet<>();
    private HashSet<String> fistLabel = new HashSet<>();
    private HashSet<String> secondAssigneeSet = new HashSet<>();
    private HashSet<String> secondLabel = new HashSet<>();
    private HashSet<String> thirdAssigneeSet = new HashSet<>();
    private HashSet<String> thirdLabel = new HashSet<>();
    private Issue issue1Open = new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", true, "26.04.2020", fistAssigneeSet, fistLabel);
    private Issue issue1Close = new Issue(1, "Ошибка при создании репозитория", "Сидоров Семён Семёныч", "24.04.2020", false, "26.04.2020", fistAssigneeSet, fistLabel);
    private Issue issue2 = new Issue(7, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", false, "", secondAssigneeSet, secondLabel);
    private Issue issue2Open = new Issue(7, "Не соответствие типов", "Сидоров Семён Семёныч", "24.05.2020", true, "", secondAssigneeSet, secondLabel);
    private Issue issue3 = new Issue(3, "Ошибка чтения файлов", "Сидоров Семён Семёныч", "06.05.2020", true, "20.05.2020", thirdAssigneeSet, thirdLabel);

    @Nested
    public class Empty {
        @Test
        void shouldAddIssue() {
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1Open);
            manager.addIssue(issue1Open);
            assertEquals(expected, manager.getAllIssue());
        }

        @Test
        void shouldGetOpen() {
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.getOpen());
        }

        @Test
        void shouldGetClose() {
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.getClose());
        }


        @Test
        void shouldUpdateById() {
            List<Issue> expected = new ArrayList<>();
            manager.updateById(7);
            assertEquals(expected, manager.getAllIssue());
        }

        @Test
        void shouldFilterByAuthor() {
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.filterByAuthor("Сидоров Семён"));
        }

        @Test
        void shouldFilterByLabel() {
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.filterByLabel("dublicat"));
        }

        @Test
        void shouldFilterByAssignee() {
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.filterByAssignee("Иванов Иван Иванович"));
        }
    }

    @Nested
    public class SingleItem {

        @BeforeEach
        void init() {
            fistAssigneeSet.add("Александрова Александра Александровна");
            fistLabel.add("bug");
            manager.addIssue(issue1Open);
        }

        @Test
        void shouldGetOpen() {
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1Open);
            assertEquals(expected, manager.getOpen());
        }

        @Test
        void shouldGetClose() {
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1Close);
            manager.updateById(1);
            assertEquals(expected, manager.getClose());
        }

        @Test
        void shouldUpdateByIdForOpen() {
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1Close);
            manager.updateById(1);
            assertEquals(expected, manager.getAllIssue());
        }

        @Test
        void shouldFilterByAuthorIsIt() {
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1Open);
            assertEquals(expected, manager.filterByAuthor("Сидоров Семён Семёныч"));
        }

        @Test
        void shouldFilterByAuthorIsNotIt() {
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.filterByAuthor("Сидоров Семён"));
        }

        @Test
        void shouldFilterByLabelIsIt() {
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1Open);
            assertEquals(expected, manager.filterByLabel("bug"));
        }

        @Test
        void shouldFilterByLabelIsNotIt() {
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.filterByLabel("bugii"));
        }

        @Test
        void shouldFilterByAssigneeIsIt() {
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1Open);
            assertEquals(expected, manager.filterByAssignee("Александрова Александра Александровна"));
        }

        @Test
        void shouldFilterByAssigneeIsNotIt() {
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.filterByAssignee("Александрова Александра"));
        }
    }

    @Nested
    public class MultipleItem {

        @BeforeEach
        void init() {
            fistAssigneeSet.add("Александрова Александра Александровна");
            fistLabel.add("bug");
            secondAssigneeSet.add("Александрова Александра Александровна");
            secondAssigneeSet.add("Иванов Иван Иванович");
            secondLabel.add("bug");
            secondLabel.add("documentation");
            thirdAssigneeSet.add("Александрова Александра Александровна");
            thirdAssigneeSet.add("Иванов Иван Иванович");
            thirdAssigneeSet.add("Петров Петр Петрович");
            thirdLabel.add("bug");
            thirdLabel.add("documentation");
            thirdLabel.add("dublicat");
            manager.addIssue(issue1Open);
            manager.addIssue(issue2);
            manager.addIssue(issue3);
        }

        @Test
        void shouldGetOpen() {
            List<Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue1Open, issue3));
            assertEquals(expected, manager.getOpen());
        }

        @Test
        void shouldGetClose() {
            List<Issue> expected = new ArrayList<>();
            expected.add(issue2);
            assertEquals(expected, manager.getClose());
        }

        @Test
        void shouldFilterByAuthorIsIt() {
            List<Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue1Open, issue2, issue3));
            assertEquals(expected, manager.filterByAuthor("Сидоров Семён Семёныч"));
        }

        @Test
        void shouldFilterByLabelIsIt() {
            List<Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue1Open, issue2, issue3));
            assertEquals(expected, manager.filterByLabel("bug"));
        }

        @Test
        void shouldFilterByAssigneeIsIt() {
            List<Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue1Open, issue2, issue3));
            assertEquals(expected, manager.filterByAssignee("Александрова Александра Александровна"));
        }

        @Test
        void shouldUpdateByIdForOpen() {
            List<Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue2, issue3, issue1Close));
            manager.updateById(1);
            assertEquals(expected, manager.getAllIssue());
        }

        @Test
        void shouldUpdateByIdForClose() {
            List<Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue2Open, issue3, issue1Open));
            manager.updateById(7);
            assertEquals(expected, manager.getAllIssue());
        }
    }
}
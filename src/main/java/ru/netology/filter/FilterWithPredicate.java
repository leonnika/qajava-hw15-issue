package ru.netology.filter;

import java.util.function.Predicate;

public class FilterWithPredicate implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return false;
    }
}

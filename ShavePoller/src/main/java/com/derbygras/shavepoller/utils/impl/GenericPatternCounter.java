package com.derbygras.shavepoller.utils.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericPatternCounter {

    private final Pattern pattern;

    /* Package Private */
    GenericPatternCounter(String regex) {
        pattern = Pattern.compile(regex);
    }

    protected int countPatternInstances(String inputText) {
        int count = 0;
        Matcher matcher = pattern.matcher(inputText);

        while (matcher.find()) {
            count++;
        }

        return count;
    }
}

package ru.devsand.classfinder.pattern;

public class CamelCasePattern implements ClassNamePattern {

    private final String pattern;

    public CamelCasePattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean match(String className) {
        return false;
    }

}

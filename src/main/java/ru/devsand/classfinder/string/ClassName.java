package ru.devsand.classfinder.string;

public class ClassName implements Comparable<ClassName> {

    private final String fullName;
    private final String packageName;
    private final String simpleClassName;

    public ClassName(String className) {
        fullName = className;
        final String[] classNameParts = className.split("\\\\");
        packageName = separatePackageName(classNameParts);
        simpleClassName = separateSimpleClassName(classNameParts);
    }

    private String separatePackageName(String[] classNameParts) {
        return "";
    }

    private String separateSimpleClassName(String[] classNameParts) {
        return "";
    }

    public String getFullName() {
        return fullName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

    @Override
    public int compareTo(ClassName another) {
        return 0;
    }

}

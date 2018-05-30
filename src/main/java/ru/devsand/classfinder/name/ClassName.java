package ru.devsand.classfinder.name;

public interface ClassName<T extends ClassName> extends Comparable<T> {

    String getFullName();

    String getPackageName();

    String getShortClassName();

}

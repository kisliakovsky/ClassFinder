package ru.devsand.classfinder.stringwrap;

public interface ClassName<T extends ClassName> extends Comparable<T> {

    String getFullName();

    String getPackageName();

    String getSimpleClassName();

}

package ru.devsand.classfinder.search;

import java.util.Collection;

public interface ClassNameFinder {

    Collection<String> find(String pattern);

}

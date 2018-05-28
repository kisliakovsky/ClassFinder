package ru.devsand.classfinder.search;

import java.util.Collection;

public interface ClassFinder {

    Collection<String> find(String pattern);

}

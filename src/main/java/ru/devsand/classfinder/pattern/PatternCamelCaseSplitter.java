package ru.devsand.classfinder.pattern;

import ru.devsand.classfinder.pattern.part.PatternPart;
import ru.devsand.classfinder.util.SpecialStringSplitter;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static ru.devsand.classfinder.util.StringSplitters.splitAsCamelCase;
import static ru.devsand.classfinder.util.StringSplitters.splitByLetters;
import static ru.devsand.classfinder.util.StringUtil.isLowerCase;
import static ru.devsand.classfinder.util.StringUtil.replaceAll;

class PatternCamelCaseSplitter implements SpecialStringSplitter<PatternPart> {

    private static final char WILDCARD = '*';

    public List<PatternPart> apply(String pattern) {
        List<String> patternParts;
        Function<String, PatternPart> mapper;
        if (isLowerCase(pattern)) {
            String purePattern = removeNonsensicalWildcards(pattern);
            patternParts = splitByLetters(purePattern);
            mapper = PatternPart::newCaseInsensitiveInstance;
        } else {
            patternParts = splitAsCamelCase(pattern);
            mapper = part -> PatternPart.from(part, WILDCARD);
        }
        return patternParts.stream().map(mapper).collect(toList());
    }


    private static String removeNonsensicalWildcards(String pattern) {
        return replaceAll(pattern, WILDCARD, "");
    }

}

package ru.devsand.classfinder.pattern.part;

import java.util.List;

import static ru.devsand.classfinder.util.StringSplitters.split;
import static ru.devsand.classfinder.util.StringUtil.replaceAll;

class WildcardPatternPart extends AbstractPatternPart {

    private final List<String> blocks;

    WildcardPatternPart(String patternPart, char wildcard) {
        super(patternPart);
        this.blocks = splitIntoBlocks(patternPart, wildcard);
    }

    private static List<String> splitIntoBlocks(String patternPart, char wildcard) {
        patternPart = removeExtraWildcards(patternPart, wildcard);
        return split(patternPart, wildcard);
    }

    private static String removeExtraWildcards(String patternPart, char wildcard) {
        return replaceAll(patternPart, "" + wildcard + wildcard, "" + wildcard);
    }

    @Override
    public int compareToString(String s) {
        if (startsWith(s) && endsWith(s) && containsBetweenInRightOrder(s)) {
                return 0;
        } else {
            return (int) Math.signum(patternPart.compareTo(s));
        }
    }

    private boolean startsWith(String s) {
        return s.startsWith(blocks.get(0));
    }

    private boolean containsBetweenInRightOrder(String s) {
        int lastIndex = blocks.size() - 1;
        StringBuilder stringBuilder = new StringBuilder(s);
        final List<String> intermediateBlocks = blocks.subList(1, lastIndex);
        for (String block: intermediateBlocks) {
            final int blockIndex = stringBuilder.indexOf(block);
            boolean blockFound = (blockIndex != -1);
            if (blockFound) {
                stringBuilder.delete(blockIndex, blockIndex + block.length());
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean endsWith(String s) {
        int lastIndex = blocks.size() - 1;
        return s.endsWith(blocks.get(lastIndex));
    }

    @Override
    public String toString() {
        return patternPart;
    }

}

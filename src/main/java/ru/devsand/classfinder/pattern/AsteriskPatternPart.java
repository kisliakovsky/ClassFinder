package ru.devsand.classfinder.pattern;

import java.util.Arrays;
import java.util.List;

class AsteriskPatternPart {

    private static final String ASTERISK = "*";
    private static final String ESCAPED_ASTERISK = "\\*";
    private final String patternPart;
    private final List<String> blocks;

    private AsteriskPatternPart(String patternPart) {
        this.patternPart = patternPart;
        this.blocks = Arrays.asList(patternPart
                .replaceAll(ESCAPED_ASTERISK + ESCAPED_ASTERISK, ASTERISK)
                .split(ESCAPED_ASTERISK));
        completeBlocks();
    }

    private void completeBlocks() {
        if (patternPart.endsWith(ASTERISK)) {
            blocks.add("");
        }
    }

    public static AsteriskPatternPart from(String patternPart) {
        return new AsteriskPatternPart(patternPart);
    }

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

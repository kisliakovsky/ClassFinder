package ru.devsand.classfinder.pattern.part;

import java.util.List;

import static ru.devsand.classfinder.util.StringSplitters.split;
import static ru.devsand.classfinder.util.StringUtil.replaceAll;

class WildcardPatternPart extends AbstractPatternPart {

    private final List<String> blocks;
    private final String startBlock;
    private final String endBlock;

    WildcardPatternPart(String patternPart, char wildcard) {
        super(patternPart);
        blocks = splitIntoBlocks(patternPart, wildcard);
        startBlock = blocks.get(0);
        int endIndex = blocks.size() - 1;
        endBlock = blocks.get(endIndex);
    }

    private static List<String> splitIntoBlocks(String patternPart, char wildcard) {
        patternPart = removeExtraWildcards(patternPart, wildcard);
        return split(patternPart, wildcard);
    }

    private static String removeExtraWildcards(String patternPart, char wildcard) {
        return replaceAll(patternPart, "" + wildcard + wildcard, "" + wildcard);
    }

    @Override
    public int compareToString(String string) {
        int defaultComparison = (int) Math.signum(patternPart.compareTo(string));
        StringBuilder stringBuilder = new StringBuilder(string);
        int pointer = -1;
        if (startBlock.isEmpty()) {
            pointer = 0;
        }
        for (String block: blocks) {
            if (!block.isEmpty()) {
                int blockIndex = stringBuilder.indexOf(block);
                if (blockIndex == -1) {
                    return defaultComparison;
                } else {
                    stringBuilder.delete(blockIndex, blockIndex + block.length());
                    if (blockIndex > pointer) {
                        pointer = blockIndex;
                    } else {
                        return defaultComparison;
                    }
                }
            }
        }
        if (endBlock.isEmpty()) {
            if (pointer < stringBuilder.length()) {
                return 0;
            } else {
                return defaultComparison;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return patternPart;
    }

}

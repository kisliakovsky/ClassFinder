package ru.devsand.classfinder;

import ru.devsand.classfinder.search.ClassFinder;
import ru.devsand.classfinder.search.SimpleClassFinder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

public class Launcher {

    private static final String ERROR_LABEL = "Error:";
    private static final String HELP_FLAG = "--help";
    private static final String TRY_HELP_MESSAGE = "Try ~$ java -jar classfinder.jar "
            + HELP_FLAG + " for more information";
    private static final String HELP_MESSAGE =
            "Usage: ~$ java -jar classfinder.jar [FLAG] <filename> <pattern>\n" +
                    "Find names of Java classes in file <filename> " +
                    "using search pattern <pattern>.\n" +
                    "Search pattern <pattern> must include " +
                    "class name camelcase upper case letters\n" +
                    "in the right order and it may contain lower case letters\n" +
                    "to narrow down the search results.\n" +
                    "For example 'FB' or 'FoBa' patterns must all match " +
                    "'a.b.FooBarBaz' and 'c.d.FooBar' classes.\n\n" +
                    "Flags:\n" +
                    "  --help    display this help and exit";

    public static void main(String[] args) {
        try {
            args = requireArgsExternallyCorrect(args);
            if (args.length == 1) {
                printHelp();
            } else {
                printClassNames(args);
            }
        } catch (IllegalArgumentException ignored) {
        }
    }

    private static String[] requireArgsExternallyCorrect(String[] args) {
        final int argsNumber = args.length;
        switch (argsNumber) {
            case 0:
                printErrorAndThrowException("missing file operand");
            case 1:
                final String arg = args[0];
                if (arg.equals(HELP_FLAG)) {
                    return args;
                } else {
                    String message = String.format("missing pattern operand after '%s'", arg);
                    printErrorAndThrowException(message);
                }
            case 2:
                return args;
            default:
                String message = String.format("extra arguments after '%s' and '%s'",
                        args[0], args[1]);
                printErrorAndThrowException(message);
        }
        throw new AssertionError(); // Cannot be reached
    }

    private static void printErrorAndThrowException(String message) {
        System.err.printf("%s %s%n%s%n", ERROR_LABEL, message, TRY_HELP_MESSAGE);
        throw new IllegalArgumentException();
    }

    private static void printHelp() {
        System.out.println(HELP_MESSAGE);
    }

    private static void printClassNames(String[] args) {
        Path filePath = Paths.get(args[0]);
        String pattern = args[1];
        ClassFinder classFinder = new SimpleClassFinder(
                () -> Arrays.asList("a.b.FooBarBaz", "c.d.FooBar"));
        Collection<String> classNames = classFinder.find(pattern);
        classNames.forEach(System.out::println);
    }

}

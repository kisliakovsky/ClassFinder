package ru.devsand.classfinder;

import ru.devsand.classfinder.extract.TextFileReader;
import ru.devsand.classfinder.extract.TextSupplier;
import ru.devsand.classfinder.search.ClassNameFinder;
import ru.devsand.classfinder.search.SimpleClassNameFinder;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.Collections.emptyList;

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
                    "Example:\n" +
                    "input\n" +
                    "~$ java -jar classfinder.jar ./classes.txt FB\n" +
                    "output\n" +
                    "a.b.FooBarBaz\n" +
                    "c.d.FooBar\n\n" +
                    "Flags:\n" +
                    "  --help    display this help and exit";

    public static void main(String[] args) {
        try {
            args = requireArgsExternallyCorrect(args);
            if (args.length == 1) {
                printHelp();
            } else {
                final List<String> classNames = findClassNamesInFile(args);
                printClassNames(classNames);
            }
        } catch (IllegalArgumentException ignored) {
        }
    }

    private static String[] requireArgsExternallyCorrect(String[] args) {
        final int argsNumber = args.length;
        switch (argsNumber) {
            case 0:
                printInputErrorAndThrowException("missing file operand");
            case 1:
                final String arg = args[0];
                if (arg.equals(HELP_FLAG)) {
                    return args;
                } else {
                    String message = String.format("missing pattern operand after '%s'", arg);
                    printInputErrorAndThrowException(message);
                }
            case 2:
                return args;
            default:
                String message = String.format("extra arguments after '%s' and '%s'",
                        args[0], args[1]);
                printInputErrorAndThrowException(message);
        }
        throw new AssertionError(); // Cannot be reached
    }

    private static void printInputErrorAndThrowException(String message) {
        System.err.printf("%s %s%n%s%n", ERROR_LABEL, message, TRY_HELP_MESSAGE);
        throw new IllegalArgumentException();
    }

    private static void printErrorAndThrowException(String message) {
        System.err.printf("%s %s%n", ERROR_LABEL, message);
        throw new IllegalArgumentException();
    }

    private static void printHelp() {
        System.out.println(HELP_MESSAGE);
    }

    private static List<String> findClassNamesInFile(String[] args) {
        String filePathString = args[0];
        Path filePath = Paths.get(filePathString);
        String pattern = args[1];
        try {
            TextSupplier classNamesSupplier = new TextFileReader(filePath);
            ClassNameFinder classFinder = new SimpleClassNameFinder(classNamesSupplier);
            return classFinder.find(pattern);
        } catch (NoSuchFileException noFileException) {
            String message = String.format("the file '%s' does not exist", filePathString);
            printErrorAndThrowException(message);
        } catch (AccessDeniedException noAccessException) {
            String message = String.format("access denied to the file '%s'", filePathString);
            printErrorAndThrowException(message);
        } catch (IOException commonException) {
            String message = String.format("unable to read the fire '%s'", filePathString);
            printErrorAndThrowException(message);
        }
        return emptyList();
    }

    private static void printClassNames(List<String> classNames) {
        classNames.forEach(System.out::println);
    }


}

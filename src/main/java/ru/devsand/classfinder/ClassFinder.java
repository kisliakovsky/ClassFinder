package ru.devsand.classfinder;

public class ClassFinder {

    private static final String ERROR_LABEL = "Error:";
    private static final String HELP_FLAG = "--help";
    private static final String TRY_HELP_MESSAGE = "Use " + HELP_FLAG + " for more information";

    public static void main(String[] args) {
        try {
            args = requireArgsCorrect(args);
            if (args.length == 1) {
                printHelp();
            } else {
                findClasses(args[0], args[1]);
            }
        } catch (IllegalArgumentException ignored) {
        }
    }

    private static String[] requireArgsCorrect(String[] args) {
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
                System.out.println("It's ok, man!");
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
        System.out.println("Let I help you!");
    }

    private static void findClasses(String fileName, String pattern) {
        System.out.println("I'm going to find classes!");
    }

}

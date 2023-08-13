package ru.etysoft.dira.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Logger {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public final static String ERROR_PREFIX = "ERROR";
    public final static String WARNING_PREFIX = "WARNING";
    public final static String CRITICAL_PREFIX = "CRITICAL ERROR";
    public final static String INFO_PREFIX = "INFO";
    public final static String DEBUG_PREFIX = "DEBUG";

    public static void debug(String message, String sender) {

        sendPrefixedMessage(ANSI_PURPLE + message + ANSI_RESET, DEBUG_PREFIX, sender);

    }

    public static void greenLog(String message, String sender) {
        sendPrefixedMessage(ANSI_GREEN + message + ANSI_RESET, DEBUG_PREFIX, sender);
    }

    public static void info(String message, String sender) {
        sendPrefixedMessage(message, INFO_PREFIX, sender);
    }

    public static void critical(String message, String sender) {
        sendPrefixedMessage(ANSI_RED_BACKGROUND + ANSI_BLACK + message + ANSI_RESET, CRITICAL_PREFIX, sender);
    }

    public static void warning(String warning, String sender) {

        sendPrefixedMessage(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + warning + ANSI_RESET, WARNING_PREFIX, sender);
    }

    public static void error(String error, String sender) {
        sendPrefixedMessage(ANSI_RED + error + ANSI_RESET, ERROR_PREFIX, sender);
    }

    public static void sendPrefixedMessage(String message, String prefix, String sender) {
        String formattedMessage = "[" + prefix + " | " + getTimePrefix() + "] " + sender + ": " + message;
        System.out.println(formattedMessage);


    }


    public static String getTimePrefix() {
        final long timestamp = new Date().getTime();

        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);

        final int minutes = cal.get(Calendar.MINUTE);

        return
                new SimpleDateFormat("HH:mm:ss").format(cal.getTime());
    }

}

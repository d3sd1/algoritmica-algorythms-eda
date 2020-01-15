/*
 * Copyright (c) 2019.
 * Práctica final Estructuras de Datos y Algoritmos.
 * Creada por Andrei García Cuadra y Rebeca Ariño Olivares.
 * Todos los derechos reservados bajo licencia MIT.
 */

package phases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EDALogger {
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

    public static void info(String text) {
        EDALogger.output(text, "INFO", ANSI_RESET + ANSI_CYAN, ANSI_RESET);
    }

    public static void error(String text) {
        EDALogger.output(text, "ERROR", ANSI_RESET + ANSI_RED, ANSI_RESET);
    }

    public static void warning(String text) {
        EDALogger.output(text, "WARNING", ANSI_RESET + ANSI_YELLOW, ANSI_RESET);
    }

    private static void output(String text, String type, String pre, String post) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        Date date = new Date();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        System.out.println(pre + "[" + dateFormat.format(date) + "] " + type + ": " + text + " on file " + stackTraceElements[3] + post);
    }
}

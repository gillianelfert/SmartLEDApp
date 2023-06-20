package de.hawhamburg.smartledapp.model.mqtt;

public class Log {
    public static void info(String string) {
        System.out.println(string);
    }

    public static void error(String string) {
        System.err.println(string);
    }
}
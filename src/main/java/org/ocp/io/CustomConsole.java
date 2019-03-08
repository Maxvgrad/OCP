package org.ocp.io;

import java.io.Console;

public class CustomConsole {

    private static Console console;

    public static void main(String[] args) {
        console = System.console();

        if (console == null) {
            System.out.println("Tty not supported.");
        }
    }
}

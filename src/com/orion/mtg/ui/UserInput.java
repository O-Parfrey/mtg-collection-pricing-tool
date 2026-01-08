package com.orion.mtg.ui;

import java.util.Scanner;

public class UserInput {

    private static Scanner scanner = new Scanner(System.in);

    public static int getInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

    public static String getString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }
}
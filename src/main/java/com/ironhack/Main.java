package com.ironhack;

import com.ironhack.supportiveClasses.CheckCommands;
import com.ironhack.supportiveClasses.PrintText;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            launchApplication();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void launchApplication() throws InterruptedException {
        String command = null;
        Scanner scanner = new Scanner(System.in);
        CheckCommands checkCommands = new CheckCommands();

        do{
            PrintText.printAvailableCommands();
            try {
                command = scanner.nextLine();
                checkCommands.checkCommand(command);
            } catch (IllegalArgumentException e){
                PrintText.wrongCommand(e.getMessage());
            } finally {
                Thread.sleep(500);
            }

        } while (!command.trim().equalsIgnoreCase("exit"));

    }

}

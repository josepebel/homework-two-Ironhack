package com.ironhack.supportiveClasses;

import com.ironhack.classes.Account;

import java.security.InvalidParameterException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class CheckCommands {

    private Interactions interactions;
    private int state;

    public CheckCommands() {

        interactions = new Interactions();
        state = 0;
    }

    public void checkCommand (String command) throws IllegalArgumentException{

        String[] words = command.trim().split(" ");
        if (words.length ==2 && "New".equalsIgnoreCase(words[0]) && "Lead".equalsIgnoreCase(words[1])) {
            interactions.createLead();
            state = 1;

        } else if (words.length==2 && "show".equalsIgnoreCase(words[0]) && "leads".equalsIgnoreCase(words[1])){
            interactions.showLeads();
            state = 2;

        } else if (words.length == 3 && words[0].equalsIgnoreCase("lookup") && words[1].equalsIgnoreCase("lead")){
            try {
                int id = Integer.parseInt(words[2]);
                interactions.lookupLead(id);
                state = 3;

            } catch (InputMismatchException e){
                PrintText.wrongIdFormat();
                state = -3;
            } catch (NumberFormatException e){
                PrintText.wrongIdFormat();
                state = -3;
            }

        } else if (words.length == 2 && words[0].equalsIgnoreCase("convert") ){
            try {
                int id = Integer.parseInt(words[1]);
                interactions.convertLead(id);
                state = 4;

            } catch (InputMismatchException e){
                PrintText.wrongIdFormat();
                state = -4;
            } catch (NumberFormatException e){
                PrintText.wrongIdFormat();
                state = -4;
            }

        } else if (words.length==2 && "show".equalsIgnoreCase(words[0]) && "opportunities".equalsIgnoreCase(words[1])){
            interactions.showOpportunities();
            state = 5;

        } else if (words.length==2 && "show".equalsIgnoreCase(words[0]) && "contacts".equalsIgnoreCase(words[1])){
            interactions.showContacts();
            state = 6;

        } else if (words.length == 3 && words[0].equalsIgnoreCase("lookup") && words[1].equalsIgnoreCase("opportunity")){
            try {
                int id = Integer.parseInt(words[2]);
                interactions.lookupOpportunity(id);
                state = 7;

            } catch (InputMismatchException e){
                PrintText.wrongIdFormat();
                state = -7;
            } catch (NumberFormatException e){
                PrintText.wrongIdFormat();
                state = -7;
            }

        } else if (words.length == 3 && words[0].equalsIgnoreCase("lookup") && words[1].equalsIgnoreCase("contact")){
            try {
                int id = Integer.parseInt(words[2]);
                interactions.lookupContact(id);
                state = 8;
            } catch (InputMismatchException e){
                PrintText.wrongIdFormat();
                state = -8;
            } catch (NumberFormatException e){
                PrintText.wrongIdFormat();
                state = -8;
            }

        } else if (words.length==2 && "close-lost".equalsIgnoreCase(words[0])){
            System.out.println();
            try {
                int id = Integer.parseInt(words[1]);
                state = interactions.closeLost(id);

            } catch (InputMismatchException e){
                PrintText.wrongIdFormat();
                state = -9;
            } catch (NumberFormatException e){
                PrintText.wrongIdFormat();
                state = -9;
            }

        } else if (words.length==2 && "close-won".equalsIgnoreCase(words[0])){
             try {
                 int id = Integer.parseInt(words[1]);
                 state = interactions.closeWon(id);

             } catch (InputMismatchException e){
                 PrintText.wrongIdFormat();
                 state = -10;
             } catch (NumberFormatException e){
                 PrintText.wrongIdFormat();
                 state = -10;
             }

        } else if (words.length == 1 && "exit".equalsIgnoreCase(words[0])){
            System.out.println("");
            System.out.println(Colors.WHITE+Colors.BOLD+"Good bye!"+Colors.RESET);
            System.err.println("");
            state = 0;

        } else {
            throw new IllegalArgumentException("Unknown command");
        }
    }

    public Interactions getInteractions() {
        return interactions;
    }

    public int getState() {
        return state;
    }
}

/*
 * CompSci 101 - Keyboard Class
 * ============================
 * This class is used for input from the keyboard. 
 * YOU DO NOT NEED TO UNDERSTAND THE DETAILS OF THIS CLASS. 
 * To use this class, put it in the same directory as the source file for your program, and include the 
 * statement:
 * String input = Keyboard.readInput(); in your program. 
 * This will assign whatever is typed at the keyboard to the input variable.
 * 
 */

import java.util.*;
import java.io.*;

public class Keyboard {

    private static Scanner in = new Scanner(System.in);;
    private static boolean redirected = false;

    public static String readInput() {

        try {
            if (!redirected) {
                redirected = System.in.available() != 0;
            }
        } catch (IOException e) {
            System.err.println("An error has occurred in the Keyboard constructor.");
            e.printStackTrace();
            System.exit(-1);
        }

        try {
            String input = in.nextLine();
            if (redirected) {
                System.out.println(input);
            }
            return input;
        } catch (IllegalStateException e) {
            System.err.println("An error has occurred in the Keyboard.readInput() method.");
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}

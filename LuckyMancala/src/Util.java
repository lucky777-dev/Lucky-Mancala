package LuckyMancala.src;

import java.util.Scanner;
import java.io.IOException;

public class Util {

    static Scanner kb = new Scanner(System.in);

    /**
     * Force the user to input a valid integer in a range of two integers.
     * 
     * @param msg The message we want to print.
     * @param min The minimum value accepted.
     * @param max The maximum value accepted.
     * @return The valid integer.
     */
    public static int askIntRange(String msg, int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Error 'askIntRange()' min >= max forbidden!");
        }
        System.out.println("" + msg + " (" + min + "/" + max + ") : ");
        System.out.print(">");
        while (!kb.hasNextInt()) {
            System.out.println("! Please enter a valid number : ");
            System.out.print(">");
            kb.next();
        }
        int result = kb.nextInt();
        kb.nextLine(); /* Delete '\n' char in 'kb' buffer */
        while (result < min || result > max) {
            System.out.println("! Please enter a number between " + min + " and " + max + " : ");
            System.out.print(">");
            while (!kb.hasNextInt()) {
                System.out.println("! Please enter a valid number : ");
                System.out.print(">");
                kb.next();
            }
            result = kb.nextInt();
            kb.nextLine(); /* Delete '\n' char in 'kb' buffer */
        }
        return result;
    }

    /**
     * Lovely way to print a message like a typewriter.
     * 
     * The method waits 100 ms after printing each char of the message.
     * 
     * Then it waits 1400 ms after printing the entire message.
     * 
     * @param msg The message we want to print.
     */
    public static void type(String msg) {
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '\n') {
                System.out.println();
            } else {
                System.out.print(msg.charAt(i));
                sleep(0.1);
            }
        }
        sleep(1.4);
    }

    /**
     * Lovely way to print a message like a typewriter.
     * 
     * The method waits 100 ms after printing each char of the message.
     * 
     * Then it waits 1400 ms after printing the entire message.
     * 
     * Finally, it prints a '\n' char.
     * 
     * @param msg The message we want to print.
     */
    public static void typeln(String msg) {
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '\n') {
                System.out.println();
            } else {
                System.out.print(msg.charAt(i));
                sleep(0.1);
            }
        }
        sleep(1.4);
        System.out.println();
    }

    /**
     * Lovely way to print a message like a typewriter.
     * 
     * The method waits 14 ms after printing each char of the message.
     * 
     * @param msg The message we want to print.
     */
    public static void fastType(String msg) {
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '\n') {
                System.out.println();
            } else if (msg.charAt(i) == ' ') {
                System.out.print(" ");
            } else {
                System.out.print(msg.charAt(i));
                sleep(0.014);
            }
        }
    }

    /**
     * Lovely way to print a message like a typewriter.
     * 
     * The method waits 14 ms after printing each char of the message.
     * 
     * Then it prints a '\n' char after the entire message.
     * 
     * @param msg The message we want to print.
     */
    public static void fastTypeln(String msg) {
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '\n') {
                System.out.println();
            } else if (msg.charAt(i) == ' ') {
                System.out.print(" ");
            } else {
                System.out.print(msg.charAt(i));
                sleep(0.014);
            }
        }
        System.out.println();
    }

    /**
     * The program waits a certain number of milliseconds before continuing.
     * 
     * @param s The number of seconds.
     */
    public static void sleep(double s) {
        int ms = (int) (s * 1000);
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            System.out.println("! <error> ('sleep' method)");
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Clear the screen of the user (I'm sure this works on Windows, and I hope this
     * will work on Linux and MacOs too... I'm praying !)
     */
    public static void clear() {
        String os = System.getProperty("os.name");
        try {
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("! <error> ('clearScreen' method)");
            sleep(1);
        }
    }
}

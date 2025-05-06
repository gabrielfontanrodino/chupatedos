package gal.uvigo.esei.aed1.chupatedos.iu;

import java.util.Scanner;

public class IU {

    private final Scanner keyboard;

    public IU() {
        keyboard = new Scanner(System.in);
    }

    /**
     * Reads a number from the keyboard.
     *
     * @param msg The message to show
     * @return El number read from the keyboard
     */
    public int readNumber(String msg) {
        boolean repeat;
        int toret = 0;

        do {
            repeat = false;
            System.out.print(msg);
            try {
                toret = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException exc) {
                repeat = true;
            }
        } while (repeat);

        return toret;
    }

    /**
     * Reads a number from the keyboard with a specified range.
     * Shows an error message if the number is not in the range [min, max] and
     * asks for a new number.
     *
     * @param msg The message to show
     * @param min The minimum value, included
     * @param max The maximum value, included
     * @return The number read from the keyboard
     */
    public int readNumber(String msg, int min, int max) {
        boolean repeat;
        int toret = 0;

        do {
            repeat = false;
            System.out.print(msg);
            try {
                toret = Integer.parseInt(keyboard.nextLine());
                if(toret < min || toret > max) {
                    System.out.println();
                    throw new NumberFormatException("El número debe estar entre " + min + " y " + max);
                }
            } catch (NumberFormatException exc) {
                repeat = true;
                this.displayError(exc.getMessage());
            }
        } while (repeat);

        return toret;
    }


    /**
     * Reads a string from the keyboard
     *
     * @param msg the message to show
     * @return the string read from the keyboard
     */
    public String readString(String msg) {
        System.out.print(msg);
        return keyboard.nextLine();
    }

    /**
     * Shows a message on the screen
     *
     * @param msg the message to show
     */
    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Shows a line to separate messages on the screen
     *
     * @param length the length of the line to show
     */
    public void displaySeparator(int length) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++) {
            line.append("=");
        }

        System.err.println(line.toString());
    }
    
    /**
     * Shows 3 dots to make a cute waiting interaction
     */
    public void displayWaitingDots() {
        for (int i = 0; i < 3; i++) {
            await(600);
            displayMessage(".");
        }
        await(600);
    }

    /**
     * Shows an empty line on the screen
     */
    public void displayEmptyLine() {
        System.out.println();
    }

    /**
     * Shows an error message on the screen
     *
     * @param msg the error message to show
     */
    public void displayError(String msg) {
        System.err.println("ERROR - " + msg);
    }

    /**
     * Waits for a specified amount of time in milliseconds.
     * 
     * @param millis the time to wait in milliseconds
     */
    public void await(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
    }

    /**
     * Closes the keyboard scanner.
     */
    public void close() {
        keyboard.close();
    }

}

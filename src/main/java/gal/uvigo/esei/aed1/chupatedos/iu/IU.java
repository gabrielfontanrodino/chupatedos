package gal.uvigo.esei.aed1.chupatedos.iu;

import java.util.Scanner;

public class IU {

    private final Scanner keyboard;

    public IU() {
        keyboard = new Scanner(System.in);
    }

    /**
     * Lee un num. de teclado
     *
     * @param msg El mensaje a visualizar.
     * @return El num., como entero
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
     * Lee un num. de teclado comprendido entre un rango
     *
     * @param msg El mensaje a visualizar.
     * @param min El valor mínimo, incluido
     * @param max El valor máximo, incluido
     * @return El num., como double
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
     * Lee un string de teclado
     *
     * @param msg mensaje a mostrar antes de la lectura
     * @return el string leido
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

    public void displaySeparator(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public void displayEmptyLine() {
        System.out.println();
    }

    public void displayError(String msg) {
        System.err.println("ERROR - " + msg);
    }

    /**
     * Shows a message and waits for the user to press Enter
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

    public void close() {
        keyboard.close();
    }

}

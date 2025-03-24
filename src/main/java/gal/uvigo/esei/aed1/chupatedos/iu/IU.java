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
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
        }
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
     * Muestra un mensaje por pantalla
     *
     * @param msg el mensaje a mostrar
     */
    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    public void displayEmptyLine() {
        System.out.println();
    }

    public void displayError(String msg) {
        System.err.println("ERROR - " + msg);
    }

    public void close() {
        keyboard.close();
    }

}

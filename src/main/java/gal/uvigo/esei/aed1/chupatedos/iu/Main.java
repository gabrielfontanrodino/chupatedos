package gal.uvigo.esei.aed1.chupatedos.iu;

import gal.uvigo.esei.aed1.chupatedos.core.Game;

public class Main {

    public static void main(String[] args) {
        UI ui = new UI();
        Game chupateDos = new Game(ui);
        chupateDos.play();
    }
}

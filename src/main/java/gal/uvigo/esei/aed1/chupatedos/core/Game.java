package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;

import java.util.ArrayList;


public class Game {

    private ArrayList<Player> players;
    private Table table;
    private DeckOfCards deck;
    private final IU iu;

    public Game(IU iu) {
        this.iu = iu;
        init();
    }

    private void welcomeMessage() {
        iu.displayMessage(" ========= Bienvenido a ChupateDos ========= ");

        //TODO: Add more info if needed
    }

    private void initPlayers() {
        int playerCount = playerCount();
        players = new ArrayList<>(playerCount);
        for (int i = 0; i < playerCount; i++) {
            String nombre = iu.readString(String.format("Nombre del jugador %d : ", i + 1));
            players.add(new Player(nombre));
        }
    }

    private int playerCount() {
        int tempPlayerCount;
        boolean validInput = false;

        do {
            try {
                tempPlayerCount = iu.readNumber("Cuántos jugadores van a jugar? --> ");
                if (tempPlayerCount < 2 || tempPlayerCount > 5) {
                    throw new IllegalArgumentException("Número de jugadores no válido. Debe ser entre 2 y 5.");
                } else {
                    validInput = true;
                }
            } catch (IllegalArgumentException exception) {
                iu.displayError(exception.getMessage());
                tempPlayerCount = -1; // Valor inválido para continuar el bucle
            }
        } while (!validInput);

        return tempPlayerCount;
    }

    private void dealCards() {
        for (Player player : players) {
            for (int j = 0; j < 7; j++) {
                player.addCard(deck.pop());
            }
        }
    }

    private void init() {
        welcomeMessage();
        initPlayers();

        table = new Table();
        deck = new DeckOfCards();

        dealCards();

        table.addToDiscardDeck(deck.pop());

        iu.displayMessage("=============================================");
        iu.displayEmptyLine();
    }

    public boolean hasWon(Player player) {
        return player.getHandSize() == 0;
    }

    /**
     * Metodo principal para jugar
     */
    public void play() {
        iu.displayMessage("Discard: " + table.topCard());
        iu.displayMessage("Discard size: " + table.getDiscardDeckSize());
        iu.displayMessage("Deck size: " + deck.getNumberOfCards());
        iu.displayEmptyLine();
        for (Player player : players) {
            iu.displayMessage(player.toString());
        }
    }
}
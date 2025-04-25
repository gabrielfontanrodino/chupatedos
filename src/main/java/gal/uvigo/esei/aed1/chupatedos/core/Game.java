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
    }

    /**
     * Method to get the number of players for the game
     */
    private void initPlayers() {
        int playerCount = iu.readNumber("Cuántos jugadores van a jugar? --> ", 2, 5);
        players = new ArrayList<>(playerCount);
        for (int i = 0; i < playerCount; i++) {
            String nombre = iu.readString(String.format("Nombre del jugador %d : ", i + 1));
            players.add(new Player(nombre));
        }
    }

    /**
     * Method to deal the cards at the start of the game
     */
    private void dealCards() {
        for (Player player : players) {
            for (int j = 0; j < 7; j++) {
                player.addCard(deck.pop());
            }
        }
    }

    /**
     * Method to refill the deck and shuffle it
     */
    private void refillDeck() {
        iu.displayMessage("Deck vacio, rellenando el deck y barajeando");
        iu.displayWaitingDots();
        Card aux = table.takeTopCard();
        while (table.getDiscardDeckSize() > 0) {
            deck.addCard(table.takeTopCard());
        }
        deck.shuffle();
        table.addToDiscardDeck(aux);
    }

    /**
     * Method to start the game
     */
    private void init() {
        welcomeMessage();
        initPlayers();

        table = new Table();
        deck = new DeckOfCards();

        dealCards();

        table.addToDiscardDeck(deck.pop());

        iu.displaySeparator(72);
        iu.displayEmptyLine();
    }

    public boolean hasWon(Player player) {
        return player.getHandSize() == 0;
    }

    /**
     * Main method to play
     */
    public void play() {

        int currentPlayerIndex = -1;

        // currentDirection: order of play, "1" == Clockwise and "-1" == CounterClockwise
        int currentDirection = 1;

        // justPlayed: On/Off switch to know if the actual card has been just played
        boolean justPlayed = true;

        do {

            // Special interaction with 7

            if (justPlayed == true && table.topCard().getNumber() == 7) {
                iu.displayMessage("Se ha jugado un 7, se invierte la dirección de juego");
                currentDirection *= -1;
            }

            // Updated Player Turn Selector 

            currentPlayerIndex = (currentPlayerIndex + currentDirection); 

            if (currentPlayerIndex >= players.size()) {
                currentPlayerIndex = 0;
            } else if (currentPlayerIndex < 0) {
                currentPlayerIndex = players.size() - 1;
            }

            Player currentPlayer = players.get(currentPlayerIndex);

            // Special interaction with 2

            if (justPlayed == true && table.topCard().getNumber() == 2) {

                iu.displayMessage(String.format(" === Turno de %s === ", currentPlayer.getName()));
                iu.displayMessage("La carta superior es un 2!");
                iu.displayMessage("Robas dos cartas y saltas tu turno");

                iu.displayMessage("----> Carta robada: " + deck.top());
                currentPlayer.addCard(deck.pop());

                if (deck.isDeckEmpty()) {
                    refillDeck();
                }

                iu.displayMessage("----> Carta robada: " + deck.top());
                currentPlayer.addCard(deck.pop());

                if (deck.isDeckEmpty()) {
                    refillDeck();
                }

                iu.displayMessage("Se ha saltado el turno :(");
                justPlayed = false;
                continue;
            }

            justPlayed = false;

            // Start of the current player turn
            iu.displayMessage(String.format(" === Turno de %s === ", currentPlayer.getName()));
            iu.await(1000);
            iu.displayEmptyLine();

            iu.displayMessage(currentPlayer.toString());

            iu.displayMessage("Pila de descartes: " + table.topCard().toString());
            iu.await(1000);
            iu.displayEmptyLine();

            // If the player has no cards to play, he must draw a card from the deck
            // else he must play one of his playable cards
            if (currentPlayer.getPlayeableHandSize(table.topCard()) == 0) {

                iu.displayMessage("No tienes cartas jugables. Robando una");
                iu.displayWaitingDots();
                iu.displayMessage("----> Carta robada: " + deck.top());

                // If the card he must draw can be played, is played instead of being draw into his hand
                if (deck.top().isCompatibleWith(table.topCard())) {
                    table.addToDiscardDeck(deck.pop());
                    iu.displayMessage("La carta robada fue jugada.");
                    justPlayed = true;
                } else {
                    currentPlayer.addCard(deck.pop());
                }

                if (deck.isDeckEmpty()) {
                    refillDeck();
                }

            } else {
                
                iu.displayMessage(currentPlayer.getPlayeableHandString(table.topCard()));
                int max = currentPlayer.getPlayeableHandSize(table.topCard());
                int cardIndex = iu.readNumber("Elige una carta para jugar: ", 1, max);

                table.addToDiscardDeck(currentPlayer.takeXPlayeableCard(cardIndex - 1, table.topCard()));
                iu.displayMessage("Has jugado: " + table.topCard().toString());
                justPlayed = true;
            }
            iu.await(1000);
            iu.displayEmptyLine();
        } while (!hasWon(players.get(currentPlayerIndex)));

        // When the code reaches this point, it means that a player, the one in
        // currentPlayerIndex, has won
        iu.displaySeparator(72);
        iu.displayEmptyLine();
        iu.displayMessage(String.format("Felicidades %s, has ganado!", players.get(currentPlayerIndex).getName()));
        iu.displayEmptyLine();
    }
}
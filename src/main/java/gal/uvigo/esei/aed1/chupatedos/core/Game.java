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
        int playerCount = iu.readNumber("Cuántos jugadores van a jugar? --> ", 2, 5);
        players = new ArrayList<>(playerCount);
        for (int i = 0; i < playerCount; i++) {
            String nombre = iu.readString(String.format("Nombre del jugador %d : ", i + 1));
            players.add(new Player(nombre));
        }
    }

    private void dealCards() {
        for (Player player : players) {
            for (int j = 0; j < 7; j++) {
                player.addCard(deck.pop());
            }
        }
    }

    //TODO: Special interactions with special cards for the first turn of the game
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
    //TODO: Rework for the special cards
    public void play() {
        int currentPlayerIndex = -1;
        do {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); // When the last player plays, the next turn is the first player

            Player currentPlayer = players.get(currentPlayerIndex);

            iu.displayMessage(String.format(" === Turno de %s === ", currentPlayer.getName()));
            iu.await(1000);
            iu.displayEmptyLine();

            iu.displayMessage(currentPlayer.toString());
            
            
            iu.displayMessage("Pila de descartes: " + table.topCard().toString());
            iu.await(1000);
            iu.displayEmptyLine();
            
            //If the player has no cards to play, he must draw a card from the deck
            if(currentPlayer.getPlayeableHandSize(table.topCard()) == 0) {
                iu.displayMessage("No tienes cartas jugables. Robando una");
                for(int i = 0; i < 3; i++){
                    iu.await(1000);
                    iu.displayMessage(".");
                }
                iu.displayMessage("----> Carta robada: " + deck.top());
                if(deck.top().isCompatibleWith(table.topCard())){
                    table.addToDiscardDeck(deck.pop());
                    iu.displayMessage("La carta robada fue jugada.");
                }
                else {
                    currentPlayer.addCard(deck.pop());
                }

                if(deck.getNumberOfCards() == 0) {
                    Card aux = table.takeTopCard();
                    while(table.getDiscardDeckSize() > 0) {
                        deck.addCard(table.takeTopCard());
                    }
                    deck.shuffle();
                    table.addToDiscardDeck(aux);
                }
            }
            else {
                iu.displayMessage(currentPlayer.getPlayeableHandString(table.topCard()));
                int cardIndex = iu.readNumber("Elige una carta para jugar: ", 1, currentPlayer.getPlayeableHandSize(table.topCard()));
                table.addToDiscardDeck(currentPlayer.takeXPlayeableCard(cardIndex - 1, table.topCard()));
                iu.displayMessage("Has jugado: " + table.topCard().toString());
            }
            iu.await(1000);
            iu.displayEmptyLine();
        } while(!hasWon(players.get(currentPlayerIndex)));

        //When the code reaches this point, it means that a player, the one in currentPlayerIndex, has won
        //POV: the code reaches this point. BUT: the player hasn't won. 
        iu.displaySeparator(72);
        iu.displayEmptyLine();
        iu.displayMessage(String.format("Felicidades %s, has ganado!", players.get(currentPlayerIndex).getName()));
        iu.displayEmptyLine();
    }
}
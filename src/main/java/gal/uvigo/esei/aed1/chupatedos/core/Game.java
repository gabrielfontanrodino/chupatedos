package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.ArrayList;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;


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
        int cantidadJugadores = cantidadJugadores();
        players = new ArrayList<>(cantidadJugadores);
        for (int i = 0; i < cantidadJugadores; i++) {
            String nombre = iu.readString(String.format("Nombre del jugador %d :", i + 1));
            players.add(new Player(nombre));
        }
    }

    private int cantidadJugadores() {
        int cantidadJugadores;
        while(true) {
            try {
                cantidadJugadores = iu.readNumber("Cuántos jugadores van a jugar? --> ");
                if(cantidadJugadores < 2 || cantidadJugadores > 5) throw new IllegalArgumentException("El número de jugadores debe estar entre 2 y 5");
                return cantidadJugadores;
            } catch(IllegalArgumentException exception) {
                iu.displayError(exception.getMessage());
            }
        }
    }
    
    private void dealCards() {
        for(int i = 0; i < players.size(); i++) {
            for(int j = 0; j < 7; j++) {
                players.get(i).addCard(deck.pop());
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

    public boolean winCondition(Player player) {
        return player.getHandSize() == 0;
    }

    /**
     * Metodo principal para jugar
     */
    public void play() {
        iu.displayMessage("Discard: "+table.topCard());
        iu.displayMessage("Discard size: "+table.getDiscardDeckSize());
        iu.displayMessage("Deck size: "+ deck.getNumberOfCards());
        for(var player : players) {
            iu.displayMessage(player.toString());
        }
    }
}
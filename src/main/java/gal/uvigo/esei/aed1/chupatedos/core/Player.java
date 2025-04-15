package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.LinkedList;

public class Player {

    private final String name;
    private LinkedList<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card takeCard(int index) {
        return hand.remove(index);
    }

    public Card peekCard(int index) {
        return hand.get(index);
    }

    public int getHandSize() {
        return hand.size();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Mano actual de ").append(this.name);
        sb.append("\n");

        // Mostrar indexación de cartas
        for (int i = 0; i < hand.size(); i++) {
            sb.append("├── ").append(i).append(": ").append(hand.get(i)).append("\n");
        }
        if (!hand.isEmpty()) {
            sb.append("└── Fin de la mano de ").append(this.name).append("\n");
        }

        return sb.toString();
    }
}
package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.LinkedList;

public class Player {

    private final String name;
    private LinkedList<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<>();
    }

    public String getName() {
        return name;
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

    public int getPlayeableHandSize(Card cardToMatch) {
        int count = 0;
        for (Card card : hand) {
            if (card.isCompatibleWith(cardToMatch)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Removes and returns a playable card from the player's hand that matches the specified
     * index among the compatible cards with the given card to match.
     * 
     * @param index The (zero-based) index of the playable card to retrieve among the compatible cards.
     * @param cardToMatch The card to match for compatibility.
     * @return The playable card at the specified index among the compatible cards, or {@code null}
     *         if no such card exists.
     */
    public Card takeXPlayeableCard(int index, Card cardToMatch) {
        int count = 0;
        for (Card card : hand) {
            if (card.isCompatibleWith(cardToMatch)) {
                if (count == index) {
                    return hand.remove(hand.indexOf(card));
                }
                count++;
            }
        }
        return null; // No se encontró la carta jugable en el índice especificado
    }

    public String getPlayeableHandString(Card cardToMatch) {
        StringBuilder sb = new StringBuilder();

        sb.append("Cartas jugables de ").append(this.name);
        sb.append("\n");

        // Mostrar indexación de cartas
        int displayIndex = 1;
        for (Card card : hand) {
            if(cardToMatch.isCompatibleWith(card)){
                sb.append("---> ").append(displayIndex).append(": ").append(card).append("\n");
                displayIndex++;
            }
        }
        if (!hand.isEmpty()) {
            sb.append("------> Fin de las cartas jugables de ").append(this.name).append("\n");
        }

        return sb.toString();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Mano actual de ").append(this.name);
        sb.append("\n");

        // Mostrar indexación de cartas
        for (int i = 0; i < hand.size(); i++) {
            sb.append("---> ").append(i+1).append(": ").append(hand.get(i)).append("\n");
        }
        if (!hand.isEmpty()) {
            sb.append("------> Fin de la mano de ").append(this.name).append("\n");
        }

        return sb.toString();
    }
}
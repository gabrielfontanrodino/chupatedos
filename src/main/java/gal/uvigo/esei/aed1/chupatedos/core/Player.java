package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
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

    /**
     * Returns the number of playable cards in the player's hand that are compatible
     * with the specified card.
     * 
     * @param cardToMatch The card to match for compatibility.
     * @return The number of playable cards in the player's hand that are compatible
     *         with the specified card.
     */
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
     * Removes and returns the playable card from the player's hand at the specified
     * index
     * **among only the cards that are compatible** with the given reference card.
     *
     * A card is considered "playable" if it is compatible with {@code cardToMatch},
     * as determined
     * by the {@code isCompatibleWith} method.
     *
     * @param index       The zero-based index of the desired card within the
     *                    filtered list of compatible cards.
     *                    For example, index {@code 0} retrieves the first
     *                    compatible card found.
     * @param cardToMatch The reference card used to determine compatibility.
     * @return The compatible card at the specified index, removed from the player's
     *         hand,
     *         or {@code null} if no such card exists.
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
        return null; // No compatible card found at the specified index
    }

    /**
     * Returns a string representation of the playable cards in the player's hand
     * that are compatible with the specified card.
     * This string includes the index of each playable card.
     * 
     * @param cardToMatch The card to match for compatibility.
     * @return A string representation of the playable cards in the player's hand.
     *         Each playable card is listed with a new index (not the original one),
     *         and the string ends with a message
     *         indicating the end of the list.
     */
    public String getPlayeableHandString(Card cardToMatch) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Cartas jugables de %s: \n", this.name));

        // Show playable cards with indexation
        int displayIndex = 1;
        for (Card card : hand) {
            if (cardToMatch.isCompatibleWith(card)) {
                sb.append(String.format("----> %d: %s \n", displayIndex, card));
                displayIndex++;
            }
        }
        if (!hand.isEmpty()) {
            sb.append(String.format("------> Fin de las cartas jugables de %s \n", this.name));
        }

        return sb.toString();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Mano actual de ").append(this.name);
        sb.append("\n");

        // Show all cards with indexation
        for (int i = 0; i < hand.size(); i++) {
            sb.append(String.format("----> %d: %s \n", i + 1, hand.get(i)));
        }
        if (!hand.isEmpty()) {
            sb.append(String.format("------> Fin de la mano de %s \n", this.name));
        }

        return sb.toString();
    }
}
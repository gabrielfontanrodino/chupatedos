package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.Stack;

public class Table {
    private Stack<Card> discardDeck;

    public Table() {
        discardDeck = new Stack<>();
    }

    public void addToDiscardDeck(Card card) {
        discardDeck.push(card);
    }

    /**
     * Method to peek the top card from the discard deck
     * @throws IllegalStateException when the deck to peek is empty
     */
    public Card topCard() throws IllegalStateException {
        checkDeckState();
        return discardDeck.peek();
    }

    /**
     * Method to take the top card from the discard deck
     * @throws IllegalStateException when the deck to pop is empty
     */
    public Card takeTopCard() throws IllegalStateException {
        checkDeckState();
        return discardDeck.pop();
    }

    /**
     * Method to check if the discard deck is empty and throw an exception when it is empty
     * @throws IllegalStateException when the deck to access is empty
     */
    private void checkDeckState() throws IllegalStateException {
        if (discardDeck.isEmpty()) {
            throw new IllegalStateException("Discard deck is empty");
        }
    }

    /**
     * Method to see the number of cards from the discard deck
     * @return discard deck size
     */
    public int getDiscardDeckSize() {
        return discardDeck.size();
    }
}
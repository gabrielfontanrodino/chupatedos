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

    public Card topCard() {
        checkDeckState();
        return discardDeck.peek();
    }

    public Card takeBottomCard() {
        checkDeckState();
        return discardDeck.pop(); // removeLast() is not a method of Stack, use pop() instead
    }

    private void checkDeckState() {
        if (discardDeck.isEmpty()) {
            throw new IllegalStateException("Discard deck is empty");
        }
    }

    public int getDiscardDeckSize() {
        return discardDeck.size();
    }
}
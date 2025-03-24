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
        if (discardDeck.isEmpty()) {
            throw new IllegalStateException("Discard deck is empty");
        }
        return discardDeck.peek();
    }

    public Card takeBottomCard() {
        if (discardDeck.isEmpty()) {
            throw new IllegalStateException("Discard deck is empty");
        }
        return discardDeck.removeLast();
    }

    public int getDiscardDeckSize() {
        return discardDeck.size();
    }
}
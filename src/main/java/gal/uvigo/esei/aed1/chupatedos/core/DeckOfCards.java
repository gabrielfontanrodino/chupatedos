package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class DeckOfCards {
    private List<Card> cards;
    
    public DeckOfCards(){
        cards = new ArrayList<>();
        restart(); // We use "restart" for avoiding code duplication
    }

    /**
     * Method to refill the deck and shuffle its elements
     */
    public void restart() {
        cards.clear();
        Collections.addAll(cards, Card.values());
        this.shuffle();
    }

    /**
     * Method to shuffle the deck
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Method to add a card
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Method to take the top card from the deck
     * @throws NoSuchElementException when the deck is empty
     */
    public Card pop() {
        if(cards.isEmpty()) throw new NoSuchElementException("The deck of cards is empty");
        return cards.removeLast();
    }

    /**
     * Method to see the top card of the deck
     * @throws NoSuchElementException when the deck is empty
     */
    public Card top() {
        if(cards.isEmpty()) throw new NoSuchElementException("The deck of cards is empty");
        return cards.getLast();
    }

    /**
     * Method to give the number of cards
     * @return deck actual size
     */
    public int getNumberOfCards() {
        return cards.size();
    }

    /**
     * Method to test if the deck is empty
     * @return 'true' if the deck is empty, 'false' otherwise
     */
    public boolean isDeckEmpty() {
        return cards.size() == 0;
    }
    
}
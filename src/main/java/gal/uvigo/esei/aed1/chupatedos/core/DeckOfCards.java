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

    public void restart() {
        cards.clear();
        Collections.addAll(cards, Card.values());
        this.shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card pop() {
        if(cards.isEmpty()) throw new NoSuchElementException("The deck of cards is empty");
        return cards.removeLast();
    }

    public Card top() {
        if(cards.isEmpty()) throw new NoSuchElementException("The deck of cards is empty");
        return cards.getLast();
    }

    public int getNumberOfCards() {
        return cards.size();
    }
    
}
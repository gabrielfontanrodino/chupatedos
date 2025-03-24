package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

import gal.uvigo.esei.aed1.chupatedos.core.Card;

public class DeckOfCards {
    private ArrayList<Card> cards = new ArrayList<Card>();
    
    public DeckOfCards(){
        restart();
    }

    public void restart() {
        cards.clear();
        for(var card : Card.values()){
            cards.add(card);
        }
        Collections.shuffle(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card pop() {
        if(cards.isEmpty()) throw new NoSuchElementException("The list is empty");
        return cards.removeLast();
    }

    public int getNumberOfCards() {
        return cards.size();
    }
    
}
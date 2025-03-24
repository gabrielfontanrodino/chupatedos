package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.LinkedList;

public class Player {

    private String name;
    private LinkedList<Card> hand = new LinkedList<Card>();

    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<Card>();
    }

    public void addCard(Card card){
        hand.add(card);
    }

    public Card takeCard(int index){
        return hand.remove(index);
    }
    
    public Card peekCard(int index ){
        return hand.get(index);
    }

    public int getHandSize(){
        return hand.size();
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();

        sb.append("Mano actual de ").append(this.name);
        sb.append("\n");

        //TODO: Mostrar indexación de cartas
        for(int i = 0; i < hand.size(); i++){
            sb.append(hand.get(i)).append(" | ");
        }
        
        return sb.toString();
    }
}
package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }
    
    public void printCard() {
    	System.out.print(name+"카드:");
    	for(Card card:cards) {
    		card.printCard();
    	}
    	System.out.println();
    }
    
    public String getName() {
    	return this.name;
    }
    
    public List<Card> getCards(){
    	return this.cards;
    }
    
    
    
    
    
    
}

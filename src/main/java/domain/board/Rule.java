package domain.board;
import java.util.List;

import domain.board.*;
import domain.card.*;
import domain.user.*;

/*player, dealer를 받아 그들의 카드를 판단한다.
 * ACE면 1 또는 10으로 계산
 * King, Queen, Jack 은 10으로 계산 
 * */

public class Rule {
	private static final int BLACKJACK_SCORE = 21;
	private static final int DEALER_LIMIT_SCORE =16;
	//enum mates {JACK,QUEEN,KING};
	
	public void isPlayerBlackjack(Player player) {
		
	}
	
	public void isDealerBlackjack(Player player) {
		
	}
	
	
	public int getPlayerScore(Player player) {
		List<Card> cards = player.getCards();
		int score=0;
		for(Card card:cards) {
			score = score+ card.getScore();
		}
		return score;
	}
	
	public int getDealerScore(Dealer dealer) {
		List<Card> cards = dealer.getCards();
		int score=0;
		for(Card card:cards) {
			score = score+ card.getScore();
		}
		return score;
	}
	
	public boolean dealerCanGetCard(Dealer dealer) {
		int score = getDealerScore(dealer);
		if(score<=DEALER_LIMIT_SCORE) {
			return true;
		}
		return false;
	}
	
	
	
	
}

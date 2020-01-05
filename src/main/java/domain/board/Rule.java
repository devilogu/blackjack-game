package domain.board;
import java.util.ArrayList;
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
	private static final double BLACKJACK_BONUS = 1.5;
	
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
	
	public boolean isPlayerBlackjack(Player player) {
		if(getPlayerScore(player)==BLACKJACK_SCORE) {
			return true;
		}
		return false;
	}
	
	public boolean isDealerBlackjack(Dealer dealer) {
		if(getDealerScore(dealer)==BLACKJACK_SCORE) {
			return true;
		}
		return false;
	}

	/* 플레이어가 블랙잭 - 1.5배 딜러에게 받는다.
	 * 플레이어, 딜러 동시에 블랙잭  - 플레이어는 베팅액 그대로. 아닌 플레이어는 딜러에게 뺏김.
	 * 딜러 21 초과 - 베팅액 돌려받는다.
	 * */
	public double getBettingResult(Dealer dealer, List<Player> players) {
		double dealerMoney=0,playerMoney=0;
		List<Player> blackjackPlayers = new ArrayList<Player>();		
		if(isDealerBlackjack(dealer)){
			for(int i=0 ; i<players.size();i++) {
				if(!isPlayerBlackjack(players.get(i))) {
					dealerMoney +=players.get(i).getBettingMoney();
					players.get(i).setBettingMoney(0);
				}
			}//블랙잭인 플레이어 - 그대로, 아닌플레이어 - 0으로 셋팅됨
		}
		if(!isDealerBlackjack(dealer)){
			for(int i=0 ; i<players.size();i++) {
				if(isPlayerBlackjack(players.get(i))) {
					//dealerBettingMoney =players.get(i).getBettingMoney();
					playerMoney = players.get(i).getBettingMoney()*BLACKJACK_BONUS;
					players.get(i).setBettingMoney(playerMoney);
				}
				if(!isPlayerBlackjack(players.get(i))) {
					players.get(i).setBettingMoney(0);
				}
			}
		}
		if(getDealerScore(dealer)>BLACKJACK_SCORE) {
			
		}
		return dealerMoney;
	}
	
}

package domain.board;

import domain.card.*;
import domain.user.*;
import java.util.*;

public class Blackjack {
	Scanner sc;
	List<Card> cards;
	List<Player> players;
	Dealer dealer;
	Rule rule;
	
	public Blackjack(){
		init();
	}
	
	public void init() {
		sc = new Scanner(System.in);
		cards = CardFactory.create();
		dealer = new Dealer();
		rule = new Rule();
		rule = new Rule();
		createPlayers();
	}
	
	public void createPlayers() {
		String names[];
		Map<String,String> users;
		names = getNameInput();
		users = setBettingMoney(names);
		createPlayer(users);
	}
	
	public String[] getNameInput() {
		String names[];
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		names = sc.next().split(",");
		return names;
	}
	
	public Map<String,String> setBettingMoney(String[] names) {
		Map<String,String> users =  new HashMap<String,String>();
		String bettingMoney;
		for(int i=0 ; i<names.length;i++) {
			System.out.println(names[i]+"의 배팅 금액은?");
			bettingMoney = sc.next();
			users.put(names[i], bettingMoney);
		}
		System.out.println();
		return users;
	}
	
	public void createPlayer(Map<String,String> users) {
		players = new ArrayList<Player>();
		for(Map.Entry<String, String> user : users.entrySet() ) {
			Player player = new Player(user.getKey(),Double.parseDouble(user.getValue()));
			players.add(player);
		}
	}
	/* 딜러, 플레이어에게 카드 두장 주기. 
	 * cards에서 카드 한장 지우고 그 카드를 유저에게 주는 giveCardTo 함수 2번호출
	 * */
	public void giveTwoCardToAll() {
		giveCardToDealer(dealer);
		giveCardToDealer(dealer);
		for(Player player : players) {
			giveCardToPlayer(player);
			giveCardToPlayer(player);
		}
	}
	
	public void giveCardToPlayer(Player player) {
		int random = (int)Math.random()*cards.size();
		Card card = cards.remove(random);
		player.addCard(card);
		
	}
	
	public void giveCardToDealer(Dealer dealer) {
		int random = (int)(Math.random()*(cards.size()-1));
		Card card = cards.remove(random);
		dealer.addCard(card);
	}
	
	public void printNoticeTwoCard() {
		System.out.print("딜러와 ");
		for(int i=0 ; i<players.size(); i++) {
			System.out.print(players.get(i).getName());
			if(i!=players.size()-1) {
				System.out.print(",");
			}
		}
		System.out.println("에게 2장을 나누었습니다.");
	}
	
	public void printAllPlayerCard() {
		dealer.printCard();
		for(Player player:players) {
			printOnePlayerCard(player);
		}
		System.out.println();
	}
	
	public void printAllPlayerCardwithScore() {
		dealer.printCard();
		System.out.println("딜러결과:"+rule.getDealerScore(dealer));
		for(Player player:players) {
			printOnePlayerCard(player);
			System.out.println(player.getName()+"결과:"+rule.getPlayerScore(player));
		}
		System.out.println();
	}
	
	public void printOnePlayerCard(Player player) {
		player.printCard();
	}
	
	//카드를 더 받는 메서드가 Player클래스에 없는 이유는 cards에서 card를  remove하려면 
	//cards가 선언된 Board 클래스에서 이루어져야 한다.
	public Player askCard(Player player) {
    	String ask="y";
    	Scanner sc = new Scanner(System.in);
    	while(true){
    		System.out.println(player.getName()+"는 한장의 카드를 더 받겠습니까?");
    		ask = sc.next();
    		if(ask.equals("n")) {
    			printOnePlayerCard(player);
    			break;
    		}
    		giveCardToPlayer(player);
    		printOnePlayerCard(player);
    	}
    	return player;
    }
	
	void decideDealerGettingCard(Dealer dealer) {
		if(rule.dealerCanGetCard(dealer)) {
			System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
			giveCardToDealer(dealer);
		}
	}
	
	public void printBettingResult() {
		double dealerMoney = rule.getBettingResult(dealer, players);
		System.out.println("##최종 수익");
		System.out.println("딜러: "+dealerMoney);
		for(Player player:players) {
			System.out.println(player.getName()+": "+player.getBettingMoney());
		}
	}
	
	public void game() {
		printNoticeTwoCard();
		giveTwoCardToAll();
		printAllPlayerCard();
		for(Player player:players){
			player = askCard(player);	
		}
		decideDealerGettingCard(dealer);
		printAllPlayerCardwithScore();
		printBettingResult();
		sc.close();
	}
	
	
	
	
	

}

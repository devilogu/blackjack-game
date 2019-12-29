package domain.board;

import domain.card.*;
import domain.user.*;
import java.util.*;

public class Board {
	Scanner sc;
	List<Card> cards;
	List<Player> players;
	Dealer dealer;
	
	public Board(){
		init();
	}
	
	public void init() {
		sc = new Scanner(System.in);
		cards = CardFactory.create();
		createPlayers();
	}
	
	public void createPlayers() {
		String names[];
		Map<String,String> users;
		names = getNameInput();
		users = createUsers(names);
		createPlayer(users);
	}
	
	private String[] getNameInput() {
		String names[];
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		names = sc.next().split(",");
		return names;
	}
	
	private Map<String,String> createUsers(String[] names) {
		Map<String,String> users =  new HashMap<String,String>();
		String bettingMoney;
		for(int i=0 ; i<names.length;i++) {
			System.out.println(names[i]+"의 배팅 금액은?");
			bettingMoney = sc.next();
			users.put(names[i], bettingMoney);
		}
		return users;
	}
	
	private void createPlayer(Map<String,String> users) {
		players = new ArrayList<Player>();
		for(Map.Entry<String, String> user : users.entrySet() ) {
			Player player = new Player(user.getKey(),Double.parseDouble(user.getValue()));
			players.add(player);
		}
	}
	
}

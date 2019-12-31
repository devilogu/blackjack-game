package domain.card;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {
    @Test
    void create() {
        List<Card> cards = CardFactory.create();
        assertThat(cards).hasSize(52);
        System.out.println(cards);
  
        System.out.print("\n 0번째 지운 후 0번째 요소 :");
		cards.get(0).printCard();

    }
}

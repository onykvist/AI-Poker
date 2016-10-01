package poker_joko;

import java.util.Random;

public class Deck {
	
	int[] cards;
	boolean[] drawn;
	
	public Deck(){
		
		cards = new int[52];
		drawn = new boolean[52];
		
		for(int i = 0; i < 13 ; i ++){
			cards[i     ] = 101 + i;
			cards[i + 13] = 201 + i;
			cards[i + 26] = 301 + i;
			cards[i + 39] = 401 + i;
			
			drawn[i     ] = false;
			drawn[i + 13] = false;
			drawn[i + 26] = false;
			drawn[i + 39] = false;
		}
	}
	
	public int drawCard(){
		while( true ){
			Random rand = new Random();
			int randomCardIndex = rand.nextInt(52);
			
			if( !drawn[ randomCardIndex ] ){
				drawn[ randomCardIndex ] = true;
				return cards[ randomCardIndex  ];
			}
		}
	    
	}
	
}

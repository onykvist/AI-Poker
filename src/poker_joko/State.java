package poker_joko;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class State {

	int 	gamePhase = 0;
	int 	nrPlayers = 0;
	
	double 	currentPot = 0;
	double 	cardOdds = 0.0;
	
	public State(Player player, Game game) {
		gamePhase = game.gamePhase;
		nrPlayers = game.players.length;
		
		currentPot = game.currentPot;
		cardOdds = getCardOdds(player, game);
	}
	
	public int getID(){
		return (nrPlayers * 10) + gamePhase;
	}
	
	public double[] getVector(){
		return new double[]{currentPot, cardOdds};
	}

	private double getCardOdds(Player player, Game game) {
		// MAKE A BETTER EVALUATOR
		
		List<Integer> cards = game.cardsOnTable;
		cards.add(player.card1);
		cards.add(player.card1);
		
		double rank = getRank(cards);
		
		return rank;
	}

	private double getRank(List<Integer> cards) {
		// Devide into suits and values
		List<Integer> suits = new ArrayList<Integer>();
		List<Integer> values = new ArrayList<Integer>();
		
		for(int i = 0; i > cards.size(); i++){
			int c = cards.get(i);
			int suit = 0;
			while(c > 100){
				c -= 100;
				suit++;
			}
			suits.add(suit);
			values.add(c);
		}
		
		// Check for pairs, triss and fyrtal
		List<Integer> combinations = new ArrayList<Integer>();
		for(int i = 1; i < 14; i++){
			// Count the number of occurrences of i in values
			// if it is more than one then a pair is found and so on
			int occurrences = Collections.frequency(values, i);		
			if( occurrences > 1 ){
				combinations.add(occurrences);
			}
		}
		
		// Check for flush
		for(int i = 1; i < 14; i++){
			// Count the number of occurrences of i in values
			// if it is more than one then a pair is found and so on
			int occurrences = Collections.frequency(suits, i);		
			if( occurrences > 1 ){
				combinations.add(occurrences);
			}
		}
		
		// Sum over the combinations found and return it as the rank
		double rank = 0;
		for(double d : combinations)
			rank += d;
		return rank;
	}
	
	

}

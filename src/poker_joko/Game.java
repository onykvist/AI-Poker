package poker_joko;

import java.util.LinkedList;
import java.util.List;

public class Game {
	
	Player[] players;
	int smallBlind;
	int bigBlind;
	int currentPot;
	int maxStake;
	Deck deck;
	
	
	public Game(int nrPlayers, int[] playerCash, int smallBlind_, int bigBlind_) {
		smallBlind = smallBlind_;
		bigBlind = bigBlind_;
		currentPot = 0;
		maxStake = 0;
		
		players = new Player[nrPlayers];
		// Create the players
		for( int i = 0; i < playerCash.length; i++){
			players[i] = new Player(this, i, playerCash[i]);
		}
		
		deck = new Deck();
	}
	
	public void newHand(){
		// Give cards to the players
		giveCards();
		
		players[0].bet(smallBlind);
		players[1].bet(bigBlind);
		
		resetMoves();
		// Update current pot
		maxStake = getMaxStake();
		
		currentPot = smallBlind + bigBlind;
		
		getMovs(2);
		
		//Flopp
		System.out.println("FLOPP");
		resetMoves();
		getMovs(0);
		//Turn
		System.out.println("TURN");
		resetMoves();
		getMovs(0);
		//River
		System.out.println("RIVER");
		resetMoves();
		getMovs(0);
	}

	private void giveCards() {
		for(Player player: players){
			player.card1 = deck.drawCard();
			player.card2 = deck.drawCard();
		}
	}

	private void resetMoves() {
		for( int i = 0; i < players.length; i ++){
			if(!players[i].isAllIn || !players[i].folded)
			players[i].madeAMove = false;
		}
		
	}

	private void getMovs(int startIndex) {
		int currentPlayer = startIndex;
		
		while( true ){
			
			// Let current player make a move if he has not folded or is already all in
			if( !players[currentPlayer].folded && !players[currentPlayer].isAllIn){
				players[currentPlayer].makeMove();
			}
			
			// Update current pot
			maxStake = getMaxStake();
			
			// Move pointer to next player
			currentPlayer ++;			
			if( currentPlayer >= players.length ){
				currentPlayer = 0;
			}
			
			// Stop argument
			if( ifAllHappy() ){
				break;
			}
		}
	}

	private int getMaxStake() {
		int ms = 0;
		for( int i = 0; i < players.length; i ++){
			ms = (ms < players[i].currentPot) ? players[i].currentPot : ms;
		}
		return ms;
	}

	private boolean ifAllHappy() {
		for( int i = 0; i < players.length; i ++){
			if(!players[i].madeAMove && !players[i].isAllIn && !players[i].folded ){
				return false;
			}
		}		
		
		
		for( int i = 0; i < players.length; i ++){
			if( players[i].currentPot < maxStake && !players[i].isAllIn && !players[i].folded){
				return false;
			}
		}
		
		return true;
	}

}










































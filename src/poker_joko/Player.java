package poker_joko;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Player {

	int cash;
	boolean folded = false;
	boolean isAllIn = false;
	boolean dealer = false;
	boolean smallBlind = false;
	boolean bigBlind = false;
	boolean madeAMove = false;
	
	int playerIndex;
	int currentPot = 0;
	int potIndex = 0;
	
	Game game;
	public int card1;
	public int card2;
	
	boolean isAI = false;
	AIBrain brain;
	
	// Initialization of a human player 
	public Player(Game game_, int playerIndex_, int cash_) {
		playerIndex = playerIndex_;
		cash = cash_;
		game = game_;
	}
	
	// Initialization of an AI-player 
	public Player(Game game_, int playerIndex_, int cash_, String fileName) {
		playerIndex = playerIndex_;
		cash = cash_;
		game = game_;
		isAI = true;
		
		URL test = getClass().getResource(fileName);
		File dataFile = new File(test.getFile().replace("%20", " "));
		
		brain = new AIBrain(dataFile);
		
	}

	public void bet(int betAmount) {
		if( betAmount >= cash ){
			betAmount = cash;
			isAllIn = true;
		}
		madeAMove = true;
		currentPot += betAmount;
		cash -= betAmount;
		System.out.println(String.format("Player %d raises %d \t he now has %d cash and %d in the current pot", playerIndex, betAmount, cash, currentPot ));
	}

	public void fold() {
		System.out.println(String.format("Player %d folded", playerIndex));
		madeAMove = true;
		folded = true;
	}

	public void makeMove() {
		if( isAI ){
			brain.makeMove(this, game);
		}
		else{
			int toBet = game.maxStake - currentPot;
			optionFrame oFrame = new optionFrame(this, playerIndex, cash, toBet, card1, card2);
			oFrame.setVisible(true);
			
			synchronized(oFrame){
	            try{
	            	oFrame.wait();
	            }catch(InterruptedException e){
	                e.printStackTrace();
	            } 
	        }
		}
	}

}


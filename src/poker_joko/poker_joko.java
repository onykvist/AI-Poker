package poker_joko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class poker_joko {

	public static void main(String[] args) {
		//int nrPlayers = getInput("Set number of players: ");
		int nrPlayers = 4;
		
		int[] playerCash = new int[nrPlayers];
		
		for(int i = 0; i < playerCash.length ; i++){
		//	playerCash[i] = getInput(String.format("Set cash for player %d: ", i));
			playerCash[i] = 100;
		}
		
		//hej Olle
		
		int smallBlind = 1;//getInput("Set small blind: ");
		int bigBlind = 2;//getInput("Set big blind: ");
		
		Game game = new Game(nrPlayers, playerCash, smallBlind, bigBlind);
		game.newHand();
		
	}
	
	
	private static int getInput(String msg){		
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        System.out.print( msg );
	        while(true){
	        	try{
		            int i = Integer.parseInt(br.readLine());
		            return i;
		        }catch(NumberFormatException nfe){
		            System.err.println("Invalid Format!");
		        } catch (IOException e) {
		        	System.err.println("Invalid Format!");
				}
	        }
	    }
}


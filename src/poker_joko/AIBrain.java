package poker_joko;

import java.io.File;

public class AIBrain {
	State[] states;
	double[][] weights;
	double maxD = 3.0;
	
	public AIBrain(File dataFile) {
		
	}
	
	private double getStateDistance(State s1, State s2){
		if(s1.getID() == s2.getID()){
			return 100000;
		}
		double dist = 0;
		
		double[] s1v = s1.getVector();
		double[] s2v = s2.getVector();
		
		double sum = 0;
		for(int i = 0; i < s1v.length; i++){
			sum += s1v[i] *s2v[i];
		}
		
		dist = Math.sqrt(sum);
		
		return dist;
		
	}

	public void makeMove(Player player, Game game) {
		State currentState = new State(player, game);
		
		if(states.length > 1){
			
			// Find the state most like this one
			double minDist = 100000;
			int minDistIndex = -1;
			for(int i = 0; i < states.length; i++){
				double distance = getStateDistance(currentState, states[i]);
				if(distance < maxD && distance < minDist){
					minDist = distance;
					minDistIndex = i;
				}
				
				// Have we experienced this before?
				if(minDistIndex > -1 ){
					//...yes, we have
					double[] w = weights[minDistIndex];
					makeTheMove(w);
				}
				else{
					//..no, we have not
					
				}
				
			}
		}
		
		
	}

	private void makeTheMove(double[] w) {
				
	}

}

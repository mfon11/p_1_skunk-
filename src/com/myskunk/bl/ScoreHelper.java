package com.myskunk.bl;

import java.util.List;
import java.util.Map;

public class ScoreHelper {
	
	private int total, s, k, u, n, K, numberOfRollsTaken, isItAHundredYet;
	
	public ScoreHelper() {
		this.total = 0;
		this.s = 0;
		this.k = 0;
		this.u = 0;
		this.n = 0;
		this.K = 0;
		this.isItAHundredYet = 0;
		this.numberOfRollsTaken = 0;
	}
	
	public void finalScore(PointsAccumulated rollsMade) {
		for (Map.Entry<Character, List<List<Integer>>> e : rollsMade.getAllRolledResults().entrySet()) {			
			for (int roll = 0; roll < e.getValue().size(); roll++) {
				if (e.getValue().get(roll).get(3) == 2) {
					if (e.getKey() == 's') {
						s = 0;
				  	}
			  		else if (e.getKey() == 'k') {
			  			s = 0;
			  			k = 0;
				  	}
			  		else if (e.getKey() == 'u') {
			  			s = 0;
			  			k = 0;
			  			u = 0;
				  	}
			  		else if (e.getKey() == 'n') {
			  			s = 0;
			  			k = 0;
			  			u = 0;
			  			n = 0;
				  	}
			  		else if (e.getKey() == 'K') {
			  			s = 0;
			  			k = 0;
			  			u = 0;
			  			n = 0;
			  			K = 0;
				  	}
				}
				else if (e.getValue().get(roll).get(1) == 1 || e.getValue().get(roll).get(2) == 1) {
					if (e.getKey() == 's') {
						s = 0;
					}
					else if (e.getKey() == 'k') {
						k = 0;
					}
					else if (e.getKey() == 'u') {
						u = 0;
					}
					else if (e.getKey() == 'n') {
						n = 0;
					}
					else if (e.getKey() == 'K') {
						K = 0;
					}
				}
				else {
					if (e.getKey() == 's') {
						s += e.getValue().get(roll).get(3);
						counterHelperForAHundred(e, roll);
					}
			  		else if (e.getKey() == 'k') {
			  			k += e.getValue().get(roll).get(3);
			  			counterHelperForAHundred(e, roll);
			  		}
			  		else if (e.getKey() == 'u') {
			  			u += e.getValue().get(roll).get(3);
			  			counterHelperForAHundred(e, roll);
			  		}
			  		else if (e.getKey() == 'n') {
			  			n += e.getValue().get(roll).get(3);
			  			counterHelperForAHundred(e, roll);
			  		}
			 		else if (e.getKey() == 'K') {
			  			K += e.getValue().get(roll).get(3);
			  			counterHelperForAHundred(e, roll);
			 		}
				}
			}
		}
	}
	
	public int totalScoreHelper(){
		total += s + k + u + n + K;
		return total;
	}
	
	private void counterHelperForAHundred(Map.Entry<Character, List<List<Integer>>> e, int roll) {
		if (isItAHundredYet <= 99) {
			quickestHundredHelper(e.getValue().get(roll).get(3));
		}
	}
	
	private void quickestHundredHelper(int addTillAHundred) {
		isItAHundredYet += addTillAHundred;
		if (isItAHundredYet <= 99) {
			numberOfRollsTaken++;
		}
	}
	
	public int playerScore(PointsAccumulated rollsMade) {
		resetNumbers();
		finalScore(rollsMade);
		return numberOfRollsTillAHundred();
	}
	
	private void resetNumbers() {
		this.total = 0;
		this.s = 0;
		this.k = 0;
		this.u = 0;
		this.n = 0;
		this.K = 0;
		this.isItAHundredYet = 0;
		this.numberOfRollsTaken = 0;
	}
	
	public boolean madeItToAHundred() {
		return isItAHundredYet > 99;
	}
	
	public int numberOfRollsTillAHundred() {
		return numberOfRollsTaken;
	}
}

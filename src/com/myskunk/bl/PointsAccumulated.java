package com.myskunk.bl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PointsAccumulated {
	
	private HashMap<Character, List<List<Integer>>> allRollResults;
	
	public PointsAccumulated () {
		this.allRollResults = new HashMap<Character, List<List<Integer>>>();
		
	}
	
	public void addResults(char skunkLetter, Dice rolledDice, int count) {
		if (skunkLetter == 's') {
			addRollHelper('s', rolledDice, count);
		}
		else if (skunkLetter == 'u') {
			addRollHelper('u', rolledDice, count);
		}
		else if (skunkLetter == 'n') {
			addRollHelper('n', rolledDice, count);
		}
		else if (skunkLetter == 'k') {		// lower case k stands for the first k in skunk
			addRollHelper('k', rolledDice, count);
		}
		else if (skunkLetter == 'K') { 		// Upper case K stands for the last k in skunk
			addRollHelper('K', rolledDice, count);
		}
	}
	
	private void addRollHelper(char skunkLetter, Dice currentRoll, int currentTurn) {
		List<Integer> newRoll = new ArrayList<>();
		List<List<Integer>> newRoll2 = new ArrayList<>();
		newRoll.add(currentTurn);
		newRoll.add(currentRoll.getDie1().getLastRoll());
		newRoll.add(currentRoll.getDie2().getLastRoll());
		newRoll.add(currentRoll.getLastRoll());
		newRoll2.add(newRoll);
		
		if (getAllRolledResults().get(skunkLetter) != null) {
			List<List<Integer>> valueAtSkunk = new ArrayList<List<Integer>>(); 
			
			valueAtSkunk.addAll(getAllRolledResults().get(skunkLetter));
			valueAtSkunk.add(newRoll);
			allRollResults.replace(skunkLetter, valueAtSkunk);
		}
		else {
			allRollResults.put(skunkLetter, newRoll2);
		}
	}
	
	public HashMap<Character, List<List<Integer>>> getAllRolledResults() {
		return allRollResults;
	}
	
	public boolean IsHashMapEmpty() {
		return allRollResults.isEmpty();
	}
	
	public void noRollMade(char skunkLetter, int count) {
		List<Integer> newRoll = new ArrayList<>();
		List<List<Integer>> newRoll2 = new ArrayList<>();
		newRoll.add(count);
		newRoll.add(0);
		newRoll.add(0);
		newRoll.add(0);
		newRoll2.add(newRoll);
		
		if (getAllRolledResults().get(skunkLetter) == null) {
			allRollResults.put(skunkLetter, newRoll2);
		}
	}
}

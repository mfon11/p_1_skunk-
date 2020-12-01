package com.myskunk.bl;

public class DiceCheckerHelper {
	
	public DiceCheckerHelper() {
		
	}

	public String diceChecker(Dice dice) {
		if (dice.getDie1().getLastRoll() == 1 && dice.getDie2().getLastRoll() == 1) {
			return "double";
		}
		else if (dice.getDie1().getLastRoll() == 1 || dice.getDie2().getLastRoll() == 1) {
			return "single";
		}
		return "goodRoll";
	}
}

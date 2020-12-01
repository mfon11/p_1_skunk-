package com.myskunk.bl;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.introcs.StdOut;

public class Turn {
	private int count; // how many rolls?
	private Player playerName; 
	private Dice dice;
	private ScoreHelper scoring;
	private char[] skunkLevel = {'s', 'k', 'u', 'n', 'K'};
    private int index; 
    private boolean runLoop;
    private PointsAccumulated rollsMade;
	 
	public Turn(String name, int numberOfPlayers) {
		this.rollsMade = new PointsAccumulated();
		this.playerName = new Player(name);
		this.dice = new Dice();
		this.scoring = new ScoreHelper();
		this.count = 0;
		this.index = 0;
	}
	
	public void addCurrentRoll(char skunkLetter, Dice rolledDice) {
		count++;
		rollsMade.addResults(skunkLetter, rolledDice, count);
	}
	
	public boolean getRunLoop() {
		return runLoop;
	}
	
	public void setIndex() {
		this.index = 0;
	}
	
	public void play() 
	{
		try {
			myRoll();
		}catch (ArrayIndexOutOfBoundsException e) {
			StdOut.println("ArrayIndexOutOfBoundsException. 1");
		}catch (IndexOutOfBoundsException  e) {
			StdOut.println("IndexOutOfBoundsException.");
		}
	}
	
	public boolean dontRoll() {
		if (index > 4) {
			summaryReport();
			StdOut.println("Game over!");
			return false;
		}
		else {
			count++;
			rollsMade.noRollMade(skunkLevel[index], count);
			index++;
			StdOut.println("Curent game phase is " + skunkLevel[index]);
			play(); 
			return true;
		}
	}
	
	private void diceCheck(Dice dice) {	//after running this there must be a check to see if loop is supposed to stop
		DiceCheckerHelper check = new DiceCheckerHelper();
		if (check.diceChecker(dice).equals("goodRoll")) {
			try {
				addCurrentRoll(skunkLevel[index], dice);
			} catch (ArrayIndexOutOfBoundsException e) {
				StdOut.println("ArrayIndexOutOfBoundsException. 2");
			} catch (IndexOutOfBoundsException  e) {
				StdOut.println("IndexOutOfBoundsException.");
			}
		}
		else if (check.diceChecker(dice).equals("single")) {
			try {
				addCurrentRoll(skunkLevel[index], dice);
				
				StdOut.println("A single was rolled.");
				
				if (index < 4) {
					index++;
					StdOut.println("Current game phase is " + skunkLevel[index]);
					if (skunkLevel[index] != 'K') {
						play();
					}
					else {
						runLoop = false;
					}
				}
				else {
					runLoop = false;
				}
			}catch (ArrayIndexOutOfBoundsException e) {
				StdOut.println("ArrayIndexOutOfBoundsException. 3");
			}catch (IndexOutOfBoundsException  e) {
				StdOut.println("IndexOutOfBoundsException.");
			}
		}
		else if (check.diceChecker(dice).equals("double")) {
			try {
				addCurrentRoll(skunkLevel[index], dice);
				
				StdOut.println("A double was rolled.");
								
				if (index < 4) {
					index++;
					StdOut.println("Current game phase is " + skunkLevel[index]);
					if (skunkLevel[index] != 'K') {
						play();
					}
					else {
						runLoop = false;
					}
				}
				else {
					runLoop = false;
				}
			}catch (ArrayIndexOutOfBoundsException e) {
				StdOut.println("ArrayIndexOutOfBoundsException. 4");
			}catch (IndexOutOfBoundsException  e) {
				StdOut.println("IndexOutOfBoundsException.");
			}
		}
	}
	
	private void myRoll() {
		dice.roll();
		
		diceCheck(dice);
		
		StdOut.println(rollReport(dice));
	}
	
	public void totalScore() {
		List<Character> letters = new ArrayList<>();
		letters.add('s');
		letters.add('k');
		letters.add('u');
		letters.add('n');
		letters.add('K');
		printRolls(letters);
	}
	
	private	void printRolls(List<Character> letters) {
		String message = "";
		
		for(char letter : letters) {
			List<List<Integer>> valueAtSkunk = rollsMade.getAllRolledResults().get(letter);
			message += letter;
			
			for (List<Integer> roll: valueAtSkunk) {
				if (roll.get(1) == 1 && roll.get(2) == 1) { 
					message += "\t" + roll.get(0) + "\t\t" + roll.get(1) + "\t" + roll.get(2) + "\t" + 
							"Double" + "\t\t" + roll.get(3) + "\n";
				}
				else if (roll.get(1) == 1 || roll.get(2) == 1) {
					message += "\t" + roll.get(0) + "\t\t" + roll.get(1) + "\t" + roll.get(2) + "\t" + 
							"Single" + "\t\t" + roll.get(3) + "\n";
				}
				else {
					message += "\t" + roll.get(0) + "\t\t" + roll.get(1) + "\t" + roll.get(2) + "\t" + 
							"NA" + "\t\t" + roll.get(3) + "\n";
				}
			}
		}
		StdOut.println(message);
		scoring.finalScore(rollsMade);
		StdOut.println("Final Score: " + scoring.totalScoreHelper());
	}

	private String rollReport(Dice dice) {
		String message = "\n**************************************\nCurrent roll Summary\n\nPlayer: " + playerName();
		
		if (index < 5) {
			message += "\nFirst die roll: " + dice.getDie1().getLastRoll() + "\nSecond die roll: " + dice.getDie2().getLastRoll() + "\nCurrent Score: " + dice.getLastRoll() +
					"\nGame level: " + skunkLevel[index];
		}
		
		return message;
	}
	
	
	
	public void summaryReport() {
		String message = "\n**************************************\nGame report final summary\n\nPlayer: " + playerName();
		message += "\nLetter\tNo. Rolls\tDie1\tDie2\tSkunk Rolled\tCurrent roll";
		StdOut.println(message);
		totalScore();
	}

	public String playerName() {
		return playerName.toString();
	}
	
	public boolean pointsIsEmpty() {
		return rollsMade.IsHashMapEmpty();
	}
	
	public List<List<Integer>> pointValues(char key) {
		return rollsMade.getAllRolledResults().get(key);
	}
	
	public int quickHundred() {
		return scoring.playerScore(rollsMade);
	}
	
	public boolean isItGreatThanAHundred() {
		return scoring.madeItToAHundred();
	}
}

package com.skunk.pl;
import java.util.ArrayList;

import com.myskunk.bl.*;

import edu.princeton.cs.introcs.*;

public class SkunkApp {
    
    private static Turn turn;
    private static int playerCount;
    private static ArrayList<Turn> players = new ArrayList<>();
    private static ArrayList<Integer> playerScores = new ArrayList<>();
    private static int index = 0;
    
    private static boolean runLoop = true;
	
	private static void settingUpPlayer () 
	{
		StdOut.println("Enter your name (Press enter when done): ");
		String playerName = StdIn.readString();
		turn = new Turn(playerName, playerCount);
	}
	
	private static void skunkApp() {
		while (runLoop) {
			StdOut.println("\n" + players.get(index).playerName() + " do you want to roll dice: (Enter yes/(y) or no/(n))");
			String answer = StdIn.readString().toLowerCase();
			
			if (answer.equals("yes") || answer.equals("y")){
				players.get(index).play();
			}
			else if (answer.equals("no") || answer.equals("n")){
				try{
					runLoop = players.get(index).dontRoll();
				} catch (ArrayIndexOutOfBoundsException e) {
					players.get(index).summaryReport();
					StdOut.println("Game Over!"); 
					if (index < playerCount - 1) {
						index++;
						if (index == playerCount) {
							// game ends
						}
						else {
							StdOut.println("\n**********************Next player is " + players.get(index).playerName() +
									"**********************");
							skunkApp();
						}
					}
					doWeHaveAWinner();
					System.exit(0);
				}
			}
		}		
		doWeHaveAWinner();
	}
	
	private static void doWeHaveAWinner() {
		boolean printWinner = false;
		
		for (int i = 0; i < players.size(); i++) {
			playerScores.add(players.get(i).quickHundred());
			StdOut.println(players.get(i).quickHundred());
			if (players.get(i).isItGreatThanAHundred() == true) {
				printWinner = true;
			}
		}
			
		int whoIsTheWinner = 0;
		
		for (int i = 1; i < playerScores.size(); i++) {
			if (playerScores.get(whoIsTheWinner) > playerScores.get(i)) {
				whoIsTheWinner = i;
			}
		}
		
		if (printWinner == true) {
			StdOut.println("\n\n\n\n\n\n\n\n");
			StdOut.println("********************************************");
			StdOut.println("********************************************");
			StdOut.println("The winner is " + players.get(whoIsTheWinner).playerName());
			StdOut.println("********************************************");
			StdOut.println("********************************************");
		}
		else {
			StdOut.println("**********************We do not have a winner.**********************");
			index = 0;
			
			for (int i = 0; i < players.size(); i++) {
				players.get(i).setIndex();
			}
			
			skunkApp();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StdOut.println("Welcome to a game of Skunk");
		StdOut.println("How many players? ");
		playerCount = StdIn.readInt();
		
		for (int i = 0; i < playerCount; i++) {
			settingUpPlayer();
			players.add(turn);
		}
		
		skunkApp();		
	}
	

}

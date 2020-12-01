package com.skunk.bl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.myskunk.bl.*;


public class TurnTest {
	private Die die1 = new Die(4), die2 = new Die(2), die3 = new Die(1), die4 = new Die(5);
	private Dice dice= new Dice(die1, die2), dice2 = new Dice(die3, die4);
	private Turn turn = new Turn ("Tester", 1);
	private String gameLevel = "S";
	
	public void init() 
	{
		gameLevel = "S";
		die1 = new Die(4);
		die2 = new Die(2);
		die3 = new Die(1);
		die4 = new Die(5);
		dice = new Dice(die1, die2);
		dice2 = new Dice(die3, die4);
		turn = new Turn ("Tester", 1);
		
		turn.addCurrentRoll('S', dice);
	}
	
	@Test
	public void savedDiceUnderSkunkRoll() {
		assertTrue(gameLevel != null);
		assertTrue(die1.getLastRoll() == 4);
		assertTrue(die2.getLastRoll() == 2);
		assertTrue(dice.getLastRoll() == 6);	
		
		turn.addCurrentRoll('S', dice);
		assertTrue(turn.pointsIsEmpty() == false);
		assertEquals(turn.pointValues('s'), List.of(List.of(1,4,2,6)));
	}
	
	@Test
	public void saveMultipleDiceRollResults() {
		assertTrue(die3.getLastRoll() == 1);
		assertTrue(die4.getLastRoll() == 5);
		assertTrue(dice2.getLastRoll() == 6);
		turn.addCurrentRoll('S', dice);
		assertFalse(turn.pointValues('s') == null);
		turn.addCurrentRoll('S', dice2);
		assertEquals(turn.pointValues('s'), List.of(List.of(1,4,2,6), List.of(2,1,5,6)));
	}
		
	@Test
	public void removeAllRollsForPlay() {
		fail("Not implemented yet.");
	}

}

package com.skunk.bl;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.myskunk.bl.Dice;
import com.myskunk.bl.Die;

class DieTest {

	Die setDie, setDie2, rollDie;
	Dice setDice, rollDice;
	
	@BeforeEach
	void rollDice() {
		setDie = new Die(5);
		setDie2 = new Die(4);
		rollDie = new Die();
		setDice = new Dice(setDie, setDie2);
		rollDice = new Dice();
	}
	
	//---------------------------------Die---------------------------------------
	/**
	 * Test Die class
	 */
	@Test
	void getDieLastRoll() {
		assertTrue(setDie.getLastRoll() == 5);
	}
	
	@Test
	void randomDieLastRoll() {
		int value = rollDie.getLastRoll();
		assertTrue(value < 7);
		assertTrue(value > 0);
		rollDie.roll();
		value = rollDie.getLastRoll();
		assertTrue(value < 7);
		assertTrue(value > 0);		
	}
	
	@Test
	void printDieFace() {
		assertEquals(setDie.toString(), "Die: 5");
	}
	
	//---------------------------------Dice--------------------------------------
	/**
	 * Test Dice class
	 */
	@Test
	void getDiceLastRoll() {
		assertEquals(setDice.getLastRoll(), 9);
	}
	
	@Test
	void randomDiceLastRoll() {
		int value = rollDice.getLastRoll();
		rollDice.roll();
		assertTrue(value < 13);
		assertTrue(value > 0);
		rollDice.roll();
		value = rollDice.getLastRoll();
		assertTrue(value < 13);
		assertTrue(value > 0);	
	}
	
	@Test
	void printDiceFace() {
		assertEquals(setDice.toString(), "Dice with last roll: 9 => 5 + 4");
	}
}

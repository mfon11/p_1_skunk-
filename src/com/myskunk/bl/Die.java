package com.myskunk.bl;

public class Die
{
	private int lastRoll;

	public Die()
	{
		this.roll();
				
	}
	
	public Die(int face) {
		this.lastRoll = face;
	}

	public int getLastRoll()
	{

		return this.lastRoll;
	}

	public void roll() 
	{
		this.lastRoll = (int) (Math.random() * 6 + 1);
	}

	@Override
	public String toString()
	{
		return "Die: " + this.getLastRoll();
	}

}

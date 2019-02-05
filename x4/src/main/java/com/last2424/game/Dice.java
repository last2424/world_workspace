package com.last2424.game;

public class Dice {
	
	public static int rollDice() {
		return (int)(1+(Math.random()*6));
	}
	
	public static int rollDices(int count) {
		int b = 6*count;
		return (int)(1+(Math.random()*b));
	}
	
}

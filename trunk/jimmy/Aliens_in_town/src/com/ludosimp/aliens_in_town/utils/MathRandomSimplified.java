package com.ludosimp.aliens_in_town.utils;

public class MathRandomSimplified {

	public static int random(int lower, int higher){

		return (int)(Math.random() * (higher-lower)) + lower;
	}
}

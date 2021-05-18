package chinchiro;

import java.util.Random;

public class Dice {
	private final int NUMBER_OF_DICE = 3;
	private int[] result;
	
	public void roll() {
		for (int i = 0; i < NUMBER_OF_DICE; i++) {
			Random rand = new Random();
			result[i] = 1+ rand.nextInt(6);
		}
	}
	
	public int[] getResult() {
		return result;
	}
}

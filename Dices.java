package chinchiro;

import java.util.ArrayList;
import java.util.Random;

public class Dices {
	private final int NUMBER_OF_DICES = 3;    // サイコロの数
	private Random RAND;
	private ArrayList<Integer> result;     // 結果の目
	
	public Dices() {
		this.RAND = new Random();
		this.result = new ArrayList<Integer>();
		for (int i = 0; i < NUMBER_OF_DICES; i++) {
			this.result.add(0);
		}
	}
	
	public void rolled() {
		for (int i = 0; i < NUMBER_OF_DICES; i++) {
			this.result.set(i, (1+ RAND.nextInt(6)));
		}
		System.out.print("結果は・・・　 ");
		Command.stopMilliSeconds(500);
		System.out.print("ざわ・・・　");
		Command.stopMilliSeconds(500);
		System.out.println("ざわ・・・　");
		Command.stopMilliSeconds(500);
		for (int i = 0; i < NUMBER_OF_DICES; i++) {
			if (this.result.get(i) == 1) {
				System.out.print("一");
			} else if (this.result.get(i) == 2) {
				System.out.print("二");
			} else if (this.result.get(i) == 3) {
				System.out.print("三");
			} else if (this.result.get(i) == 4) {
				System.out.print("四");
			} else if (this.result.get(i) == 5) {
				System.out.print("五");
			} else {
				System.out.print("六");
			}
		}
		System.out.print("！　");
		Command.stopMilliSeconds(500);
	}
	
	public ArrayList<Integer> getResult() {
		return result;
	}
}

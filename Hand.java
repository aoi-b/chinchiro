package chinchiro;

import java.util.Arrays;

public class Hand {
	private int[] sortedResult;
	private int power;
	private int rate;
	
	public void selectHand(int[] result) {
		sortedResult = result;
		Arrays.sort(sortedResult);
		if (sortedResult[0] ==  sortedResult[1]) {
			if (sortedResult[0] ==  sortedResult[2]) {
				this.zorome();
			} else {
				if (sortedResult[2] == 6) {
					this.normal6();
				} else if (sortedResult[2] == 5) {
					this.normal5();
				} else if (sortedResult[2] == 4) {
					this.normal4();
				} else if (sortedResult[2] == 3) {
					this.normal3();
				} else  {
					this.normal2();
				}
			}
		} else if (sortedResult[1] ==  sortedResult[2]) {
			if (sortedResult[0] == 5) {
				this.normal5();
			} else if (sortedResult[0] == 4) {
				this.normal4();
			} else if (sortedResult[0] == 3) {
				this.normal3();
			} else if (sortedResult[0] == 2) {
				this.normal2();
			} else if (sortedResult[0] == 1) {
				this.normal1();
			}
		} else if (sortedResult[0] == 4 && 
				sortedResult[1] == 5 && 
				sortedResult[2] == 6) {
			this.shigoro();
		} else if (sortedResult[0] == 1 && 
				sortedResult[1] == 2 && 
				sortedResult[2] == 3) {
			this.shigoro();
		} else {
			this.menashi();
		}
	}
	
	public void zorome() {
		System.out.println("ゾロ目！3倍づけ！");
		this.power = 30;
		this.rate = 3;
	}
	
	public void shigoro() {
		System.out.println("四五六！2倍づけ！");
		this.power = 20;
		this.rate = 2;
	}
	
	public void normal6() {
		System.out.println("6の目！勝てば張った分だけ取り！");
		this.power = 6;
		this.rate = 1;
	}
	
	public void normal5() {
		System.out.println("5の目！勝てば張った分だけ取り！");
		this.power = 5;
		this.rate = 1;
	}
	
	public void normal4() {
		System.out.println("4の目！勝てば張った分だけ取り！");
		this.power = 4;
		this.rate = 1;
	}
	
	public void normal3() {
		System.out.println("3の目！勝てば張った分だけ取り！");
		this.power = 3;
		this.rate = 1;
	}
	
	public void normal2() {
		System.out.println("2の目！勝てば張った分だけ取り！");
		this.power = 2;
		this.rate = 1;
	}
	
	public void normal1() {
		System.out.println("1の目！勝てば張った分だけ取り！");
		this.power = 1;
		this.rate = 1;
	}
	
	public void menashi() {
		System.out.println("目なし！");
		this.power = 0;
		this.rate = -1;
	}
	
	public void hihumi() {
		System.out.println("一二三！2倍払い！");
		this.power = -20;
		this.rate = -2;
	}
	
	public int getPower() {
		return this.power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public int getRate() {
		return this.rate;
	}
	
	public void setRate(int rate) {
		this.rate = rate;
	}
}

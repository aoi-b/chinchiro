package chinchiro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Hands {
	private String hand;
	private HashMap<String, Integer> power = new HashMap<>();
	private HashMap<String, Integer> rate = new HashMap<>();
	private HashMap<String, String> commentParent = new HashMap<>();
	
	public Hands () {
		power.put("ピンゾロ", 50);
		power.put("アラシ", 30);
		power.put("四五六", 20);
		power.put("六の目", 6);
		power.put("五の目", 5);
		power.put("四の目", 4);
		power.put("三の目", 3);
		power.put("二の目", 2);
		power.put("一の目", -10);
		power.put("目なし", 0);
		power.put("一二三", -20);
		
		rate.put("ピンゾロ", 5);
		rate.put("アラシ", 3);
		rate.put("四五六", 2);
		rate.put("六の目", 1);
		rate.put("五の目", 1);
		rate.put("四の目", 1);
		rate.put("三の目", 1);
		rate.put("二の目", 1);
		rate.put("一の目", 1); // ただし、払いのレート
		rate.put("目なし", 0);
		rate.put("一二三", 2); // ただし、払いのレート
		
		commentParent.put("ピンゾロ", "5倍づけ！　なんという僥倖…！");
		commentParent.put("アラシ", "3倍づけ！　圧倒的　感謝っ…！");
		commentParent.put("四五六", "2倍づけ！　しあわせぇ～…！");
		commentParent.put("六の目", "総取り！　やったっ…！");
		commentParent.put("五の目", "いいざんす…！");
		commentParent.put("四の目", "地道にいこう…！");
		commentParent.put("三の目", "大丈夫勝てる…！");
		commentParent.put("二の目", "信じろっ…！");
		commentParent.put("一の目", "総払い！　犯罪的だっ…！");
		commentParent.put("目なし", "アウツ…！");
		commentParent.put("一二三", "2倍払い！　ぐにゃあっ");
	}
	
	public String getHand(ArrayList<Integer> result) {
		Collections.sort(result);
		if (result.get(0) ==  result.get(1)) {
			if (result.get(0) ==  result.get(2)) {
				if (result.get(0) == 1) {
					this.hand =  "ピンゾロ";
				} else {
					this.hand =  "アラシ";
				}
			} else {
				if (result.get(2) == 6) {
					this.hand =  "六の目";
				} else if (result.get(2) == 5) {
					this.hand =  "五の目";
				} else if (result.get(2) == 4) {
					this.hand =  "四の目";
				} else if (result.get(2) == 3) {
					this.hand =  "三の目";
				} else  {
					this.hand =  "二の目";
				}
			}
		} else if (result.get(1) ==  result.get(2)) {
			if (result.get(0) == 5) {
				this.hand =  "五の目";
			} else if (result.get(0) == 4) {
				this.hand =  "四の目";
			} else if (result.get(0) == 3) {
				this.hand =  "三の目";
			} else if (result.get(0) == 2) {
				this.hand =  "二の目";
			} else if (result.get(0) == 1) {
				this.hand =  "一の目";
			}
		} else if (result.get(0) == 4 && 
				result.get(1) == 5 && 
				result.get(2) == 6) {
			this.hand =  "四五六";
		} else if (result.get(0) == 1 && 
				result.get(1) == 2 && 
				result.get(2) == 3) {
			this.hand =  "一二三";
		} else {
			this.hand =  "目なし";
		}
		return this.hand;
	}
	
	public int getPower(String hand) {
		return power.get(hand);
	}
	
	public int getRate(String hand) {
		return rate.get(hand);
	}
	
	public String getCommentParent(String hand) {
		return commentParent.get(hand);
	}
}

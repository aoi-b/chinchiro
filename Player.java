package chinchiro;

public class Player {
	private String name;    // プレイヤーの名前
	private int money;    // 現在の所持金
	private int bet;    // 掛け金
	private int order;    // プレイヤーの順番
	private String hand;    // プレイヤーの持つ目・役
	private int power;    // プレイヤーの持つ目・役の強さ
	private int rate;    // プレイヤーの持つ目・役の倍率
	
	public Player(String name) {
		this.name = name;
	}
	
	// サイコロ3個セットを投げる
	public void rollDices(Dices dices) {
		dices.rolled();
	}
	
	// 子が親に掛け金×親のレート取られる
	public int takenBet(Player parent) {
		this.money -= this.bet * parent.getRate();
		parent.setMoney(parent.getMoney() + this.bet * parent.getRate());
		
		if (this.power == -20) {
			System.out.println(this.name + "は、親" + parent.getName() + "に" +
					(2 * this.bet * parent.getRate()) + "ペリカ払った！");
		} else {
			// 通常の取られ額
			System.out.println(this.name + "は、親" + parent.getName() + "に" +
					(this.bet * parent.getRate()) + "ペリカ払った！");
		}
		
		// 子が一二三の場合、2倍取られる
		if (this.power == -20) {
			return 2 * this.bet * parent.getRate();
		} else {
			// 通常の取られ額
			return this.bet * parent.getRate();
		}
	}
	
	// 子が親から掛け金×子のレート取る
		public int takeBet(Player parent) {
			this.money += this.bet * this.getRate();
			parent.setMoney(parent.getMoney() - this.bet * this.getRate());
			System.out.println(this.name + "は、親" + parent.getName() + "から" +
					(this.bet * this.getRate()) + "ペリカ取った！");
			return this.bet * this.getRate();
		}
	
	// ゲッターとセッター
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getBet() {
		return this.bet;
	}
	
	public void setBet(int bet) {    // 掛け金を決める際に使用
		this.bet = bet;
	}
	
	public int getOrder() {
		return this.order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	public String getHand() {
		return this.hand;
	}
	
	public void setHand(String hand) {
		this.hand = hand;
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

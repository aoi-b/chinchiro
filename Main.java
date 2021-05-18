package chinchiro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		// 入力設定
		Scanner scanner = new Scanner(System.in);
		
		// ゲーム開始画面
		System.out.println("------------------------------");
		System.out.println("   　　　チンチロリン　　　  ");
		System.out.println("------------------------------");
		System.out.println("");
		System.out.print("------- Press Enter ! --------");
		Command.pressEnter();
		
		// ルール表示
		
		// ゲーム設定
		System.out.println("");
		System.out.println("------------------------------");
		System.out.println("ゲーム設定");
		
		// プレイヤー設定
		int numberOfPlayers = 3;
		while(true) {
			System.out.print("何人でプレイしますか？（2~8人）：");
			numberOfPlayers = scanner.nextInt();
			if (numberOfPlayers >= 2 && numberOfPlayers <= 8) {
				break;
			}
			System.out.println("2~8人でプレイしてください！");
			System.out.println("------------------------------");
		}
		
		System.out.println("------------------------------");
		ArrayList<Player> players= new ArrayList<Player>();
		for (int i = 0; i < numberOfPlayers; i++) {
			System.out.print("プレイヤー" + (i+1) + "の名前を入力してください：");
			players.add(new Player(scanner.next()));
		}
		
		// サイコロをインスタンス化
		var dices = new Dices();
		
		// 役・目のテーブルをインスタンス化
		var hands = new Hands();
		
		// 順番設定
		System.out.println("全員の名前が入力し終わりました！");
		System.out.println("------------------------------");
		System.out.println("続いて、順番を決めます！");
		System.out.print("------------------------------");
		Command.pressEnter();
		Collections.shuffle(players);
		System.out.print("順番は、");
		for (int i = 0; i < numberOfPlayers; i++) {
			Command.stopMilliSeconds(500);
			if (i != 0) {
				System.out.print(" → ");
			}
			System.out.print(players.get(i).getName());
			System.out.print("さん");
		}
		System.out.println(" です！");
		
		// 周回数設定
		System.out.println("------------------------------");
		int lap = 1;
		while(true) {
			System.out.print("何周プレイしますか？（1~5周）：");
			lap = scanner.nextInt();
			if (lap >= 1 && lap <= 5) {
				break;
			}
			System.out.println("1~5周でプレイしてください！");
			System.out.println("------------------------------");
		}
		
		// 所持金設定
		System.out.println("------------------------------");
		System.out.println("最初の所持金は、全員10000ペリカです");
		for (int i = 0; i < numberOfPlayers; i++) {
			players.get(i).setMoney(10000);
		}
		
		// 回数初期化
		int count = 0;    // サイコロを投げた回数で使用
		int difference = 0;    // 親の損得金額表示で使用
		boolean parentWin = true;    // 親が全員に勝ったかどうかで使用
		
		// ゲーム開始
		System.out.println("------------------------------");
		System.out.println("さあ、ゲームを開始しましょう！");
		System.out.print("------------------------------");
		Command.pressEnter();
		
		// 周回数分のループ
		for (int l = 0; l < lap; l++) {
			System.out.println("   　　　　" + lap + "周目！　　　　  ");
			System.out.print("------------------------------");
			Command.pressEnter();
			
			for (int p = 0; p < numberOfPlayers; p++) { // 1周分のループ				
				// 親が終わるまでのループ
				do {
					
					// 全員の持っている目・役のリセット
					for (int i = 0; i < numberOfPlayers; i++) {
						players.get(i).setPower(0);
						players.get(i).setRate(0);
					}
					
					// 親の表示
					System.out.println("親は、" + players.get(p).getName() + "さんです");
					System.out.println("親" + players.get(p).getName() + "さんの所持金は" +
							players.get(p).getMoney() + "ペリカです");
					System.out.print("------------------------------");
					Command.pressEnter();
					
					// 子の掛け金の設定のループ
					System.out.println("子の掛け金を設定します");
					for (int i = 0; i < numberOfPlayers; i++) {
						if (i == p) {
							continue; // 親の場合はスキップ
						}
						System.out.println("------------------------------");
						System.out.println(players.get(i).getName() + "さんの所持金は" + 
								players.get(i).getMoney() + "ペリカです");
						System.out.print(players.get(i).getName() + 
								"さん、掛け金（ペリカ）を入力してください：");
						players.get(i).setBet(scanner.nextInt());
					}
					System.out.println("------------------------------");
					System.out.println("全員の掛け金が決まりました！");
					System.out.print("------------------------------");
					Command.pressEnter();
					
					count = 0;    // カウントを初期化
					
					System.out.println("それでは、サイコロ投げに入りましょう！");
					System.out.print("------------------------------");
					Command.pressEnter();
					System.out.println("まず、親" + players.get(p).getName() + 
							"さんが投げます");
					
					// 親のサイコロ投げのループ
					do {
						System.out.println("------------------------------");
						System.out.println("親" + players.get(p).getName() + 
								"さん、サイコロを投げてください（" + (count+1) + "投目）");
						System.out.print("------------------------------");
						Command.pressEnter();
						
						// サイコロを振る、同時にdicesに結果を保存
						players.get(p).rollDices(dices);
						
						// 目・役をプレイヤーにセット
						players.get(p).setHand(
								hands.getHand(dices.getResult())
								);
						
						// 目・役の強さをプレイヤーにセット
						players.get(p).setPower(
								hands.getPower(players.get(p).getHand())
								);
						
						// 目・役のレートをプレイヤーにセット
						players.get(p).setRate(
								hands.getRate(players.get(p).getHand())
								);
						
						// 役・目の表示
						System.out.println(
								players.get(p).getHand() + "！　" +
								hands.getCommentParent(players.get(p).getHand())
						);
						
						Command.stopMilliSeconds(500);
						
						if (players.get(p).getPower() != 0) {
							count = 3;    // 目なしではなかったら、カウントを3にして終了
						} else {
							count ++;    // 目なしならば、カウントに1を追加
						}
						
					} while (count < 3); // カウントが3になるまでループ
					
					System.out.print("------------------------------");
					Command.pressEnter();
					
					if (players.get(p).getPower() >= 6 || 
							players.get(p).getPower() <= -10) {
						System.out.println("親が役だったので、子は投げられません");
					} else {
						System.out.println("続いて、子が投げます");
					}
					
					// 子全員のサイコロ投げのループ
					for (int i = 0; i < numberOfPlayers; i++) {
						
						// 親の場合はスキップ
						if (i == p) {
							continue;
						}
						
						 // 親が6の目以上の役・目なら子は振れない
						if (players.get(p).getPower() >= 6 ) {
							break;
						}
						
						// 親が一の目または一二三の場合
						if (players.get(p).getPower() <= -10) {
							if (i == p) {
								continue; // 親の場合はスキップ
							}
								
							// 子の強さは0で初期化されているので、子が勝つ
							// 子の目・役のレートに1または2をセット
							players.get(i).setRate(players.get(p).getRate());
							continue;
						}
						
						count = 0;    // カウントを初期化
						
						// 子一人のサイコロ投げのループ
						do {
							System.out.println("------------------------------");
							System.out.println(players.get(i).getName() + 
									"さん、サイコロを投げてください（" + (count+1) + "投目）");
							System.out.print("------------------------------");
							Command.pressEnter();
							
							// サイコロを振る、同時にdicesに結果を保存
							players.get(i).rollDices(dices);
							
							// 目・役をプレイヤーにセット
							players.get(i).setHand(
									hands.getHand(dices.getResult())    // 目・役の名前
									);
							
							// 目・役の強さをプレイヤーにセット
							players.get(i).setPower(
									hands.getPower(players.get(i).getHand())
									);
							
							// 目・役のレートをプレイヤーにセット
							players.get(i).setRate(
									hands.getRate(players.get(i).getHand())
									);
							
							// 役・目の表示
							if (players.get(i).getPower() == 0) {
								if (count <= 1) {
									System.out.println("目なし！　もう一回だっ！");
								} else {
									System.out.println("目なし！　クズだっ…！");
								}
							} else if (players.get(i).getPower() > players.get(p).getPower()) {
								System.out.println(players.get(i).getHand() + "！　親に勝った！");
							} else if (players.get(i).getPower() < players.get(p).getPower()){
								System.out.println(players.get(i).getHand() + "！　ヘナッ…");
							} else {
								System.out.println(players.get(i).getHand() + "！　引き分けか…");
							}
							
							Command.stopMilliSeconds(500);
							
							if (players.get(i).getPower() != 0) {
								count = 3;    // 目なしではなかったら、カウントを3にして終了
							} else {
								count ++;    // 目なしならば、カウントに1を追加
							}
							
						} while (count < 3); // カウントが3になるまでループ
						
					}
					
					// 親の損得金額の初期化
					difference = 0;
					
					// 親が全員に勝ったかどうかの初期化
					parentWin = true;
					
					System.out.println("------------------------------");
					// 子の払い・取り分のループ
					for (int i = 0; i < numberOfPlayers; i++) {
						if (i == p) {
							continue; // 親の場合はスキップ
						}
						
						if (players.get(p).getPower() >    // 親の勝ち
						players.get(i).getPower()) {
							difference += players.get(i).takenBet(players.get(p));
						} else if (players.get(p).getPower() <    // 子の勝ち
						players.get(i).getPower()) {
							difference -= players.get(i).takeBet(players.get(p));
							parentWin = false;
						} else {    // 引き分け
							System.out.println(players.get(i).getName() + 
									"は親" + players.get(p).getName() + "と引き分けた！");
							parentWin = false;
						}
					}
					
					// 親の払い・取り分の表示
					System.out.print("親" + players.get(p).getName() + "は全部で、");
					if (difference >= 0) {
						System.out.println(difference + "ペリカ取った！");
					} else {
						System.out.println(Math.abs(difference) + "ペリカ払った！");
					}
					
					System.out.print("------------------------------");
					Command.pressEnter();
					
					// 全員の所持金表示
					for (int i = 0; i < numberOfPlayers; i++) {
						System.out.println(players.get(i).getName() + "さんの所持金は" + 
								players.get(i).getMoney() + "ペリカです");
					}
					
					System.out.print("------------------------------");
					Command.pressEnter();
					
				} while (parentWin);    // 親が全員に勝ったら続行、falseを書き換える
				
			}    // 1周分のループ終了
			
		}    // 周回数分のループ終了
		
		// ゲーム終了
		System.out.println("------------------------------");
		System.out.println("   　　　  結果発表！ 　　　  ");
		System.out.println("------------------------------");
		System.out.print("------------------------------");
		Command.pressEnter();
		
		// 全員の最終金額表示
		for (int i = 0; i < numberOfPlayers; i++) {
			Command.stopMilliSeconds(500);
			System.out.println(players.get(i).getName() + "さんの所持金は" + 
					players.get(i).getMoney() + "ペリカでした");
		}
		
		scanner.close();
	}
	
}

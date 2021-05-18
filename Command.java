package chinchiro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Command {
	public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	
	public static void pressEnter() {
		try {
			while (!(BR.readLine()).equals("")) {
				System.out.print("Press Enter ! ");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void stopMilliSeconds(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

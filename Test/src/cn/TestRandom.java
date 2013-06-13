package cn;

import java.util.Random;

public class TestRandom {
	public static void main(String[] args) {
		Random rand = new Random();
		System.out.println(rand.nextInt(999));
		// double money = 1.365;
		System.out.println();

		double money = 1.365;
		String result = String.format("%.2f", money);
		System.out.println(result);

	}

}

package user.ui;

import java.util.Random;

public class test {
	public static void main(String[] args) {
		Random rand = new Random();
		int max = 1110;
		int min = 5;
		int count = 3;
		int[] array = new int[count];
		for (int a = 0; a < count; a++) {
			array[a] = rand.nextInt((max - min) + 1) + min;
			System.out.println(array[a]);
		}
		int maximum = 0;
		for (int a = 0; a < count; a++) {
			if (maximum < array[a]) {
				maximum = array[a];
			}
		}
		System.out.println("Вывод максимального числа: " + maximum);

		int lol = 0;
		for (int a = 0; a < count; a++) {
			if (array[a] % 2 == 0) {
				lol++;
			}
		}
		System.out.println("Чётных чисел: " + lol);

		int minimum = 1110;
		for (int a = 0; a < count; a++) {
			if (minimum > array[a]) {
				minimum = array[a];
			}
		}
		System.out.println("Вывод мин числа: " + minimum);

		for (int a = 2; a >= 0; a--) {
			System.out.println(array[a]);
		}
		int secmax = 0;
		int maximumm = 0;
		for (int a = 0; a < count; a++) {
			if (maximumm < array[a]) {
				secmax = maximumm;
				maximumm = array[a];

			} else if (secmax < array[a]) {
				secmax = array[a];
			}
		}
		System.out.println("Вывод максимального числа: " + secmax);
	}
}

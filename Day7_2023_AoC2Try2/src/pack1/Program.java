package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Program {

	static char[] cardValue = { 'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2' };

	public static void main(String[] args) throws IOException {
		// written code for every day
		String[] input;
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String st;
		int count = 0;
		while ((st = br.readLine()) != null) {
			count++;
		}
		input = new String[count];
		count = 0;
		br = new BufferedReader(new FileReader("input.txt"));
		while ((st = br.readLine()) != null) {
			input[count] = st;
			count++;
		}
		System.out.println(Arrays.toString(input));
		////////////////////////////////////////////
		LinkedList<String>[] cardsSorted = new LinkedList[7]; // first -> highest (5 of a kind)
		for (int i = 0; i < cardsSorted.length; i++) {
			cardsSorted[i] = new LinkedList<String>();
		}
		for (String s : input) {
			String cards = s.substring(0, 5);
			LinkedList<String> cardAmount = new LinkedList<>();

			for (int i = 0; i < cards.length(); i++) {
				for (int j = 0; j < cardAmount.size(); j++) {
					if (cards.charAt(i) == cardAmount.get(j).charAt(0)) {
						cardAmount.set(j, cardAmount.get(j) + cards.charAt(i));
						break;
					}
					if (j == cardAmount.size() - 1) {
						cardAmount.add(new String(Character.toString(cards.charAt(i))));
						break;
					}
				}

				if (cardAmount.size() == 0) {
					cardAmount.add(new String(Character.toString(cards.charAt(i))));

				}

			}

			System.out.println(cardAmount);

			for (int i = 0; i < cardAmount.size(); i++) {
				if (cardAmount.get(i).length() == 1) {
					if (i == cardAmount.size() - 1) {
						cardsSorted[6].add(s);
						break;
					}
				} else if (cardAmount.get(i).length() == 2) {
					if (i + 1 >= cardAmount.size()) {
						cardsSorted[5].add(s);
					} else {
						for (int j = i + 1; j < cardAmount.size(); j++) {
							if (cardAmount.get(j).length() == 2) {
								cardsSorted[4].add(s);
								break;
							} else if (cardAmount.get(j).length() == 3) {
								cardsSorted[2].add(s);
								break;
							} else if (j == cardAmount.size() - 1) {
								cardsSorted[5].add(s);
								break;
							}
						}
					}
					break;

				} else if (cardAmount.get(i).length() == 3) {
					if (i + 1 >= cardAmount.size()) {
						cardsSorted[3].add(s);
					} else {
						for (int j = i + 1; j < cardAmount.size(); j++) {
							if (cardAmount.get(j).length() == 2) {
								cardsSorted[2].add(s);
							} else if (j == cardAmount.size() - 1) {
								cardsSorted[3].add(s);
							}
						}
					}
					break;
				} else if (cardAmount.get(i).length() == 4) {
					cardsSorted[1].add(s);
					break;
				} else if (cardAmount.get(i).length() == 5) {

					cardsSorted[0].add(s);
					break;
				}

			}

		}
		System.out.println();

		for (int i = 0; i < cardsSorted.length; i++) {
			sortAll(cardsSorted[i]);
		}
		for(int i = 0; i < cardsSorted.length; i++) {
			System.out.println(cardsSorted[i]);
		}
		System.out.println();

		int a = 1;
		int ergebnis = 0;
		for (int i = cardsSorted.length - 1; i >= 0; i--) {
			for (int j = cardsSorted[i].size() - 1; j >= 0; j--) {
				String value = cardsSorted[i].get(j).substring(6, cardsSorted[i].get(j).length());
				ergebnis += a * Integer.parseInt(value);
				a++;
			}
		}
		System.out.println(a);
		System.out.println(ergebnis);

	}

	static LinkedList<String> sortAll(LinkedList<String> input) { // input is the List to sort, by Cards

		int max = Integer.MAX_VALUE;
		int a = input.size();
		for (int j = 0; j < a; j++) {
			for (int i = j; i < input.size(); i++) {
				if (i == j) {
					max = j;
				} else if (compareTwoCards(input.get(max), input.get(i)) == 1) {
					max = i;
				}
			}
			input = swap(input, j, max);
		}
		return input;

	}

	static int compareTwoCards(String card1, String card2) {

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < cardValue.length; j++) {

				if (card1.charAt(i) == cardValue[j] && card2.charAt(i) != cardValue[j]) {

					return 0;
				} else if (card1.charAt(i) != cardValue[j] && card2.charAt(i) == cardValue[j]) {

					return 1;
				}
			}
		}
		System.out.println(card2);
		return 2;
	}

	static LinkedList<String> swap(LinkedList<String> toSort, int i1, int i2) {
		String temp = toSort.get(i1);
		toSort.set(i1, toSort.get(i2));
		toSort.set(i2, temp);

		return toSort;

	}

}

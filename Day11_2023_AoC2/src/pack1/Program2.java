package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Program2 {
	
	static int multiplier = 1000000; //for part 1 its 2, for part2 its 1_000_000
	
	
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
		LinkedList<Integer> vertical = new LinkedList<>();
		LinkedList<Integer> horizontal = new LinkedList<>();
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length(); j++) {
				if (input[i].charAt(j) != '.') {
					break;
				}
				if (j == input[i].length() - 1) {
					horizontal.add(i);
				}
			}
		}

		for (int i = 0; i < input[0].length(); i++) {
			for (int j = 0; j < input.length; j++) {
				if (input[j].charAt(i) != '.') {
					break;
				}
				if (j == input.length - 1) {
					vertical.add(i);
				}
			}

		}

		char[][] inputNew = new char[input.length][input[0].length()];

		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length(); j++) {
				inputNew[i][j] = input[i].charAt(j);
			}
		}

		for (char[] i : inputNew) {
			for (char c : i) {
				System.out.print(c);
			}
			System.out.println();
		}

		// inputNew contains parsed input
		long ergebnis = 0;
		for (int i = 0; i < inputNew.length; i++) {
			for (int j = 0; j < inputNew[0].length; j++) {
				if (inputNew[i][j] == '#') {

					for (int t = j + 1; t < inputNew[0].length; t++) {
						if (inputNew[i][t] == '#') {
							int plus = 0; // wie viele leere Spalten dazwischen?
							//die andere galaxy kann nur rechts liegen: 
							for (int u = j; u < t; u++) {
								if (vertical.contains(u)) {
									plus++;
								}
							}

							int dis = ((t - j) - plus) + plus * multiplier;
							ergebnis += dis;
							System.out.print("(" + j + ", " + i + ")" + "(" + t + ", " + i + ")  ");
							System.out.println(dis);
						}
					}

					for (int k = i + 1; k < inputNew.length; k++) {
						for (int o = 0; o < inputNew[0].length; o++) {
							if (inputNew[k][o] == '#') {
								// berechne distance zwischen punkt (j, i) und (o, k)
								// Berechnung mit Distance d = |x2 - x1| + |y2 - y1| =
								System.out.print("(" + j + ", " + i + ")" + "(" + o + ", " + k + ")  ");
								int plusX = 0, plusY = 0;
								//Galaxy kann auch rechts liegen
								if (j < o) {
									for (int u = j; u < o; u++) {
										if (vertical.contains(u)) {
											plusX++;
										}
									}
								}else if(o < j) {
									for(int u = j; u > o; u--) {
										if(vertical.contains(u)) {
											
											plusX++; 
										}
									}
								}
								//galaxy liegt immer darunter: 
									for (int u = i; u < k; u++) {
										if (horizontal.contains(u)) {
											plusY++;
										}
									}
								

								int dis = Math.abs(o - j )- plusX + plusX * multiplier + Math.abs(k - i ) - plusY + plusY * multiplier;
								System.out.println(dis);
								ergebnis += dis;
							}
						}
					}
				}
			}
		}
		System.out.println(ergebnis);
	}
}

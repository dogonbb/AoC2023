package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Program {
	//fyi: Program2 works for part1 and part2, just change multiplier as explained 
	
	
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
		System.out.println(horizontal.size());
		System.out.println(vertical.size());
		for (int i = 0; i < horizontal.size(); i++) {
			horizontal.set(i, horizontal.get(i) + i);
		}
		for (int i = 0; i < vertical.size(); i++) {
			vertical.set(i, vertical.get(i) + i);
		}
		char[][] inputNew = new char[input.length + horizontal.size()][input[0].length() + vertical.size()];

		for (int i = inputNew.length - 1; i >= 0; i--) {
			for (int j = inputNew[0].length - 1; j >= 0; j--) {
				if (vertical.contains(j) || horizontal.contains(i)) {
					inputNew[i][j] = '.';
				}
			}
		}
		int countX = 0, countY = 0;
		for (int i = 0; i < inputNew.length; i++) {
			countX=0; 
			for (int j = 0; j < inputNew[0].length; j++) {
				if (inputNew[i][j] != '.') {
					inputNew[i][j] = input[countY]
							.charAt(countX);
					countX++;
					
				}
			}
			if(!horizontal.contains(i)) countY++;
			
		}

		for (char[] i : inputNew) {
			for (char c : i) {
				System.out.print(c);
			}
			System.out.println();
		}
		
		//inputNew contains parsed input
		int ergebnis = 0; 
		for(int i = 0; i < inputNew.length; i++) {
			for(int j = 0; j < inputNew[0].length; j++) {
				if(inputNew[i][j] == '#') {
					
					for(int t = j + 1; t < inputNew[0].length; t++) {
						if(inputNew[i][t] == '#') {
							ergebnis+= t-j; 
//							System.out.print("(" + j+", " +i +  ")" + "(" + t+ ", " +i + ")  ");
						}
					}
					
					for(int k = i + 1; k < inputNew.length; k++) {
						for(int o = 0; o < inputNew[0].length; o++) {
							if(inputNew[k][o] == '#') {
								//berechne distance zwischen punkt (j, i) und (o, k)
								//Berechnung mit Distance d = |x2 - x1| + |y2 - y1| =
//								System.out.print("(" + j+", " +i +  ")" + "(" + o+ ", " +k + ")  ");
								int dis = Math.abs(o - j)  + Math.abs(k - i); 
//								System.out.println(dis);
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

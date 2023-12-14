package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Program2 {
	public static void main(String[] args) throws IOException, InterruptedException {
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
		int ergebnis = 0;
		char[][] inputChar = new char[input.length][];
		// geting char array for easier change of charAt
		for (int i = 0; i < input.length; i++) {
			inputChar[i] = new char[input[i].length()];
			for (int j = 0; j < input[i].length(); j++) {
				inputChar[i][j] = input[i].charAt(j);
			}
		}

		System.out.println();
		printArray(inputChar);
		// north rotation
		char[][] inputCharStart = new char[inputChar.length][inputChar[0].length];
		for(int i = 0; i < inputChar.length; i++) {
			for(int j = 0; j < inputChar[0].length; j++) {
				inputCharStart[i][j] = inputChar[i][j]; 
			}
		}

		System.out.println(compare_two_felder(inputChar, inputCharStart));
		int repeat = 0; 
		do {
		repeat++;
		for (int k = 0; k < input.length; k++) {
			for (int i = 1; i < inputChar.length; i++) {
				for (int j = 0; j < inputChar[i].length; j++) {
					if (inputChar[i][j] == 'O') {
						if (inputChar[i - 1][j] == '.') {
							inputChar[i - 1][j] = 'O';
							inputChar[i][j] = '.';
						}
					}
				}
			}
		}
		if(compare_two_felder(inputChar, inputCharStart)) break;

		// west
		for (int k = 0; k < input.length; k++) {
			for (int i = 0; i < inputChar.length; i++) {
				for (int j = 1; j < inputChar[i].length; j++) {
					if (inputChar[i][j] == 'O') {
						if (inputChar[i][j - 1] == '.') {
							inputChar[i][j - 1] = 'O';
							inputChar[i][j] = '.';
						}
					}
				}
			}
		}
		if(compare_two_felder(inputChar, inputCharStart)) break;

		// south
		for (int k = 0; k < input.length; k++) {
			for (int i = 0; i < inputChar.length - 1; i++) {
				for (int j = 0; j < inputChar[i].length; j++) {
					if (inputChar[i][j] == 'O') {
						if (inputChar[i + 1][j] == '.') {
							inputChar[i + 1][j] = 'O';
							inputChar[i][j] = '.';
						}
					}
				}
			}
		}
		if(compare_two_felder(inputChar, inputCharStart)) break;
		// east
		for (int k = 0; k < input.length; k++) {
			for (int i = 0; i < inputChar.length; i++) {
				for (int j = 0; j < inputChar[i].length - 1; j++) {
					if (inputChar[i][j] == 'O') {
						if (inputChar[i][j + 1] == '.') {
							inputChar[i][j + 1] = 'O';
							inputChar[i][j] = '.';
						}
					}
				}
			}
		}
		
		if(repeat%500==0) System.out.println(repeat);
		printArray(inputCharStart);
		System.out.println();
		printArray(inputChar);
		System.out.println();
		System.out.println();
		Thread.sleep(1000);
		}while(!compare_two_felder(inputChar, inputCharStart));
		System.out.println(repeat);
		// ergebnis berechnen
		int counter = 1;
		for (int i = inputChar.length - 1; i >= 0; i--) {
			for (int j = 0; j < inputChar[i].length; j++) {
				if (inputChar[i][j] == 'O') {
					ergebnis += counter;
				}
			}
			counter++;
		}
		System.out.println(ergebnis);
	}

	static boolean compare_two_felder(char input1[][], char input2[][]) {
		for(int i = 0; i < input1.length; i++) {
			if(!Arrays.equals(input1[i], input2[i])) return false; 
		}
		return true; 
	}
	
	
	static void printArray(char[][] inputChar) {
		for (int i = 0; i < inputChar.length; i++) {
			for (int j = 0; j < inputChar[i].length; j++) {

				System.out.print(inputChar[i][j]);

			}
			System.out.println();
		}
	}
}

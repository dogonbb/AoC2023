package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Program {
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
		int ergebnis = 0; 
		char[][] inputChar = new char[input.length][];
		// geting char array for easier change of charAt
		for (int i = 0; i < input.length; i++) {
			inputChar[i] = new char[input[i].length()];
			for (int j = 0; j < input[i].length(); j++) {
				inputChar[i][j] = input[i].charAt(j);
			}
		}
		for(char[] c : inputChar) {
			System.out.println(Arrays.toString(c));
		}
		
		for (int k = 0; k < input.length + 1; k++) {
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
			System.out.println();
			for(char[] c : inputChar) {
				System.out.println(Arrays.toString(c));
			}
		}
		for(int i = 0; i < inputChar.length; i++) {
			for(int j = 0; j < inputChar[i].length; j++) {
			
					System.out.print(inputChar[i][j]);
				
					}
			System.out.println();
		}
		
		int counter = 1; 
		for(int i = inputChar.length - 1; i >= 0; i--) {
			for(int j = 0; j < inputChar[i].length; j++) {
				if(inputChar[i][j] == 'O') {
					ergebnis+=counter; 
				}
					}
			counter++; 
		}
		System.out.println(ergebnis);
	}
}

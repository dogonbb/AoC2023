package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Program2 {
	public static void main(String[] args) throws IOException {
		//FOR PART TWO:
		/*
		 * I just changed all datatypes from int to long
		 * and adjusted the puzzle input, with removing the spaces between the numbers
		 * so for ex. 11 11 -> 1111
		 * Part 1 will only work if input is readjusted to the original 
		 */
		
		
		
		
		
		
		//written code for every day
		String[] input; 
		BufferedReader br = new BufferedReader(new FileReader("input.txt")); 
		String st;
		int count=0; 
		while((st = br.readLine()) != null) {
			count++;
		}
		input = new String[count]; 
		count = 0; 
		br = new BufferedReader(new FileReader("input.txt"));  
		while((st = br.readLine()) != null) {
			input[count] = st; 
			count++;
		}
		System.out.println(Arrays.toString(input));
		////////////////////////////////////////////
		
		int Races = 1; 
		long time[][] = new long [2][Races]; //CHANGE WITH DIFFRENT INPUT
		long counter = 0; 
		for(String s : input) {
			int a = 0;
			long counter2 = 0; 
			for(long b : time[0]) {
				while(!Character.isDigit(s.charAt(a) )) {
					a++;
				}
				String temp =""; 
				while( a < s.length() &&   Character.isDigit(s.charAt(a) )) {
					temp+=s.charAt(a); 
					a++;
				}
				time[(int) counter][(int) counter2] = Long.parseLong(temp); 
				counter2++; 
			}
			
			counter++; 
		}
		
		long wayPossibilities = 0; 
		long ergebnis = 1; 
		for(int i = 0; i < Races; i++) {
			wayPossibilities=0; 
			for(int j = 1; j < time[0][i]; j++) {
				if((time[0][i]-j)*j > time[1][i]) {
					wayPossibilities+=1; 
				}
			}
			System.out.println(wayPossibilities);
			ergebnis*=wayPossibilities; 
		}
		System.out.println(ergebnis);
		
	}
}

package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Program {
	public static void main(String[] args) throws IOException {
		/*
		 * !!!!!
		 * I adjusted the puzzle input for part2
		 * part1 will not!! work if not readjusted 
		 * :))
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
		int time[][] = new int [2][Races]; //CHANGE WITH DIFFRENT INPUT
		int counter = 0; 
		for(String s : input) {
			int a = 0;
			int counter2 = 0; 
			for(int b : time[0]) {
				while(!Character.isDigit(s.charAt(a) )) {
					a++;
				}
				String temp =""; 
				while( a < s.length() &&   Character.isDigit(s.charAt(a) )) {
					temp+=s.charAt(a); 
					a++;
				}
				time[counter][counter2] = Integer.parseInt(temp); 
				counter2++; 
			}
			
			counter++; 
		}
		
		int wayPossibilities = 0; 
		int ergebnis = 1; 
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

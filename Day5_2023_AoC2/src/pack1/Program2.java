package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Program2 {
	public static void main(String[] args) throws IOException {
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
				
		location_to_seed(input, 0); 
	}
	
	
	static long location_to_seed(String[] input, int location) {
		//ranges bestimmen
		int[][] ranges = new int[10][2];
		String zahlen[] = input[0].substring(7, input[0].length()).split("\\s+");
		System.out.println(Arrays.toString(zahlen));
		for(int i = 0; i < zahlen.length; i++) {
			
		}
		//von hinten durchgehen 
		
		//auf range prüfen
		
		return 0; 
	}
	
	static long seed_to_location(String[] input, long seed) {
		int a = 3; 
		long dest = seed; 
		out: while(a < input.length && Character.isDigit(input[a].charAt(0))) {
			String[] inputZeile = input[a].split("\\s+"); 
			long[] inputZeileInteger = new long[inputZeile.length]; 
			for(int i = 0; i < inputZeile.length; i++) {
				inputZeileInteger[i] = Long.parseLong(inputZeile[i]); 
			}
			
			if(inputZeileInteger[1] + inputZeileInteger[2] > dest && inputZeileInteger[1] <= dest) {
				long x = dest - inputZeileInteger[1]; 
				
				dest = inputZeileInteger[0] + x; 
			
				do {
					a++; 
					if(a >=  input.length) break out;
				}
				while( input[a].length() == 0 || Character.isDigit(input[a].charAt(0)));
				
			}
			
			a++;
			if(a >= input.length) break; 
			if(input[a].length() == 0) {
				a+=2; 
			}
		}
		return dest; 
	}
}

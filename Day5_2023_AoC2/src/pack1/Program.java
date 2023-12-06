package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Program {
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
		
		long inputNumber[] = new long[input[0].split("\\s+").length - 1]; 
		String[] inputNumberString = input[0].split("\\s+"); 
		for(int i = 1; i < inputNumberString.length; i++) {
			inputNumber[i-1] =	Long.parseLong(inputNumberString[i]); 
		}
		long min = Long.MAX_VALUE; 
		for(long number : inputNumber) {
			long b; 
			if(min > (b = seed_to_location(input,number))) {
				min = b; 
			}
		}
		System.out.println(min);
		
		
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

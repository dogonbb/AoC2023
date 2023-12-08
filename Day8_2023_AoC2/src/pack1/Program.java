package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Program {
	public static void main(String[] args) throws IOException, InterruptedException {
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
		
		
		String instruction = input[0];
		
		int startLine = 447; 
		int schritte = 0; 
		out: while(true) {
			
			for(int i = 0; i < instruction.length(); i++) {
				
				schritte++; 
				if(schritte%100000 == 0) {
					System.out.println(schritte);
				}
				String tempString = ""; 
				if(instruction.charAt(i) == 'L') {
					
					 tempString = input[startLine].substring(7, 10); 
				}
				else if(instruction.charAt(i) == 'R') {
					
					 tempString = input[startLine].substring(12, 15); 
				}
				
				if(tempString.equals( "ZZZ")) break out; 
				for(int j = 2; j < input.length; j++) {
					if(input[j].substring(0, 3).equals(tempString)) {
						startLine = j; 
					}
				}
			}
		}
		System.out.println(schritte);
		
	}
}

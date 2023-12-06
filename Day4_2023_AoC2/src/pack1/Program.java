package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
		int ergebnis = 0; 
		for(String s : input) {
			int a = 0; 
			while(s.charAt(a) != ':') {
				a++; 
			}
			while(!Character.isDigit(s.charAt(a))) {
				a++; 
			}
			String string1 = ""; 
			String string2 = ""; 
			while(s.charAt(a) != '|') {
				string1+=s.charAt(a); 
				a++; 
			}
			a++;
			a++; 
			while(a < s.length()) {
				string2+=s.charAt(a); 
				a++; 
			}
		
			String[] splited1 = string1.split("\\s+");
			String[] splited2 = string2.split("\\s+"); 
			Arrays.toString(splited1); 
			Arrays.toString(splited2); 
			
		
			int count1 = 0; 
			for(int i = 0; i < splited2.length; i++) {
				
				for(int j = 0; j < splited1.length; j++) {
					if(splited2[i].equals(splited1[j])) {
						if(count1 == 0) count1=1; 
						else count1+=count1; 
						
						break; 
					}
				}
			}
			System.out.println("count1 : "  + count1);
			ergebnis+= count1; 
		}
		System.out.println(ergebnis);
	}
}

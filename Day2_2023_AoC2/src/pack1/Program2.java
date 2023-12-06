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
	
		////////////////////////////////////////////
		
		//12 red, 13 green 14 blue
		int R = 12, G = 13, B = 14; 
		int ergebnis = 0; 
		for(String s : input) {
			int red = 0, green = 0, blue = 0; 
			for(int i = 8; i < s.length(); i++) {
				if(Character.isDigit(s.charAt(i))) {
					int a = i; 
					String toComp = ""; 
					while(Character.isDigit(s.charAt(a))) {
						toComp += s.charAt(a); 
						a++; 
					}
					a+=1; 
					
					if(s.charAt(a) == 'r') {
						if(red < Integer.parseInt(toComp)) red = Integer.parseInt(toComp);  
					}else if(s.charAt(a) == 'g') {
						if(green < Integer.parseInt(toComp)) green = Integer.parseInt(toComp);  
					}else if(s.charAt(a) == 'b') {
						if(blue < Integer.parseInt(toComp)) blue = Integer.parseInt(toComp);  
					}
					i = a; 
					
				}else if( i == s.length() -1) {
					ergebnis+=red*green*blue; 
					
				}
				
			}
			System.out.println();
		}
		System.out.println("Ergebnis: " + ergebnis);
	}
}

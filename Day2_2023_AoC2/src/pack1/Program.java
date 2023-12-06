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
						red += Integer.parseInt(toComp); 
					}else if(s.charAt(a) == 'g') {
						green += Integer.parseInt(toComp); 
					}else if(s.charAt(a) == 'b') {
						blue +=  Integer.parseInt(toComp); 
					}
					i = a; 
					
				}else if(s.charAt(i) == ';' || i == s.length() -1) {
					System.out.println(red);
					System.out.println(blue);
					System.out.println(green);
					if(red > R) {
						break; 
					}else if(green > G) {
						break; 
					}else if(blue > B) {
						break; 
					}
					blue = 0; 
					red = 0; 
					green = 0; 
				}
				if(i == s.length()-1) {
					int a = 5; 
					String temp = ""; 
					while(Character.isDigit(s.charAt(a))) {
						temp += s.charAt(a); 
						a++; 
					}
					ergebnis+=Integer.parseInt(temp); 
				}
			}
			System.out.println();
		}
		System.out.println("Ergebnis: " + ergebnis);
	}
}

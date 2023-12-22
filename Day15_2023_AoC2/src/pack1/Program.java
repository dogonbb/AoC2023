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
		input[0]+= ','; //sprecial length 
		System.out.println(Arrays.toString(input));
		////////////////////////////////////////////
		String temp = "";
		long ergebnis = 0; 
		for(int i = 0; i < input[0].length(); i++) {
			if(input[0].charAt(i) != ',') {
				temp+=input[0].charAt(i);
			}else {
				ergebnis += get_hash(temp); 
				System.out.println(get_hash(temp));
				temp =""; 
			}
		}
		System.out.println(ergebnis);
		
	}
	
	static int get_hash(String string) {
		int assci = 0;
		for(int i = 0; i < string.length(); i++) {
			assci += (int) string.charAt(i);
			assci*=17; 
			assci%=256; 
		}
		return assci; 
		
	}
	
}

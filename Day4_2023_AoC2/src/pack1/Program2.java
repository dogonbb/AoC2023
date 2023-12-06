package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Program2 {
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
		ArrayList<Integer> number = new ArrayList<>();
		for (String s : input) {
			int a = 0;

			while (!Character.isDigit(s.charAt(a))) {
				a++;
			}
			String cardNumber = "";
			while (Character.isDigit(s.charAt(a))) {
				cardNumber += s.charAt(a);
				a++;
			}
			while (!Character.isDigit(s.charAt(a))) {
				a++;
			}
			String string1 = "";
			String string2 = "";
			while (s.charAt(a) != '|') {
				string1 += s.charAt(a);
				a++;
			}
			a += 2;
			while (a < s.length()) {
				string2 += s.charAt(a);
				a++;
			}

			String[] splited1 = string1.split("\\s+");
			String[] splited2 = string2.split("\\s+");
			Arrays.toString(splited1);
			Arrays.toString(splited2);

			int count1 = 0;
			for (int i = 0; i < splited2.length; i++) {
				for (int j = 0; j < splited1.length; j++) {
					if (splited2[i].equals(splited1[j])) {
						count1++;

						break;
					}
				}
			}
			// count1 = wie viele Matching cards
			int b = Integer.parseInt(cardNumber); // b = cardNumber in integer
			int wieOftCardNumberInArrayList = 1;
			for (int p : number) {
				if (p == b) {
					wieOftCardNumberInArrayList++;
				}
			}
			
			
			ergebnis+= wieOftCardNumberInArrayList; //idk why i have to do this, but when i get the size of list number at the end, i get a wrong solution??
			
			int count2 = count1; 
			for (int i = 0; i < wieOftCardNumberInArrayList; i++) {
				while (count1 > 0) {
					b++;
					number.add(b);
					count1--; 
				}
				b = Integer.parseInt(cardNumber);
				count1 = count2; 
			}

			
		}
		System.out.println(ergebnis);
	}
}

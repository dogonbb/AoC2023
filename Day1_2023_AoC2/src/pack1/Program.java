package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Program {
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

		////////////////////////////////////////////
		int endergebnis = 0;
		String[] input_string = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
		String tempNumber = "";

		for (String s : input) {

			String ergebnis = "";
			out: for (int i = 0; i < s.length(); i++) {

				for (String s2 : input_string) {
					if(s2.charAt(0) == s.charAt(i)) {
						String temp = ""; 
						for(int j = i; j < i+s2.length(); j++) {
							if(j < s.length()) {
							temp += s.charAt(j);} 
						}
						if(s2.equals(temp)) {
							for (int o = 1; o < input_string.length + 1; o++) {
								if (input_string[o - 1].equals(temp)) {

									ergebnis+= o; 
									break out;
								}
							}
						
						}
					}
				}

				if (Character.isDigit(s.charAt(i))) {
					ergebnis += s.charAt(i);
					break;
				}
			}
			System.out.println(" uighdsfipgh");
			System.out.println(ergebnis);
			
			tempNumber = "";

			int index1 = -1, index2 = -1;
			String zahl1 = "", zahl2 = "";
			out2: for (int j = s.length() - 1; j > 0; j--) {
				for (String s2 : input_string) {

					if (s.charAt(j) == s2.charAt(s2.length() - 1)) {
						String temp = "";
						for (int o = j - s2.length() + 1; o <= j; o++) {
							if(o>=0) {
							temp += s.charAt(o); }
						}

						if (temp.equals(s2)) {

							for (int o = 1; o < input_string.length + 1; o++) {
								if (input_string[o - 1].equals(temp)) {

									zahl1 = Integer.toString(o);
									index1 = j - s2.length() + 1;
									break out2;
								}
							}
						}
					}
				}

			}
			for (int i = s.length() - 1; i >= 0; i--) {
				if (Character.isDigit(s.charAt(i))) {
					index2 = i;
					zahl2 = "" + s.charAt(i);
					break; 
				}

			}
			
			if (index1 > index2) {
				ergebnis += zahl1;
			} else {
				ergebnis += zahl2;
			}

			System.out.println(ergebnis);
			endergebnis += Integer.parseInt(ergebnis);
		}

		System.out.println("Ende: " + endergebnis);
	}
}

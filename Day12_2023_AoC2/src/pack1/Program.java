package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Program {
	public static void main(String[] args) throws Exception {
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

		// speichern String von Fragezeichen in List

		for (String s : input) {
			String[] zahlenString;
			int a = 0;
			String toEdit = new String("");
			for (int i = 0; i < s.length(); i++) { 	toEdit += s.charAt(i);// erkennen der Zahlen
				if (s.charAt(i + 1) == ' ') {
				
					a = i + 1;
					break;
				}
			}
			System.out.println(toEdit);
			zahlenString = s.substring(a + 1, s.length()).split(",");
			int zahlen[] = new int[zahlenString.length];
			for (int i = 0; i < zahlenString.length; i++) {
				zahlen[i] = Integer.parseInt(zahlenString[i]);
			}
			System.out.println(Arrays.toString(zahlen));
			int[][] stellen = new int[zahlen.length][2 /* start, end */];
			stellen[0][0] = 0; // startet immer bei 0
			for (int i = 1; i < stellen.length; i++) {
				stellen[i][0] = 0;
				for (int j = i - 1; j >= 0; j--) {
					stellen[i][0] += zahlen[j];
				}
			}
			for (int i = 0; i < stellen.length - 1; i++) {
				stellen[i][1] = stellen[i + 1][0] - 1;
			}
			stellen[stellen.length - 1][1] = stellen[stellen.length - 1][0] + zahlen[zahlen.length - 1] - 1;
			System.out.println(toEdit.length());
			while (true) {
				TimeUnit.SECONDS.sleep(2);
				while (stellen[stellen.length - 1][1] != toEdit.length() - 1) {
					for (int k = 0; k < 2; k++) {
						System.out.println(1);
						
						stellen[stellen.length - 1][k]++;
						
					}
ergibtSinn(stellen); 
				}
				
				
				for(int i = 0; i < stellen.length - 1; i++) {
					if(stellen[i][1] != stellen[i+1][0]) {
						for (int k = 0; k < 2; k++) {
							stellen[i][k]++;
							
						}
					}
				}
				int temp = stellen[stellen.length - 1][1] - stellen[stellen.length - 1][0]; 
				stellen[stellen.length - 1][0] = stellen[stellen.length - 2][1] + 1; 
				stellen[stellen.length - 1][1] = stellen[stellen.length - 1][0] + temp; 

			}

		}

	}
	
	static boolean ergibtSinn(int[][] stellen) {
		for(int i[] : stellen) {
		System.out.println(Arrays.toString(i));
		}
		return false; 
	}
}

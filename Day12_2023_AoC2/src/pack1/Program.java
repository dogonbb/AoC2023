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
		int ergebnis = 0; 
		for (String s : input) {
			String[] zahlenString;
			int a = 0;
			String toEdit = new String("");
			for (int i = 0; i < s.length(); i++) {
				toEdit += s.charAt(i);// erkennen der Zahlen
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
			whileloop: while (true) {
//				TimeUnit.SECONDS.sleep(2);
				while (stellen[stellen.length - 1][1] != toEdit.length() - 1) {
					for (int k = 0; k < 2; k++) {
						

						stellen[stellen.length - 1][k]++;

					}
					if(ergibtSinn(stellen, toEdit)) {
						ergebnis++; 
					}
				}

				//wenn alle intervalle aneinander anschließen und bis zum ende durchgehen -> break whileloop; 
				
				out: for (int i = 0; i < stellen.length - 1; i++) {
					if (stellen[i][1] != stellen[i + 1][0]) {
						for (int k = 0; k < 2; k++) {
							stellen[i][k]++;
							 
						}
						break out; //sobald eins gefunden wurde soll rausgegangen werden, wenn kein gefuden wird:
					}
					
				}
				
				//get the last thing back
				int temp = stellen[stellen.length - 1][1] - stellen[stellen.length - 1][0];
				stellen[stellen.length - 1][0] = stellen[stellen.length - 2][1] /*+1 möglich, weil es immer einen entfernt sein muss*/;
				stellen[stellen.length - 1][1] = stellen[stellen.length - 1][0] + temp;

			}

		}
System.out.println(ergebnis);
	}

	static boolean ergibtSinn(int[][] stellen, String s) {
		//hier prüfen ob die reihenfolge möglich
		//wenn alle # im String s auch noch # in stellen ist (die Werte in Stellen sind #, als start und end)
		out: for(int i = 0; i< s.length(); i++) {
			if(s.charAt(i) == '#') {
				for(int j[] : stellen) {
					if(j[0] <= i && j[1] >= i) {
						break out; 
					}
					if(j[1] == stellen[stellen.length - 1][1]) { //letztes element geprüft aber noch nicht gebreakt
						return false; 
					}
				}
			}
			
		}
		//wenn alle Stellen einen auseinander liegen
		for(int i = 0; i < stellen.length - 1; i++) {
			if(stellen[i][1] + 1 == stellen[i+1][0]) {
				return false; 
			}
		}
		//jeder andere fall nicht!!
		for (int i[] : stellen) {
			System.out.println(Arrays.toString(i));
		}
		return true;
	}
}
 
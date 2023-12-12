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
				
				//letztes Intervall solange nach rechts verschieben bis es ganz außer angekommen ist
				
				//wenn ganz rechts außen angekommen dann nach links suchen, für das nächste intervall 
				//wenn das andere Intervall schon direkt dran -> hinter diesem Intervall weiter suchen 
				//solange bis ein Intervall gefunden wurde, was nicht ganz rechts ist 							oder man bei 0 ankommt
				//alle Intervalle die ganz rechts sind nach links wieder anschließen							return; 
				
				
				// wenn alle intervalle aneinander anschließen und bis zum ende durchgehen ->
				// break whileloop;
				for (int i = 0; i < stellen.length - 1; i++) {
					if (stellen[i][1] != stellen[i + 1][0] - 1) {
						break;
					}
					if (i == stellen.length - 2 && toEdit.length() - 1 == stellen[i + 1][1]) {
						break whileloop;
					}
				}

				
			
			}
		}
		System.out.println("Ergebnis: " + ergebnis);
	}
	
	
	
	static boolean ergibtSinn(int[][] stellen, String s, int mengeZahlen) {
		//über keinen Punkt
		for(int i[] : stellen) {
			for(int j : i) {
				if(s.charAt(j) == '.') {
					return false; 
				}
			}
		}
		//alle # werden abgedeckt
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '#') {
				for(int j = 0; j < stellen.length; j++) {
					if(stellen[j][0] <= i && stellen[j][1] >= i) {
						break; 
					}
					if(j==stellen.length - 1) {
						return false; 
					}
				}
			}
		}
		//alle stellen die in Intervall sind durch # austauschen
		StringBuilder stringBuilder = new StringBuilder(s);
		for(int i = 0; i < stellen.length; i++) {
			for(int j = 0; j < stellen[i].length; j++) {
				stringBuilder.setCharAt(stellen[i][j], '#');
			}
		}
		
		s = stringBuilder.toString(); 
		
		//gucken ob es geanuso viele "Inseln" von # gibt wie es geben soll (mengeZahlen)
		int count = 0; 
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '#') {
				int a = i; 
				while(a < s.length() && s.charAt(a) == '#') {
					a++; 
				}
				count++; 
				i=a; 
			}
		}
		if(count != mengeZahlen)  {
			return false; 
		}
		
	
		
		
		return true;
	}
}
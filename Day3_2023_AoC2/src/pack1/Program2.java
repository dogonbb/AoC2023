package pack1;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

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
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length(); j++) {
				if (input[i].charAt(j) == '*') {
					String[] tempStrings = starAdj(input, j, i); 
					System.out.println(Arrays.toString(tempStrings));
					if ((tempStrings[1] ) != "") {
						int tempZahl =   Integer.parseInt(tempStrings[0])*Integer.parseInt(tempStrings[1]); 
					
							ergebnis += tempZahl; 
						
					}

					/*
					 * LinkedList<Point> tempPoints = new LinkedList<>(); String number = ""; int a
					 * = 0;
					 * 
					 * while(j+a < input[0].length() && Character.isDigit(input[i].charAt(j + a))) {
					 * tempPoints.add(new Point(j + a, i)); number += input[i].charAt(j + a); a++; }
					 * 
					 * j+=a;
					 * 
					 * for(Point p : tempPoints) {
					 * 
					 * if(hashtagAdj(input, p.x, p.y)) { ergebnis += Integer.parseInt(number);
					 * 
					 * break; } }
					 */

				}
			}
		}
		System.out.println(ergebnis);

	}

	static boolean hashtagAdj(String input[], int x, int y) {
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				System.out.println("prüfe: " + i + " " + j);
				if (i > 0 && i < input[0].length() && j > 0 && j < input.length && input[j].charAt(i) != '.'
						&& !Character.isDigit(input[j].charAt(i))) {

					return true;
				}
			}
		}
		return false;
	}

	static String[] starAdj(String input[], int x, int y) {
		int count = 0;
		String number[] = {"", ""};
		for (int i = y - 1; i <=y+ 1; i++) {
			for (int j = x - 1; j <= x + 1; j++) {
				System.out.println(input[i].charAt(j));
			
				if (j >= 0 && j < input[0].length() && i >= 0 && j < input.length && input[i].charAt(j) != '.'
						&& Character.isDigit(input[i].charAt(j))) {
					System.out.println("enter inner if");
					if(count > number.length - 1) {
						String temp[]= {"", ""}; 
						return  temp; //errorvalue
					}
					int a = 0;
					while ( j + a - 1 >= 0 && Character.isDigit(input[i].charAt(j + a - 1))) {

						a--;
					}
					while (j+a < input[0].length() && Character.isDigit(input[i].charAt(j + a))) {
						number[count] +=input[i].charAt(j + a); 
						a++;
					}
					j = a+j; 
					count++;

				}
			}
		}
		return number; 

	}

}

package pack1;


import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

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
		for(int i = 0; i < input.length; i++)  {
			for(int j = 0; j < input[0].length(); j++) {
				if(Character.isDigit(input[i].charAt(j))) {
					LinkedList<Point> tempPoints = new LinkedList<>();
					String number = "";
					int a = 0;
					System.out.println("start: " + j +" " +  i);
					while(j+a < input[0].length() &&  Character.isDigit(input[i].charAt(j + a))) {
						tempPoints.add(new Point(j + a, i)); 
						number += input[i].charAt(j + a); 
						a++; 
					}
					j+=a; 
					System.out.println("end: " + j + i);
					System.out.println("starte den Algorithmus mit: " + number);
					for(Point p : tempPoints) {
						System.out.println("start Algorithmus!" + p.toString());
						if(hashtagAdj(input, p.x, p.y)) {
							ergebnis += Integer.parseInt(number);	
							System.out.println("Füge Zahl " + number + "hinzu");
							break;
						}
					}
					
				}
			}
		}
		System.out.println(ergebnis);
		
		
		
	}
	
	static boolean hashtagAdj(String input[], int x, int y) {
		for(int i = x - 1; i <= x + 1; i++) {
			for(int j = y - 1; j <= y + 1; j++) {
				System.out.println("prüfe: " + i + " " + j);
				if(i > 0 && i < input[0].length() && j > 0 && j < input.length &&  input[j].charAt(i) != '.' && !Character.isDigit(input[j].charAt(i)) ) {
					System.out.println("TRUE");
					return true; 
				}
			}
		}
		return false; 
	}
	
}

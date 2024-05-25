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
		System.out.println(Arrays.toString(input));
		////////////////////////////////////////////
		int maxDown = 0;
		int maxRight = 0; 
		for(String s : input) {
			if(s.charAt(0) == 'D') {
				maxDown +=	Integer.parseInt(Character.toString(s.charAt(2))); 
			}
			if(s.charAt(0) == 'R') {
				maxRight +=	Integer.parseInt(Character.toString(s.charAt(2))); 
			}
		}
		int inkr = 200; 
		int map[][] = new int[maxDown -400][maxRight + 1 + inkr]; //guessed inkr and -400
		//1 - digged
		Point currentPoint = new Point(inkr, inkr); 
		map[currentPoint.y][currentPoint.x] = 1; 
		
	
		for(String s : input) {
			if(s.charAt(0) == 'D') {
				int a = Integer.parseInt(Character.toString(s.charAt(2))); 
				while(a > 0) {
					currentPoint.y++;
					map[currentPoint.y][currentPoint.x] = 1; 
					a--; 
				}
			}
			if(s.charAt(0) == 'R') {
				int a = Integer.parseInt(Character.toString(s.charAt(2))); 
				while(a > 0) {
					currentPoint.x++;
					map[currentPoint.y][currentPoint.x] = 1; 
					a--; 
				}
			}
			if(s.charAt(0) == 'U') {
				int a = Integer.parseInt(Character.toString(s.charAt(2))); 
				while(a > 0) {
					currentPoint.y--;
					map[currentPoint.y][currentPoint.x] = 1; 
					a--; 
				}
			}
			if(s.charAt(0) == 'L') {
				int a = Integer.parseInt(Character.toString(s.charAt(2))); 
				while(a > 0) {
					currentPoint.x--;
					map[currentPoint.y][currentPoint.x] = 1; 
					a--; 
				}
			}
		}
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				if(map[i][j] == 1) {
					System.out.print("#");
				}else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
	}
}

class Point {
	int x, y; 
	
	public Point(int x, int y ) {
		this.x = x; 
		this.y = y; 
	}
}

package pack1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

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
		int inkr = 0; 
		int map[][] = new int[maxDown/* -400*/+ 400][maxRight + 1 + inkr+ 400]; //guessed inkr and -400
		//1 - digged
		Point currentPoint = new Point(200, 200); 
		map[currentPoint.y][currentPoint.x] = 1; 
		
	
		for(String s : input) {
			if(s.charAt(0) == 'D') {
				String number = Character.toString(s.charAt(2)); 
				if(s.charAt(3) != ' ') {
					number += Character.toString(s.charAt(3)); 
				}
				int a = Integer.parseInt(number); 
				while(a > 0) {
					currentPoint.y++;
					map[currentPoint.y][currentPoint.x] = 1; 
					a--; 
				}
			}
			if(s.charAt(0) == 'R') {
				String number = Character.toString(s.charAt(2)); 
				if(s.charAt(3) != ' ') {
					number += Character.toString(s.charAt(3)); 
				}
				int a = Integer.parseInt(number); 
				while(a > 0) {
					currentPoint.x++;
					map[currentPoint.y][currentPoint.x] = 1; 
					a--; 
				}
			}
			if(s.charAt(0) == 'U') {
				String number = Character.toString(s.charAt(2)); 
				if(s.charAt(3) != ' ') {
					number += Character.toString(s.charAt(3)); 
				}
				int a = Integer.parseInt(number); 
				while(a > 0) {
					currentPoint.y--;
					map[currentPoint.y][currentPoint.x] = 1; 
					a--; 
				}
			}
			if(s.charAt(0) == 'L') {
				String number = Character.toString(s.charAt(2)); 
				if(s.charAt(3) != ' ') {
					number += Character.toString(s.charAt(3)); 
				}
				int a = Integer.parseInt(number); 
				while(a > 0) {
					currentPoint.x--;
					map[currentPoint.y][currentPoint.x] = 1; 
					a--; 
				}
			}
		}
		
		
        //choosing line 43 (array 43) in map, got per stdout that in that line there is a possible value to start:
        //BFS
        /*
        for(int i = 0; i < map[43].length; i++) {
        	System.out.println(map[43][i]);
        	if(map[43][i] == 1) {
        		System.out.println(i);
        		return; 
        	}
        }*/
        //knowing through code above that at (247|43) is a # 
        //so we start at (248 | 43)
        
        Point sp = new Point(248, 43); 
        LinkedList<Point> stack = new LinkedList<>(); 
        stack.add(new Point(sp.x, sp.y)); //stack = queue | just didnt renamed it :) 
		while(!stack.isEmpty()){
			
			
			
			int x = stack.get(0).x; 
			int y = stack.get(0).y; 
			stack.remove(0); 
			map[y][x] = 1; 
			if(map[y - 1][x] == 0) {
				map[y - 1][x] = -1; 
				stack.add(new Point(x, y - 1)); 
			}
			if(map[y][x + 1] == 0) {
				map[y][x + 1] = -1; 
				stack.add(new Point(x + 1, y)); 
			}
			if(map[y + 1][x] == 0) {
				map[y + 1][x] = -1; 
				stack.add(new Point(x , y + 1)); 
			}
			if(map[y][x - 1] == 0) {
				map[y][x - 1] = -1; 
				stack.add(new Point(x - 1, y)); 
			}
		}
		
		
		int counter = 0; 
		
		
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] == 1) {
                        writer.write("#");
                        counter++;
                    } else {
                        writer.write(" ");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(counter);
        
     
        
        
        
		
	}
}

class Point {
	int x, y; 
	
	public Point(int x, int y ) {
		this.x = x; 
		this.y = y; 
	}
}

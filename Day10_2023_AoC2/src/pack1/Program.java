package pack1;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

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
		System.out.println(Arrays.toString(input));
		////////////////////////////////////////////

		int x = -1, y = -1; // startx, y

		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length(); j++) {
				if (input[i].charAt(j) == 'S') {
					x = j;
					y = i;
				}
			}
		}
		int sX = x, sY = y;
		LinkedList<Point> path = new LinkedList<>();
		path.add(new Point(sX, sY)); 
		System.out.println("x: " + x + " y: " + y);
		while(x != sX || sY+1 != y) {
			char feldChar = input[y].charAt(x); 
			if (feldChar == 'F') {
				// ich kann nach rechts und nach unten
				// rechts:
				if ((input[y].charAt(x + 1) == '-' || input[y].charAt(x + 1) == 'J' || input[y].charAt(x + 1) == '7')
						&& !contains_list_Point(path, new Point(x+1, y))) {
					path.add(new Point(x + 1, y));

				}
				// unten
				else if ((input[y + 1].charAt(x) == '|' || input[y + 1].charAt(x) == 'J' || input[y + 1].charAt(x) == 'L')
						&& !contains_list_Point(path, new Point(x, y + 1))) {
					path.add(new Point(x, y + 1));
				}
			} else if (feldChar == '|' || feldChar == 'S' ) {
				// oben:
				
				if ((input[y - 1].charAt(x) == '|' || input[y - 1].charAt(x) == 'F' || input[y - 1].charAt(x) == '7')
						&& !contains_list_Point(path, new Point(x, y - 1))) {
					path.add(new Point(x, y - 1));
					
				}
				// unten
				else if ((input[y + 1].charAt(x) == '|' || input[y + 1].charAt(x) == 'J' || input[y + 1].charAt(x) == 'L')
						&& !contains_list_Point(path, new Point(x, y + 1))) {
					path.add(new Point(x, y + 1));
				}
			} else if (feldChar == '-') {
				// links
				if ((input[y].charAt(x - 1) == '-' || input[y].charAt(x - 1) == 'L' || input[y].charAt(x - 1) == 'F')
						&& !contains_list_Point(path, new Point(x-1, y))) {
					path.add(new Point(x - 1, y));
				}
				// rechts
				else if ((input[y].charAt(x + 1) == '-' || input[y].charAt(x + 1) == 'J' || input[y].charAt(x + 1) == '7')
						&& !contains_list_Point(path, new Point(x+1, y))) {
					path.add(new Point(x + 1, y));
				}

			} else if (feldChar == 'L') {
				// oben
				if ((input[y - 1].charAt(x) == '|' || input[y - 1].charAt(x) == 'F' || input[y - 1].charAt(x) == '7')
						&& !contains_list_Point(path, new Point(x, y - 1))) {
					path.add(new Point(x, y - 1));
				}
				// rechts
				else if ((input[y].charAt(x + 1) == '-' || input[y].charAt(x + 1) == 'J' || input[y].charAt(x + 1) == '7')
						&& !contains_list_Point(path, new Point(x + 1, y))) {
					path.add(new Point(x + 1, y));
				}
			} else if (feldChar == 'J') {
				// oben
				if ((input[y - 1].charAt(x) == '|' || input[y - 1].charAt(x) == 'F' || input[y - 1].charAt(x) == '7')
						&& !contains_list_Point(path, new Point(x, y - 1))) {
					path.add(new Point(x, y - 1));
				}
				// links
				else if ((input[y].charAt(x - 1) == '-' || input[y].charAt(x - 1) == 'L' || input[y].charAt(x - 1) == 'F')
						&& !contains_list_Point(path, new Point(x-1, y))) {
					path.add(new Point(x - 1, y));
				}
			} else if (feldChar == '7') {
				// links
				if ((input[y].charAt(x - 1) == '-' || input[y].charAt(x - 1) == 'L' || input[y].charAt(x - 1) == 'F')
						&& !contains_list_Point(path, new Point(x-1, y))) {
					path.add(new Point(x - 1, y));
				}
				// unten
				else if ((input[y + 1].charAt(x) == '|' || input[y + 1].charAt(x) == 'J' || input[y + 1].charAt(x) == 'L')
						&& !contains_list_Point(path, new Point(x, y + 1))) {
					path.add(new Point(x, y + 1));
				}
			}
			
			
			x = path.getLast().x; 
			y = path.getLast().y; 
		}
		System.out.println(path.size()/2
				);
		
		
	}

	
	
	
	
	static boolean contains_list_Point(LinkedList<Point> list, Point p2) {
		for (Point p : list) {
			if (p.x == p2.x && p.y == p2.y) {
				
				return true;
			}
		}
		return false;
	}





	static void remove_doppel_Punkte(LinkedList<Point> list) {
		for (int i = list.size() - 1; i >= 0; i--) {
			for (int j = 0; j < list.size(); j++) {
				if (list.get(i).x == list.get(j).x && list.get(i).y == list.get(j).y && i != j) {
					list.remove(i);
				}
			}
		}
	}

	static void find_shortest_way(int x, int y, String[] input, int[][] inputSteps, int steps) { // does work, but not
																									// for large input
																									// as the puzzle
																									// input
																									
		char feldChar = input[y].charAt(x);
		LinkedList<Point> possibleVisitPoints = new LinkedList<>();

		inputSteps[y][x] = steps;

		if (feldChar == 'F') {
			// ich kann nach rechts und nach unten
			// rechts:
			if ((input[y].charAt(x + 1) == '-' || input[y].charAt(x + 1) == 'J' || input[y].charAt(x + 1) == '7')
					&& inputSteps[y][x + 1] > steps + 1) {
				possibleVisitPoints.add(new Point(x + 1, y));

			}
			// unten
			if ((input[y + 1].charAt(x) == '|' || input[y + 1].charAt(x) == 'J' || input[y + 1].charAt(x) == 'L')
					&& inputSteps[y + 1][x] > steps + 1) {
				possibleVisitPoints.add(new Point(x, y + 1));
			}
		} else if (feldChar == '|' || feldChar == 'S' ) {
			// oben:
			if ((input[y - 1].charAt(x) == '|' || input[y - 1].charAt(x) == 'F' || input[y - 1].charAt(x) == '7')
					&& inputSteps[y - 1][x] > steps + 1) {
				possibleVisitPoints.add(new Point(x, y - 1));
			}
			// unten
			if ((input[y + 1].charAt(x) == '|' || input[y + 1].charAt(x) == 'J' || input[y + 1].charAt(x) == 'L')
					&& inputSteps[y + 1][x] > steps + 1) {
				possibleVisitPoints.add(new Point(x, y + 1));
			}
		} else if (feldChar == '-') {
			// links
			if ((input[y].charAt(x - 1) == '-' || input[y].charAt(x - 1) == 'L' || input[y].charAt(x - 1) == 'F')
					&& inputSteps[y][x - 1] > steps + 1) {
				possibleVisitPoints.add(new Point(x - 1, y));
			}
			// rechts
			if ((input[y].charAt(x + 1) == '-' || input[y].charAt(x + 1) == 'J' || input[y].charAt(x + 1) == '7')
					&& inputSteps[y][x + 1] > steps + 1) {
				possibleVisitPoints.add(new Point(x + 1, y));
			}

		} else if (feldChar == 'L') {
			// oben
			if ((input[y - 1].charAt(x) == '|' || input[y - 1].charAt(x) == 'F' || input[y - 1].charAt(x) == '7')
					&& inputSteps[y - 1][x] > steps + 1) {
				possibleVisitPoints.add(new Point(x, y - 1));
			}
			// rechts
			if ((input[y].charAt(x + 1) == '-' || input[y].charAt(x + 1) == 'J' || input[y].charAt(x + 1) == '7')
					&& inputSteps[y][x + 1] > steps + 1) {
				possibleVisitPoints.add(new Point(x + 1, y));
			}
		} else if (feldChar == 'J') {
			// oben
			if ((input[y - 1].charAt(x) == '|' || input[y - 1].charAt(x) == 'F' || input[y - 1].charAt(x) == '7')
					&& inputSteps[y - 1][x] > steps + 1) {
				possibleVisitPoints.add(new Point(x, y - 1));
			}
			// links
			if ((input[y].charAt(x - 1) == '-' || input[y].charAt(x - 1) == 'L' || input[y].charAt(x - 1) == 'F')
					&& inputSteps[y][x - 1] > steps + 1) {
				possibleVisitPoints.add(new Point(x - 1, y));
			}
		} else if (feldChar == '7') {
			// links
			if ((input[y].charAt(x - 1) == '-' || input[y].charAt(x - 1) == 'L' || input[y].charAt(x - 1) == 'F')
					&& inputSteps[y][x - 1] > steps + 1) {
				possibleVisitPoints.add(new Point(x - 1, y));
			}
			// unten
			if ((input[y + 1].charAt(x) == '|' || input[y + 1].charAt(x) == 'J' || input[y + 1].charAt(x) == 'L')
					&& inputSteps[y + 1][x] > steps + 1) {
				possibleVisitPoints.add(new Point(x, y + 1));
			}
		}

		if (possibleVisitPoints.size() == 0)
			return;
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < inputSteps.length; i++) {	//4692
			for(int j = 0; j < inputSteps[0].length; j++) {
				if(inputSteps[i][j] == Integer.MAX_VALUE) System.out.print(".");
				else System.out.print(inputSteps[i][j]);
			}
			System.out.println();
		}
		steps++;
		for (Point p : possibleVisitPoints) {
			find_shortest_way(p.x, p.y, input, inputSteps, steps);
		}

	}

}

package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

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

		// getting input as integer array:
		int[][] map = new int[input.length][input[0].length()];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length(); j++) {
				map[i][j] = Integer.parseInt(Character.toString(input[i].charAt(j)));
			}
		}
		int[][][] mapVisited = new int[map.length][map[0].length][4/* 4 directions */ * 3/* max 3 Schritte */];
		for (int i = 0; i < mapVisited.length; i++) {
			for (int j = 0; j < mapVisited[i].length; j++) {
				for (int k = 0; k < mapVisited[i][j].length; k++) {
					mapVisited[i][j][k] = 0;
				}
			}
		}

		ListPositions list = new ListPositions(new Position(0, 0, -1, new Dir(1, 0)));// heat = -1, easier insert, will not be deleted
	

		// inserte Startelement
		
		
		list.insert(new Position(0, 0, 0, new Dir(1, 0)));
	
		list.printList();
		// prüfe bei kleinstem Element in welche Richtung möglich (letzte Richtung nicht
		// möglich!!!!)
		// kleinstes Element head.next
		for(;;) {
			
			LinkedList<Position> posnew = new LinkedList<>();

			if (list.head.next.y == map.length - 1 && list.head.next.x == map[0].length - 1) {
				System.out.println("Ergebnis!!!: " + list.head.next.heat);
				break;
			}
			
			switch (list.head.next.d.dir) {
			case 0:
				if (list.head.next.d.steps < 3 && list.head.next.y - 1 >= 0) { // north
					posnew.add(new Position(list.head.next.x, list.head.next.y - 1,
							list.head.next.heat + map[list.head.next.y - 1][list.head.next.x],
							new Dir(list.head.next.d.dir, list.head.next.d.steps + 1)));
				}

				break;
			case 1:
				if (list.head.next.d.steps < 3 && list.head.next.x + 1 < map[0].length) {
					posnew.add(new Position(list.head.next.x + 1, list.head.next.y,
							list.head.next.heat + map[list.head.next.y][list.head.next.x + 1],
							new Dir(list.head.next.d.dir, list.head.next.d.steps + 1)));
				}

				break;
			case 2:
				if (list.head.next.d.steps < 3 && list.head.next.y + 1 < map.length) {
					posnew.add(new Position(list.head.next.x, list.head.next.y + 1,
							list.head.next.heat + map[list.head.next.y + 1][list.head.next.x],
							new Dir(list.head.next.d.dir, list.head.next.d.steps + 1)));
				}

				break;
			case 3:
				if (list.head.next.d.steps < 3 && list.head.next.x - 1 >= 0) {
					posnew.add(new Position(list.head.next.x - 1, list.head.next.y,
							list.head.next.heat + map[list.head.next.y][list.head.next.x - 1],
							new Dir(list.head.next.d.dir, list.head.next.d.steps + 1)));
				}

				break;

			}
			// north
			if (list.head.next.d.dir != 0 && list.head.next.d.dir != 2) {
				if (list.head.next.y - 1 >= 0) {
					posnew.add(new Position(list.head.next.x, list.head.next.y - 1,
							list.head.next.heat + map[list.head.next.y - 1][list.head.next.x], new Dir(0, 1)));
				}
			}

			// right
			if (list.head.next.d.dir != 1 && list.head.next.d.dir != 3) {
				if (list.head.next.x + 1 < map[0].length) {
					posnew.add(new Position(list.head.next.x + 1, list.head.next.y,
							list.head.next.heat + map[list.head.next.y][list.head.next.x + 1], new Dir(1, 1)));
				}
			} 
			// down
			if (list.head.next.d.dir != 2 && list.head.next.d.dir != 0) {
				if (list.head.next.y + 1 < map.length) {
					posnew.add(new Position(list.head.next.x, list.head.next.y + 1,
							list.head.next.heat + map[list.head.next.y + 1][list.head.next.x], new Dir(2, 1)));
				}
			}

			// left
			if (list.head.next.d.dir != 3 && list.head.next.d.dir != 1 ) {
				if (list.head.next.x - 1 >= 0) {
					posnew.add(new Position(list.head.next.x - 1, list.head.next.y,
							list.head.next.heat + map[list.head.next.y][list.head.next.x - 1], new Dir(3, 1)));
				}
			}
			for(int j = posnew.size() - 1; j>=0; j--) {
				if(posnew.get(j).d.dir*3+posnew.get(j).d.steps > 0) {
					if(mapVisited[posnew.get(j).y][posnew.get(j).x][posnew.get(j).d.dir*3+posnew.get(j).d.steps - 1] == 1) {
						posnew.remove(j); 
					}
					else {
						mapVisited[posnew.get(j).y][posnew.get(j).x][posnew.get(j).d.dir*3+posnew.get(j).d.steps - 1] = 1; 
					}
				}
				
			}
			
			list.remove_element_after_head();
	
			for(int j = 0; j<posnew.size(); j++) {
				list.insert(posnew.get(j));
			}
			
			
			
			
		}
	}
}

class ListPositions {
	Position head;

	public ListPositions(Position head) {
		this.head = head;
	}
	
	public void remove_element_after_head() {
		this.head.next = this.head.next.next; 
	}
	

	public void printList() {
		System.out.println("-------------------------");
		Position temp = head;

		while (temp.next != null) {
			System.out.println(temp.toString());
			temp = temp.next;
		}

		System.out.println(temp.toString());
		System.out.println("-------------------------");
	}

	void insert(Position p) { // inserts in Order of heat
		Position temp = head;
		while (temp.next != null && p.heat > temp.next.heat) {
			temp = temp.next;
		}
		
		Position temp2 = temp.next;
		temp.next = p;
		p.next = temp2;
	
	}

}

class Position {
	int x, y, heat/* loss */;
	Dir d;
	Position next = null;

	public Position(int x, int y, int heat, Dir d) {
		this.x = x;
		this.y = y;
		this.heat = heat;
		this.d = d;
	}

	@Override
	public String toString() {
		String s = new String("x: " + x + " y: " + y + " heat: " + heat + " " + d.toString());
		return s;
	}

	boolean compare_to_other_Pos(Position p) {
		if (p.x == x && p.y == y && p.d.dir == d.dir && p.d.steps == d.steps)
			return true;

		return false;
	}

}

class Dir {
	int dir; // 0 north, 1 right (...)
	int steps;

	public Dir(int dir, int steps) {
		this.dir = dir;
		this.steps = steps;
	}

	@Override
	public String toString() {
		String s = new String("dir: " + dir + " steps: " + steps);
		return s;
	}
}

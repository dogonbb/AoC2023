package pack1;

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

		// getting input as integer array:
		int[][] map = new int[input.length][input[0].length()];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length(); j++) {
				map[i][j] = Integer.parseInt(Character.toString(input[i].charAt(j)));
			}
		}
		int[][][] mapVisited = new int[map.length][map[0].length][4
				/* 4 directions */ * 7/* max |{4,5,6,7,8,9,10}| = 7 Schritte */];
		for (int i = 0; i < mapVisited.length; i++) {
			for (int j = 0; j < mapVisited[i].length; j++) {
				for (int k = 0; k < mapVisited[i][j].length; k++) {
					mapVisited[i][j][k] = 0;
				}
			}
		}

		ListPositions list = new ListPositions(new Position(0, 0, -1, new Dir(1, 0)));// heat = -1, easier insert, will
																						// not be deleted

		// inserte Startelement

		list.insert(new Position(0, 0, 0, new Dir(0, 0)));

		list.printList();
		// prüfe bei kleinstem Element in welche Richtung möglich (letzte Richtung nicht
		// möglich!!!!)
		// kleinstes Element head.next
		for (;;) {

			LinkedList<Position> posnew = new LinkedList<>();

			if (list.head.next.y == map.length - 1 && list.head.next.x == map[0].length - 1) {
				System.out.println("Ergebnis!!!: " + list.head.next.heat);
				break;
			}
			// bei 5 Schritten => 0 steps
			switch (list.head.next.d.dir) {
			case 0:
				if (list.head.next.d.steps != 0 && list.head.next.d.steps < 7 && list.head.next.y - 1 >= 0) { // north
					posnew.add(new Position(list.head.next.x, list.head.next.y - 1,
							list.head.next.heat + map[list.head.next.y - 1][list.head.next.x],
							new Dir(list.head.next.d.dir, list.head.next.d.steps + 1)));
				}
				break;
			case 1:
				if (list.head.next.d.steps != 0 && list.head.next.d.steps < 7 && list.head.next.x + 1 < map[0].length) {
					posnew.add(new Position(list.head.next.x + 1, list.head.next.y,
							list.head.next.heat + map[list.head.next.y][list.head.next.x + 1],
							new Dir(list.head.next.d.dir, list.head.next.d.steps + 1)));
				}
				break;
			case 2:
				if (list.head.next.d.steps != 0 && list.head.next.d.steps < 7 && list.head.next.y + 1 < map.length) {
					posnew.add(new Position(list.head.next.x, list.head.next.y + 1,
							list.head.next.heat + map[list.head.next.y + 1][list.head.next.x],
							new Dir(list.head.next.d.dir, list.head.next.d.steps + 1)));
				}
				break;
			case 3:
				if (list.head.next.d.steps != 0 && list.head.next.d.steps < 7 && list.head.next.x - 1 >= 0) {
					posnew.add(new Position(list.head.next.x - 1, list.head.next.y,
							list.head.next.heat + map[list.head.next.y][list.head.next.x - 1],
							new Dir(list.head.next.d.dir, list.head.next.d.steps + 1)));
				}
				break;

			}

			// north
			if (list.head.next.d.dir != 0 && list.head.next.d.dir != 2) {
				if (list.head.next.y - 4 >= 0) {
					int plus = getRangeInArray(map, list.head.next.x, list.head.next.y - 4, list.head.next.x,
							list.head.next.y - 1);

					posnew.add(new Position(list.head.next.x, list.head.next.y - 4, list.head.next.heat + plus,
							new Dir(0, 1)));
				}
			}

			// right
			if (list.head.next.d.dir != 1 && list.head.next.d.dir != 3) {
				if (list.head.next.x + 4 < map[0].length) {
					int plus = getRangeInArray(map, list.head.next.x + 1, list.head.next.y, list.head.next.x + 4,
							list.head.next.y);
					posnew.add(new Position(list.head.next.x + 4, list.head.next.y, list.head.next.heat + plus,
							new Dir(1, 1)));
				}
			}
			// down
			if (list.head.next.d.dir != 2 && list.head.next.d.dir != 0) {

				if (list.head.next.y + 4 < map.length) {
					int plus = getRangeInArray(map, list.head.next.x, list.head.next.y + 1, list.head.next.x,
							list.head.next.y + 4);
					posnew.add(new Position(list.head.next.x, list.head.next.y + 4, list.head.next.heat + plus,
							new Dir(2, 1)));
				}
			}

			// left
			if (list.head.next.d.dir != 3 && list.head.next.d.dir != 1) {

				if (list.head.next.x - 4 >= 0) {
					int plus = getRangeInArray(map, list.head.next.x - 4, list.head.next.y, list.head.next.x - 1,
							list.head.next.y);
					posnew.add(new Position(list.head.next.x - 4, list.head.next.y, list.head.next.heat + plus,
							new Dir(3, 1)));
				}
			}
			// wenn dir ==

			for (int j = posnew.size() - 1; j >= 0; j--) {
				if (posnew.get(j).d.dir * 3 + posnew.get(j).d.steps > 0) {
					if (mapVisited[posnew.get(j).y][posnew.get(j).x][posnew.get(j).d.dir * 7 + posnew.get(j).d.steps
							- 1] == 1) {
						posnew.remove(j);
					} else {
						mapVisited[posnew.get(j).y][posnew.get(j).x][posnew.get(j).d.dir * 7 + posnew.get(j).d.steps
								- 1] = 1;
					}
				}

			}

			list.remove_element_after_head();

			for (int j = 0; j < posnew.size(); j++) {
				list.insert(posnew.get(j));
			}

		}
	}

	static int getRangeInArray(int mat[][], int x1, int y1, int x2, int y2) { // parameter have to be: x1==x2||y1==y2 dann der andere parameter a1<a2
//		System.out.println(x1 + " " + x2 + " " + " "+ y1 + " " + y2);
		int ergebnis = 0;
		if (x1 == x2) {
			for (int i = y1; i <= y2; i++) {
				ergebnis += mat[i][x1];
			}
		} else {
			for (int i = x1; i <= x2; i++) {
				ergebnis += mat[y1][i];
			}
		}
//		System.out.println(ergebnis);
		return ergebnis;
	}

}

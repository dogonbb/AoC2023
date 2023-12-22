package pack1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
		ArrayList<Beam> beams = new ArrayList<>();
		int[][] alreadyVisited = new int[input.length][input[0].length()]; // with dir!!
		beams.add(new Beam(-1, 0, 1));
		for (int i = 0; i < alreadyVisited.length; i++) {
			for (int j = 0; j < alreadyVisited[0].length; j++) {
				alreadyVisited[i][j] = 4; // nothing visited
			}
		}
//		alreadyVisited[0][0] = 1;

		while (beams.size() != 0) {
			int l = beams.size(); // size could be edited in for loop
			for (int i = l - 1; i >= 0; i--) { // going through list from behind so i can del elements
				// schritt machen
				if (beams.get(i).dir == 0)
					beams.get(i).y -= 1;
				else if (beams.get(i).dir == 1)
					beams.get(i).x += 1;
				else if (beams.get(i).dir == 2)
					beams.get(i).y += 1;
				else if (beams.get(i).dir == 3)
					beams.get(i).x -= 1;
				// prüfen ob schon visited => falls ja beam löschen, falls nein zu visited
				// hinzufügen
				if (beams.get(i).x > input[0].length() - 1 || beams.get(i).y > input.length - 1
						|| Math.min(beams.get(i).x, beams.get(i).y) < 0) {
					beams.remove(i);
				}
				 else {
					
					// remove if outside of the grid
					if (alreadyVisited[beams.get(i).y][beams.get(i).x] == beams.get(i).dir) {
						beams.remove(i);
					}else { // not outside of the grid
						alreadyVisited[beams.get(i).y][beams.get(i).x] = beams.get(i).dir;
						// neue direction berechnen und beam ggf. hinzufügen
						if (input[beams.get(i).y].charAt(beams.get(i).x) == '\\') {
							switch (beams.get(i).dir) {
							case 0:
								beams.get(i).dir = 3;
								break;
							case 1:
								beams.get(i).dir = 2;
								break;
							case 2:
								beams.get(i).dir = 1;
								break;
							case 3:
								beams.get(i).dir = 0;
								break;

							}
						} else if (input[beams.get(i).y].charAt(beams.get(i).x) == '/') {
							switch (beams.get(i).dir) {
							case 0:
								beams.get(i).dir = 1;
								break;
							case 1:
								beams.get(i).dir = 0;
								break;
							case 2:
								beams.get(i).dir = 3;
								break;
							case 3:
								beams.get(i).dir = 2;
								break;

							}
						} else if (input[beams.get(i).y].charAt(beams.get(i).x) == '|') {
							switch (beams.get(i).dir) {
							case 0:
								// no dir change
								break;
							case 1:
								beams.get(i).dir = 0;
								beams.add(new Beam(beams.get(i).x, beams.get(i).y, 2)); 
								break;
							case 2:
								// no dir change
								break;
							case 3:
								beams.get(i).dir = 0;
								beams.add(new Beam(beams.get(i).x, beams.get(i).y, 2)); 
								break;

							}
						} else if (input[beams.get(i).y].charAt(beams.get(i).x) == '-') {
							switch (beams.get(i).dir) {
							case 0:
								beams.get(i).dir = 1;
								beams.add(new Beam(beams.get(i).x, beams.get(i).y, 3)); 
								break;
							case 1:
								// no dir change
								break;
							case 2:
								beams.get(i).dir = 1;
								beams.add(new Beam(beams.get(i).x, beams.get(i).y, 3)); 
								break;
							case 3:
								// no dir change
								break;

							}
						} else if (input[beams.get(i).y].charAt(beams.get(i).x) == '.') {
							//no dir change
						}
					}
				}
			}
		}
		int klara = 0; 
		for (int i = 0; i < alreadyVisited.length; i++) {
			for (int j = 0; j < alreadyVisited[0].length; j++) {
				if(alreadyVisited[i][j] != 4) {
					klara++; 
//					System.out.print("#");	
				}else {
//					System.out.print('.');
				}
			}
//			System.out.println();
		}
		System.out.println(klara);
	}
}

class Beam {
	int x, y;
	int dir; // note: 0 north, 1 east, 2 south, 3 west

	public Beam(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

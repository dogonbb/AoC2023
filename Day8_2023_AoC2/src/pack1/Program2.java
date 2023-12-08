package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Program2 {
	public static void main(String[] args) throws IOException, InterruptedException {
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

		String instruction = input[0];

		int[] startPoints;
		// finding number of starting points
		int counter = 0;
		for (int j = 2; j < input.length; j++) {
			if (input[j].charAt(2) == 'A') {
				counter++;
			}
		}

		startPoints = new int[counter];
		// get starting points:
		counter = 0;
		for (int j = 2; j < input.length; j++) {
			if (input[j].charAt(2) == 'A') {
				startPoints[counter] = j;

				counter++;
			}
		}
		LinkedList<Long> steps = new LinkedList<>();

		for (int k = 0; k < startPoints.length; k++) {
			int startLine = startPoints[k];
			int schritte = 0;
			out: while (true) {
				for (int i = 0; i < instruction.length(); i++) {
					schritte++;
					String tempString = "";
					if (instruction.charAt(i) == 'L') {
						tempString = input[startLine].substring(7, 10);
					} else if (instruction.charAt(i) == 'R') {
						tempString = input[startLine].substring(12, 15);
					}
					if (tempString.charAt(2) == 'Z')
						break out;
					for (int j = 2; j < input.length; j++) {
						if (input[j].substring(0, 3).equals(tempString)) {
							startLine = j;
						}
					}
				}
			}

			steps.add((long) schritte);
			startPoints[k] = startLine;

		}
		//weg von ..A -> ..Z = weg von ..Z -> ..Z
		
		
		long lcm = steps.get(steps.size() - 1); 
		steps.remove(steps.size() - 1); 
		
		for(long step : steps) {
			lcm = lcm * step/berechneGgT(lcm, step); 
		}
			System.out.println(lcm);
	

	}




  

    public static long berechneGgT(long a, long b) {
        // Verwende den Euklidischen Algorithmus, um den ggT zu berechnen
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }

        return Math.abs(a);
    }

}

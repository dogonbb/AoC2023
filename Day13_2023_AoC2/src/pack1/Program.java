package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		input = new String[count /* added this, just for this day: */ + 1];
		/* and this: */input[input.length - 1] = "9gidhfihgfdg0ßh"; // so i can parse input easier
		count = 0;
		br = new BufferedReader(new FileReader("input.txt"));
		while ((st = br.readLine()) != null) {
			input[count] = st;
			count++;
		}
		System.out.println(Arrays.toString(input));
		////////////////////////////////////////////

		// ein Feld einlesen
		int start = 0;
		int ergebnis = 0;
		for (int i = 0; i < input.length; i++) {
			String[] input2;

			if (input[i].equals("") || i == input.length - 1) {

				input2 = new String[i - start];
				System.arraycopy(input, start, input2, 0, i - start);
				start = i + 1;

				// vertical proof:
				// immer linke (a = i)und rechts (b = i+1)zeile in String
				boolean hinzugefügt = false;
				for (int j = 0; j < input2[0].length() - 1; j++) {
					String string1 = "";
					String string2 = "";

					int a = j, b = j + 1;
					while (true) {
						for (int k = 0; k < input2.length; k++) {
							string1 += input2[k].charAt(a);
							string2 += input2[k].charAt(b);
						}
						if (!string1.equals(string2)) {
							break;
						}
						if (a == 0 || b == input2[0].length() - 1) {
							ergebnis += j + 1;
							hinzugefügt = true;
							break;
						}
						a--;
						b++;

					}
				}
				if (!hinzugefügt) {
					for (int j = 0; j < input2.length - 1; j++) {
						String string1, string2;

						int a = j, b = j + 1;
						while (true) {
							string1 = input2[a];
							string2 = input2[b];
							if (!string1.equals(string2)) {
								break;
							}
							if (a == 0 || b == input2.length - 1) {
								ergebnis += (j + 1) * 100;
								;

								break;
							}
							a--;
							b++;
						}
					}

				}

			}

		}
		System.out.println(ergebnis);

	}

	// fucntion to compare to Strings:

}

package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Program2 {
	static String[] input;

	public static void main(String[] args) throws IOException {
		// written code for every day

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

		long inputNumber[] = new long[input[0].split("\\s+").length - 1];
		String[] inputNumberString = input[0].split("\\s+");
		for (int i = 1; i < inputNumberString.length; i++) {
			inputNumber[i - 1] = Long.parseLong(inputNumberString[i]);
		}
		long min = Long.MAX_VALUE;
		// count all number
		long start[] = new long[10];
		long end[] = new long[10];
		for (int i = 0; i < inputNumber.length; i += 2) {
			start[i / 2] = inputNumber[i];
			end[i / 2] = inputNumber[i + 1];
		}

		Task[] tasks = new Task[500]; // 50 threads for 1 start - end
		for (int i = 0; i < 10; i++) {
			long x = (long) ((end[i] - start[i]) / 50);
			for (int j = 0; j < 49; j++) {
				tasks[i * 50 + j] = new Task(start[i] + x * j, start[i] + x * j + x);
				tasks[i * 50 + j].run();

				if (j == 48) {
					tasks[i * 50 + 49] = new Task(start[i] + x * j, end[i]);
					tasks[i * 50 + 49].run();
				}
			}
		}
		out: while (true) {
			for (int i = 0; i < tasks.length; i++) {
				if (tasks[i].end_task = false) {
					i = Integer.MAX_VALUE;
				}
				if (i == tasks.length - 1)
					break out;
			}
		}

		for (int i = 0; i < tasks.length; i++) {
			System.out.println(i);
			if (min > tasks[i].min)
				min = tasks[i].min;
		}

		System.out.println(min);
	}

	static long seed_to_location(String[] input, long seed) {
		int a = 3;
		long dest = seed;
		out: while (a < input.length && Character.isDigit(input[a].charAt(0))) {
			String[] inputZeile = input[a].split("\\s+");
			long[] inputZeileInteger = new long[inputZeile.length];
			for (int i = 0; i < inputZeile.length; i++) {
				inputZeileInteger[i] = Long.parseLong(inputZeile[i]);
			}

			if (inputZeileInteger[1] + inputZeileInteger[2] > dest && inputZeileInteger[1] <= dest) {
				long x = dest - inputZeileInteger[1];

				dest = inputZeileInteger[0] + x;

				do {
					a++;
					if (a >= input.length)
						break out;
				} while (input[a].length() == 0 || Character.isDigit(input[a].charAt(0)));

			}

			a++;
			if (a >= input.length)
				break;
			if (input[a].length() == 0) {
				a += 2;
			}
		}
		return dest;
	}
}

class Task extends Thread {
	long start, end;
	long min = Long.MAX_VALUE;
	boolean end_task = false;

	public Task(long start, long end) {
		this.end = end;
		this.start = start;
	}

	@Override
	public void run() {

		for (long i = start; i < end; i++) {
			long a = Program2.seed_to_location(Program2.input, i);
			if (a < min)
				min = a;
			
		}
		end_task = true;
	}
}

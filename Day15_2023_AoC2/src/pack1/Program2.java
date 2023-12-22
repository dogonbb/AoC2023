package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Program2 {
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
		LinkedList<Element>[] boxes = new LinkedList[256];
		  for (int i = 0; i < boxes.length; i++) {
	          boxes[i] = new LinkedList<>();
	      }
		String[] list = input[0].split(",");
		for(String s : list) {
			String temp = ""; 
			if(s.contains("-")) {
				int a = 0; 
				while(s.charAt(a) != '-') {
					temp += s.charAt(a); 
					a++; 
				}
				int ascii = get_hash(temp); 
				for(int i = boxes[ascii].size() - 1; i >= 0; i--) {
					if(boxes[ascii].get(i).s.equals(temp) ) {
						boxes[ascii].remove(i); 
						break; 
					}
				}
			}else if(s.contains("=")){
				int a = 0; 
				while(s.charAt(a) != '=') {
					temp += s.charAt(a); 
					a++; 
				}
				int ascii = get_hash(temp); 
				a++; 
				String value = s.substring(a,s.length()); 
				int valueInt = Integer.parseInt(value);
				if( boxes[ascii].size() == 0) boxes[ascii].add(new Element(temp, valueInt)); 
				for(int i = boxes[ascii].size() - 1; i >= 0; i--) {
					if(boxes[ascii].get(i).s.equals(temp) ) {
						boxes[ascii].get(i).value = valueInt; 
						break; 
					}
					if(i == 0) {
						System.out.println("ghdsfig");
						boxes[ascii].add(new Element(temp, valueInt)); 
					}
				}
				
				
			}
			
			
		}
		int ergebnis = 0; 
		for(int i = 0; i < boxes.length; i++) {
			for(int j = 0; j < boxes[i].size(); j++) {
				ergebnis += (i+1) * (j+1) *  boxes[i].get(j).value; 
			}
			
			
		}
		System.out.println(ergebnis);
	}
	
	static int get_hash(String string) {
		int assci = 0;
		for(int i = 0; i < string.length(); i++) {
			assci += (int) string.charAt(i);
			assci*=17; 
			assci%=256; 
		}
		return assci; 
		
	}
	
}

class Element {
	String s;
	int value;
	public Element(String s, int value) {
		this.s = s; 
		this.value = value; 
	}
}



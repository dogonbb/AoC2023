package pack1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Program2 {
	static LinkedList<LinkedList<Integer>> sammel = new LinkedList<>(); 
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
		
		
		int ergebnis = 0; 
		
		for(String s : input) {
			sammel.clear(); 
			String[] splited = s.split("\\s+");
			sammel.add(new LinkedList<>()); 
			int[] splitedInt = new int[splited.length]; 
			for(int i = 0; i < splited.length; i++) {
				splitedInt[i] = Integer.parseInt(splited[i]); 
			}
			for(int i : splitedInt) {
				sammel.get(0).add(i);
			}
			create_new_sub_list(sammel.get(0)) ;
			for(LinkedList<Integer> i : sammel) System.out.println(i);
			sammel.get(sammel.size() - 1).add(0); 
			for(int i = sammel.size() - 1;  i >= 1; i--) {
				System.out.println(sammel.get(i).get(sammel.get(i).size() - 1) + "  " + sammel.get(i - 1).get(0));
				int x =   sammel.get(i - 1).get(0)- sammel.get(i).get(sammel.get(i).size() - 1) ; 
				sammel.get(i - 1).add(x); 
			}
		
			ergebnis+= sammel.get(0).get(sammel.get(0).size() - 1); 
		}
		System.out.println(ergebnis);
		
		
	}
	
	static void create_new_sub_list(LinkedList<Integer> list ) {
		LinkedList<Integer> temp = new LinkedList<>(); 
		for(int i = list.size() - 1 ; i >= 1 ; i--) {
			temp.add(list.get(i) - list.get(i - 1 )); 
		}

		reverse_linked_list(temp);
	
		sammel.add(temp);
		if(check_if_list_contains_other_than_zeros(temp)) create_new_sub_list(temp); 
		
		//jetzt alle sub Listen in sammel enthalten
	
	
		
		
		
	
		
	}
	
	static void reverse_linked_list(LinkedList<Integer> list) { //works
		
		//appending from behind then removing half of list
		int a = list.size(); 
		for(int i = a - 1; i >= 0; i--) {
			list.add(list.get(i)); //double list
		}
		a = list.size() / 2; 
		for(int i = 0; i < a; i++) {
			list.remove(0); 
		}
		
	}
	
	
	static boolean check_if_list_contains_other_than_zeros(LinkedList<Integer> list) {
		for(int i : list) {
			if(i != 0) {
				return true; 
			}
		}
		return false; 
	}
	
}

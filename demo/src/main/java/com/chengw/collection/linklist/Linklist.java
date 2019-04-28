package com.chengw.collection.linklist;
import java.util.*;

public class Linklist {

	private static Scanner sc;

	public static void main(String[] args) {
		List<Integer> list = new LinkedList<Integer>();
		Random r = new Random();
		sc = new Scanner(System.in);
		System.out.println("���������С");
		int size;
		size = sc.nextInt();
		for(int i = 0; i < size;i++){ 
			list.add(r.nextInt(5*size));	
		}
		long start = System.currentTimeMillis();
		Collections.sort(list);
		long end = System.currentTimeMillis();
		System.out.println(list);
		System.out.println("Time ="+ (end-start)+"����");
		
	}
	
	

}

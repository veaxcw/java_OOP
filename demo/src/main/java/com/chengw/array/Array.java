
package com.chengw.array;
import java.util.*;
public class Array {
	public static void main(String[] args) {
		int[] a = new int[5];
		int[] b = new int[5];
		int[] c = new int[6];
		Integer[] m = new Integer[5];//
		Integer[] n = new Integer[5];
		Arrays.fill(a, 5);
		Arrays.fill(b, 5);
		Arrays.fill(c, 50);
		Arrays.fill(m,new Integer(55));
		Arrays.fill(n, new Integer(55));
		System.out.println("a = "+toString(a));
		System.out.println("b = "+toString(b));
		//System.arraycopy(a, 0, b, 0, a.length);//数组复制
		//System.out.println("b = "+toString(b));
		//System.arraycopy(c, 0, a, 0, a.length);
		//System.arraycopy(n, 0, m, m.length/2, n.length);
		//System.out.println("a = "+toString(a));
		System.out.println("m = "+Arrays.asList(m));//
		System.out.println("n = "+Arrays.asList(n));
		System.out.println(Arrays.equals(a,b));
		System.out.println(Arrays.equals(m,n));
	} 

	private static String toString(int[] a) {
		StringBuffer buf = new StringBuffer("[");//
		int i;
		for(i = 0;i < a.length;i++) {
			buf.append(a[i]);
			if(i < a.length-1)
				buf.append(",");
		}
		buf.append("]");
		return buf.toString();
	}
}

package com.chengw.reflection.demo;

import com.chengw.reflection.objectanalyzer.ObjectAnalyser;
import lombok.Data;

/**
 * @author veax
 */
@Data
public class Book {

	static void staticMethod() {
		System.out.println("ִThis is static method");
	}

	private String name;
	public int id;
	private float price;
	protected boolean isLoan;
	public Book() {}

	protected Book(String _name,int _id) {
		this.name = _name;
		this.id = _id;
	}
//	public Book(String...strings) throws NumberFormatException{
//		id = Integer.valueOf(strings.length == 0?strings[0]:strings[1]);
//	}
	public void print() {
		System.out.println("name:" + this.name + "\n" +
				           " id:" + this.id + "\n" +
				           "price:" + this.price);
	}

	public int publicMethod(int i) {
		System.out.println("ִthis is public method");
		return i++;
	}

	protected int protectedMethod(String s,int i) throws NumberFormatException{
		System.out.println("ִthis is protected Method");
		return Integer.valueOf(s) + i;
	}

	private String privateMethod(String...strings) {
		System.out.println("ִthis is private method");
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < strings.length; i++) {
			str.append(strings[i]);
		}
		return str.toString();
	}

	@Override
	public String toString() {
		return new ObjectAnalyser().toString(this);
	}
}

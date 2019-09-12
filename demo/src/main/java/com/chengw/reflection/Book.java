package com.chengw.reflection;

public class Book {
	String name;
	int id;
	int price;

	private Book() {}

	protected Book(String _name,int _id) {
		this.name = _name;
		this.id = _id;
	}

	public Book(String...strings) throws NumberFormatException{
		if(0 < strings.length)
			id = Integer.valueOf(strings[0]);
		if(1 < strings.length)
			price = Integer.valueOf(strings[1]);
	}

	public void print() {
		System.out.println("name:" + this.name + "\n" + " id:" + this.id + "\n" + this.price);
	}

}

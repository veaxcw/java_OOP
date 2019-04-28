package com.chengw.anonymousClassDemo;
abstract class AnonymousClass {
	abstract public String value();

}
public class AnonymousClassDemo{
	public AnonymousClass makeac(){
		return new AnonymousClass() {
		String string = "AnonymousClass";
		public String value() {
			return string;
		}
		};
	}
	public static void main(String[] args) {
	AnonymousClassDemo a = new AnonymousClassDemo();
	AnonymousClass a1 = a.makeac();
		System.out.println(a1.value());
		
	}
}

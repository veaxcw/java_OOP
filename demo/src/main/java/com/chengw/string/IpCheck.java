package com.chengw.string;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class IpCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ragex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
		Scanner scan = new Scanner(System.in);
		String Ip = scan.next();
		Pattern pattern = Pattern.compile(ragex);
		Matcher matcher = pattern.matcher(Ip);
		boolean bool = matcher.matches();
		if(bool)
			System.out.println("Done");
		
	}

}

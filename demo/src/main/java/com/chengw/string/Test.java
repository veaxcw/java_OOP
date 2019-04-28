package com.chengw.string;


import static utils.Addresses.printAddresses;

public class Test {



	public static void main(String[] args) {
		String str1 = "Persistence can produce a miracle Unfortunately" 
					+ " very fel people can persist long enough to see a miacle happen";
		String str2 = "The secret of Success if Persistence";
		String str3 = str1.substring(0);
		String str4 = str2.substring(0);
		str4 = str4.concat(str1.substring(5, 10));
		System.out.println(str3.concat(str2.substring(0)));
		System.out.println(str4);
		
	}

	@org.junit.jupiter.api.Test
	public void test(){



		String str1 = "1234";//这个是放在常量池中
		String str2 = new String("1234");//对象是放在堆去
		String str3 = new String("12") + new String("34");
		String str4 = new String("1234");
		String str5 = "1234";
		String str6 = "12" + "34";



		printAddresses("str1",str1);
		printAddresses("str2",str2);
		printAddresses("str3",str3);
		printAddresses("str4",str4);
		printAddresses("str5",str5);
		printAddresses("str6:",str6);


		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		System.out.println(str3.hashCode());

		System.out.println(str1==str2);//false
		System.out.println(str3==str2);//false
		System.out.println(str2==str4);//false
		System.out.println(str1==str5);//true
		System.out.println(str1==str6);//true




	}




}

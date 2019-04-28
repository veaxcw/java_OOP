package com.chengw.math;
import java.util.Date;
import java.util.Calendar;
public class DateTest {
	public static void main(String[] args) {
		Date today = new Date();
		Date Day = new Date();
		System.out.println(today.toString());
		try {
			Thread.sleep(1);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		Date Day2 = new Date();
		System.out.println(Day2.toString());
		System.out.println(Day.before(Day2));
		//Calendar
		System.out.println("Calendar");
		Calendar c = Calendar.getInstance();//Calendar�ǳ�����  ����ʹ��new�ؼ��֣�ͨ��getInstance ������һ������
		System.out.println(c.getTime());//��ȡ��ǰʱ��
		System.out.println(c.get(Calendar.YEAR));//��ȡ��ǰ���
		System.out.println(c.get(Calendar.MONTH));//��ȡ��ǰ�·�
		System.out.println(c.get(Calendar.DATE));//��ȡ��ǰ��
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		System.out.println(c.get(Calendar.HOUR_OF_DAY));
		System.out.println(c.get(Calendar.MINUTE));
		System.out.println(c.get(Calendar.SECOND));
		
	}

}

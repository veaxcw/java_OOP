package com.chengw.collection;
import java.util.*;

import org.junit.jupiter.api.Test;
import student.Student;
public class CollectionDemo {
	public static void main(String[] args) {
		/*c.add(1);
		c.add(2);
		c.add("yeah!");
		System.out.println(c.isEmpty());
		System.out.println(c.remove("yeah!"));
		*/
		ArrayList<Student> students = new ArrayList<Student>();
		Student s1 = new Student("����",24);
		Student s2 = new Student("����",22);
		Student s3 = new Student("����",29);
		students.add(s1);
		students.add(s2);
		students.add(s3);
		Iterator<Student> it = students.iterator();//���������������Ϊ������±� ���������е�ָ��
		Object[] objs = students.toArray();//������ת��������
		for (Object obj:objs
			 ) {
			System.out.println(obj.getClass());

		}
		/*while(it.hasNext()) {
			Student s = (Student)objs[i];
			it.next();//�൱��temp = temp->next 
			System.out.println(s.getname()+ "----"+s.getage());
			i++;
		}*/
		while(it.hasNext()) {//�����Ż�
			Student s = (Student)students.get(1);
			it.next();
			System.out.println(s.getname()+ "----"+s.getage());
		}
		
	}

	@Test
	public  void  hashMap(){
		Map<Integer,Integer> test = new HashMap<>();
		for(int i = 0;i < 10; i++){
			test.put(i,i<<2);
		}
	}

	/**hashmapH�м���hash���㷨***/
	int hash(Object key) {
		int h;
		System.out.println(key.hashCode());
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	/**
	 * String�е�hashcode�㷨
	public int hashCode() {
		int h = hash;
		if (h == 0 && value.length > 0) {
			char val[] = value;

			for (int i = 0; i < value.length; i++) {
				h = 31 * h + val[i];
			}
			hash = h;
		}
		return h;
	}
	 **/

	@Test
    /**�о�hashMap��putVal Դ��**/
	void test(){
		String demo = "veax";

		//�Լ�û����������һ��demo��hashCode,�Լ�hashMap�е�hashֵ
		char[] test = {'v','e','a','x'};
		int testHashCode = 0;
		for(int i = 0;i < test.length;i++){
			testHashCode = testHashCode*31 + test[i];
		}
		/**����Ƕ�ӦhashMap�е�hashֵ*/
		int hashTest = testHashCode^(testHashCode >>> 16);

		//ģ����������hashMap�е�λ��
		int size = 10;//����hashMap��bucket�Ĵ�С
		int location =  size & hashTest;
        /**
         * �����location��Ľڵ�Ϊ��,��ֵ����K,V���½ڵ��������
         * ����ǿ�,��Ƚϸýڵ��hash,key���β��Ƿ���ͬ,������key�ظ�
         * ���������ظ�����Ϊ��K,V�����½ڵ㡣**/

		int hash = hash(demo);
		System.out.println(hash);
	}


	@Test
	/**HashSet������ָ�����˳��������˳��һ��**/
    public void testHashSet(){
	    /**HashSet������HashMap��key��ʵ�ֲ��ظ�**/
	    HashSet<String> demo = new HashSet<>();
	    StringBuilder compare = new StringBuilder("[");
	    Random r = new Random();
	    r.setSeed(System.currentTimeMillis());
	    for(int i = 0;i < 10;i++){
	    	String temp = String.valueOf((char)(r.nextInt(100) + 33));
	    	compare.append(temp + ".");
			demo.add(temp);
        }
	    compare.append("]");

	    int j = 0;
	    StringBuilder str = new StringBuilder();
	    while(j++ != 1){
	    	str.append("{");


            for (String e : demo) {
               str.append(e + ".");
            }
            str.append("}");
            System.out.println(str.toString());
            str.delete(0,str.length());
        }

	    System.out.println(compare);

    }


    /**TreeSetDemo**/
    /**��ײ�ʵ��ΪTreeMap**/
    /***
	 * TreeMapʵ����NavigableMap�ӿ�
	 * TreeMap�ĵײ�ʵ��Ϊ�����  */
	public void testTreeSet(){
		//todo
		/**��ѧ��������˵**/
	}

}

package com.chengw.enumTest;
import java.util.EnumMap;
public class Test {
	private EnumMap<DataBaseType,String> urls = new EnumMap<DataBaseType,String>(DataBaseType.class);
	public Test(){
		urls.put(DataBaseType.DB2, "jdbc:db2://localhost:5000/sample");
		urls.put(DataBaseType.MYSQL, "jdbc:mysql://localhost/mydb");
		urls.put(DataBaseType.ORACLE, "jdbc:oracle:thin:@localhost:1521/mydb");
	}
	public String getURL(DataBaseType type){
		return this.urls.get(type);
	}

}

package com.datadriven.frame.util;

import java.util.Hashtable;

public class ReadingData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\config\\testcases\\TestData.xlsx");
		int rows=xls.getRowCount("Login");
		System.out.println("Total rows-"+ rows);
		
		xls.setCellData("Login","Password", 20, "DrPatel");
		
		Hashtable table = new Hashtable();
		table.put("name", "Automarg1");
		table.put("City", "Bartlett");
		
		System.out.println(table.get("name"));
		
	}

}

package com.datadriven.frame.util;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestA {

	@Test(dataProvider="getData")
	public void testA(Hashtable<String,String>data) {
		System.out.println(data.get("Runmode")+"___"+data.get("Col1")+"____"+data.get("Col2"));
		
	}
	
	@DataProvider
	public Object[][] getData(){
		// TODO Auto-generated method stub
		String testCaseName="TestA";
		String sheetName="Data";
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\config\\testcases\\TestData.xlsx");

		int testStartRowNum=1;
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(testCaseName)){
			testStartRowNum++;
		}
		System.out.println("Test starts from row "+ testStartRowNum);
		int colStartRowNum=testStartRowNum+1;
		int dataStartRowNum=testStartRowNum+2;
		
		
		//Calculate rows of the data 
		int rows=0;
		while(!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals("")){
			rows++;
		}
		System.out.println("total rows are -"+ rows);
		
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals("")){
			cols++;
			
		}
		System.out.println("total cols are -"+ cols);
		
		Object[][]data = new Object[rows][1];
		//read the data
		int dataRow=0;
		Hashtable<String, String>table=null;
		for(int rNum=dataStartRowNum; rNum<dataStartRowNum+rows;rNum++) {
		table =new Hashtable<String,String>();
			for(int cNum=0; cNum<cols;cNum++) {
				String key=xls.getCellData(sheetName,cNum,colStartRowNum);
				String value=xls.getCellData(sheetName, cNum, rNum);
				table.put(key, value);
		}
			data[dataRow][0]=table;
		dataRow++;
		}
		return data;
	} 
		
	}

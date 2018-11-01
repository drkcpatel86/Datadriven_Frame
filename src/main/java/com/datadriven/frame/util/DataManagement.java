package com.datadriven.frame.util;

public class DataManagement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testCaseName="TestC";
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


		for(int rNum=dataStartRowNum; rNum<dataStartRowNum+rows;rNum++) {
		for(int cNum=0; cNum<cols;cNum++) {
			System.out.println(xls.getCellData(sheetName, cNum, rNum));
		}

}
}
	
}
package com.fusion.block1;


import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class GetExcels extends Base {


//Main test class
	@Test(dataProvider="getdata")
	public void loginApp(String dates) throws Exception {

//Calling the Login method 
		logIn();

//Switch to inner frame of the web page
		driver.switchTo().frame(0);

		//Select date using calendar
		/*driver.findElement(By.id("_paramsAs_of_dateAnchor")).click();
		Select sel = new Select(driver.findElement(By.id("_paramsAs_of_dateCalendar_divmonthPicker")));
		List<WebElement> sel1 = sel.getAllSelectedOptions();

		for(int i=0;i<sel1.size();i++) {
			sel.selectByIndex(i);
		}*/
		 

		driver.findElement(By.xpath("//input[@class='inputbox']")).clear();

		driver.findElement(By.xpath("//input[@class='inputbox']")).sendKeys(dates);
		
		
		driver.findElement(By.id("reportViewApply")).click();
		System.out.println("Report Downloaded for "+dates);
        
		Thread.sleep(15000);

	}


//Data provider to send data one by one
	@DataProvider(name = "getdata")
	public Object[][] getData() throws Throwable{

		return readExcel(xlpath);

	}

	
//To Read the Excel File values
	
	public Object[][] readExcel(String path) throws Exception{
		FileInputStream fs = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		XSSFSheet sh = wb.getSheetAt(0);


		Object[][] data = new Object[sh.getLastRowNum()][1]; 

		for(int i=1;i<=sh.getLastRowNum();i++) {
			for(int j=0;j<sh.getRow(i).getLastCellNum();j++) {
				Cell cell=sh.getRow(i).getCell(j); 

				DataFormatter format= new DataFormatter();
				data[i-1][j]=format.formatCellValue(cell);
				
			}
		}

		return data;

	}
	


}

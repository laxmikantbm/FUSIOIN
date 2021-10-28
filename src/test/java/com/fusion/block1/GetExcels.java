package com.fusion.block1;


import java.io.FileInputStream;

import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetExcels {

	String url = "https://hcgt.fa.us1.oraclecloud.com/analytics/saw.dll?bipublisherEntry&Action=open&itemType=.xdo&bipPath=%2FCustom%2FPower%20BI%2FHR%20PBI%20Active%20No%20Payroll%20Date%20Parameter%20Report.xdo&path=%2Fshared%2FCustom%2FPower%20BI%2FHR%20PBI%20Active%20No%20Payroll%20Date%20Parameter%20Report.xdo";
	WebDriver driver;
	String usn = "mahesh.alavala@brillio.com";
	String pass = "Welcome1";
	String xlpath = ".//Excel//Dates.xlsx";

	
//Invoking the browser
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();

//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("user-data-dir=C://Users//laxmikant.mudhkan//AppData//Local//Google//Chrome//User Data//Profile 8");
//		options.addArguments("--profile-directory=Profile 8");
//		options.addArguments("--start-maximized");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	
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
	
//To perform Login 
	public void logIn() throws Exception {
		driver.findElement(By.name("userid")).sendKeys(usn);

		driver.findElement(By.name("password")).sendKeys(pass);

		driver.findElement(By.id("btnActive")).click();
		 System.out.println("Login successful !");
		Thread.sleep(5000);
		
	}

//Quitting the browser
	@AfterMethod
	public void tearDown() throws Throwable {
		Thread.sleep(10000);
		driver.quit();
	}

}

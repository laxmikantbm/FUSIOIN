package com.fusion.block1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	
	
	
	String url = "https://hcgt.fa.us1.oraclecloud.com/analytics/saw.dll?bipublisherEntry&Action=open&itemType=.xdo&bipPath=%2FCustom%2FPower%20BI%2FHR%20PBI%20Active%20No%20Payroll%20Date%20Parameter%20Report.xdo&path=%2Fshared%2FCustom%2FPower%20BI%2FHR%20PBI%20Active%20No%20Payroll%20Date%20Parameter%20Report.xdo";
	WebDriver driver;
	String usn = "mahesh.alavala@brillio.com";
	String pass = "Welcome1";
	String xlpath = ".//Excel//Dates.xlsx";
	
	
	//Invoking the browser
		@BeforeMethod
		public void setup() {
			
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(url);
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

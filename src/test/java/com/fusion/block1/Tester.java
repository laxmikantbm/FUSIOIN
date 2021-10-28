package com.fusion.block1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Tester {
	public static String url = "https://hcgt.fa.us1.oraclecloud.com/analytics/saw.dll?bipublisherEntry&Action=open&itemType=.xdo&bipPath=%2FCustom%2FPower%20BI%2FHR%20PBI%20Active%20No%20Payroll%20Date%20Parameter%20Report.xdo&path=%2Fshared%2FCustom%2FPower%20BI%2FHR%20PBI%20Active%20No%20Payroll%20Date%20Parameter%20Report.xdo";
	public static WebDriver driver;
	public static String usn = "mahesh.alavala@brillio.com";
	public static String pass = "Welcome1";
	
	
	
public static void main(String[] args) throws Exception {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get(url);
	////table[@class='fieldText parameters']/child::tbody[1]//td[2]/table//tbody/tr/td/div/input[@id='_paramsAs_of_date']
	
	////table[@class='fieldText parameters']/child::tbody[1]//td[2]/table//tbody/tr/td/div/input[@id='_paramsAs_of_date']
	logIn();
Thread.sleep(3000);
	driver.switchTo().frame(0);
	
	//driver.findElement(By.xpath("//input[@id='_paramsAs_of_date']")).sendKeys("01-01-2021");

	driver.findElement(By.xpath("//button[@id='reportViewApply']")).click();
	
	
}

public static void logIn() throws Exception {
	driver.findElement(By.name("userid")).sendKeys(usn);
	
	driver.findElement(By.name("password")).sendKeys(pass);
	
	driver.findElement(By.id("btnActive")).click();

}


}

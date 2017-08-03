package com.qait.TestNGAssignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SendMail {

	public Object[][] readExcel(String filePath,String fileName,String sheetName) throws IOException{
		
		 File file =    new File(filePath+"\\"+fileName);
		 FileInputStream inputStream = new FileInputStream(file);
		 Workbook workbook = null;
		workbook = new XSSFWorkbook(inputStream);
		
		Sheet sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		Object[][] arr=new Object[rowCount+1][3]; 
		
		for(int i = 0; i < rowCount+1; i++) {

	        Row row = sheet.getRow(i);
	        //Create a loop to print cell values in a row
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	            //Print Excel data in console
	        	arr[i][j]=row.getCell(j).getStringCellValue();
	       
	            System.out.print(row.getCell(j).getStringCellValue()+"|| ");
	        }

	        System.out.println();
		}
		return arr;
		
	}
	
	@DataProvider(name="recipients",parallel=true)
	public Object[][] dataprovider() throws IOException{
		
		SendMail obj = new SendMail();
		String filePath = "C:/Users/priyanka.sharma/Downloads/Priyanka";
		Object[][] arr= obj.readExcel(filePath,"Assignment.xlsx","Sheet1");
		return arr;
	}
	
	
	@Test(dataProvider="recipients")
	public void sendMail(String recipient,String subject, String body) throws InterruptedException{
		
		System.setProperty("webdriver.gecko.driver","C:/Users/priyanka.sharma/Desktop/geckodriver.exe");
		WebDriver driver= new FirefoxDriver();
		driver.get("https://accounts.google.com/signin");
		
		//to login
		driver.findElement(By.xpath(".//*[@id='identifierId']")).sendKeys("testps.qait");
		driver.findElement(By.xpath(".//*[@id='identifierNext']/content/span")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input")).click();
		driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input")).sendKeys("priyanka123");
		driver.findElement(By.xpath(".//*[@id='passwordNext']/content/span")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@aria-label='Mail']")).click();
		Thread.sleep(9000);
		
		//Compose Mail
		driver.findElement(By.xpath("//div[text()='COMPOSE']")).click();
		driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys(recipient);
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subject);
		driver.findElement(By.xpath("//div[@aria-label='Message Body']")).sendKeys(body);
		driver.findElement(By.xpath("//div[text()='Send']")).click();
		Thread.sleep(2000);
		
		//Sign out
		driver.findElement(By.xpath("//span[@class='gb_7a gbii']")).click();
		driver.findElement(By.xpath("//a[text()='Sign out']")).click();
		
		
	}
	
		
		
		  
	

		
	
}

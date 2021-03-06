package com.test.todo;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase1 {

	@Test
	public void add() throws InterruptedException
	{
		//get the path of user current working
		final String projectDiretcory = System.getProperty("user.dir");	
		String replaceString=projectDiretcory.replace("\\","//");
		System.out.println("After replacing \\ with / updated current directory is = " + replaceString);

		//Creating Driver Object for chrome browser
		System.setProperty("webdriver.chrome.driver", replaceString+"\\chromedriver.exe");
		//Launch driver
		WebDriver driver = new ChromeDriver();
		//maximize window
		driver.manage().window().maximize();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Explicit wait
		WebDriverWait wait = new WebDriverWait(driver,60);

		String todoValue[]={"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ","0123456789","`~!@#$%^&*()_   +-={}|[]\\/.,<>?':;","      J@mE$_Bond 007"};

		//Hit the URL
		driver.get("http://todomvc.com/examples/typescript-react/#/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/div/header/input")));
		//check if page loaded successfully and proceed
		if(driver.findElement(By.xpath("/html/body/section/div/header/input")).isDisplayed()){

			Assert.assertEquals(true, driver.findElement(By.xpath("/html/body/section/div/header/input")).isDisplayed());
			System.out.println("Text box verified successfully");

			//Validate the title of Page
			String pageTitle = driver.getTitle();
			System.out.println("Page title = "+pageTitle);
			Assert.assertEquals("React � TodoMVC", pageTitle);
			if(pageTitle.equals("React � TodoMVC"))
			{
				System.out.println("Page Title verified successfully");
				assert true;
			}
			else {
				System.out.println("Invalid Page Title");
				assert	false;	
			} 

			//Validate the URL
			String curURL = driver.getCurrentUrl();
			System.out.println("Current URL = "+curURL);
			Assert.assertEquals("http://todomvc.com/examples/typescript-react/#/", curURL);
			if(curURL.equals("http://todomvc.com/examples/typescript-react/#/"))
			{
				System.out.println("URL verified successfully");
				assert true;
			}
			else {
				System.out.println("Invalid URL");
				assert	false;	
			}

			//Check for water mark
			if(driver.findElement(By.className("new-todo")).isDisplayed()){
				Assert.assertEquals(true, driver.findElement(By.className("new-todo")).isDisplayed());
				System.out.println("Water mark verified successfully");

				for(int i = 0; i< todoValue.length; i++)
				{
					//Provide text value
					System.out.println("Original value = "+todoValue[i]);
					System.out.println("Value after trimming spaces = "+todoValue[i].trim());
					driver.findElement(By.xpath("/html/body/section/div/header/input")).sendKeys(todoValue[i]);

					//press Enter
					WebElement textbox = driver.findElement(By.xpath("/html/body/section/div/header/input"));
					textbox.sendKeys(Keys.ENTER);

					//wait
					Thread.sleep(1000);

					//check if list is displayed
					if(driver.findElement(By.className("todo-list")).isDisplayed()){
						Assert.assertEquals(true, driver.findElement(By.className("todo-list")).isDisplayed());
						System.out.println("To do added successfully");
						if(todoValue[i].length() > todoValue[i].trim().length())
						{
							System.out.println("Additional space in the String removed successfully");
						}
						System.out.println("No of elements in list = "+driver.findElement(By.className("todo-count")).getText());
						assert true;
					}
					else
					{
						System.out.println("Failed to add a To do");
						assert false;
					}
				}
			}
			else{
				System.out.println("Water mark not found");
			}
		}
		else{
			System.out.println("Text box not found");
		}
		//Closes the browser/window
		driver.close();
	}
}
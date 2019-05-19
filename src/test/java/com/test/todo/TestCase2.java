package com.test.todo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase2 {

	@Test
	public void delete() throws InterruptedException{
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

		String todoValue="abcdefghiklmnopqrstuvwxyz";

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
			Assert.assertEquals("React • TodoMVC", pageTitle);
			if(pageTitle.equals("React • TodoMVC"))
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

				//Provide text value
				System.out.println("Original value = "+todoValue);
				System.out.println("Value after trimming spaces = "+todoValue.trim());
				driver.findElement(By.xpath("/html/body/section/div/header/input")).sendKeys(todoValue.trim());

				//press Enter
				WebElement textbox = driver.findElement(By.xpath("/html/body/section/div/header/input"));
				textbox.sendKeys(Keys.ENTER);
				//wait
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(driver.findElement(By.className("todo-list")).isDisplayed()){
					Assert.assertEquals(true, driver.findElement(By.className("todo-list")).isDisplayed());
					System.out.println("To do added successfully");
					System.out.println("No of elements in list = "+driver.findElement(By.className("todo-count")).getText());
					assert true;					

					//press Delete
					driver.findElement(By.xpath("/html/body/section/div/section/ul/li[1]/div")).click();

					WebElement list = driver.findElement(By.xpath("/html/body/section/div/section/ul/li[1]/div/button"));

					if(list.isEnabled()){
						Action b = new Actions(driver).moveToElement(list).click().build();
						b.perform();

						Thread.sleep(2000);

						System.out.println("PageSource: "+driver.findElement(By.xpath("html/body")).getText());

						if(driver.findElement(By.xpath("html/body")).getText().contains(todoValue))
							System.out.println("Failed to delete a to do");
						else
							System.out.println("To do deleted successfully");	
					}

					//wait
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("Failed to add a To do");
					assert false;
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
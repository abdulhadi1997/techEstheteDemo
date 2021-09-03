package com.techesthete.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegressionTests {
	private WebDriver driver;
	
	@BeforeMethod
	private void setup() {
		System.out.println("Starting loginTest");

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		 driver = new ChromeDriver();

		driver.manage().window().maximize();

		String url = "https://www.facebook.com/";
		driver.get(url);
		System.out.println("Page opened");

	}
	
	@AfterMethod
	private void tearDown() {
		sleep(3000);
		System.out.println("Close driver");
		// Close browser
		driver.quit();
	}

	@Test
	public void loginTest() {
		WebElement username = driver.findElement(By.id("email"));
		username.sendKeys("techesthetedemo@gmail.com");

		WebElement password = driver.findElement(By.id("pass"));
		password.sendKeys("passcodeesthete1");

		WebElement logInButton = driver.findElement(By.xpath("//button[@name='login']"));
		logInButton.click();

		WebElement nameTag = driver.findElement(By.xpath("//span[.='John']"));
		Assert.assertTrue(nameTag.isDisplayed(), "Profile link is not visible");

	}
	
	@Test
	public void enterKeyOnLoginTest() {
		WebElement username = driver.findElement(By.id("email"));
		username.sendKeys("techesthetedemo@gmail.com");

		WebElement password = driver.findElement(By.id("pass"));
		password.sendKeys("passcodeesthete1");
		password.sendKeys(Keys.ENTER);

		WebElement nameTag = driver.findElement(By.xpath("//span[.='John']"));
		Assert.assertTrue(nameTag.isDisplayed(), "Profile link is not visible");
	}
	
	@Test
	public void cursorOnEmailFieldTest() {
		WebElement username = driver.findElement(By.id("email"));
		Assert.assertTrue(username.equals(driver.switchTo().activeElement()), "The email field is focused.");
	}

	@Test
	public void forgotPasswordTest() {
		WebElement forgotPasswordLink = driver.findElement(By.cssSelector("._6ltj > a"));
		forgotPasswordLink.click();

		WebElement forgotPasswordHeader = driver.findElement(By.cssSelector(".uiHeaderTitle"));
		forgotPasswordHeader.isDisplayed();
	}

	@Test
	public void incorrectUsernameTest() {
		WebElement username = driver.findElement(By.id("email"));
		username.sendKeys("incorrect");

		WebElement password = driver.findElement(By.id("pass"));
		password.sendKeys("passcodeesthete1");

		WebElement logInButton = driver.findElement(By.xpath("//button[@name='login']"));
		logInButton.click();

		WebElement incorrectCredentialsError = driver.findElement(By.cssSelector("._9ay7"));
		incorrectCredentialsError.isDisplayed();
	}

	@Test
	public void incorrectPasswordTest() {
		WebElement username = driver.findElement(By.id("email"));
		username.sendKeys("techesthetedemo@gmail.com");

		WebElement password = driver.findElement(By.id("pass"));
		password.sendKeys("incorrectPassword");

		WebElement logInButton = driver.findElement(By.xpath("//button[@name='login']"));
		logInButton.click();

		WebElement incorrectCredentialsError = driver.findElement(By.cssSelector("._9ay7"));
		incorrectCredentialsError.isDisplayed();
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

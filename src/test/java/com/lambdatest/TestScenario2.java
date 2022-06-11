package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class TestScenario2 {

	private RemoteWebDriver driver;
	private String Status = "failed";

	@BeforeMethod
	public void setup(Method m, ITestContext ctx) throws MalformedURLException {
		String username = System.getenv("LT_USERNAME") == null ? "anita.qa18" : System.getenv("LT_USERNAME");
		String authkey = System.getenv("LT_ACCESS_KEY") == null ? "J8cHCwc68YAuXe0BZlY4lrljHz3cs4RITSRmW6nI9XCW46tRuB"
				: System.getenv("LT_ACCESS_KEY");

		String hub = "@hub.lambdatest.com/wd/hub";

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("build", "your build name");
		capabilities.setCapability("name", "your test name");
		capabilities.setCapability("platform", "Windows 11");
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("version","102.0");
		capabilities.setCapability("selenium_version","4.1.2");
		capabilities.setCapability("console","true");
		capabilities.setCapability("network",true);
		capabilities.setCapability("visual",true);
		capabilities.setCapability("timezone","UTC-05:00");
		capabilities.setCapability("geoLocation","US");
		capabilities.setCapability("driver_version","102.0");
		capabilities.setCapability("build", "TestNG With Java");
		capabilities.setCapability("name", m.getName() + this.getClass().getName());
		capabilities.setCapability("plugin", "git-testng");

		String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
		capabilities.setCapability("tags", Tags);

		driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), capabilities);
	}

	@Test
	public void testScenario2() throws InterruptedException {

		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("(//ul[@class='list-disc pl-30 pb-30'])[2]/li[3]/a")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,0400)", "");

		// Element which need to drag

		WebElement from = driver.findElement(By.xpath("//input[@value='15']"));

		// Using Action class for drag and drop.
		Actions A = new Actions(driver);
		// Drag and Drop by Offset.
		A.dragAndDropBy(from, 119, 0).build().perform();
		String txt = driver.findElement(By.xpath("//output[@id='rangeSuccess']")).getText();
		Assert.assertEquals(txt, "95");
		System.out.println(txt);
		Status = "passed";
		Thread.sleep(150);

		System.out.println("TestFinished");

	}

	@AfterMethod
	public void tearDown() {
		driver.executeScript("lambda-status=" + Status);
		driver.quit();
	}

}
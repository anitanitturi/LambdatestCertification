package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

;

public class TestScenario1 {

	private RemoteWebDriver driver;
	private String Status = "failed";

	@BeforeMethod
	public void setup(Method m, ITestContext ctx) throws MalformedURLException {
		String username = System.getenv("LT_USERNAME") == null ? "anita.qa18" : System.getenv("LT_USERNAME");
		String authkey = System.getenv("LT_ACCESS_KEY") == null ? "J8cHCwc68YAuXe0BZlY4lrljHz3cs4RITSRmW6nI9XCW46tRuB"
				: System.getenv("LT_ACCESS_KEY");
		;
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
	public void testScenario() throws InterruptedException {

		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Simple Form Demo")).click();
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL.contains("simple-form-demo"), true);
		System.out.println(URL);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,0500)", "");
		String text = "Welcome to LambdaTest";
		driver.findElement(By.id("user-message")).sendKeys(text);
		driver.findElement(By.cssSelector("button#showInput")).click();
		String msg = driver.findElement(By.id("message")).getText();
		Assert.assertEquals(msg, "Welcome to LambdaTest");
		System.out.println(msg);
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
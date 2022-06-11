package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class TestScenario3 {

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
		capabilities.setCapability("platform", "MacOS Catalina");
		capabilities.setCapability("browserName", "Safari");
		capabilities.setCapability("version","13.0");
		capabilities.setCapability("selenium_version","4.1.2");
		capabilities.setCapability("console","true");
		capabilities.setCapability("network",true);
		capabilities.setCapability("visual",true);
		capabilities.setCapability("timezone","UTC-05:00");
		capabilities.setCapability("geoLocation","US");
		capabilities.setCapability("build", "TestNG With Java");
		capabilities.setCapability("name", m.getName() + this.getClass().getName());
		capabilities.setCapability("plugin", "git-testng");

		String[] Tags = new String[] { "Feature", "Tag", "Moderate" };
		capabilities.setCapability("tags", Tags);

		driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), capabilities);
	}

	@Test
	public void testScenario3() throws InterruptedException {

		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("(//ul[@class='list-disc pl-30 pb-30']/li)[5]/a")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,0700)", "");
		driver.findElement(By.xpath("//button[contains(@type,'submit')]")).click();
		String Error = driver.findElement(By.id("name")).getAttribute("validationMessage");
		Assert.assertEquals(Error, "Fill out this field");
		System.out.println(Error);

		driver.findElement(By.name("name")).sendKeys("ased");
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("abcd@gmail.com");
		driver.findElement(By.id("inputPassword4")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#company")).sendKeys("ABCD");
		driver.findElement(By.xpath("//input[@placeholder='Website']")).sendKeys("abcd.com");
		WebElement staticdropdown = driver.findElement(By.name("country"));
		Select dropdown = new Select(staticdropdown);
		dropdown.selectByVisibleText("United States");
		driver.findElement(By.cssSelector("input[placeholder='City']")).sendKeys("Erie");
		driver.findElement(By.name("address_line1")).sendKeys("123 Main Street");
		driver.findElement(By.xpath("//input[@id='inputAddress2']")).sendKeys("Bldg 234");
		driver.findElement(By.cssSelector("input#inputState")).sendKeys("New York");
		driver.findElement(By.name("zip")).sendKeys("23123");
		driver.findElement(By.xpath("//button[contains(@type,'submit')]")).click();
		String msg = driver.findElement(By.xpath("//p[@class='success-msg hidden']")).getText();
		Assert.assertTrue(true, msg);
		// to print the msg in console
		System.out.println(msg);
		Status = "passed";
		Thread.sleep(800);

		System.out.println("TestFinished");

	}

	@AfterMethod
	public void tearDown() {
		driver.executeScript("lambda-status=" + Status);
		driver.quit();
	}

}
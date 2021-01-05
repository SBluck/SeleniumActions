package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


import pages.HomePage;

public class YouiDrawTest {
	private static WebDriver driver;
	Actions action = new Actions(driver);
	HomePage home = PageFactory.initElements(driver, HomePage.class);
	
	@BeforeClass
	public static void init() {
//		extent = new ExtentReports("src/test/resources/reports/report1.html", false);
//		test = extent.startTest("Google search Extent testing");

		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions cOptions = new ChromeOptions();
		cOptions.setHeadless(false);

		driver = new ChromeDriver(cOptions);
//		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.manage().window().setSize(new Dimension(1366, 768));

		// Settings
		prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("network.cookie.cookieBehavior", 2);
		prefs.put("profile.block_third_party_cookies", true);

		// Create ChromeOptions to disable Cookies pop-up
		cOptions.setExperimentalOption("prefs", prefs);

		driver.manage().window().setSize(new Dimension(1366, 768));

// use implicit in before - waits before running tests
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

	}

	@Before
	public void setup() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try {
			driver.get(home.URL);  // error if try to access via home - must be accessed in static way
		} catch (TimeoutException e) {
			System.out.println("Page: " + home.URL + "unable to load within 20 seconds");
		}

	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
		
	}

	// check the current driver url
	@Test
	public void urlTest() {
		driver.get(home.URL);
		assertEquals("https://www.youidraw.com/apps/painter/", driver.getCurrentUrl());

	}
	
	// draw initial letters on site
	@Test
	public void drawInitial() throws InterruptedException {
		WebElement image = driver.findElement(By.cssSelector("#catch"));

		home.clickBrush();
		
		// S
		action.moveToElement(image,-200,0).clickAndHold().moveByOffset(100,0)
			.moveByOffset(0,-100).moveByOffset(-100,0).moveByOffset(0,-100)
			.moveByOffset(100,0).click().moveToElement(image,0, 0).build().perform();
		
		
		// C original trial, S and B moved to accommodate
//		action.moveByOffset(100,0).clickAndHold().moveByOffset(-100,0)
//			.moveByOffset(0,-200).moveByOffset(100,0)
//			.click().moveToElement(image,0, 0).build().perform();
		
		// C slight corner trial
		action.moveByOffset(100,-10).clickAndHold().moveByOffset(-10,10)
			.moveByOffset(-80,0).moveByOffset(-10,-10)
			.moveByOffset(0,-180).moveByOffset(10,-10)
			.moveByOffset(80,0).moveByOffset(10,10)
			.click().moveToElement(image,0, 0).build().perform();
		
		// B
		action.moveByOffset(200,0).clickAndHold().moveByOffset(0,-200)
		.moveByOffset(100,0).moveByOffset(0,100).moveByOffset(-100,0)
		.moveByOffset(100,0).moveByOffset(0,100).moveByOffset(-100,0)
		.click().moveToElement(image,0, 0).build().perform();		
		
		Thread.sleep(2000);
	}

}

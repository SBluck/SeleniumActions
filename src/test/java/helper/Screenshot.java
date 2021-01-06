package helper;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class Screenshot {

	//Take screenshot
	public static void takeShot(WebDriver driver, String fileWithPath) throws IOException {
	
		// -- Convert web driver object to TakeScreenshot 
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		
		// -- Call getScreenshotAs method to create image file
		File scrFile = scrShot.getScreenshotAs(OutputType.FILE);
		
		//File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		// -- Move image file to new destination
		 File destFile = new File(fileWithPath);
		
		// -- Copy file at destination
		 Files.copy(scrFile, destFile);
		
		//Files.copy(scrFile, new File(fileWithPath);
			
	}
}

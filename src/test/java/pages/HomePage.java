package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	// Attributes

		public final String URL = "http://YouiDraw.com/apps/painter/";

		@FindBy(css = "#brush")
		private WebElement brush;

		// Get brush
		public void clickBrush() {
			brush.click();
			
		}
		
}

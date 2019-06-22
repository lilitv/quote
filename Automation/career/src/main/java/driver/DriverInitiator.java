package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverInitiator {

	private static WebDriver driver;

	private DriverInitiator() {
	}

	public static WebDriver getWebDriverInstance() {
		if (null == driver) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/driverbinaries/chromedriver.exe");
			driver = new ChromeDriver();
		}
		return driver;
	}

}

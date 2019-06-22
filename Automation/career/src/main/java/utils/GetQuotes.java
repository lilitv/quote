package utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import constants.Constants;

public class GetQuotes {

	public static List<String> getQuotes(String author) {
		List<String> quotes = new ArrayList<String>();
		List<WebElement> list = null;
		System.setProperty("webdriver.chrome.driver", "src/test/resources/driverbinaries/chromedriver.exe");
		WebDriver tempdriver = new ChromeDriver();
		tempdriver.get(Constants.QUOTE_URL);
		list = tempdriver.findElements(By.xpath("//*[contains(text(),\"Gideon\")]"));
		for (int i = 0; i < list.size(); i++) {

			WebElement el = list.get(i);
			String text = (String) ((JavascriptExecutor) tempdriver)
					.executeScript("return arguments[0].nextSibling.textContent.trim()", el);
			quotes.add(text);

		}
		tempdriver.close();
		return quotes;
	}

}

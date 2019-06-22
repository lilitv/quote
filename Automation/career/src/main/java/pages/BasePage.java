package pages;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.Constants;

public class BasePage {
	WebDriverWait wait = null;
	protected WebDriver driver = null;

	public BasePage(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(id = "show-modal")
	private WebElement addNewQuoteButton;

	@FindBy(id = "searchBar")
	private WebElement searchBar;

	@FindBy(xpath = "//*[contains(text(),'Gideon')]")
	private WebElement authorButton;

	@FindBy(xpath = "//*[contains(text(),'No quotes found.')]")
	private WebElement noQuotesMessage;

	public WebElement getAddNewQuoteButton() {

		return this.addNewQuoteButton;
	}

	public WebElement getAuthorButton() {

		return this.authorButton;
	}

	public WebElement getSearchBar() {

		return this.searchBar;
	}

	public WebElement getNoQuotesMessage() {

		return this.noQuotesMessage;
	}

	public boolean isAlertPresent() {

		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public int getCountsofAuthor() {

		this.authorButton.click();
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='rightsection']//li"));
		return list.size();
	}

}

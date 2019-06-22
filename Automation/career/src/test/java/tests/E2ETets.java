package tests;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import constants.Constants;
import driver.DriverInitiator;
import pages.AddQuotePage;
import pages.BasePage;
import utils.GetQuotes;

public class E2ETets {

	WebDriver driver = null;
	AddQuotePage addQuotePage = null;
	BasePage basePage = null;
	DesiredCapabilities cap = null;
	int counterAfter = 0;
	List<String> quotes = null;
	String newAuthor = "HELEN";
	String newQuote = "automation";

	@BeforeClass
	public void setUP() throws MalformedURLException {

		driver = DriverInitiator.getWebDriverInstance();
		basePage = new BasePage(driver);
		addQuotePage = new AddQuotePage(driver);
		driver.get(Constants.BASE_URL);
		driver.manage().timeouts().implicitlyWait(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	

	@Test(priority = 1, description="Check quote search works both for upper and lower case, Bug ID-1003")
	public void verifySearchOfQuotos() throws InterruptedException {
		basePage.getAddNewQuoteButton().click();
		addQuotePage.addQuote(newAuthor, newQuote);
		basePage.getSearchBar().sendKeys(newQuote.toUpperCase());
		Thread.sleep(5000);
		Assert.assertFalse(driver.findElement(By.xpath("//*[contains(text(),\"No quotes found.\")]")).isDisplayed());
	}

	@Test(priority = 2,description="Check plagiarism  for existing quotes, Bug Id-1004")
	public void verifyPlagiarismOfCoutes() throws InterruptedException {
		String author = "MARK";
		String quote = "This is a test quote.";
		basePage.getAddNewQuoteButton().click();
		addQuotePage.addQuote(author, quote);
		basePage.getAddNewQuoteButton().click();
		addQuotePage.addQuote(author, quote);
		Assert.assertTrue(basePage.isAlertPresent());
	}
	
	@Test(priority = 3, description = "Check adding of quotes by Gideon")
	public void addQuotes() throws InterruptedException {
		quotes = GetQuotes.getQuotes(Constants.AUTHOR);
		for (int i = 0; i < quotes.size(); i++) {
			
			basePage.getAddNewQuoteButton().click();
			String quoteText = quotes.get(i);
			addQuotePage.addQuote(Constants.AUTHOR, quoteText);
		}
		basePage.getAuthorButton().click();
		counterAfter = basePage.getCountsofAuthor();
		Assert.assertTrue(counterAfter == quotes.size() + 1);
	}

	@AfterClass
	public void quit() {
		driver.quit();
	}
}

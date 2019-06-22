package pages;

import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddQuotePage extends BasePage {

	public AddQuotePage(WebDriver driver) throws MalformedURLException {
		super(driver);
	}

	@FindBy(id = "autorInput")
	private WebElement authorInput;

	@FindBy(id = "quoteInput")
	private WebElement quoteInput;

	@FindBy(xpath = "//*[contains(text(),'Save')]")
	private WebElement saveButton;

	public WebElement getAuthorInput() {
		return this.authorInput;
	}

	public WebElement getQuoteInput() {
		return this.quoteInput;
	}

	public WebElement getSaveButton() {
		return this.saveButton;
	}

	public void addQuote(String author, String quote) throws InterruptedException {
		this.authorInput.sendKeys(author);
		this.quoteInput.sendKeys(quote);
		this.saveButton.click();
		Thread.sleep(4000);
		
	}

}

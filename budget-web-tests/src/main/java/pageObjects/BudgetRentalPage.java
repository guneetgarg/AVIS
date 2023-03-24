package pageObjects;


import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avis.qa.core.AbstractBasePage;

public class BudgetRentalPage extends AbstractBasePage{
	
	public BudgetRentalPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	@FindBy(xpath = "//button[@class='btn btn-primary-avis pull-right'] | //button[contains(@id,'res-extras-continue-bottom')] | (//button[contains(text(),'Continue')])[2] | (//button[contains(text(),'Continue')])[1]")
	private WebElement CONTINUEBUTTON;

	@FindBy(xpath = "(//div[@class='vehicle-name'])[1] | //p[@class='car-name']")
	private WebElement vehicleName;

	@FindBy(xpath = "//h1[@class='pull-left mar-bottom-0']")
	private WebElement recommendExtras;//verify
	
	@FindBy(xpath = "//div[@class='uplift-step3-text hidden-xs']")
	private WebElement upliftDetails;
	
	@FindBy(xpath = "//span[text()='Base Rate']")
    private WebElement BaseRate;
	
	@FindBy(xpath = "//span[@class='four-seats-feat']")
    private WebElement NumberOfSeats;

    @FindBy(xpath = "//a[@id='rate-terms']")
    private WebElement SeeRateTerms;


	public void rentalPage(Map<?, ?> testDataMap) {
//		assertTrue(upliftDetails.getText().contains("or  as low as"));
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(BaseRate));
		BaseRate.isDisplayed();
		NumberOfSeats.isDisplayed();
		SeeRateTerms.isDisplayed();
		wait.until(ExpectedConditions.visibilityOf(recommendExtras));
		assertTrue(recommendExtras.getText().contains("Recommended Extras"));
		clickOn(CONTINUEBUTTON);
	}

	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub
		
	}
}

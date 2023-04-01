package pageObjects;


import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.Configuration;

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

    @FindBy(xpath = "//div[@class='step3-tabs-header hidden-xs']")
	private WebElement recommendExtras1;
    
    @FindBy(xpath = "//span[@ng-if=\"vm.currencySymbol!='$ CA'\"]//span[@ng-bind-html='vm.prod.reservationSummary.rateSummary.currencySymbol']")
	private WebElement VerifySymbol;
    
    @FindBy(xpath = "//div[@class='taxes-fees']")
    private WebElement feesTaxes;

    @FindBy(xpath = "//div[@class='estimate mobile-hide']")
    private WebElement estimatedTotal;

    @FindBy(xpath = "//div[@ng-show='showTerms']")
    private WebElement verifyRateTerms;
  
    @FindBy(xpath = "//div[@class='uplift-step3-text hidden-xs']//span[@class='s-icon res-helpIcon']")
    private WebElement upliftLogo;
    
    @FindBy(xpath = "//div[@aria-labelledby='dialogTitle']")
    private WebElement upliftpopupDisplayed;
    
    @FindBy(xpath = "//div[@class='top']//a[@aria-label='Click to open FAQ']")
    private WebElement faqclicked;
    
    @FindBy(xpath = "//div[@class='col-xs-10 col-sm-6 faq-container']")
    private WebElement frequentlyques;
    
    @FindBy(xpath = "//div[@class='modal-header']//div[@aria-label='Close the FAQ']")
    private WebElement frequentlyquesback;
    
    @FindBy(xpath = "//div[@class='top']//a[@class='modal-close']")
    private WebElement closeuplift;
    
    @FindBy(xpath = "//div[@class='estimate mobile-hide text-size-1_5x']")
    private WebElement EstimatedTotal;
    
    
  //div[@class='uplift-step3-text hidden-xs']//span[@class='c-icon uplift-logo']
	public void rentalPage(Map<?, ?> testDataMap) {
		WebDriverWait wait = new WebDriverWait(driver, 90);
		if(testDataMap.get("Continue").toString().equalsIgnoreCase("Yes")) {
		wait.until(ExpectedConditions.visibilityOf(BaseRate));
		BaseRate.isDisplayed();
		VerifySymbol.isDisplayed();
		feesTaxes.isDisplayed();
		if(Configuration.DOMAIN.equalsIgnoreCase("US") && Configuration.DOMAIN.equalsIgnoreCase("CA")) {
		estimatedTotal.isDisplayed();
		}
		if(Configuration.DOMAIN.equalsIgnoreCase("NZ")) {
			EstimatedTotal.isDisplayed();
		}
		NumberOfSeats.isDisplayed();
		SeeRateTerms.isDisplayed();
		SeeRateTerms.click();
		verifyRateTerms.isDisplayed();
		if(testDataMap.get("UpliftInfo").toString().equalsIgnoreCase("Yes")) {
			wait.until(ExpectedConditions.visibilityOf(upliftLogo));
			upliftLogo.click();
			upliftLogo.click();
			upliftpopupDisplayed.isDisplayed();
			faqclicked.click();
			frequentlyques.isDisplayed();
			frequentlyquesback.click();
			closeuplift.click();			
		}
		if(Configuration.DOMAIN.equalsIgnoreCase("US") && Configuration.DOMAIN.equalsIgnoreCase("CA")) {
		wait.until(ExpectedConditions.visibilityOf(recommendExtras));
		assertTrue(recommendExtras.getText().contains("Recommended Extras"));
		}
		if(Configuration.DOMAIN.equalsIgnoreCase("NZ")) {
			wait.until(ExpectedConditions.visibilityOf(recommendExtras1));
			assertTrue(recommendExtras1.getText().contains("Recommended Extras"));
			
		}
		wait.until(ExpectedConditions.visibilityOf(CONTINUEBUTTON));
		clickOn(CONTINUEBUTTON);
		}
	}

	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub
		
	}
}

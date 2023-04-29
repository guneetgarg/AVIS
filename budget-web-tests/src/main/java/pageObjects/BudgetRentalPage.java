package pageObjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.Configuration;
import com.avis.qa.core.TestBase;

public class BudgetRentalPage extends AbstractBasePage {
	TestBase test = new TestBase();

	public BudgetRentalPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//button[@class='btn btn-primary-avis pull-right'] | //button[contains(@id,'res-extras-continue-bottom')] | (//button[contains(text(),'Continue')])[2] | (//button[contains(text(),'Continue')])[1]")
	private WebElement CONTINUEBUTTON;

	@FindBy(xpath = "(//div[@class='vehicle-name'])[1] | //p[@class='car-name']")
	private WebElement vehicleName;

	@FindBy(xpath = "//h1[@class='pull-left mar-bottom-0']")
	private WebElement recommendExtras;// verify

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

	@FindBy(css = ".uplift-step3-text.hidden-xs .uplift-logo")
	private WebElement upliftLogo;

	@FindBy(xpath = "//div[@class='page-container']")
	private WebElement upliftpopupDisplayed;

	@FindBy(xpath = "//div[@class='top']//a[@aria-label='Click to open FAQ']")
	private WebElement faqclicked;

	@FindBy(xpath = "//div[@class='faq']")
	private WebElement frequentlyques;

	@FindBy(xpath = "//a[@class='back']")
	private WebElement frequentlyquesback;

	@FindBy(xpath = "//div[@class='top']//a[@class='modal-close']")
	private WebElement closeuplift;

	@FindBy(xpath = "//div[@class='estimate mobile-hide text-size-1_5x']")
	private WebElement EstimatedTotal;
	
//  @FindBy(xpath = "//div[@ng-show='showTerms']//strong[text()='BCD:']")
    @FindBy(xpath = "//div[@ng-show='showTerms']//strong[text()='BCD:']/parent::span")
    private WebElement verifyBCD;
    
	public void rentalPage(Map<?, ?> testDataMap) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 90);
		if (testDataMap.get("Continue").toString().equalsIgnoreCase("Yes")) {
			wait.until(ExpectedConditions.visibilityOf(BaseRate));
			BaseRate.isDisplayed();
			VerifySymbol.isDisplayed();
			feesTaxes.isDisplayed();
			if (Configuration.DOMAIN.equalsIgnoreCase("US") && Configuration.DOMAIN.equalsIgnoreCase("CA")) {
				estimatedTotal.isDisplayed();
			}
			if (Configuration.DOMAIN.equalsIgnoreCase("NZ")) {
				EstimatedTotal.isDisplayed();
			}     
			NumberOfSeats.isDisplayed();
			SeeRateTerms.isDisplayed();
			SeeRateTerms.click();
			verifyRateTerms.isDisplayed();
			if(Configuration.DOMAIN.equalsIgnoreCase("AU")) {
            	if(!testDataMap.get("BCD").toString().equalsIgnoreCase("NA")) {
                    String BCDNo = verifyBCD.getText();
                    System.out.println(BCDNo);
                    String [] BCDValue= verifyBCD.getText().replace(" ", "").split(":");
                    String BCDNumber = BCDValue[1];
                    System.out.println(BCDNumber);
                    assertEquals(BCDNumber,testDataMap.get("BCD").toString() );
                    
            	}
            	
            }   
			if (testDataMap.get("UpliftInfo").toString().equalsIgnoreCase("Yes")) {
				wait.until(ExpectedConditions.visibilityOf(upliftLogo));
				Boolean c = true;
				while (c) {
					upliftLogo.click();
					try {
						Thread.sleep(5000);
						driver.switchTo().frame("up-prequal-iframe");
						Boolean b = driver.findElement(By.xpath("//a[@id='faq']/div")).isDisplayed();

						System.out.println(b);

						if(b == true) {
							c = false;
						} else {
							driver.switchTo().defaultContent();
						}
					} catch (NoSuchElementException e) {
						e.printStackTrace();

					}
					System.out.println("IN while Loop : " + c);
				}
				upliftpopupDisplayed.isDisplayed();
				wait.until(ExpectedConditions.visibilityOf(faqclicked));
				faqclicked.click();
				frequentlyques.isDisplayed();
				frequentlyquesback.click();
				closeuplift.click();
				driver.switchTo().defaultContent();
			}
			if (Configuration.DOMAIN.equalsIgnoreCase("US") && Configuration.DOMAIN.equalsIgnoreCase("CA")) {
				wait.until(ExpectedConditions.visibilityOf(recommendExtras));
				assertTrue(recommendExtras.getText().contains("Recommended Extras"));
			}
			if (Configuration.DOMAIN.equalsIgnoreCase("NZ")) {
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

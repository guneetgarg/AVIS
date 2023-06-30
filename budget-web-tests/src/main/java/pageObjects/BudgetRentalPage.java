package pageObjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.Configuration;
import com.avis.qa.core.TestBase;
import com.avis.qa.utilities.HelperFunctions;

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

	@FindBy(xpath = "//div[@ng-show='showTerms']//strong[text()='BCD:']/parent::span")
	private WebElement verifyBCD;

	@FindBy(xpath = "//div[@class='col-sm-12 vehicle-container']")
	private WebElement vehicleInfo;

	@FindBy(xpath = "//span[text()='Protections & Coverages']")
	private WebElement protectionCoverage;

	@FindBy(xpath = "//section[@class='rental-summary container-fluid full-bleed-width']//div[@class='col-sm-7 location']//div[@class='col-sm-6 destination']//div[@class='pull-right mar-right-10 mobile-hide']//a[@ng-click=\"vmx.reloadModelValue(this);vm.analytics('LocationAndDateModify')\"]")
	private WebElement modifyLink;

	@FindBy(xpath = "//div[@class='modal-content']//div[@class='modal-header vehRedBg-for-mob res-popup']//h3[contains(text(),'Modify Rental Details')]")
	private WebElement modifyRentalDetails;

	@FindBy(xpath = "//div[@class='row res-inputFldPrt res-inputFldBack']")
	private WebElement verifyRentalDetails;

	@FindBy(xpath = "//div[@class='res-mainContent resDesktopView']//div[@class='modal-footer offers-landing-selbtn']//div[@class='step2-renter-summary-detail-btn hidden-xs']//button[@aria-label='Update']")
	private WebElement updateButton;

	@FindBy(xpath = "//div[@class='modal-dialog modal-popup reservation-modal renter-summary-detail']")
	private WebElement modifyRentalDetailsPopup;
	
//	@FindBy(xpath = "//div[@class='estimate mobile-hide']/span[@class='pull-right']/span/span[last()]")
//	private WebElement estimatedTotalAmount;
	@FindBy(xpath = "//div[@class='estimate mobile-hide']/span[@class='pull-right']/span")
	private WebElement estimatedTotalAmount;
	
//	String estimatedTotalAmount="//div[@class='estimate mobile-hide']/span[@class='pull-right']/span/span[last()]";

	String protectionsCoverages="//div[contains(@class,'extra-package-lists')]//p[@class='f_head' and contains(text(),'Cover Myself (PAE)')]/../parent::div/following-sibling::div[@class='col-lg-4 col-md-2 col-sm-3 col-xs-12']//div[@class='customChk']";
	
	String equipmentServices="//div[contains(@class,'extra-package-lists')]//p[@class='f_head' and contains(text(),'Curbside Drop-Off')]/../parent::div/following-sibling::div[@class='col-lg-4 col-md-2 col-sm-3 col-xs-12 pull-right']//div[@class='customChk']";
	
	static String TotalEstimatedAmount;
	
	static int TotalDays;
	
	static String SELECTPRICE;
	
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
			//verify vehicle information US
			vehicleInfo.isDisplayed();

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
						driver.switchTo().frame("up-modal-iframe");
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
				protectionCoverage.isDisplayed();
			}
			if (Configuration.DOMAIN.equalsIgnoreCase("NZ")) {
				wait.until(ExpectedConditions.visibilityOf(recommendExtras1));
				assertTrue(recommendExtras1.getText().contains("Recommended Extras"));

			}
			if(!testDataMap.get("ProtectionCoverages").toString().equalsIgnoreCase("NA")) {
				WebElement protectionsCoverage = driver.findElement(By.xpath(HelperFunctions.createDynamicLocator(protectionsCoverages,testDataMap.get("ProtectionCoverages").toString())));
				Actions actions = new Actions(driver);
				actions.moveToElement(protectionsCoverage);
				actions.perform();
				clickOn(protectionsCoverage);
			}
			if(!testDataMap.get("EquipmentServices").toString().equalsIgnoreCase("NA")) {
				WebElement equipsServices = driver.findElement(By.xpath(HelperFunctions.createDynamicLocator(equipmentServices,testDataMap.get("EquipmentServices").toString())));
				Actions actions = new Actions(driver);
				actions.moveToElement(equipsServices);
				actions.perform();
				clickOn(equipsServices);
			}
			if(testDataMap.get("ModifyRentalDetails").toString().equalsIgnoreCase("Yes")) {
				modifyLink.click();
				modifyRentalDetails.isDisplayed();
				verifyRentalDetails.isDisplayed();
				updateButton.click();
				BudgetVehiclesPage budgetVehicle=new BudgetVehiclesPage(driver);
				budgetVehicle.extras(testDataMap);
//				modifyRentalDetails.click();
//				modifyRentalDetailsPopup.isDisplayed();
//				updateButton.click();
//				Thread.sleep(10000);
//				modifyRentalDetails.click();
//				modifyRentalDetailsPopup.isDisplayed();
//				updateButton.click();
//				BudgetVehiclesPage budgetVehicle=new BudgetVehiclesPage(driver);
//				budgetVehicle.extras(testDataMap);
			}

			TotalDays=BudgetHomePage.TotalDays;
			SELECTPRICE=BudgetVehiclesPage.SELECTPRICE;
//			WebElement estTtl=driver.findElement(By.xpath(HelperFunctions.createDynamicLocator(protectionsCoverages,testDataMap.get("ProtectionCoverages").toString())));
			wait.until(ExpectedConditions.visibilityOf(estimatedTotalAmount));
			String totalAmount=estimatedTotalAmount.getText();
			
			if (SELECTPRICE.equals(estimatedTotalAmount)) {
				System.out.println("Strings are equal");
			} else {
				System.out.println("Strings are NOT equal");
			}
//			Double TotalEstimatedAmount=((Integer.valueOf(totalAmount)>(Double.valueOf(SELECTPRICE))));
			wait.until(ExpectedConditions.visibilityOf(CONTINUEBUTTON));
			clickOn(CONTINUEBUTTON);
		}
	}   

	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub

	}
}

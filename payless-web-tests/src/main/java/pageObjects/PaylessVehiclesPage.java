package pageObjects;

import static com.avis.qa.utilities.CommonUtils.TEN_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.avis.qa.core.AbstractBasePage;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;

public class PaylessVehiclesPage extends AbstractBasePage {

	public PaylessVehiclesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]|(//a[@id='res-vehicles-pay-later'])[1] | (//a[contains(text(),'Pay Later')])[2]|(//a[contains(text(),'Pay at Counter')])[1]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]|((//p[contains(@class,'totalPay')]/../a)[1])")
	private WebElement PayLater;

	@FindBy(css = "li[title='Choose a Car']")
	private WebElement selectACarText;

	@FindBy(xpath = "(//a[contains(text(),'Pay Now')])[4]|(//a[contains(text(),'Payer maintenant')])[1]|(//a[contains(text(),'Pagar ahora')])[1]|(//a[contains(text(),'Select')])[1]")
	private WebElement PaynowButton;

	@FindBy(xpath = "(//div[@class='location-info'])[1] | (//div[@class='summary-location'])[1]")
	private WebElement pickUpLocationVerify;

	@FindBy(xpath = "(//div[@class='vehicle-name'])[1] | //p[@class='car-name']")
	private WebElement vehicleName;

	@FindBy(xpath = "//li[@ng-if='vm.response.reservationSummary.coupon.number']/span[2] | //span[@class='coupon-value']")
	private WebElement couponCode;

	@FindBy(xpath = "//li[@ng-if='vm.response.reservationSummary.awdNumber']/span[2] | //p[contains(@ng-if,'vm.confirmation.reservationSummary.awdNumber')]")
	private WebElement pdn;


	public void chooseVehicles(Map<?, ?> testDataMap){

		if(!testDataMap.get("PickUpLocation").toString().equalsIgnoreCase("NA")) {
			assertTrue(pickUpLocationVerify.getText().toString().contains((PaylessHomePage.location).toString()));
		}

		if (!testDataMap.get("Coupon").toString().equalsIgnoreCase("NA")) {
			assertTrue(couponCode.getText().toString().contains(testDataMap.get("Coupon").toString())); 
		}
		if (!testDataMap.get("PDN").toString().equalsIgnoreCase("NA")) {
			assertTrue(pdn.getText().toString().contains(testDataMap.get("PDN").toString())); 
		}

		if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("Paylater")) {
			assertTrue(selectACarText.getText().toString().contains("Choose a Car"));
			helper.scrollToElement(PayLater);
			clickOn(PayLater);
		}
		if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("PayNow")) {
			assertTrue(selectACarText.getText().toString().contains("Choose a Car"));
			clickOn(PaynowButton);
		}
//		if(testDataMap.get("TestCaseName").toString().contains("Misc")) {
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='available-car-facilities']")));
//			List<WebElement> car = driver.findElements(By.xpath("//div[@class='available-car-facilities']"));
//			System.out.println(car.size());
//		      ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//			for (int i = 0; i < car.size(); i++) {
//				WebElement carImage = car.get(i);
//				threadSleep(TEN_SECONDS);
////				carImage.isDisplayed();
//			}
//		}
	}

	@Override
	public void isOnPage() {

	}


}



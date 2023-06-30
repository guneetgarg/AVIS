package pageObjects;

import static org.testng.Assert.assertTrue;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.avis.qa.core.AbstractBasePage;

public class PaylessRentalPage extends AbstractBasePage{

	public PaylessRentalPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[@class='btn btn-primary-avis pull-right'] | //button[contains(@id,'res-extras-continue-bottom')] | (//button[contains(text(),'Continue')])[2] | (//button[contains(text(),'Continue')])[1]")
	private WebElement continueButton;

	@FindBy(xpath = "(//div[@class='vehicle-name'])[1] | //p[@class='car-name']")
	private WebElement vehicleName;

	@FindBy(xpath = "//h1[@class='pull-left mar-bottom-0']")
	private WebElement recommendExtras;

	@FindBy(xpath = "//label[@ng-if=\"insurance.price != 'INCLUDED'\"]")
	private WebElement selectRecommends;


	public void rentalPage(Map<?, ?> testDataMap) {
		if(testDataMap.get("Continue").toString().equalsIgnoreCase("Yes")) {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			try {
			wait.until(ExpectedConditions.visibilityOf(recommendExtras));
			assertTrue(recommendExtras.getText().contains("Recommended Extras"));
			wait.until(ExpectedConditions.visibilityOf(continueButton));
			clickOn(continueButton);
			} catch (Exception e) {
				System.out.println(e);
			}
		}	
	}


	@Override
	public void isOnPage() {

	}

}

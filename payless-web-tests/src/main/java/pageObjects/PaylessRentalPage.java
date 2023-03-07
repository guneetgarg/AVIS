package pageObjects;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.avis.qa.core.AbstractBasePage;

public class PaylessRentalPage extends AbstractBasePage{

	public PaylessRentalPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//button[@class='btn btn-primary-avis pull-right'] | //button[contains(@id,'res-extras-continue-bottom')] | (//button[contains(text(),'Continue')])[2] | (//button[contains(text(),'Continue')])[1]")
	private WebElement CONTINUEBUTTON;

	@FindBy(xpath = "(//div[@class='vehicle-name'])[1] | //p[@class='car-name']")
	private WebElement vehicleName;

	@FindBy(xpath = "//h1[@class='pull-left mar-bottom-0']")
	private WebElement recommendExtras;//verify


	public void rentalPage(Map testDataMap) {
		assertTrue(recommendExtras.getText().contains("Recommended Extras"));
		clickOn(CONTINUEBUTTON);
	}


	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub

	}

}
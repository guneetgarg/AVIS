package pageObjects;

import java.util.Map;
import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.utilities.CommonUtils.FIVE_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;

import com.avis.qa.core.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public  class PaylessPofileDashboardPage extends AbstractBasePage{

	public PaylessPofileDashboardPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "edit-password")
	private WebElement modifyPassword;

	@FindBy(id = "currentPasswordHide")
	private WebElement currentPassword;

	@FindBy(id = "newPasswordHide")
	private WebElement newPassword;

	@FindBy(xpath = "(//button[contains(text(),'Save')])[2]")
	private WebElement SaveButton;

	@FindBy(xpath = "(//button[contains(text(),'Save')])[3]")
	private WebElement SaveButton1;

	@FindBy(id = "addressline1")
	private WebElement updateAddress;

	@FindBy(xpath = "//div[@class='success-alert']")
	private WebElement verifyMessage;

	@FindBy(xpath = "(//a[@class='modify-link'])[last()]")
	private WebElement modifyAddress;


	public void modify(Map testDataMap) {
		if (!testDataMap.get("Password1").toString().equalsIgnoreCase("NA")) {
			clickOn(modifyPassword);
			threadSleep(TWO_SECONDS);
			fillText(currentPassword, testDataMap.get("Password").toString());
			fillText(newPassword, testDataMap.get("Password1").toString());
			clickOn(SaveButton);
			threadSleep(FIVE_SECONDS);
			assertTrue(verifyMessage.getText().toString().contains("Your password has been successfully changed."));		
		}
		if (!testDataMap.get("MailingAddress1").toString().equalsIgnoreCase("NA")) {
			clickOn(modifyAddress);
			fillText(updateAddress, testDataMap.get("MailingAddress1").toString());
			clickOn(SaveButton1);
			threadSleep(FIVE_SECONDS);
			assertTrue(verifyMessage.getText().toString().contains("Your information has been successfully updated"));	
		}
		}

	@Override
	public void isOnPage() {

	}
}


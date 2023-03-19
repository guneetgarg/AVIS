package pageObjects;

import static com.avis.qa.utilities.CommonUtils.TEN_SECONDS;
import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.utilities.CommonUtils.FIVE_SECONDS;

import static com.avis.qa.utilities.CommonUtils.threadSleep;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.utilities.CommonUtils;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class BudgetHomePage extends AbstractBasePage {

	public BudgetHomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static final String COUPON = "Coupon";

	@FindBy(id = "avis-logo")
	private WebElement payLessBrandText;

	@FindBy(xpath = "//div[@class='bx-wrap']")
	private WebElement AdOverLayDiv;

	@FindBy(xpath = "//div[@class='bx-wrap']//button[@data-click='close']")
	private WebElement AdOverLayCloseButton;

	@FindBy(xpath = "//li[@class='dropdown']")
	private WebElement reservation;

	@FindBy(xpath = "//a[@href='/en/reservation/make-reservation.html']")
	private WebElement makeaReservation;

	@FindBy(xpath = "(//input[@id='PicLoc_value'])[1]")
	private WebElement pickUpLocation;

	@FindBy(xpath = "((//div[@class='angucomplete-results'])[1]//div[@class='angucomplete-description'])[1] | ((//div[@id='PicLoc_dropdown'])[1]//div[@class='angucomplete-row'])[1]")
	private WebElement suggestionLocation;

	@FindBy(xpath = "//input[@ng-model='vm.reservationModel.pickUpDateDisplay']")
	private WebElement pickUpDate;

	@FindBy(xpath = "//*[contains(@name,'reservationModel.pickUpTime')]")
	private WebElement pickUpTime;

	@FindBy(xpath = "//input[@ng-model='vm.reservationModel.dropDateDisplay']")
	private WebElement returnDatePath;

	@FindBy(xpath = "//*[contains(@name,'reservationModel.dropTime')]")
	private WebElement dropOffTime;

	@FindBy(xpath = "//*[contains(@id,'DropLoc_value')]")
	private WebElement dropOffLocation;

	@FindBy(id = "DropLoc_value")
	private WebElement enterReturnLocation;

	@FindBy(xpath = "(//div[@class='angucomplete-description'])[1]")
	private WebElement dropOffSuggestion;

	@FindBy(xpath = "(//*[contains(@ng-click,'getVehicles.submit')])[1]")
	private WebElement selectMyCarButton;

	@FindBy(id = "age")
	private WebElement ageDropDown;

	@FindBy(xpath = "(//*[@id='reservationModel.personalInfoRQ.residency'])[1]")
	private WebElement selectCountry;

	@FindBy(id = "couponpdn")
	private WebElement CouponCheckBox;

	@FindBy(id = "coupon")
	private WebElement CouponTextField;

	@FindBy(id = "awd")
	private WebElement pdnTextField;

	@FindBy(xpath = "//div[@class='res-discFld form-controlD']")
	private WebElement offerCodes;

	@FindBy(xpath = "//div[@class='res-wizardFld form-controlD']")
	private WebElement addCustomerID;

	@FindBy(xpath = "//input[@aria-label='Res-wizard Number']")
	private WebElement enterCustomerID;

	@FindBy(xpath = "//input[@id='res-home-lastName']")
	private WebElement enterLastName;

	@FindBy(xpath = "//*[@id='awd']")
	private WebElement AWDOrBCDOrPDN_TextField;

	@FindBy(xpath = "//input[@id='partnerMembershipId']")
	private WebElement membershipTextField;

	@FindBy(xpath = "(//a[text()='Reservations'])[1]")
	private WebElement ReservationsTab;

	@FindBy(xpath = "//a[text()='View / Modify / Cancel']")
	private WebElement Reservation_ViewModifyCancelLink;

	@FindBy(xpath = "//a[@class='ui-datepicker-next ui-corner-all']")
	private WebElement nextMonth;

	@FindBy(xpath = "//input[@aria-label='corporate email address']")
	private WebElement corporateEmailId;

	@FindBy(xpath = "((//a[@id='res-login-profile'])[2]) | ((//a[contains(text(),'Sign In')])[1])")
	private WebElement HeaderLoginButton2;

	@FindBy(xpath = "(//a[contains(text(),'Sign In')])[2] | (//a[@id='res-login-profile'])[2]")
	private WebElement HeaderLoginButton;

	@FindBy(xpath = "(//ul[@class='header-secondary']//li//a[@id='res-login-profile'])[1]")
	private WebElement HeaderLoginButtonBudget;

	@FindBy(id = "username")
	private WebElement UserName;

	@FindBy(id = "password")
	private WebElement Password;

	@FindBy(id = "res-login-profile")
	private WebElement LoginButton;

	@FindBy(xpath = "//input[@id='rememberme']")
	private WebElement RememberMeToggle;

	@FindBy(xpath = "//span[@class='close-FC g-icon']")
	public WebElement PopupBudget;

	@FindBy(xpath = "//input[@name='otp']")
	private WebElement OTPtextBox;

	@FindBy(xpath = "//button[@id='otp_submit']")
	private WebElement OTPSubmitButton;

	@FindBy(id = "warning-msg-err")
	private WebElement soldOutWarningMessage;

	@FindBy(xpath = "(//span[@class='step-title'])[2]")
	private WebElement selectCarTitle;

	public void selectYourCar(Map testDataMap) throws InterruptedException {

		clickOn(AdOverLayCloseButton);

		if (testDataMap.get("UserType").toString().equalsIgnoreCase("Guest")) {

			clickOn(reservation);
			clickOn(makeaReservation);

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(pickUpLocation));
			String pickupLocations[] = testDataMap.get("PickUpLocation").toString().split(",");

//			for (int i = 0; i < pickupLocations.length; i++) {
//				if(i>0) {
//					pickUpLocation.click();
//					pickUpLocation.clear();
//				}
				clickOn(pickUpLocation);
//				String randomNumber = CommonUtils.getRandomNumber(0, 6);
//				fillText(pickUpLocation, pickupLocations[Integer.valueOf(randomNumber)]);
				fillText(pickUpLocation, testDataMap.get("PickUpLocation").toString());
				clickOn(suggestionLocation);
				if (!testDataMap.get("PickUpDate").toString().equalsIgnoreCase("NA")) {
					clickOn(pickUpDate);
					pickUpDate.clear();
					fillText(pickUpDate, testDataMap.get("PickUpDate").toString());
				}
				if (!testDataMap.get("PickUpTime").toString().equalsIgnoreCase("NA")) {
				fillText(pickUpTime, testDataMap.get("PickUpTime").toString());
				}
				if (!testDataMap.get("DropOffDate").toString().equalsIgnoreCase("NA")) {
					clickOn(returnDatePath);
					returnDatePath.clear();
					fillText(returnDatePath, testDataMap.get("DropOffDate").toString());
				}
				if (!testDataMap.get("DropOffTime").toString().equalsIgnoreCase("NA")) {
				fillText(dropOffTime, testDataMap.get("DropOffTime").toString());
				}
				if (!testDataMap.get("DropOffLocation").toString().equalsIgnoreCase("NA")) {
					fillText(enterReturnLocation, testDataMap.get("DropOffLocation").toString());
					clickOn(dropOffSuggestion);
				}
				if (!testDataMap.get("Age").toString().equalsIgnoreCase("NA")) {
					clickOn(ageDropDown);
					fillText(ageDropDown, testDataMap.get("Age").toString());
				}
				if (!testDataMap.get("Country").toString().equalsIgnoreCase("NA")) {
					clickOn(selectCountry);
					fillText(selectCountry, testDataMap.get("Country").toString());
					clickOn(selectCountry);
				}
				if (!testDataMap.get(COUPON).toString().equalsIgnoreCase("NA")) {
					clickOn(offerCodes);
					clickOn(CouponCheckBox);
					fillText(CouponTextField, testDataMap.get(COUPON).toString());
				}

				if (!testDataMap.get("CustomerID").toString().equalsIgnoreCase("NA")) {
					clickOn(offerCodes);
					clickOn(addCustomerID);
					clickOn(enterCustomerID);
					fillText(enterCustomerID, testDataMap.get("CustomerID").toString());
					fillText(enterLastName, testDataMap.get("LastName1").toString());
				}
				if (!testDataMap.get("BCD").toString().equalsIgnoreCase("NA")) {
					clickOn(offerCodes);
					clickOn(AWDOrBCDOrPDN_TextField);
					AWDOrBCDOrPDN_TextField.clear();
					AWDOrBCDOrPDN_TextField.sendKeys(testDataMap.get("BCD").toString(), Keys.TAB);
					if (!testDataMap.get("CorporateEmailID").toString().equalsIgnoreCase("NA")) {
						fillText(corporateEmailId, testDataMap.get("CorporateEmailID").toString());
					} else {
						if (testDataMap.get("BCD").toString().contains("W8")) {
							fillText(membershipTextField, "112000000000");
						} else {
							fillText(membershipTextField, testDataMap.get("MemberNumber").toString());
						}
					}
				}
				clickOn(selectMyCarButton);

//				if (isVehicleAvailable()) {
//					break;
//				}

//			}

		}

		if (testDataMap.get("UserType").toString().equalsIgnoreCase("Signin")) {

			clickOn(HeaderLoginButton);
			waitForVisibilityOfElement(UserName);
			UserName.sendKeys(testDataMap.get("Username").toString());
			waitForVisibilityOfElement(Password);
			Password.sendKeys(testDataMap.get("Password").toString());
			threadSleep(TWO_SECONDS);
			clickUsingJS(LoginButton);
		}

	}

	public boolean isVehicleAvailable() throws InterruptedException {
		boolean isVehiclePresent = false;
		Thread.sleep(10000);
		List<WebElement> errorMessage = driver.findElements(By.id("warning-msg-err"));
		List<WebElement> selectCarTitle = driver.findElements(By.xpath("(//a[@class='modify-link']/span)[2]"));
		
		System.out.println(errorMessage.size());
		System.out.println(selectCarTitle.size());
		if(errorMessage.size()==0 && selectCarTitle.size()>0) {
			isVehiclePresent = true;
		}
		return isVehiclePresent;

	}

	public void viewModify(Map<?, ?> testDataMap) {
		clickOn(ReservationsTab);
		clickOn(Reservation_ViewModifyCancelLink);
	}

	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub

	}

}

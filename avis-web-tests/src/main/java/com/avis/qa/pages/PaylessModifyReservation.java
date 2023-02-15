package com.avis.qa.pages;

import static com.avis.qa.utilities.CommonUtils.ONE_SECOND;
import static com.avis.qa.utilities.CommonUtils.THREE_SECONDS;
import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.avis.qa.core.TestBase;
import com.avis.qa.core.AbstractBasePage;

public class PaylessModifyReservation extends TestBase {
	
	
	@FindBy(xpath = "//span[text()='Edit']")
	private WebElement editLink;
	
	@FindBy(id = "modify-link")
	private WebElement modifyReservation;
	
	@FindBy(xpath = "//*[contains(@id,'from')] | //input[@id='from']")
	private WebElement pickupDate;
	
	@FindBy(xpath = "//div[contains(@class,'ui-datepicker-group ui-datepicker-group-last')]/div/a")
	private WebElement nextMonthSelection;

	@FindBy(xpath = "//table[contains(@class,'ui-datepicker-calendar uitable ui-datepicker-table-first ')]//a[contains(text(),16)]")
	private WebElement pickupDateSelection;

	@FindBy(xpath = "//table[contains(@class,'datepicker-calendar')]//a[contains(text(),10)]")
	private WebElement returnDateSelection;
	
	@FindBy(xpath = "//input[@ng-model='vm.reservationModel.pickUpDateDisplay']")
	private WebElement pickUpDate;

	@FindBy(xpath = "//*[contains(@name,'reservationModel.pickUpTime')]")
	private WebElement pickUpTime;

	@FindBy(xpath = "//input[@ng-model='vm.reservationModel.dropDateDisplay']")
	private WebElement returnDatePath;

	@FindBy(xpath = "//*[contains(@name,'reservationModel.dropTime')]")
	private WebElement dropOffTime;

	
	
	
	public void modifyReservationPage(Map testDataMap) {
		editLink.click();
		modifyReservation.click();
		if(!testDataMap.get("").toString().equalsIgnoreCase("")) {
		helper.scrollBy("-600");
		threadSleep(TWO_SECONDS);
		pickupDate.click();
//		fillText(pickUpDate,testDataMap.get("PickUpDate").toString());
//		fillText(pickUpTime,testDataMap.get("PickUpTime").toString());
//		fillText(returnDatePath,testDataMap.get("DropOffDate").toString());
//		fillText(dropOffTime,testDataMap.get("DropOffTime").toString());		
		}	
		
		
	}

}

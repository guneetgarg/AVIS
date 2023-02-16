package com.avis.qa.pages;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.avis.qa.core.AbstractBasePage;

public class BudgetHomePage {
	
	
	@FindBy(xpath = "//div[@class='bx-wrap']")
    private WebElement AdOverLayDiv;

    @FindBy(xpath = "//div[@class='bx-wrap']//button[@data-click='close']")
    private WebElement AdOverLayCloseButton;
    
    
    
    public void selectYourCar(Map testDataMap) {
    	
    	
    	
    }
	

}

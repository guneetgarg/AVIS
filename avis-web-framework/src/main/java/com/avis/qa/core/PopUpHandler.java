package com.avis.qa.core;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class PopUpHandler {

    private WebDriver driver;
    public PopUpHandler(WebDriver driver) {

        this.driver = driver;
        if(isDisplayed()){
            close();
        }
    }

    public boolean isDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
           return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-click='close']"))).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void close(){
        log.info("PopUp is present. Killing it!!");
        this.driver.findElement(By.xpath("//button[@data-click='close']")).click();
    }

}

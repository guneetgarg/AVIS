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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            log.info("Waiting for PopUp");
           return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='bx-wrap']"))).isDisplayed();
        }catch (Exception e){
            log.info("PopUp is not present");
            return false;
        }
    }

    public void close(){
        log.info("PopUp is present. Killing it!!");
        this.driver.findElement(By.xpath("//button[@data-click='close']")).click();
    }

}

package com.avis.qa.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PopUpHandler {

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
           return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='bx-wrap']"))).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void close(){
        this.driver.findElement(By.xpath("//button[@data-click='close']")).click();
    }

}

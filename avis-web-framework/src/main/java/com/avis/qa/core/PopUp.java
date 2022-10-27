package com.avis.qa.core;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PopUp {

    private WebDriver driver;
    public PopUp(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed(){
//        FluentWait wait = new FluentWait(driver);
//        wait.withTimeout(Duration.ofSeconds(3));
//        wait.pollingEvery(Duration.ofMillis(100));
//        wait.ignoring(NoSuchElementException.class);


//        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='bx-wrap']")));
        WebDriverWait wait = new WebDriverWait(driver, 5);
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

package com.avis.qa.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Field;

public class MyPageFactory {
    public static <T> void initElements(WebDriver driver, T pageobject){

        //first init elements
        PageFactory.initElements(driver, pageobject);

        //then access all the WebElements and create a wrapper
        for(Field f:pageobject.getClass().getDeclaredFields()){
            if(f.getType().equals(WebElement.class)){
                boolean accessible = f.isAccessible();
                f.setAccessible(true);
                //reset the webelement with proxy object
                try {
                    f.set(pageobject, ElementGuard.guard(driver , (WebElement) f.get(pageobject)));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                f.setAccessible(accessible);
            }
        }

    }

}

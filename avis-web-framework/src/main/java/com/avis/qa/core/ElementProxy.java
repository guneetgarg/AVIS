package com.avis.qa.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ElementProxy implements InvocationHandler {
     private final WebElement element;
     private PopUp popUp;

    public ElementProxy(WebDriver driver, WebElement element) {
        this.element = element;
        this.popUp = new PopUp(driver);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.checkForPopupAndKill();
        Object result = method.invoke(element, args);
        return result;
    }

    private void checkForPopupAndKill() {
//        if (popUp.isDisplayed()) {
//            System.out.println("Killing the popup");
//            popUp.close();
//        }
        System.out.println("hello");
    }
}

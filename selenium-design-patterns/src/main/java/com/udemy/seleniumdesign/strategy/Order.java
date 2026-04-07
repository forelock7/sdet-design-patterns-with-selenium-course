package com.udemy.seleniumdesign.strategy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Order {
    @FindBy(id = "buy")
    private WebElement buyButton;

    @FindBy(id = "ordernumber")
    private WebElement orderNumber;

    public Order(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String placeOrder() {
        buyButton.click();
        return orderNumber.getText();
    }

}

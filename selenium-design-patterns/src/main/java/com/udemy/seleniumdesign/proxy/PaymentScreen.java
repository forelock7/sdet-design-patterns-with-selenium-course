package com.udemy.seleniumdesign.proxy;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PaymentScreen {
    private WebDriver driver;
    private UserInformation userInformation;
    private OrderComponent orderComponent;
    private PaymentOption paymentOption;

    public PaymentScreen(WebDriver driver) {
        this.driver = driver;
        this.userInformation = PageFactory.initElements(driver, UserInformation.class);
        this.orderComponent = PageFactory.initElements(driver, OrderComponentProxy.class);
    }

    public void goTo() {
        driver.get("https://vins-udemy.s3.amazonaws.com/ds/strategy.html");
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public OrderComponent getOrder() {
        return orderComponent;
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
        PageFactory.initElements(driver, paymentOption);
    }

    public void pay(Map<String, String> paymentDetails) {
        paymentOption.enterPaymentInformation(paymentDetails);
    }

}

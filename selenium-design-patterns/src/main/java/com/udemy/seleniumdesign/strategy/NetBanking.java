package com.udemy.seleniumdesign.strategy;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class NetBanking implements PaymentOption {
    @FindBy(id = "bank")
    private WebElement bank;

    @FindBy(id = "acc_number")
    private WebElement account;
    
    @FindBy(id = "pin")
    private WebElement pin;

    @Override
    public void enterPaymentInformation(Map<String, String> paymentDetails) {
        Select bankDropdown = new Select(this.bank);
        bankDropdown.selectByValue(paymentDetails.get("bank"));
        this.account.sendKeys(paymentDetails.get("account"));
        this.pin.sendKeys(paymentDetails.get("pin"));
    }

}

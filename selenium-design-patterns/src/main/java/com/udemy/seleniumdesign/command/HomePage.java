package com.udemy.seleniumdesign.command;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private final WebDriver driver;

    // buttons
    @FindBy(css = "div.button-box button.btn-info")
    private WebElement infoBtn;

    @FindBy(css = "div.button-box button.btn-warning")
    private WebElement warningBtn;

    @FindBy(css = "div.button-box button.btn-success")
    private WebElement successBtn;

    @FindBy(css = "div.button-box button.btn-danger")
    private WebElement dangerBtn;

    // notifications
    @FindBy(css = "div.jq-icon-info")
    private WebElement infoAlert;

    @FindBy(css = "div.jq-icon-warning")
    private WebElement warningAlert;

    @FindBy(css = "div.jq-icon-success")
    private WebElement successAlert;

    @FindBy(css = "div.jq-icon-error")
    private WebElement dangerAlert;

    // dismissal alerts
    @FindBy(css = "div.card-body div.row div:nth-child(2) div.alert-info")
    private WebElement dismissInfoAlert;

    @FindBy(css = "div.card-body div.row div:nth-child(2) div.alert-warning")
    private WebElement dismissWarningAlert;

    @FindBy(css = "div.card-body div.row div:nth-child(2) div.alert-success")
    private WebElement dismissSuccessAlert;

    @FindBy(css = "div.card-body div.row div:nth-child(2) div.alert-danger")
    private WebElement dismissDangerAlert;

    public HomePage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void goTo() {
        this.driver.get("https://vins-udemy.s3.amazonaws.com/ds/admin-template/admin-template.html");
    }

    public List<ElementValidator> getElementValidators() {
        return Arrays.asList(
            // notification alerts
            new NotificationValidator(infoBtn, infoAlert),
            new NotificationValidator(successBtn, successAlert),
            new NotificationValidator(warningBtn, warningAlert),
            new NotificationValidator(dangerBtn, dangerAlert),
            // dismissal alerts
            new DismissalAlertValidator(dismissInfoAlert),
            new DismissalAlertValidator(dismissSuccessAlert),
            new DismissalAlertValidator(dismissWarningAlert),
            new DismissalAlertValidator(dismissDangerAlert));
    }

}

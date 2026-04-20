package com.udemy.seleniumdesign.command;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import com.google.common.util.concurrent.Uninterruptibles;

public class DismissalAlertValidator extends ElementValidator {
    private final WebElement dismissalAlert;

    public DismissalAlertValidator(final WebElement element) {
        this.dismissalAlert = element;
    }

    @Override
    public boolean validate() {
        boolean appearanceState = this.dismissalAlert.isDisplayed(); // true
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        boolean disappearanceState = this.dismissalAlert.isDisplayed(); // false
        return appearanceState && !disappearanceState;
    }

}

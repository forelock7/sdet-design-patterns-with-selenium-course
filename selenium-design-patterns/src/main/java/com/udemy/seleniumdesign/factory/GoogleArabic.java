package com.udemy.seleniumdesign.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class GoogleArabic extends GoogleEnglish {

    @FindBy(css = "div#gws-output-pages-elements-homepage_additional_languages__als a")
    private WebElement language;

    @FindBy(css = "span.h0oLGe")
    private WebElement keyboardBtn;

    @FindBy(id = "kbd")
    private WebElement keyboard;


    public GoogleArabic(final WebDriver driver) {
        super(driver);
    }

    @Override
    public void launchSite() {
        this.driver.get("https://www.google.com.sa");
        this.language.click();
    }

    @Override
    public void search(String keaword) {
        this.wait.until(d -> this.keyboardBtn.isDisplayed());
        this.keyboardBtn.click();
        this.wait.until(d -> this.keyboard.isDisplayed());
        super.search(keaword);
    }
}

package com.udemy.seleniumdesign.factory;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

class GoogleEnglish extends GooglePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    @FindBy(css = "div.rc")
    private List<WebElement> results;

    public GoogleEnglish(final WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    @Override
    public void launchSite() {
        this.driver.get("https://www.google.com/");
    }

    @Override
    public void search(String keaword) {
        this.searchBox.sendKeys(keaword);
        this.searchButton.click();
    }

    @Override
    public int getResultsCount() {
        this.wait.until(d -> this.results.size() > 1);
        return this.results.size();
    }

}

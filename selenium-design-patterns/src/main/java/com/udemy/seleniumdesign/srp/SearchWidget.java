package com.udemy.seleniumdesign.srp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchWidget extends AbstractComponent {

    @FindBy(name = "q")
    private WebElement searchBox;

    public SearchWidget(final WebDriver driver) {
        super(driver);
    }

    public void enter(String searchText) {
        this.searchBox.clear();
        this.searchBox.sendKeys(searchText);
    }

    @Override
    public boolean isDisplayed() {
        return this.wait.until((driver) -> this.searchBox.isDisplayed());
    }

}

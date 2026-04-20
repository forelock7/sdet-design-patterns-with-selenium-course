package com.udemy.seleniumdesign.test.command;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.udemy.seleniumdesign.command.HomePage;
import com.udemy.seleniumdesign.test.BaseTest;

public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @BeforeTest
    public void setHomePage() {
        this.homePage = new HomePage(this.driver);
    }

    @Test
    public void validateHomePageElements() {
        this.homePage.goTo();
        this.homePage.getElementValidators()
        .stream()
        .parallel()
        .map(ev -> ev.validate())
        .forEach(b -> Assert.assertTrue(b));
    }

}

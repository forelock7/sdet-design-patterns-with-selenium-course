package com.udemy.seleniumdesign.test.factory;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.udemy.seleniumdesign.factory.GoogleFactory;
import com.udemy.seleniumdesign.factory.GooglePage;
import com.udemy.seleniumdesign.test.BaseTest;

public class GoogleSearchTest extends BaseTest {

    private GooglePage googlePage;

    @Test(dataProvider = "getData")
    public void searchTest(String language, String keyword) {
        this.googlePage = GoogleFactory.get(language, this.driver);
        this.googlePage.launchSite();
        this.googlePage.search(keyword);
        int resultsCount = this.googlePage.getResultsCount();
        System.out.println("Results count: " + resultsCount);
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
                {"ENG", "selenium"},
                {"FR", "Patrones de diseño en Selenium"},
                {"SA", "Modèles de conception Selenium"},
                {"ES", "Patrones de diseño en Selenium"}
        };
    }   
}

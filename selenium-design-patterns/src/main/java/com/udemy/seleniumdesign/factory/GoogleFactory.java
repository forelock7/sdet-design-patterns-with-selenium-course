package com.udemy.seleniumdesign.factory;

import java.util.Map;
import java.util.function.Function;

import org.openqa.selenium.WebDriver;

public class GoogleFactory {
    private static final Function<WebDriver, GooglePage> ENG = driver -> new GoogleEnglish(driver);
    private static final Function<WebDriver, GooglePage> FR = driver -> new GoogleFrench(driver);
    private static final Function<WebDriver, GooglePage> SA = driver -> new GoogleArabic(driver);
    private static final Function<WebDriver, GooglePage> ES = driver -> new GoogleES(driver);

    private static final Map<String, Function<WebDriver, GooglePage>> LANGUAGE_MAP = Map.of(
            "ENG", ENG,
            "FR", FR,
            "SA", SA,
            "ES", ES
    );

    public static GooglePage get(final String language, final WebDriver driver) {
        return LANGUAGE_MAP.get(language).apply(driver);
    }
}

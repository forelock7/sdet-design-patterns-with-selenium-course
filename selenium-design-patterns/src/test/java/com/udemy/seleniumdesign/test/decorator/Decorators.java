package com.udemy.seleniumdesign.test.decorator;

import java.util.List;
import java.util.function.Consumer;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.udemy.seleniumdesign.decorator.DashboardPage;

public class Decorators {
    private static void shouldDisplay(List<WebElement> elements) {
        elements.forEach(element -> Assert.assertTrue(element.isDisplayed())); 
    }

    private static void shouldNotDisplay(List<WebElement> elements) {
        elements.forEach(element -> Assert.assertFalse(element.isDisplayed())); 
    }

    // ingredients
    private static final Consumer<DashboardPage> adminComponentPresent = (dp) -> shouldDisplay(dp.getAdminComponents());
    private static final Consumer<DashboardPage> adminComponentNotPresent = (dp) -> shouldNotDisplay(dp.getAdminComponents());
    private static final Consumer<DashboardPage> superAdminComponentPresent = (dp) -> shouldDisplay(dp.getSuperAdminComponents());
    private static final Consumer<DashboardPage> superAdminComponentNotPresent = (dp) -> shouldNotDisplay(dp.getSuperAdminComponents());
    private static final Consumer<DashboardPage> guestComponentPresent = (dp) -> shouldDisplay(dp.getGuestComponents());
    private static final Consumer<DashboardPage> guestComponentNotPresent = (dp) -> shouldNotDisplay(dp.getGuestComponents());

    // role selection
    private static final Consumer<DashboardPage> adminSelection = (dp) -> dp.selectRole("admin");
    private static final Consumer<DashboardPage> superAdminSelection = (dp) -> dp.selectRole("superadmin");
    private static final Consumer<DashboardPage> guestSelection = (dp) -> dp.selectRole("guest");

    // user role pages
    public static final Consumer<DashboardPage> adminPage = adminSelection
            .andThen(adminComponentPresent)
            .andThen(superAdminComponentPresent)
            .andThen(guestComponentPresent);
    public static final Consumer<DashboardPage> superAdminPage = superAdminSelection
            .andThen(adminComponentNotPresent)
            .andThen(superAdminComponentPresent)
            .andThen(guestComponentPresent);
    public static final Consumer<DashboardPage> guestPage = guestSelection
            .andThen(adminComponentNotPresent)
            .andThen(superAdminComponentNotPresent)
            .andThen(guestComponentPresent);
}

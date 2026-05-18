package com.udemy.seleniumdesign.test.executearound;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.udemy.seleniumdesign.executearound.FrameA;
import com.udemy.seleniumdesign.executearound.FrameB;
import com.udemy.seleniumdesign.executearound.MainPage;
import com.udemy.seleniumdesign.test.BaseTest;

public class FrameTest extends BaseTest {

    private MainPage mainPage;

    @BeforeTest
    public void setMainPage() {
        mainPage = new MainPage(driver);
    }

    @Test
    public void testFrames() {
        mainPage.goTo();

        this.mainPage.onFrameA(a -> {   
            a.setFirstName("Vins");
            a.setLastName("Chouhan");
            a.setAddress("India");
            a.setMessage("Hello from Frame A");
        });
    }   
}

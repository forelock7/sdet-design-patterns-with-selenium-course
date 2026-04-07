package com.udemy.seleniumdesign.test.strategy;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// import com.udemy.seleniumdesign.strategy.CreditCard;
// import com.udemy.seleniumdesign.strategy.NetBanking;
// import com.udemy.seleniumdesign.strategy.PaymentOption;
import com.udemy.seleniumdesign.strategy.PaymentOptionFactory;
import com.udemy.seleniumdesign.strategy.PaymentScreen;
import com.udemy.seleniumdesign.test.BaseTest;

public class PaymentScreenTest extends BaseTest {

    private PaymentScreen paymentScreen;

    @BeforeClass
    public void setPaymentScreen() {
        this.paymentScreen = new PaymentScreen(this.driver);
    }

    @Test
    public void testPaymentScreen(String paymentOption, Map<String, String> paymentDetails) {
        paymentScreen.goTo();
        paymentScreen.getUserInformation().enterDetails("John", "Doe", "john.doe@example.com");
        paymentScreen.setPaymentOption(PaymentOptionFactory.get(paymentOption));
        paymentScreen.pay(paymentDetails);

        String orderNumber = paymentScreen.getOrder().placeOrder();
        System.out.println("Order number: " + orderNumber);
    }

    @DataProvider
    public Object[][] paymentOptions() {
        // Strategy pattern
        // return new Object[][] {
        //     { new CreditCard(), Map.of("cc", "1234 5678 9012 3456", "year", "2025", "cvv", "123") },
        //     { new NetBanking(), Map.of("bank", "bank1", "account", "12345678", "pin", "1234") }
        // };
        // Factory pattern
        return new Object[][] {
            { "CC", Map.of("cc", "1234 5678 9012 3456", "year", "2025", "cvv", "123") },
            { "NB", Map.of("bank", "bank1", "account", "12345678", "pin", "1234") }
        };
    }
}

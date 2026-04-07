# sdet-design-patterns-with-selenium-course

Link:

# Section 1: Introduction

# Section 2: Single Responsibility Principle

## 5. Single Responsibility Principle - Introduction

Class should have only one resposibility / one reason to change

## 6. [Optional] - Coupling & Cohesion

Coupling - is a measure of the degree of the dependancies among classes / modules

Cohesion - is a measure of the the degree of the relationships of the members within classes / modules

## 7. Sample Workflow For Automation

## 8. Page Components Design

## 9. **_ Source Code _**

- [https://github.com/vinsguru/selenium-design-patterns/blob/master/pom.xml](https://github.com/vinsguru/selenium-design-patterns/blob/master/pom.xml)
- [**_https://github.com/vinsguru/selenium-design-patterns_**](https://github.com/vinsguru/selenium-design-patterns)

## 10. IDE - Setup

## 11. Abstract Component

## 12. Search Widget Component Implementation

## 13. Search Suggestion Component Implementation

## 14. Explicit Wait Using Lambda

```
@Override
public boolean isDisplayed() {
    return this.wait.until((driver) -> this.suggestions.size() > 5);
}
```

## 15. Refactoring

## 16. Navigation Bar Component Implementation

## 17. Result Stat Component Implementation

```
package com.udemy.seleniumdesign.srp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultStat extends AbstractComponent {

    @FindBy(id = "resultStats")
    private WebElement stat;

    public ResultStat(WebDriver driver) {
        super(driver);
    }

    public String getStat() {
        return this.stat.getText();
    }

    @Override
    public boolean isDisplayed() {
        return this.wait.until((d) -> this.stat.isDisplayed());
    }

}
```

## 18. Google Main Page Implementation

```

package com.udemy.seleniumdesign.srp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class GoogleMainPage {

    private WebDriver driver;
    private SearchWidget searchWidget;
    private SearchSuggestion searchSuggestion;

    public GoogleMainPage(final WebDriver driver) {
        this.driver = driver;
        this.searchWidget = PageFactory.initElements(driver, SearchWidget.class);
        this.searchSuggestion = PageFactory.initElements(driver, SearchSuggestion.class);
    }

    public void goTo() {
        this.driver.get("https://www.google.com");
    }

    public SearchWidget getSearchWidget() {
        return searchWidget;
    }

    public SearchSuggestion getSearchSuggestion() {
        return searchSuggestion;
    }
}
```

## 19. Google Result Page Implementation

## 20. Base Test

```
public class BaseTest {
    private WebDriver driver;

    @BeforeTest
    public void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        this.driver = new ChromeDriver();
    }

    @AfterTest
    public void quitDriver() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        this.driver.quit();
    }
}
```

## 21. Google Search Test - Part 1

## 22. Google Search Test - Part 2

```
public class GoogleTest extends BaseTest {
    private GoogleMainPage googleMainPage;
    private GoogleResultPage googleResultPage;

    @BeforeTest
    public void setupPages() {
        this.googleMainPage = new GoogleMainPage(driver);
        this.googleResultPage = new GoogleResultPage(driver);
    }

    @Test
    public void googleWorkflow() {
        String keyword = "selenium webdriver";
        int index = 3;

        googleMainPage.goTo();
        Assert.assertTrue(googleMainPage.getSearchWidget().isDisplayed());

        googleMainPage.getSearchWidget().enter(keyword);
        Assert.assertTrue(googleMainPage.getSearchSuggestion().isDisplayed());

        googleMainPage.getSearchSuggestion().clickSuggestionByIndex(index);
        Assert.assertTrue(googleResultPage.getNavigationBar().isDisplayed());

        googleResultPage.getSearchWidget().enter(keyword);
        Assert.assertTrue(googleMainPage.getSearchSuggestion().isDisplayed());

        googleResultPage.getSearchSuggestion().clickSuggestionByIndex(index);

        googleResultPage.getNavigationBar().gotToNews();

        System.out.println(googleResultPage.getResultStat().getStat());
    }

}
```

## 23. Few Corrections

```
    public void enter(String keyword) {
        this.searchBox.clear();
        for (char ch: keyword.toCharArray()) {
            Uninterruptibles.sleepUninterruptibly(20, TimeUnit.MILLISECONDS);
            this.searchBox.sendKeys(String.valueOf(ch));
        }
    }
```

## 24. Google Search Test Parameterize

```
    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
                {"selenium", 3},
                {"DOCKER", 2}
        };
    }

    @Test(dataProvider = "getData")
    public void googleWorkflow(String keyword, int index) {

        googleMainPage.goTo();
...
```

## 26. Package Refactoring

Create java packages for each page. Add components for certain pages and in 'common' package add common components.

## 27. Cohesion

If want to add some element, we should do it in particular component/page which it belongs to.

## 28. Project Structure

Tests (TestNG/Junit) -> Page Object -> Components (Page Fragments) -> Selenium -> Browser

Use SRP (Single Respocibility Principle)

## 29. Clarification For BDD

**BDD** -> Tests (TestNG/Junit) -> Page Object -> Components (Page Fragments) -> Selenium -> Browser

## 30. Single Responsibility Principle - Summary

- KISS principle (Keep It Simple Stupid)
- ~100 lines class
- Class should have only one responsibility.
- Create smaller / maintainable classes
- SRP is an interface. All the design patterns are implementation of this SRP.

# Section 3: Factory Pattern

## 31. Design Pattern - Introduction

## 32. SRP vs Design Patterns

Single Responcibility Principle vs Design Patterns

SRP - Trat class like a person. Assign 1 task.

Design Patterns - protocol / How 2 persons should talk

## 33. Design Patterns Classification

- **_Creational Pattern_** (Object creation mechanism)
  - Factory
- **_Behavioral Pattern_** (Object with algorithms/behaviors)
  - Strategy
  - Template Method
  - Command
  - Execute Around Method
- **_Structural Pattern_** (how to assemble objects and classes into larger structures)
  - Decorator
  - Proxy

## 34. Factory Introduction

- Create a new Object without exposing the installation logic
- Refer to the newly created object using its common interface

## 35. Factory - Exercise

Google Search Site:

- English (www.google.com)
- French (www.google.fr)
- Arabic (www.google.com.sa)

Steps:

- Launch site
- Enter the search keyword
- Just land on the search result page

## 36.Factory - Abstract Google Page

## 37. Factory - Google English Page Implementation

## 38. Factory - Google French Page Implementation

## 39. Factory - Google Arabic Page Implementation

## 40. Google Factory Implementation

```
import java.util.Map;
import java.util.function.Function;

import org.openqa.selenium.WebDriver;

public class GoogleFactory {
    private static final Function<WebDriver, GooglePage> ENG = driver -> new GoogleEnglish(driver);
    private static final Function<WebDriver, GooglePage> FR = driver -> new GoogleFrench(driver);
    private static final Function<WebDriver, GooglePage> SA = driver -> new GoogleArabic(driver);

    private static final Map<String, Function<WebDriver, GooglePage>> LANGUAGE_MAP = Map.of(
            "ENG", ENG,
            "FR", FR,
            "SA", SA
    );

    public static GooglePage getGooglePage(final WebDriver driver, final String language) {
        return LANGUAGE_MAP.get(language).apply(driver);
    }
}
```

## 41. Google Search Test With Factory

```
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
                {"SA", "Modèles de conception Selenium"}
        };
    }
}
```

## 42. Factory - Test Run Demo

## 43. Accommodating New Requirements

## 44. Additional Materials

- [WebDriver Management using Factory Pattern](https://www.vinsguru.com/selenium-webdriver-design-patterns-in-test-automation-factory-pattern/)
- [Factory Pattern using Java 8 Supplier](https://www.vinsguru.com/selenium-webdriver-factory-design-pattern-using-java-8-supplier/)

## 45. Factory - Summary

Usage in Test Automation:

- Application Localization support (English, French);
- Framework multiple browsers support (Chrome, Firefox);
- Require one object from multiple options.

# Section 4: Strategy Pattern

## 46. Strategy - Introduction

Goal:

- Define a family of algorithms/behaviours, encapsulate each one, and make them interchangeable.
- Ex: BluRay player (DVD, BluRay)

## 47. Strategy - Sample Application Walk-through

Resoource: https://vins-udemy.s3.amazonaws.com/ds/strategy.html

## 48. Strategy - Sample Application - Regular Design

## 49. Strategy - Payment Screen Design

## 50. Strategy - Payment Option Implementation

## 51. Strategy - Payment Screen Components

## 52. Strategy - Payment Screen Test - Part 1

## 53. Strategy - Payment Screen Test - Part 2

## 54. Strategy - Payment Screen Test - Run

## 55. Strategy Pattern Explanation

## 56. Factory vs Strategy

- Factory is creational pattern (there is no object at the beggining)
- Strategy is behavioral pattern (there is object at the beggining and we use different implementations inside that objects)

## 57. Combining Factory and Strategy

## 58. Additional Materials

- https://blog.vinsguru.com/selenium-webdriver-design-patterns-in-test-automation-strategy-pattern/

## 59. Strategy - Summary

- Application provides multiple options to the user & based on the user selection, application executes the business rule slightly differently

- Google Search Strategy
  - Text
  - Voice
  - Image

- Payment Options
  - Credit Card
  - Promocode
  - Bank Account/Routing
  - Ether Wallet

- Shipping
  - Immediate Door delivery
  - 2 Day air shipping
  - 5 Day ground shipping

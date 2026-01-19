# sdet-design-patterns-with-selenium-course

Link:

## Section 1: Introduction

## Section 2: Single Responsibility Principle

### 5. Single Responsibility Principle - Introduction

Class should have only one resposibility / one reason to change

### 6. [Optional] - Coupling & Cohesion

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

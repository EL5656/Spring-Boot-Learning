package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultPage extends BaseClass {
    //count>0
    private By searchResult = By.xpath("//div[@class='productinfo text-center']//img");

    WebDriver driver;

    public SearchResultPage(){
        this.driver = getDriver();
    }

    public boolean isProductAvailable() throws InterruptedException {
        WebElement result = getDriver().findElement(searchResult);
        return Action.isDisplayed(getDriver(), result);
    }

    public ProductDetailsPage clickOnViewProduct(){
        WebElement viewBtn = getDriver().findElement(By.xpath("//a[contains(@href, 'product') and contains(text(), 'View')]"));
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", viewBtn);
        Action.click(getDriver(), viewBtn);
        return new ProductDetailsPage();
    }

}

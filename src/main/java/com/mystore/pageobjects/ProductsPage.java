package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage extends ProductPage {
    WebDriver driver;

    ProductsPage(){
        this.driver = getDriver();
    }

    public SearchResultPage searchProduct(String productName){

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("search_product"))
        );
        Action.type(searchBox, productName);
        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("submit_search"))
        );
        Action.click(getDriver(), button);
        return new SearchResultPage();
    }
}

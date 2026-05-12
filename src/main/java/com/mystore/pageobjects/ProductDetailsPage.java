package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailsPage extends SearchResultPage {
    private By successMessage = By.xpath("//h4[text()='Added!']");

    Action action = new Action();

    WebDriver driver;

    public ProductDetailsPage(){
        this.driver = getDriver();
    }

    public void enterQuantity(String number){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement elem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("quantity"))
        );
          Action.type(elem, number);
    }

    public void clickOnAddToCart(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement addToCartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Add to cart')]"))
        );
        Action.click(getDriver(), addToCartBtn);
    }

    public boolean validateAddToCart(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(successMessage)
        );
        return Action.isDisplayed(getDriver(), successMsg);
    }

    public CartPage goToViewCart(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        WebElement viewCart = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[u[text()='View Cart']]"))
        );
        Action.click(getDriver(), viewCart);
        return new CartPage();
    }
}

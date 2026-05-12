package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductPage extends LoginOrSignUpPage {

    List<String> product = new ArrayList<>();

    By itemImage = By.xpath("//div[@class='features_items']//img");
    static By itemName = By.xpath("//div[@class='features_items']//div[@class='productinfo text-center']//p");
    By price = By.xpath("//div[@class='features_items']//div[@class='productinfo text-center']//h2");
    By login = By.xpath("//a[contains(text(), 'Logged in as')]");
    private WebElement cartBtn = getDriver().findElement(By.xpath("//div[@class='shop-menu pull-right']//a[contains(normalize-space(), 'Cart')]"));

    WebDriver driver;

    public ProductPage() {//constructor
        this.driver= getDriver();
    }

    public String getCurrentURL(){
        return getDriver().getCurrentUrl();
    }

    public CartPage clickCartBtn(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        WebElement cart = wait.until(
                ExpectedConditions.elementToBeClickable(cartBtn)
        );
        Action.click(getDriver(), cart);
        return new CartPage();
    }

    public boolean login(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            WebElement loggedIn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(login)
            );
            return Action.isDisplayed(getDriver(), loggedIn);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public List<String> getAllProductNames() {
        List<WebElement> productNames = getDriver().findElements(itemName);
        for (WebElement productName : productNames) {
            product.add(productName.getText().trim());
        }
        return product;
    }

    public List<String> getAllPrice() {
        List<WebElement> prices = getDriver().findElements(price);
        for (WebElement singlePrice : prices) {
            product.add(singlePrice.getText().trim());
        }
        return product;
    }

    private static int getCount(WebDriver driver, By locator) {
        return driver.findElements(locator).size();
    }

    public boolean validateProductImage(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='features_items']")
        ));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                itemImage, 0
        ));
        int cards = getDriver().findElements(
                By.xpath("//div[@class='features_items']//div[@class='product-image-wrapper']")
        ).size();
        int images = getDriver().findElements(itemImage).size();
        System.out.println("Number of cards: " + cards);
        System.out.println("Number of images: " + images);
        return cards == images;
    }
}

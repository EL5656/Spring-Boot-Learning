package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends CartPage{
    //if no login - show message
    private By checkoutMessage = By.xpath("//p[contains(text, checkout)]");
    private By loginButton = By.xpath("//a[u[contains(text(),'Login')]]");
    private By placeOrderButton = By.xpath("//a[text()='Place Order']");

    WebDriver driver;
    public CheckoutPage() {
        this.driver = getDriver();
    }

    public boolean validateCheckoutMessage(){
        WebElement checkoutMsg = getDriver().findElement(checkoutMessage);
        return Action.isDisplayed(getDriver(),checkoutMsg);
    }

    public LoginOrSignUpPage loginOrSignUpClick() throws InterruptedException {
        WebElement loginBtn = getDriver().findElement(loginButton);
        Action.click(getDriver(), loginBtn);
        return new LoginOrSignUpPage();
    }

    public PaymentPage placeOrder(){
        WebElement placeOrderBtn = getDriver().findElement(placeOrderButton);
        action.scrollByVisibilityOfElement(getDriver(), placeOrderBtn);
        Action.click(getDriver(),placeOrderBtn);
        return new PaymentPage();
    }

}

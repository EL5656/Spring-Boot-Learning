package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage extends CheckoutPage{

    private By payment = By.xpath("//h2[text()='Payment']");

    WebDriver driver;

    public PaymentPage() {
        this.driver = getDriver();
    }

    public boolean isPaymentDisplayed(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement label = wait.until(
                ExpectedConditions.visibilityOfElementLocated(payment)
        );
        return Action.isDisplayed(getDriver(),label);
    }

}

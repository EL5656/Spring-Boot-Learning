package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends ProductDetailsPage {

    private By proceedToCheckoutButton = By.xpath("//a[text()='Proceed To Checkout']");
    private By totalPrice = By.xpath("//td[@class='cart_total']/p");

    WebDriver driver;

    public CartPage() {
        this.driver = getDriver();
    }

    public CheckoutPage proceedToCheckoutClick() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        WebElement proceedToCheckOutBtn = wait.until(
                ExpectedConditions.elementToBeClickable(proceedToCheckoutButton)
        );
        Action.click(getDriver(), proceedToCheckOutBtn);
        return new CheckoutPage();
    }

    public double getUnitPrice(){
        WebElement price = getDriver().findElement(By.xpath("//td[@class='cart_price']/p"));
        String unitPrice = price.getText();
        String unit = unitPrice.replaceAll("[^0-9]","");
        System.out.println(unit);
        double finalUnitPrice = Double.parseDouble(unit);
        return finalUnitPrice;
    }

    public double getTotalPrice(){
        WebElement price = getDriver().findElement(totalPrice);
        String totalPrice1 = price.getText();
        String total = totalPrice1.replaceAll("[^0-9]","");
        System.out.println(total);
        double totalUnitPrice = Double.parseDouble(total);
        return totalUnitPrice;
    }

    public void deleteItem(String name){
        By deleteItem = By.xpath("//a[text()='"+name+"']//following::a[class='cart_quantity_delete']");
        WebElement delete = getDriver().findElement(deleteItem);
        Action.click(getDriver(), delete);
    }
    //todo - row initial count and curCount - not feasible to print all values

}

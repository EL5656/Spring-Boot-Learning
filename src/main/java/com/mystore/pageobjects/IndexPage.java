package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IndexPage extends BaseClass {
    //home page before login

    WebElement signUpOrLoginBtn = getDriver().findElement(By.xpath("//a[contains(text(), 'Login')]"));
    private WebElement myStoreLogo = getDriver().findElement(By.xpath("//img[@alt='Website for automation practice']"));

    WebDriver driver;

    public IndexPage() {
        this.driver = getDriver();
    }

    public LoginOrSignUpPage clickOnSignUpOrLogin(){
        Action.click(getDriver(),signUpOrLoginBtn);
        return new LoginOrSignUpPage();
    }

    public ProductsPage clickProductsBtn(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement productsBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@href='/products']")
                )
        );
        Action.click(getDriver(),productsBtn);
        return new ProductsPage();
    }

    public boolean validateLogo() throws Throwable{
        return Action.isDisplayed(getDriver(), myStoreLogo);
    }

    public String getMyStoreTitle(){
        String myStoreTitle = getDriver().getTitle();
        return myStoreTitle;
    }
}

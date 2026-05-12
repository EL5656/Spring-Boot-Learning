package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginOrSignUpPage extends BaseClass {

    WebDriver driver;

    public LoginOrSignUpPage(){
        this.driver = getDriver();
    }

    public ProductPage login(String email, String pswd) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        WebElement userName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-email']"))
        );
        WebElement password = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("password"))
        );
        Action.type(userName, email);
        Action.type(password, pswd);
        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-qa='login-button']"))
        );
        Action.click(getDriver(), loginBtn);
        return new ProductPage();
    }

    public AccountCreationPage createNewAccount(String signUpName, String username){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        WebElement name = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("name"))
        );
        WebElement usernameForNewAccount = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='signup-email']"))
        );
        Action.type(name,signUpName);
        Action.type(usernameForNewAccount,username);
        WebElement signupButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-qa='signup-button']"))
        );
        Action.click(getDriver(), signupButton);
        return new AccountCreationPage();
    }

}

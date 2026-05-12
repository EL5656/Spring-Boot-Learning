package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AccountCreationPage extends BaseClass {

    private By formTitle = By.xpath("//b[text()='Enter Account Information']");

    public boolean validateAccountCreatePage(){
        WebElement title = getDriver().findElement(formTitle);
        return Action.isDisplayed(getDriver(), title);
    }



}

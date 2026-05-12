package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AccountCreationPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginOrSignUpPage;
import com.mystore.pageobjects.ProductPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class AccountCreationTest extends BaseClass {
    IndexPage indexPage;
    LoginOrSignUpPage loginOrSignUpPage;
    AccountCreationPage accountCreationPage;

    @Parameters("browser")
    @BeforeMethod(groups={"Smoke","Sanity","Regression"})
    public void setup(@Optional("chrome") String browser){
        launchApp(browser);
    }

    @AfterMethod(groups={"Smoke","Sanity","Regression"})
    public void tearDown(){
        getDriver().quit();
    }

    @Test(groups = "Sanity")
    public void verifySignUp() throws Throwable {
        indexPage = new IndexPage();
        loginOrSignUpPage = indexPage.clickOnSignUpOrLogin();
        accountCreationPage = loginOrSignUpPage.createNewAccount("admin","jivoj88704@example.com");
        boolean signup = accountCreationPage.validateAccountCreatePage();
        Assert.assertTrue(signup);
    }

}

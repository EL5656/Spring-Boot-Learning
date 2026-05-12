package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginOrSignUpPage;
import com.mystore.pageobjects.ProductPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class HomePageTest extends BaseClass {
    IndexPage indexPage;
    LoginOrSignUpPage loginOrSignUpPage;
    ProductPage productPage;

    @Parameters("browser")
    @BeforeMethod(groups={"Smoke","Sanity","Regression"})
    public void setup(@Optional("chrome") String browser){
        launchApp(browser);
    }

    @AfterMethod(groups={"Smoke","Sanity","Regression"})
    public void tearDown(){
        getDriver().quit();
    }

    @Test(groups = "Smoke")
    public void verifyProductImage() throws Throwable{
        indexPage = new IndexPage();
        loginOrSignUpPage = indexPage.clickOnSignUpOrLogin();
        productPage = loginOrSignUpPage.login(prop.getProperty("username"), prop.getProperty("password"));
        boolean validateProductImage = productPage.validateProductImage();
        Assert.assertTrue(validateProductImage);
    }

    @Test(groups = "Smoke")
    public void verifyLoggedInAsIcon() throws Throwable{
        indexPage = new IndexPage();
        loginOrSignUpPage = indexPage.clickOnSignUpOrLogin();
        productPage = loginOrSignUpPage.login(prop.getProperty("username"), prop.getProperty("password"));
        boolean validateLoginAs = productPage.login();
        Assert.assertTrue(validateLoginAs);
    }
}

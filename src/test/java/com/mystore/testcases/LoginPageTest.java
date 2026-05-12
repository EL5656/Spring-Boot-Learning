package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginOrSignUpPage;
import com.mystore.pageobjects.ProductPage;
import com.mystore.utility.Log;
import org.testng.Assert;
import org.testng.annotations.*;


public class LoginPageTest extends BaseClass {
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

    @Test()
    public void loginTest()  {
        Log.startTestCase("loginTest");
        indexPage = new IndexPage();
        Log.info("user is going to click on sign in");
        loginOrSignUpPage = indexPage.clickOnSignUpOrLogin();
        Log.info("enter username and password");
        productPage= loginOrSignUpPage.login(prop.getProperty("username"), prop.getProperty("password"));
        String actual = productPage.getCurrentURL();
        System.out.println(actual);
        String expected = "https://automationexercise.com/";
        Log.info("verifying if user is able to login");
        Assert.assertTrue(actual.equalsIgnoreCase(expected));
        Log.info("Login is success");
        Log.endTestCase("loginTest");
    }

}

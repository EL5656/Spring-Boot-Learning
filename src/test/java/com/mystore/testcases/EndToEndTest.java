package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class EndToEndTest extends BaseClass {
    IndexPage indexPage;
    ProductsPage productsPage;
    SearchResultPage searchResultPage;
    ProductDetailsPage productDetailsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    LoginOrSignUpPage loginOrSignUpPage;
    ProductPage productPage;
    PaymentPage paymentpage;

    @Parameters("browser")
    @BeforeMethod(groups={"Smoke","Sanity","Regression"})
    public void setup(@Optional("chrome") String browser){
        launchApp(browser);
    }

    @AfterMethod(groups={"Smoke","Sanity","Regression"})
    public void tearDown(){
        getDriver().quit();
    }

    @Test(groups="Regression")
    public void endToEndTest() throws Throwable {
        indexPage = new IndexPage();
        productsPage = indexPage.clickProductsBtn();
        if (getDriver().getCurrentUrl().contains("google_vignette")) {
            getDriver().get("https://automationexercise.com/products");
        }
        searchResultPage = productsPage.searchProduct("Top");
        productDetailsPage = searchResultPage.clickOnViewProduct();
        productDetailsPage.enterQuantity("2");
        productDetailsPage.clickOnAddToCart();
        cartPage = productDetailsPage.goToViewCart();
        checkoutPage = cartPage.proceedToCheckoutClick();
        loginOrSignUpPage = checkoutPage.loginOrSignUpClick();
        productPage= loginOrSignUpPage.login(prop.getProperty("username"), prop.getProperty("password"));
        cartPage = productPage.clickCartBtn();//
        checkoutPage = cartPage.proceedToCheckoutClick();
        paymentpage = checkoutPage.placeOrder();
        Assert.assertTrue(paymentpage.isPaymentDisplayed());
    }
}

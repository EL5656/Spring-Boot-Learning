package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class AddToCartTest extends BaseClass {
    IndexPage indexPage;
    ProductsPage productsPage;
    SearchResultPage searchResultPage;
    ProductDetailsPage productDetailsPage;

    @Parameters("browser")
    @BeforeMethod(groups={"Smoke","Sanity","Regression"})
    public void setup(@Optional("chrome") String browser){
        launchApp(browser);
    }

    @AfterMethod(groups={"Smoke","Sanity","Regression"})
    public void tearDown(){
        getDriver().quit();
    }

    @Test(groups = {"Regression","Sanity"})
    public void addToCartTest() throws Throwable{
        indexPage = new IndexPage();
        productsPage =indexPage.clickProductsBtn();
        if (getDriver().getCurrentUrl().contains("google_vignette")) {
            getDriver().get("https://automationexercise.com/products");
        }
        searchResultPage = productsPage.searchProduct("Top");

        productDetailsPage = searchResultPage.clickOnViewProduct();
        productDetailsPage.enterQuantity("2");
        productDetailsPage.clickOnAddToCart();
        boolean result = productDetailsPage.validateAddToCart();
        Assert.assertTrue(result);
    }
}

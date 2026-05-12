package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class CartPageTest extends BaseClass {
    IndexPage indexPage;
    ProductsPage productsPage;
    SearchResultPage searchResultPage;
    ProductDetailsPage productDetailsPage;
    CartPage cartPage;

    @Parameters("browser")
    @BeforeMethod(groups={"Smoke","Sanity","Regression"})
    public void setup(@Optional("chrome") String browser){
        launchApp(browser);
    }

    @AfterMethod(groups={"Smoke","Sanity","Regression"})
    public void tearDown(){
        getDriver().quit();
    }

    @Test(groups = {"Regression"})
    public void validatePriceTest() throws Throwable{
        indexPage = new IndexPage();
        productsPage =indexPage.clickProductsBtn();
        if (getDriver().getCurrentUrl().contains("google_vignette")) {
            getDriver().get("https://automationexercise.com/products");
        }
        searchResultPage = productsPage.searchProduct("Top");
        productDetailsPage = searchResultPage.clickOnViewProduct();//
        productDetailsPage.enterQuantity("2");
        productDetailsPage.clickOnAddToCart();
        cartPage = productDetailsPage.goToViewCart();

        Double unitPrice = cartPage.getUnitPrice();
        Double totalPrice = cartPage.getTotalPrice();
        Double totalExpectedPrice = (unitPrice*2);
        System.out.println(totalExpectedPrice);
        Assert.assertEquals(totalPrice, totalExpectedPrice);
    }
}

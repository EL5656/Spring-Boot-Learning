package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.ProductsPage;
import com.mystore.pageobjects.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class SearchResultPageTest extends BaseClass {
    IndexPage indexPage;
    ProductsPage productsPage;
    SearchResultPage searchResultPage;

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
    public void validateProductAvailability() throws Throwable{
        indexPage = new IndexPage();
        productsPage =indexPage.clickProductsBtn();
        if (getDriver().getCurrentUrl().contains("google_vignette")) {
            getDriver().get("https://automationexercise.com/products");
        }
        searchResultPage = productsPage.searchProduct("Top");
        boolean result = searchResultPage.isProductAvailable();
        Assert.assertTrue(result);
    }
}

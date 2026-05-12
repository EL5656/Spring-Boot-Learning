package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.ProductPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ProductPageTest extends BaseClass {
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

    @Test(groups="Smoke")
    public void verifyProductImage() throws Throwable{
        productPage = new ProductPage();
        boolean validateProductImage = productPage.validateProductImage();
        Assert.assertTrue(validateProductImage);
    }

    @Test(groups="Smoke")
    public void validateProductNames() {
        productPage = new ProductPage();
        List<String> productNames = productPage.getAllProductNames();
        System.out.println("Total Products: " + productNames.size());
        for (String name : productNames) {
            System.out.println(name);
            Assert.assertFalse(name.isEmpty(),
                    "Product name is empty!");
        }
    }

    @Test(groups="Smoke")
    public void validateProductPrice(){
        productPage = new ProductPage();
        List<String> productPrices = productPage.getAllPrice();
        int count = 0;
        for (int i = 0; i < productPrices.size(); i++) {
            String text = productPrices.get(i);
            int price = Integer.parseInt(text.replaceAll("[^0-9]", ""));
            System.out.println(price);
            count++;
        }
        Assert.assertTrue(count>0);
        System.out.println(count);
    }

}

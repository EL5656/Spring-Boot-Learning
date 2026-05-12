package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class IndexPageTest extends BaseClass {

    IndexPage indexPage;

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
    public void verifyLogo() throws Throwable{
        indexPage = new IndexPage();
        boolean result = indexPage.validateLogo();
        Assert.assertTrue(result);
    }

    @Test(groups = "Smoke")
    public void verifyTitle(){
        String actualTitle = indexPage.getMyStoreTitle();
        Assert.assertEquals(actualTitle,"Automation Exercise");
    }
}

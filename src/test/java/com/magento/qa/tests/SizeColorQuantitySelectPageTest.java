package com.magento.qa.tests;

import com.magento.qa.base.TestBase;
import com.magento.qa.pages.*;
import com.magento.qa.testdata.magentoDataProviders;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SizeColorQuantitySelectPageTest extends TestBase {
    SignInPage signInpage;
    CreateAccountPage createAccountPage;
    ConsumerAccountIndexPage consumerAccountIndexPage;

    WomenMenuPage womenMenuPage;
    SizeColorQuantitySelectPage sizeColorQuantitySelectPage;
    ShoppingCartPage shoppingCartPage;
    String firstName = "firstName"+System.currentTimeMillis();
    String lastName  = "lastName"+System.currentTimeMillis();
    String emailAddress = "emailaddress"+System.currentTimeMillis()+"@yahoo.com";
    String password     = "qwerty12*";
    public SizeColorQuantitySelectPageTest(){
        super();
    }
    @BeforeClass
    public void testDataCreation(){
        initialization();
        LandingPage landingPage = new LandingPage();
        createAccountPage =landingPage.getCreateAccountPage();
        signInpage= createAccountPage.signUp(firstName,lastName,emailAddress,password,password);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofMillis(200));
        wait.until(ExpectedConditions.titleContains("My Account"));
        Assert.assertTrue(driver.getPageSource().contains(firstName));
        Assert.assertTrue(driver.getPageSource().contains(lastName));
        Assert.assertTrue(driver.getPageSource().contains(emailAddress));
        driver.quit();
    }

    @BeforeMethod
    public void setup(){
        initialization();
        LandingPage landingPage = new LandingPage();
        signInpage=landingPage.getSignInPage();
        consumerAccountIndexPage= signInpage.getConsumerAccountIndexPageForAnAccount(emailAddress,password);
        womenMenuPage=consumerAccountIndexPage.getMenu_WomenPageHtml();
        sizeColorQuantitySelectPage =womenMenuPage.getSizeColorQuantitySelectPage();
    }

    @Test(dataProvider = "sizeColorQuantityOptions",dataProviderClass = magentoDataProviders.class)
    public void shoppingCartWithOptions(String size,String color,String quantity){
        if(size.equalsIgnoreCase("medium")){
            sizeColorQuantitySelectPage.selectMediumSize();
        }
        if(color.equalsIgnoreCase("blue")){
            sizeColorQuantitySelectPage.selectBlueColor();
        }
        sizeColorQuantitySelectPage.enterQuantity(quantity);
        sizeColorQuantitySelectPage.addToCartBtnClick();
        shoppingCartPage=sizeColorQuantitySelectPage.getShoppingCart();
        Assert.assertEquals(shoppingCartPage.getTitle(),"Shopping Cart");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}

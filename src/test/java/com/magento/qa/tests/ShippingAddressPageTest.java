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

public class ShippingAddressPageTest extends TestBase {
    SignInPage signInpage;
    CreateAccountPage createAccountPage;
    ConsumerAccountIndexPage consumerAccountIndexPage;

    WomenMenuPage womenMenuPage;
    SizeColorQuantitySelectPage sizeColorQuantitySelectPage;
    ShoppingCartPage shoppingCartPage;

    ShippingAddressPage shippingAddressPage;
    PlaceOrderPage placeOrderPage;
    String firstName = "firstName"+System.currentTimeMillis();
    String lastName  = "lastName"+System.currentTimeMillis();
    String emailAddress = "emailaddress"+System.currentTimeMillis()+"@yahoo.com";
    String password     = "qwerty12*";
    String defaultQuantity ="2";
    String defaultOrderAmount="$154.00";
    public ShippingAddressPageTest(){
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
        sizeColorQuantitySelectPage.selectMediumSize();
        sizeColorQuantitySelectPage.selectBlueColor();
        sizeColorQuantitySelectPage.enterQuantity(defaultQuantity);
        sizeColorQuantitySelectPage.addToCartBtnClick();
        shoppingCartPage=sizeColorQuantitySelectPage.getShoppingCart();
        //shoppingCartPage.checkOrderTotal(defaultOrderAmount);
        shippingAddressPage=shoppingCartPage.setProceedToCheckOut();

    }

    @Test(dataProvider = "getShippingAddressInfo", dataProviderClass = magentoDataProviders.class)
    public void testShippingAddressPageSuccess(String stAddress,String city, String state,String zipcode,String countryName,String phoneno){
        shippingAddressPage.setStreetAddress(stAddress);
        shippingAddressPage.setCity(city);
        shippingAddressPage.selectState(state);
        shippingAddressPage.setZipcode(zipcode);
        shippingAddressPage.setCountrySelect(countryName);
        shippingAddressPage.setPhoneNumber(phoneno);
        placeOrderPage= shippingAddressPage.submitFromShippingAddressPage();
        Assert.assertEquals(placeOrderPage.getTitle(),"Checkout");



    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

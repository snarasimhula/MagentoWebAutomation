package com.magento.qa.tests;

import com.magento.qa.base.TestBase;
import com.magento.qa.pages.*;
import com.magento.qa.testdata.magentoDataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class PlaceOrderPageTest extends TestBase {
    SignInPage signInpage;
    CreateAccountPage createAccountPage;
    ConsumerAccountIndexPage consumerAccountIndexPage;

    WomenMenuPage womenMenuPage;
    SizeColorQuantitySelectPage sizeColorQuantitySelectPage;
    ShoppingCartPage shoppingCartPage;

    ShippingAddressPage shippingAddressPage;
    PlaceOrderPage placeOrderPage;

    OrderSuccessfulPage orderSuccessfulPage;
    String firstName = "firstName"+System.currentTimeMillis();
    String lastName  = "lastName"+System.currentTimeMillis();
    String emailAddress = "emailaddress"+System.currentTimeMillis()+"@yahoo.com";
    String password     = "qwerty12*";
    String defaultQuantity ="2";
    String defaultOrderAmount="$154.00";
    String stAddress="7805 NE 4th Ave";
    String city ="Mckinney";
    String state ="Texas";
    String country="United States";
    String zipcode ="75071";
    String phoneNo ="4256788989";
    public PlaceOrderPageTest(){
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
        Assert.assertEquals(shoppingCartPage.getOrderTotal(),defaultOrderAmount);
        shippingAddressPage=shoppingCartPage.setProceedToCheckOut();


    }

    @Test
    public void testPlaceOrderSuccess(){
        shippingAddressPage.setStreetAddress(stAddress);
        shippingAddressPage.setCity(city);
        shippingAddressPage.selectState(state);
        shippingAddressPage.setZipcode(zipcode);
        shippingAddressPage.setCountrySelect(country);
        shippingAddressPage.setPhoneNumber(phoneNo);
        shippingAddressPage.selectPurchaseOption();
        placeOrderPage= shippingAddressPage.submitFromShippingAddressPage();
        orderSuccessfulPage = placeOrderPage.placeOrderSuccess();
        Assert.assertEquals(orderSuccessfulPage.getTitle(),"Success Page");
        Assert.assertTrue(!orderSuccessfulPage.getListText("Your order number is:").isEmpty());
        Assert.assertTrue(orderSuccessfulPage.getListText("Your order number is:").get(0).getText().contains("Your order number is:"));
        orderSuccessfulPage.clickOrderSuccessfulLink();
        Assert.assertTrue(!orderSuccessfulPage.getListText("Order # ").isEmpty());
        Assert.assertTrue(orderSuccessfulPage.getListText("Order # ").get(1).getText().contains("Order # "));


    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

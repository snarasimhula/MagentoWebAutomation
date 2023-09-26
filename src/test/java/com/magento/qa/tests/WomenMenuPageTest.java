package com.magento.qa.tests;

import com.magento.qa.base.TestBase;
import com.magento.qa.pages.*;
import com.magento.qa.testdata.GetDataFromExcelSheet;
import com.magento.qa.utils.TestUtils;
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

public class WomenMenuPageTest extends TestBase {
    SignInPage signInpage;
    CreateAccountPage createAccountPage;
    ConsumerAccountIndexPage consumerAccountIndexPage;

    WomenMenuPage womenMenuPage;

    ShoppingCartPage shoppingCartPage;


    TestUtils testUtils;
    SizeColorQuantitySelectPage sizeColorQuantitySelectPage;
    String firstName = "firstName"+System.currentTimeMillis();
    String lastName  = "lastName"+System.currentTimeMillis();
    String emailAddress = "emailaddress"+System.currentTimeMillis()+"@yahoo.com";
    String password     = "qwerty12*";
    public WomenMenuPageTest(){
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
    }




    @Test
    public void getSizeColorQuantitySelectPageTest(){
        sizeColorQuantitySelectPage =womenMenuPage.getSizeColorQuantitySelectPage();
        Assert.assertEquals(sizeColorQuantitySelectPage.getTitle(),"Juno Jacket");
    }

    @Test
    public void testVariousOptionsForWomenMenu(){
       Object[][] testData = GetDataFromExcelSheet.getTestData("Women");
       int i=0;
       while(i<testData.length ){
           String highLevelCategory =testData[i][i].toString();
           String subCategory = testData[i][1].toString();
           String brand = testData[i][2].toString();
           String size = testData[i][3].toString();
           String color =testData[i][4].toString();
           String quantity =testData[i][5].toString();
            if(highLevelCategory.equalsIgnoreCase("Tops") &&
                   subCategory.equalsIgnoreCase("Jackets")&&
                   brand.equalsIgnoreCase("Juno Jacket") ){
                   sizeColorQuantitySelectPage= womenMenuPage.getSizeColorQuantitySelectPage();
               if(size.equalsIgnoreCase("Medium"))
                   sizeColorQuantitySelectPage.selectMediumSize();
               if(color.equalsIgnoreCase("Blue"))
                   sizeColorQuantitySelectPage.selectBlueColor();
                   sizeColorQuantitySelectPage.enterQuantity(quantity);
                   sizeColorQuantitySelectPage.addToCartBtnClick();
               shoppingCartPage=sizeColorQuantitySelectPage.getShoppingCart();
               Assert.assertEquals(shoppingCartPage.getTitle(),"Shopping Cart");
           }
           i++;
           }
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

package com.magento.qa.tests;

import com.magento.qa.pages.LandingPage;
import com.magento.qa.base.TestBase;
import com.magento.qa.pages.CreateAccountPage;
import com.magento.qa.pages.SignInPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SignInPageTest extends TestBase {
    SignInPage signInpage;
    CreateAccountPage createAccountPage;
    String firstName = "firstName"+System.currentTimeMillis();
    String lastName  = "lastName"+System.currentTimeMillis();
    String emailAddress = "emailaddress"+System.currentTimeMillis()+"@yahoo.com";
    String password     = "qwerty12*";
    public SignInPageTest(){
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
    }
    @Test
    public void testSignIn(){
        signInpage.signIn(emailAddress,password);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = driver.findElement(By.xpath("//button[@class=\"action switch\"]"));
        wait.until(ExpectedConditions.titleContains("Home Page"));
        wait.until(d->element.isDisplayed());
        element.click();
        WebElement myAccountLink = driver.findElement(By.xpath("//a[@href=\"https://magento.softwaretestingboard.com/customer/account/\"]"));
       Assert.assertTrue(myAccountLink.isDisplayed());
        WebElement myWishListLink = driver.findElement(By.xpath("//a[@href=\"https://magento.softwaretestingboard.com/wishlist/\"]"));
       Assert.assertTrue(myWishListLink.isDisplayed());
        WebElement signOutLink = driver.findElement(By.xpath("//a[@href=\"https://magento.softwaretestingboard.com/customer/account/logout/\"]"));
        Assert.assertTrue(signOutLink.isDisplayed());
    }


    @AfterMethod
    public void tearDown(){
       //driver.quit();
    }
}

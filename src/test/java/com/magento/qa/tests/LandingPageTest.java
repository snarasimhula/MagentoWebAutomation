package com.magento.qa.tests;

import com.magento.qa.base.TestBase;
import com.magento.qa.pages.LandingPage;
import com.magento.qa.pages.CreateAccountPage;
import com.magento.qa.pages.SignInPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LandingPageTest extends TestBase {
    LandingPage landingPage;
    CreateAccountPage createAccountPage;


    SignInPage signInPage;

    public LandingPageTest(){
        super();
    }
    @BeforeMethod
    public  void setUp(){
        initialization();
        landingPage = new LandingPage();
    }
    @Test(priority=1)
    public void landingPageTitleTest(){
        String title=    landingPage.getLandingPageTitle();
        Assert.assertEquals(title,"Home Page");
    }

    @Test(priority=2)
    public void landingPageLogoTest(){
        boolean logoExist = landingPage.isLogoDisplayed();
        System.out.println(logoExist);
        Assert.assertTrue(logoExist);
    }
    @Test(priority=3)
    public void landingPageToCreateAccountPageTest(){
        createAccountPage = landingPage.getCreateAccountPage();
        String createAccountPageTitle = createAccountPage.getCreateAccountPageTitle();
        Assert.assertEquals(createAccountPageTitle,"Create New Customer Account");

    }
    @Test(priority=4)
    public void landingPageToSignInPageTest(){
        signInPage = landingPage.getSignInPage();
        String signUpPageTitle = signInPage.getSignInPageTitle();
        Assert.assertEquals(signUpPageTitle,"Customer Login");

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}

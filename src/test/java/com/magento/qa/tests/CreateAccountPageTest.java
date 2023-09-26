package com.magento.qa.tests;

import com.magento.qa.pages.LandingPage;
import com.magento.qa.base.TestBase;
import com.magento.qa.pages.CreateAccountPage;
import com.magento.qa.pages.SignInPage;
import com.magento.qa.testdata.magentoDataProviders;
import com.magento.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CreateAccountPageTest extends TestBase {
    CreateAccountPage createAccountPage;
    SignInPage signInPage;

    TestUtils testUtils;
    public CreateAccountPageTest(){
        super();
    }
    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        initialization();
        LandingPage landingPage = new LandingPage();
        createAccountPage = landingPage.getCreateAccountPage();
        testUtils = new TestUtils();
    }

    @Test
    public void signUpTitleTest(){
     String title=    createAccountPage.getCreateAccountPageTitle();
     Assert.assertEquals(title,"Create New Customer Account");
    }

    @Test(groups ={"sanity"})
    public void createAccountTest(){
       String firstName = "firstName"+System.currentTimeMillis();
       String lastName  = "lastName"+System.currentTimeMillis();
       String emailAddress = "emailaddress"+System.currentTimeMillis()+"@yahoo.com";
       String password     = "qwerty12*";
       signInPage= createAccountPage.signUp(firstName,lastName,emailAddress,password,password);
       Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofMillis(200));
       wait.until(ExpectedConditions.titleContains("My Account"));
       Assert.assertTrue(driver.getPageSource().contains(firstName));
       Assert.assertTrue(driver.getPageSource().contains(lastName));
       Assert.assertTrue(driver.getPageSource().contains(emailAddress));
    }

    @Test(dataProvider = "createAccountNegativeCases", dataProviderClass = magentoDataProviders.class)
    public void invalidSignUpData(String firstName,String lastName, String emailAddress, String password,String confirmPass,String errorText) {
        signInPage = createAccountPage.signUp(firstName, lastName, emailAddress, password, confirmPass);
        if (testUtils.invalidName(firstName)) {
            WebElement element= driver.findElement(By.xpath("//*[contains(text(),'First Name is not valid!')]"));
            element.isDisplayed();
            Assert.assertTrue(element.getText().contains(errorText));
        }
        if (testUtils.invalidName(lastName)) {
            WebElement element= driver.findElement(By.xpath("//*[contains(text(),'Last Name is not valid!')]"));
            element.isDisplayed();
            Assert.assertTrue(element.getText().contains(errorText));
        }
        else {
            Assert.assertTrue(driver.getPageSource().contains(errorText));
        }
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
       driver.quit();
    }
}

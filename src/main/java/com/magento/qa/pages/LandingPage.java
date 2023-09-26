package com.magento.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.magento.qa.base.TestBase.driver;

public class LandingPage {

   // https://magento.softwaretestingboard.com/customer/account/create/
    @FindBy(linkText = "Create an Account")
    @CacheLookup
    private WebElement createAnAccount;

    @FindBy(linkText = "Sign In")
    @CacheLookup
    private WebElement signIn;
    @FindBy(xpath = "//img[contains(@src, 'https://magento.softwaretestingboard.com/pub/static/version1694847925/frontend/Magento/luma/en_US/images/logo.svg')]")
    @CacheLookup
    private WebElement logo;

    public LandingPage(){
        PageFactory.initElements(driver,this);
    }

    //Actions

    public String getLandingPageTitle(){
        return driver.getTitle();
    }

    public boolean isLogoDisplayed(){
        return logo.isDisplayed();
    }

    public CreateAccountPage getCreateAccountPage(){
        createAnAccount.click();
        return new CreateAccountPage();
    }

    public SignInPage getSignInPage(){
        signIn.click();
        return new SignInPage();
    }

}

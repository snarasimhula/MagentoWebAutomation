package com.magento.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.magento.qa.base.TestBase.driver;

public class LandingPage {

   // https://magento.softwaretestingboard.com/customer/account/create/
    @FindBy(linkText = "Create an Account")
    @CacheLookup
    private WebElement createAnAccount;

    @FindBy( className ="logo")
    @CacheLookup
    private WebElement logo;

    @FindBy(linkText = "Sign In")
    @CacheLookup
    private WebElement signIn;
    @FindBy(xpath = "//img[contains(@src, 'https://magento.softwaretestingboard.com/pub/static/version1694847925/frontend/Magento/luma/en_US/images/logo.svg')]")
    @CacheLookup
    private WebElement logoIMg;


    public LandingPage(){
        PageFactory.initElements(driver,this);
    }

    //Actions

    public String getLandingPageTitle(){
        return driver.getTitle();
    }

    public boolean isLogoDisplayed(){
        System.out.println(logo.getText());
        return logo.isDisplayed();
    }

    public boolean isLogoByImgSrc(){
        System.out.println(logoIMg.getText());
        return logoIMg.isDisplayed();
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

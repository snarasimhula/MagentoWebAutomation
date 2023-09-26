package com.magento.qa.pages;

import com.magento.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends TestBase {
    //PageFactory repository
    @FindBy(id = "email")
    @CacheLookup
    private WebElement email;


    @FindBy(id = "pass")
    @CacheLookup
    private WebElement password;

    @FindBy(id = "send2")
    @CacheLookup
    private WebElement signIn;

    @FindBy(css = "a[href='https://magento.softwaretestingboard.com/customer/account/forgotpassword/']")
    @CacheLookup
    private WebElement forgotYourPassword;


    public SignInPage(){
        PageFactory.initElements(driver, this);
    }
    //Actions

    public String getSignInPageTitle(){
        return  driver.getTitle();
    }

    public  void signIn(String emailId,String pwd){
        email.sendKeys(emailId);
        password.sendKeys(pwd);
        signIn.click();
    }

    public  ConsumerAccountIndexPage getConsumerAccountIndexPageForAnAccount(String emailId,String pwd){
        email.sendKeys(emailId);
        password.sendKeys(pwd);
        signIn.click();
        return new ConsumerAccountIndexPage();
    }

}

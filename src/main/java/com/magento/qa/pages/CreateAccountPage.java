package com.magento.qa.pages;

import com.magento.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage extends TestBase {
    @FindBy(id = "firstname")
    @CacheLookup
    private WebElement firstName;
    @FindBy(id = "lastname")
    @CacheLookup
    private WebElement lastName;
    @FindBy(id = "email_address")
    @CacheLookup
    private WebElement email;


    @FindBy(id = "password")
    @CacheLookup
    private WebElement password;
    @FindBy(id = "password-confirmation")
    @CacheLookup
    private WebElement confirmPassword;

    @FindBy(css = "a[href='https://magento.softwaretestingboard.com/customer/account/forgotpassword/']")
    @CacheLookup
    private WebElement forgotYourPassword;

    @FindBy(xpath ="//*[@id=\"form-validate\"]/div/div[1]/button")
    @CacheLookup
    private WebElement createAnAccount;

    //Initialize page objects
    public CreateAccountPage(){
        PageFactory.initElements(driver,this);
    }

    //Actions on the page
    public String getCreateAccountPageTitle(){
        return driver.getTitle();
    }

    public SignInPage signUp(String fName, String lName, String emailAddress, String pwd, String confPassword ){
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        email.sendKeys(emailAddress);
        password.sendKeys(pwd);
        confirmPassword.sendKeys(confPassword);
        createAnAccount.click();
        return new SignInPage();
    }





}

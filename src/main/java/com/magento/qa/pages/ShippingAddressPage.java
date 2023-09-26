package com.magento.qa.pages;

import com.magento.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ShippingAddressPage extends TestBase {


     @FindBy(xpath="//input[@name=\"street[0]\"]")
     @CacheLookup
     WebElement streetAddress;
    @FindBy(xpath="//*[@name=\"city\"]")
    @CacheLookup
    WebElement city;
    @FindBy(xpath="//*[@name=\"region_id\"]")
    @CacheLookup
    Select stateSelect;

    @FindBy(xpath="//*[@name=\"postcode\"]")
    @CacheLookup
    WebElement zipcode;

    @FindBy(xpath="//*[@name=\"country_id\"]")
    @CacheLookup
    Select countrySelect;

    @FindBy(xpath="//*[@name=\"telephone\"]")
    @CacheLookup
    WebElement phoneNumber;

    @FindBy(xpath="//*[@id=\"checkout-shipping-method-load\"]/table/tbody/tr[1]/td[1]/input")
    @CacheLookup
    WebElement purchaseOptionSelect;

     @FindBy(xpath="//*[@id=\"shipping-method-buttons-container\"]/div/button/span")
     @CacheLookup
     WebElement next;


    public ShippingAddressPage(){
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void setStreetAddress(String stAddress){
        streetAddress.sendKeys(stAddress);
    }
    public void setCity(String cty){
        city.sendKeys(cty);
    }

    public void selectState(String state){
        Select selectState = new Select( driver.findElement(By.xpath("//*[@name=\"region_id\"]")));
        selectState.selectByVisibleText(state);
    }

    public void setZipcode(String zip){
        zipcode.sendKeys(zip);
    }

    public  void setCountrySelect(String country){
        Select selectCountry = new Select(  driver.findElement(By.xpath("//*[@name=\"country_id\"]")));
        selectCountry.selectByVisibleText(country);
    }

    public void setPhoneNumber(String phoneNo){
     phoneNumber.sendKeys(phoneNo);
    }

    public void selectPurchaseOption(){
        purchaseOptionSelect.click();
    }

    public PlaceOrderPage submitFromShippingAddressPage(){
        next.click();
        return new PlaceOrderPage();

    }


}

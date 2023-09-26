package com.magento.qa.pages;

import com.magento.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaceOrderPage extends TestBase {


        @FindBy(xpath = "//button[@title=\"Place Order\"]")
        @CacheLookup
        WebElement placeOrderBtn;
    public PlaceOrderPage(){
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public OrderSuccessfulPage placeOrderSuccess(){
        Actions actions = new Actions(driver);
        actions.click(placeOrderBtn).build().perform();
        placeOrderBtn.click();
        return new OrderSuccessfulPage();
    }
}

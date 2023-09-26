package com.magento.qa.pages;

import com.magento.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SizeColorQuantitySelectPage extends TestBase {




        @FindBy(xpath="//*[@id=\"option-label-size-143-item-168\"]")
        @CacheLookup
        WebElement mediumSize;
        @FindBy(xpath="//*[@id=\"option-label-color-93-item-50\"]")
        @CacheLookup
        WebElement blueColor;

        @FindBy(xpath="//*[@id=\"qty\"]")
        @CacheLookup
        WebElement quantity;



        @FindBy(xpath="//*[@id=\"product-addtocart-button\"]")
        @CacheLookup
        WebElement addToCartBtn;

        @FindBy(xpath="//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div/a")
        @CacheLookup
        WebElement shoppingcart;


    public SizeColorQuantitySelectPage(){
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void selectMediumSize(){
        mediumSize.click();
    }

    public void selectBlueColor(){
        blueColor.click();
    }

    public void  enterQuantity(String qty){
        quantity.clear();
        quantity.sendKeys(qty);
    }

    public void  addToCartBtnClick(){
        addToCartBtn.click();
    }

    public ShoppingCartPage getShoppingCart(){
        shoppingcart.click();
        return new ShoppingCartPage();

    }

}

package com.magento.qa.pages;

import com.magento.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class ShoppingCartPage extends TestBase {
    @FindBy(xpath = "//*[@id=\"cart-totals\"]/div/table/tbody/tr[3]/th/strong")
    @CacheLookup
    WebElement orderTotal;

    @FindBy(xpath="//*[@id=\"maincontent\"]/div[3]/div/div[2]/div[1]/ul/li[1]/button/span")
    @CacheLookup
    WebElement   proceedToCheckOut;

  public ShoppingCartPage(){
      PageFactory.initElements(driver, this);
  }

  public String getTitle(){
      return driver.getTitle();
  }
  public void checkOrderTotal(String orderAmt){

      Assert.assertEquals(orderTotal.getText(),orderAmt);
  }

  public String getOrderTotal(){
      return orderTotal.getText();
  }

  public ShippingAddressPage setProceedToCheckOut(){
      proceedToCheckOut.click();
      return new ShippingAddressPage();
  }

}

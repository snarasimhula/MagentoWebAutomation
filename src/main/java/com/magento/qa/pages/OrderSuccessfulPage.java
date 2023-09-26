package com.magento.qa.pages;

import com.magento.qa.base.TestBase;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class OrderSuccessfulPage extends TestBase {


        @FindBy(xpath="//*[@id=\"maincontent\"]/div[3]/div/div[2]/p[1]/a" )
        @CacheLookup
        WebElement orderSuccessfulLink;
    List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Your order number is:')]"));
    List<WebElement> list1 = driver.findElements(By.xpath("//*[contains(text(),'Order #')]"));

    public OrderSuccessfulPage(){
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public  List<WebElement> getListText(String str){
        return driver.findElements(By.xpath("//*[contains(text(),'"+str+"')]"));

        }

    public void clickOrderSuccessfulLink(){
        orderSuccessfulLink.click();
    }

}

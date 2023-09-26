package com.magento.qa.pages;

import com.magento.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConsumerAccountIndexPage extends TestBase {


    @FindBy(xpath = "//a[@href=\"https://magento.softwaretestingboard.com/women.html\"]")
    @CacheLookup
    private WebElement womenLink;
    public ConsumerAccountIndexPage(){
        PageFactory.initElements(driver, this);
    }

    public String getConsumerAccountIndexPageTitle(){
        return  driver.getTitle();
    }

    public  WomenMenuPage getMenu_WomenPageHtml(){
               womenLink.click();
               return new WomenMenuPage();
    }
}

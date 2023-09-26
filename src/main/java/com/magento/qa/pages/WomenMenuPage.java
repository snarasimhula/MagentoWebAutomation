package com.magento.qa.pages;

import com.magento.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WomenMenuPage  extends TestBase {

    @FindBy(xpath = "//*[@id=\"narrow-by-list2\"]/dd/ol/li[1]/a")
    @CacheLookup
    private WebElement topsLink;




    @FindBy(xpath = "//*[@id=\"narrow-by-list\"]/div[1]/div[1]")
    @CacheLookup
    private WebElement categoryDropDown;

    @FindBy(xpath="//a[@href=\"https://magento.softwaretestingboard.com/juno-jacket.html\"]")
    @CacheLookup
    WebElement junoJacketLink;

    @FindBy(xpath="//a[@href=\"https://magento.softwaretestingboard.com/women/tops-women.html?cat=23\"]")
    @CacheLookup
    private WebElement jacketsLink;
    public WomenMenuPage(){
        PageFactory.initElements(driver, this);
    }


    public String getWomenPageTitle(){
        return driver.getTitle();
    }
    public void getTopsCategories(){
        topsLink.click();
    }

    public void getCategoriesDropDown(){
        categoryDropDown.click();
    }

    public void selectJackets(){
        jacketsLink.click();

    }
    public void selectJunoJacket(){
        junoJacketLink.click();
    }
    public SizeColorQuantitySelectPage getSizeColorQuantitySelectPage(){
        topsLink.click();
        categoryDropDown.click();
        jacketsLink.click();
        junoJacketLink.click();
        return new SizeColorQuantitySelectPage();
    }


}

package com.magento.qa.base;


import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestBase {

   public static WebDriver driver;
    static Properties properties;
    public static Logger logger =null;

    public TestBase(){
        try{
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\magento\\qa\\environment\\test.properties");
            properties.load(fileInputStream);
            logger = LogManager.getLogger(TestBase.class);
            }catch(IOException e){
            System.out.print(e.getMessage());
            logger.error("Exception occured", new Exception(e));
        }
    }

    public static void initialization(){
        String browserName = properties.getProperty("browser");
        if(browserName.equals("chrome")){
           // System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }else if(browserName.equals("firefox")){
           // System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(properties.getProperty("url"));
    }


}

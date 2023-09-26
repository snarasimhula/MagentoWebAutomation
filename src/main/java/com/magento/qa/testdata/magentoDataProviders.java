package com.magento.qa.testdata;

import org.testng.annotations.DataProvider;

public class magentoDataProviders {

    @DataProvider(name ="createAccountNegativeCases")
    public Object[][] signUpData(){
      return new Object[][]{
              {"fname","lname","emailid","1234qw","1234qw","Please enter a valid email address (Ex: johndoe@domain.com)."},
              {"fname","lname","emailaddress"+System.currentTimeMillis()+"@gmail.com","1234qw","1234qw","Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored."},
              {"fname","lname","emailaddress"+System.currentTimeMillis()+"@gmail.com","qwerty12*","qwerty12#","Please enter the same value again."},
              {"fname*","lname","emailaddress"+System.currentTimeMillis()+"@gmail.com","qwerty12*","qwerty12*","First Name is not valid!"},
              {"fname","lname$","emailaddress"+System.currentTimeMillis()+"@gmail.com","qwerty12*","qwerty12*","Last Name is not valid!"},
      };
    }

    @DataProvider(name ="sizeColorQuantityOptions")
    public Object[][] getSizeColorQuantityOptions(){
        return new Object[][]{
                {"medium","blue","2"},
        };
    }

    @DataProvider(name="getShippingAddressInfo")
    public Object[][] getShippingAddressInfo(){
        return new Object[][]{
                {"7805 NE4th Ave","Mckinney","Texas","75071","United States","4256788989"}
        };
    }

    @DataProvider(name="getOrderAmounts")
    public Object[][] getOrderAmounts(){
        return new Object[][]{
                {"$154.00"}
        };
    }
}

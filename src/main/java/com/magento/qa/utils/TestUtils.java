package com.magento.qa.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUtils {

    public boolean invalidName(String name){
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s]");
        Matcher matcher = pattern.matcher(name);
        return matcher.find();

    }
}

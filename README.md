# MagentoWebAutomation
# As this framework uses selenium 4.12 which is above 4.6 version, we don't need to specify driver location and selenium manager automatically inbuilt drivers.
# For sanity tests please run by right click src/main/resources/testngsanity.xml
# For complete tests please run by right click on src/main/resources/testng.xml
# PlaceOrderTest - testPlaceOrderSuccess does the testing of the entire flow  from account creation, login, selecting women, tops,jackets, Juno-jacket, medium size, color blue, quantity 2 add to cart, validate the total amount, proceed to checkout, filling shipping details, select a purchase option and place order, order successful
# POM.xml contains all the dependencies required.
# This framework runs fine on intellij or any ide that understands maven project
# From IDE - file - new Project from version control and use this link https://github.com/snarasimhula/MagentoWebAutomation, download GIT if it is not already there
# Requires JDK 11 

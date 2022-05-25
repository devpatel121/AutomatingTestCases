package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Testsuit {

    protected static WebDriver driver;


        @BeforeMethod
        public void openBrowser() {
            System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get("https://demo.nopcommerce.com/");
        }

        @Test
        public void userShouldBeAbleToRegisterSuccessfully(){

        // click on register button
        clickOnElement(By.className("ico-register"));

        //select gender
        driver.findElement(By.xpath("//input[@id=\"gender-male\"]")).click();

        // enter firstname
        typeText(By.xpath("//input[@name='FirstName']"), "Testing");

        // enter lastname
       typeText(By.id("LastName"), "Patel");

       // select day of the birthday
        Select birthDay = new Select (driver.findElement(By.xpath("//select[@name=\"DateOfBirthDay\"]")));
        birthDay.selectByValue("16");

        // select month of the birthday
        Select birthMonth = new Select (driver.findElement(By.xpath("//select[@name=\"DateOfBirthMonth\"]")));
        birthMonth.selectByValue("2");

        // select year of the birthday
        Select birthYear = new Select (driver.findElement(By.xpath("//select[@name=\"DateOfBirthYear\"]")));
        birthYear.selectByVisibleText("2012");

        //enter email
        typeText(By.id("Email"), "abcd" + randomDate() + "@gmail.com");

        //enter password
       typeText(By.id("Password"), "as23.twoone");

        //confirm password
        typeText(By.id("ConfirmPassword"), "as23.twoone");

        //register
        clickOnElement(By.id("register-button"));

        String expectedMessage= "Your registration completed";
        String actualMessage= driver.findElement(By.className("result")).getText();
        System.out.println("Actual Message:"+ actualMessage);
        Assert.assertEquals(actualMessage,expectedMessage, "Registration is not working");
        }

        @Test
        public void userShouldBeAbleToChangeTheCurrencyToEuro(){

        Select currencyUsDollar = new Select(driver.findElement(By.xpath("//div[@class=\"currency-selector\"]/select")));
        currencyUsDollar.selectByValue("https://demo.nopcommerce.com/changecurrency/1?returnUrl=%2F");

        String actualcurrency = driver.findElement(By.xpath("//div[@class=\"prices\"]/span")).getText();
        Assert.assertTrue(actualcurrency.contains("$"));

        Select currencyEur = new Select(driver.findElement(By.xpath("//div[@class=\"currency-selector\"]/select")));
        currencyEur.selectByValue("https://demo.nopcommerce.com/changecurrency/6?returnUrl=%2F");

        String actualcurrency1 = driver.findElement(By.xpath("//div[@class=\"prices\"]/span[contains(text(),\"€\")]")).getText();
        Assert.assertTrue(actualcurrency1.contains("€"));
        }

        @Test
        public void userShouldBeAbleToReferAProductToAFriend() {

            // click on register button
            clickOnElement(By.className("ico-register"));

            //select gender
            driver.findElement(By.xpath("//input[@id=\"gender-male\"]")).click();

            // enter firstname
            typeText(By.xpath("//input[@name='FirstName']"), "Testing");

            // enter lastname
            typeText(By.id("LastName"), "Patel");

            // select day of the birthday
            Select birthDay = new Select(driver.findElement(By.xpath("//select[@name=\"DateOfBirthDay\"]")));
            birthDay.selectByValue("16");

            // select month of the birthday
            Select birthMonth = new Select(driver.findElement(By.xpath("//select[@name=\"DateOfBirthMonth\"]")));
            birthMonth.selectByValue("2");

            // select year of the birthday
            Select birthYear = new Select(driver.findElement(By.xpath("//select[@name=\"DateOfBirthYear\"]")));
            birthYear.selectByVisibleText("2012");

            //enter email
            typeText(By.id("Email"), "abcd" + randomDate() + "@gmail.com");

            //enter password
            typeText(By.id("Password"), "as23.twoone");

            //confirm password
            typeText(By.id("ConfirmPassword"), "as23.twoone");

            //register
            clickOnElement(By.id("register-button"));

            String expectedMessage = "Your registration completed";
            String actualMessage = driver.findElement(By.className("result")).getText();
            System.out.println("Actual Message:" + actualMessage);
            Assert.assertEquals(actualMessage, expectedMessage, "Registration is not working");

            driver.findElement(By.xpath("//div/a[@class=\"button-1 register-continue-button\"]")).click();

            clickOnElement(By.xpath("//div [@class=\"product-item\"]/div[2]/h2/a[@href=\"/build-your-own-computer\"]"));
            clickOnElement(By.className("email-a-friend"));
            typeText(By.xpath("//div/input[@class=\"friend-email\"]"), "xyz" +randomDate() + "@gmail.com");
            typeText(By.xpath("//div[@class=\"inputs\"]/textarea[@id=\"PersonalMessage\"]"), "A great recommendation");
            clickOnElement(By.xpath("//div[@class=\"buttons\"]//button[@name=\"send-email\"]"));

            String expectedMessage1 = "Your message has been sent.";
            String actualMessage1 = driver.findElement(By.xpath("//div[@class=\"result\"]")).getText();
            Assert.assertEquals(actualMessage1,expectedMessage1, "Your message has been sent.");
        }


        @Test
        public void userShouldBeAbleToAddAProductToTheShoppingCart(){

        clickOnElement(By.xpath("//a[@href=\"/computers\"]"));
        clickOnElement(By.xpath("//ul[@class=\"sublist\"]/li[1]/a"));
        clickOnElement(By.xpath("//div [@class=\"product-item\"]/div[2]/h2/a[@href=\"/build-your-own-computer\"]"));
        Select processor = new Select(driver.findElement(By.xpath("//dl//dd[1]//select[@id=\"product_attribute_1\"]")));
        processor.selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");
        Select ram = new Select(driver.findElement(By.xpath("//dl//dd[2]//select['product_attribute_2']")));
        ram.selectByValue("3");
        clickOnElement(By.xpath("//input [@id=\"product_attribute_3_6\"]"));
        clickOnElement(By.xpath("//input [@id=\"product_attribute_4_9\"]"));
        clickOnElement(By.xpath("//input [@id=\"product_attribute_5_11\"]"));
        clickOnElement(By.xpath("//input [@id=\"product_attribute_5_12\"]"));
        clickOnElement(By.xpath("//button[@id=\"add-to-cart-button-1\"]"));
        clickOnElement(By.className("cart-label"));

        String expectedMessage = "Shopping cart";
        String actualMessage = driver.findElement(By.xpath("//div /h1")).getText();
        Assert.assertEquals(expectedMessage,actualMessage, "Product is not added to the shopping cart");
        Assert.assertEquals(driver.findElement(By.className("product name")).getText(),"Build your own computer");
        }

        @AfterMethod
        public void closeBrowser(){driver.quit();}

    public static void clickOnElement(By by){
        driver.findElement(by).click();
    }
    public static void typeText(By by, String text){
        driver.findElement(by).sendKeys(text);
    }
    public static String getTextFromElement(By by){
        return
                driver.findElement(by).getText();
    }
    public static String randomDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
        return formatter.format(date);
    }
    public static void driverWaitsUntillURLTobe(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    }


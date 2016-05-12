
package com.swe.zonein.zonein.Testing;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.*;

///**
// * Created by malsa on 5/5/2016.
//

public class TestClass {
    AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        // Created object of DesiredCapabilities class.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set android deviceName desired capability. Set your device name.
        capabilities.setCapability("deviceName", "Android Emulator");

        // Set BROWSER_NAME desired capability. It's Android in our case here.
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

        // Set android VERSION desired capability. Set your mobile device's OS version.
        capabilities.setCapability(CapabilityType.VERSION, "6.0");

        // Set android platformName desired capability. It's Android in our case here.
        capabilities.setCapability("platformName", "Android");

        // Set android appPackage desired capability. It is
        // com.android.calculator2 for calculator application.
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appPackage", "com.swe.zonein.zonein");

        // Set android appActivity desired capability. It is
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appActivity", "com.swe.zonein.zonein.Activities.SignInActivity");

        // Created object of RemoteWebDriver will all set capabilities.
        // Set appium server address and port number in URL string.
        // It will launch calculator app in android device.
        //driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        System.out.println(capabilities.toString());

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }


    @Test
    public void testcaseLogin()throws  Exception
    {

        driver.findElementById("fab").click();
        Assert.assertTrue(driver.findElement(By.xpath("//android.widget.Button[@text='Sort By Nearby']")).isDisplayed());
    }

    @Test
    public void testcaseSavePlace() throws Exception
    {
        driver.findElementById("fab").click();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']")).click();
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='All Places']")).click();
        driver.findElement(By.xpath("//android.widget.Button[@text='Save']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//android.widget.Button[@text='unSave']")).isDisplayed());
    }

    @Test
    public void testcasefollowPeople() throws Exception
    {
        driver.findElementById("fab").click();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']")).click();
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='All users']")).click();
        driver.findElement(By.xpath("//android.widget.Button[@text='Follow']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//android.widget.Button[@text='UnFollow']")).isDisplayed());
    }

    @Test
    public void testcaseCheckIn() throws Exception
    {
        driver.findElementById("fab").click();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']")).click();
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='All Places']")).click();
       for(int i = 0; i < 5; i++){
            driver.findElement(By.xpath("//android.widget.TextView[@text='FCI']")).click();
        }

        driver.findElement(By.xpath("//android.widget.Button[@text='Check In']")).click();
        driver.findElement(By.xpath("//android.widget.Button[@index='3']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text='U']")).isDisplayed());
    }


    @After
    public void testCaseTearDown()
    {
        driver.quit();
    }


}

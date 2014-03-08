package org.qagile.selenium;


import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.iphone.IPhoneDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.qagile.utils.PropReader;

import java.net.URL;

public class DriverFactory {

    private RemoteWebDriver driver;

    public RemoteWebDriver getSauceDriver() {
        DesiredCapabilities capabillities = getDesiredCapabilities();
        String sauceUserName = PropReader.getPropValue("sauce.user");
        String sauceApiKey = PropReader.getPropValue("sauce.api");

        if(sauceUserName == null) {
            throw new IllegalArgumentException("");
        }

        try {
            String url = String.format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub",sauceUserName,sauceApiKey);
            this.driver = new RemoteWebDriver(new URL(url),capabillities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }


    public WebDriver getLocalIphoneDriver() {
        try {
            return new IPhoneDriver();
        } catch (Exception e) {
            throw new RuntimeException("Cant create local Iphone driver");
        }
    }

    private DesiredCapabilities getDesiredCapabilities() {
        String device = System.getProperty("browser", "android");
        String version = System.getProperty("version", "4.0");
        String os = System.getProperty("os", "linux");

        System.out.println("Sauce capability" + "Browser: " + device + " Version: " + version +" OS: " + os);

        DesiredCapabilities capabillities = new DesiredCapabilities();

        if (device.equalsIgnoreCase("iphone")) {
            capabillities = DesiredCapabilities.iphone();
        } else if (device.equalsIgnoreCase("android")) {
            capabillities = DesiredCapabilities.android();
        } else if (device.equalsIgnoreCase("ipad")) {
            capabillities = DesiredCapabilities.ipad();
        } else if (device.equalsIgnoreCase("firefox")) {
            capabillities = DesiredCapabilities.firefox();
        } else if (device.equalsIgnoreCase("ie")) {
            capabillities = DesiredCapabilities.internetExplorer();
        }

        if (os.equalsIgnoreCase("mac")) {
            capabillities.setCapability("platform", "Mac 10.8");
        } else if (os.equalsIgnoreCase("windows")) {
            capabillities.setCapability("platform", Platform.XP);
        } else if (os.equalsIgnoreCase("linux")) {
            capabillities.setCapability("platform", Platform.LINUX);
        }

        capabillities.setCapability("version", version);
        return capabillities;
    }

    public WebDriver getLocalDriver(String browser) {
        if ("firefox".equalsIgnoreCase(browser)) {
           return new FirefoxDriver();
        } else if("iphone".equalsIgnoreCase(browser)) {
           getLocalIphoneDriver(); }
        else if("htmlUnit".equals(browser)) {

           return new HtmlUnitDriver();

        } else if("chrome".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.chrome.driver", PropReader.getPropValue("chrome.driver.location"));
            System.out.println(PropReader.getPropValue("chrome.driver.location"));
            System.out.println("Creating chrome driver");
            return new ChromeDriver();
        } else {
            throw new IllegalArgumentException("Cant find browser type" + browser);

        }
        return driver;
    }

}

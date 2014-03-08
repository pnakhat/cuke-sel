package org.qagile.selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DriverProvider implements WebDriver, TakesScreenshot {

    private WebDriver driver;
    private String env =System.getProperty("env", "local");
    private String browser = System.getProperty("browser", "firefox");

    public void create() {
        if (driver == null) {
            System.out.println("Value of driver: " + env + "------- " + browser);
            if ("local".equalsIgnoreCase(env)) {
                driver = new DriverFactory().getLocalDriver(browser);
            }

            if ("sauce".equalsIgnoreCase(env)) {
                driver = new DriverFactory().getSauceDriver();
            }
        }
    }


    public void setEnv(String browser) {
        this.env = browser;
    }

    @Override
    public void get(String url) {
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        FluentWait<WebDriver> webDriverWait = new WebDriverWait(driver, 10)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Could not find element");
        return webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    @Override
    public WebElement findElement(By by) {
        return getWebElement(by, 10);
    }

    private WebElement getWebElement(By by, int timeout) {
        FluentWait<WebDriver> webDriverWait = new WebDriverWait(driver, timeout)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class)
                .withMessage("Could not find element : Located by " + by.toString());
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement findElement(By by, int timeout) {
        return getWebElement(by, timeout);
    }

    public WebElement findElementWithDisplayed(By by) {
        FluentWait<WebDriver> webDriverWait = new WebDriverWait(driver, 10).pollingEvery(1, TimeUnit.SECONDS).withMessage("Could not find element");
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    public WebDriver gerDriverToUse() {
        return driver;
    }

    public String executeJs(String js) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String text = (String) javascriptExecutor.executeScript(js);
        return text;
    }

    public String catureScreen(String fileName) {
        String path;
        File dest = null;
        File source = null;
        try {
            if (!(driver instanceof FirefoxDriver)) {
                WebDriver augmentedDriver = new Augmenter().augment(driver);
                source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
            } else {
                source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            }


            path = "./target/screenshots/" + source.getName();
            dest = new File(path);
            FileUtils.copyFile(source, dest);
        } catch (IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
        System.out.println(dest.getAbsolutePath());
        return path;

    }

    public void selectValue(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> xOutputType) throws WebDriverException {
        return ((TakesScreenshot) driver).getScreenshotAs(xOutputType);
    }



}

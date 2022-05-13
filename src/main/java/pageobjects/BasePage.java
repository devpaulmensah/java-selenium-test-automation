package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private static final int TimeOut = 30;

    public static final String clickElementScript = "arguments[0].click()";

    public BasePage(WebDriver driver){
        setWebDriver(driver);
    }

    public void setWebDriver(WebDriver driver) {
        webDriver = driver;
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(TimeOut));
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }
}

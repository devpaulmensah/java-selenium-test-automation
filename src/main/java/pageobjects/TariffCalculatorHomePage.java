package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class TariffCalculatorHomePage extends BasePage {
    private static final Logger logger = LogManager.getLogger();
    private final static By calculateTariffLinkInHeroSection = By.cssSelector("a[id='Content_LeadIn::Button::Jetzt-berechnen']");
    public TariffCalculatorHomePage(WebDriver driver) {
        super(driver);
    }

    public void loadPage(){
        logger.info("Opening browser to load page");
        getWebDriver().get("https://hausrat.allianz.de/");
        getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void navigateToHouseholdGoodsRateCalculatorPage(){
        try {
            logger.info("Loading page to calculate household goods rate");

            getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(calculateTariffLinkInHeroSection));
            var linkElement = getLinkToHouseholdGoodsRateCalculatorPage();

            if (linkElement == null)
                return;

            var link = linkElement.getAttribute("href");

            getWebDriver().get(link);
        } catch (Exception e) {
            logger.error("An error occurred navigating to household good calculator page", e);
        }
    }

    public WebElement getLinkToHouseholdGoodsRateCalculatorPage(){
        try {
            logger.info("Getting link element to household goods rate calculator page");

            return getWebDriver().findElement(calculateTariffLinkInHeroSection);
        } catch(Exception e) {
            logger.error("An error occurred getting link to household calculator page element", e);

            return null;
        }
    }
}

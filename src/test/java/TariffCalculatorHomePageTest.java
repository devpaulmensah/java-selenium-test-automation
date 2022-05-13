import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import pageobjects.TariffCalculatorHomePage;

public class TariffCalculatorHomePageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger();
    private static final String testTag = "TariffCalculatorHomePage";

    @Test
    @Tag(testTag)
    @DisplayName("Link to basic data page must be present on the home page")
    public void linkInHeroSectionMustExistOnHomePage(){
        try {
            logger.info("<== Checking if link element on hero section is present ==>");

            var tariffCalculatorHomePage = new TariffCalculatorHomePage(driver);
            tariffCalculatorHomePage.loadPage();

            var calculateTariffLinkInHeroSection = tariffCalculatorHomePage.getLinkToHouseholdGoodsRateCalculatorPage();

            if (calculateTariffLinkInHeroSection == null)
                Assertions.fail("Link element to household goods rate calculator not found");

            Assertions.assertNotNull(calculateTariffLinkInHeroSection,
                    "Link to navigate to calculate household goods rate does not exists in hero section of home page");
        } catch (Exception e) {
            logger.error("An error occurred while performing test", e);
            Assertions.fail();
        }
    }

    @Test
    @Tag(testTag)
    @DisplayName("Page must be redirected to basic data page when calculate now button is clicked")
    public void calculateTariffLinkMustRedirectToHouseholdGoodsRateCalculatorPage(){
        try {
            logger.info("<== Checking if page is redirected to the correct page when \"Calculate Now\" is clicked ==>");

            var tariffCalculatorHomePage = new TariffCalculatorHomePage(driver);
            tariffCalculatorHomePage.loadPage();
            tariffCalculatorHomePage.navigateToHouseholdGoodsRateCalculatorPage();

            var currentPageUrl = tariffCalculatorHomePage.getWebDriver().getCurrentUrl();
            var expectedPageUrlAfterLinkIsClicked = "https://www.allianz.de/recht-und-eigentum/hausratversicherung/rechner/";

            Assertions.assertTrue(expectedPageUrlAfterLinkIsClicked.equalsIgnoreCase(currentPageUrl));
        } catch(Exception e) {
            logger.error("An error occurred while performing test", e);
            Assertions.fail();
        }
    }
}

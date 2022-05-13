import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pageobjects.BasicDataPage;
import shared.ApartmentFloorLevel;
import shared.HouseInhabitedStatus;
import shared.HouseType;

import java.time.Duration;

public class BasicDataPageTest extends BaseTest{
    private static final Logger logger = LogManager.getLogger();
    private static final String testTag = "BasicDataPage";

    @Test
    @Tag(testTag)
    @DisplayName("Redirect to modules page when user provides all correct data")
    public void calculateHouseholdGoodsRate(){
        try {
            logger.info("<== Calculating household goods rates ==>");

            var basicDataPage = new BasicDataPage(driver);
            basicDataPage.loadPage();

            basicDataPage.acceptCookies();
            basicDataPage.setValueForPostCode(97074);
            basicDataPage.setValueForStreetNumber("420 A4");
            basicDataPage.setValueForBirthDate("14.09.1995");
            basicDataPage.setValueForStreetName("Lehnleitenweg");
            basicDataPage.setValueForLivingSpace(25);
            basicDataPage.setValueForHouseholdLocation(HouseType.Detached, ApartmentFloorLevel.GroundFloor);
            basicDataPage.setValueForGotDamages(true);
            basicDataPage.setValueForHouseDamageCount(2);
            basicDataPage.setValueForBikeTheftCount(2);
            basicDataPage.clickCalculateButton();

            var modulesPage = "https://www.allianz.de/recht-und-eigentum/hausratversicherung/rechner/#/angebot";
            var currentUrl = basicDataPage.getWebDriver().getCurrentUrl();

            Assertions.assertTrue(modulesPage.equalsIgnoreCase(currentUrl));
        } catch (Exception e) {
            logger.error("An error occurred calculating household goods rate", e);
            Assertions.fail();
        }
    }

    @Test
    @Tag(testTag)
    @DisplayName("Decrease household damage count when reduce household damage button is clicked")
    public void decrease_HouseholdDamageCount_When_ReduceHouseholdDamageButton_Is_Clicked(){
        try {
            logger.info("<== Testing decrease household damage count value when decrease household damage button is clicked ==>");

            var basicDataPage = new BasicDataPage(driver);
            basicDataPage.loadPage();
            basicDataPage.acceptCookies();

            basicDataPage.setValueForGotDamages(true);
            basicDataPage.setValueForHouseDamageCount(2);
            basicDataPage.decreaseHouseDamageCount();
            var valueAfterButtonIsClicked = basicDataPage.getValueForHouseDamageCount();

            Assertions.assertTrue(valueAfterButtonIsClicked < 2);

        } catch (Exception e) {
            logger.error("An error occurred testing decrease household damage count when decrease button is clicked", e);
            Assertions.fail();
        }
    }

    @Test
    @Tag(testTag)
    @DisplayName("Increase household damage count when increase household damage button is clicked")
    public void increase_HouseholdDamageCount_When_IncreaseHouseholdDamageButton_Is_Clicked(){
        try {
            logger.info("<== Testing increase household damage count value when increase household button is clicked ==>");

            var basicDataPage = new BasicDataPage(driver);
            basicDataPage.loadPage();
            basicDataPage.acceptCookies();

            basicDataPage.setValueForGotDamages(true);
            basicDataPage.setValueForHouseDamageCount(2);
            basicDataPage.increaseHouseDamageCount();
            var valueAfterButtonIsClicked = basicDataPage.getValueForHouseDamageCount();

            Assertions.assertTrue(valueAfterButtonIsClicked > 2);
        } catch (Exception e) {
            logger.error("An error occurred testing increase household damage count when increase button is clicked", e);
            Assertions.fail();
        }
    }

    @Test
    @Tag(testTag)
    @DisplayName("Decrease bike theft count when reduce bike theft count button is clicked")
    public void decrease_BikeTheftCount_When_ReduceBikeTheftButton_Is_Clicked(){
        try {
            logger.info("<== Testing decrease bike theft count value when decrease bike theft button is clicked ==>");

            var basicDataPage = new BasicDataPage(driver);
            basicDataPage.loadPage();
            basicDataPage.acceptCookies();

            basicDataPage.setValueForGotDamages(true);
            basicDataPage.setValueForBikeTheftCount(2);
            basicDataPage.decreaseBikeTheftCount();
            var valueAfterButtonIsClicked = basicDataPage.getValueForBikeTheftCount();

            Assertions.assertTrue(valueAfterButtonIsClicked < 2);

        } catch (Exception e) {
            logger.error("An error occurred testing decrease bike theft count when decrease button is clicked", e);
            Assertions.fail();
        }
    }

    @Test
    @Tag(testTag)
    @DisplayName("Increase bike theft count when increase bike theft count button is clicked")
    public void increase_BikeTheftCount_When_IncreaseBikeTheftButton_Is_Clicked(){
        try {
            logger.info("<== Testing increase bike theft count value when increase bike theft button is clicked ==>");

            var basicDataPage = new BasicDataPage(driver);
            basicDataPage.loadPage();
            basicDataPage.acceptCookies();

            basicDataPage.setValueForGotDamages(true);
            basicDataPage.setValueForBikeTheftCount(2);
            basicDataPage.increaseBikeTheftCount();
            var valueAfterButtonIsClicked = basicDataPage.getValueForBikeTheftCount();

            Assertions.assertTrue(valueAfterButtonIsClicked > 2);

        } catch (Exception e) {
            logger.error("An error occurred testing increase bike theft count when increase button is clicked", e);
            Assertions.fail();
        }
    }

    @Test
    @Tag(testTag)
    @DisplayName("Maximum number of both household damages and bike theft must not exceed 4 in the last 5 years")
    public void maximumNumberOfBoth_HouseholdDamages_And_BikeTheft_MustNot_Exceed_5_PerYear(){
        try {
            logger.info("<== Testing maximum number of both household damages and bike theft must not exceed 4 in the last 5 years ==>");

            var basicDataPage = new BasicDataPage(driver);
            basicDataPage.loadPage();
            basicDataPage.acceptCookies();

            basicDataPage.setValueForGotDamages(true);
            basicDataPage.setValueForBikeTheftCount(2);
            basicDataPage.setValueForHouseDamageCount(3);

            Thread.sleep(Duration.ofMillis(150).toMillis());
            var modalElement = basicDataPage.getHouseholdDamagesModalElement();

            Assertions.assertNotNull(modalElement);
        } catch (Exception e) {
            logger.error("An error occurred testing maximum number of both household damages and bike theft must not exceed 4 in the last 5 years", e);
            Assertions.fail();
        }
    }

    @Test
    @Tag(testTag)
    @DisplayName("Calculate insurance tariff for only households that are permanently inhabited")
    public void calculateInsuranceTariff_For_Only_PermanentlyInhabited_Households(){
        try {
            logger.info("<== Testing calculate insurance tariff for only households that are permanently inhabited ==>");

            var basicDataPage = new BasicDataPage(driver);
            basicDataPage.loadPage();
            basicDataPage.acceptCookies();

            basicDataPage.setValueForHouseInhabitedStatus(HouseInhabitedStatus.NotPermanentlyInhabited);

            Thread.sleep(Duration.ofMillis(150).toMillis());
            var modalElement = basicDataPage.getHouseholdNotPermanentlyInhabitedErrorModalElement();

            Assertions.assertNotNull(modalElement);
        } catch (Exception e) {
            logger.error("An error occurred testing calculate insurance tariff for only households that are permanently inhabited", e);
            Assertions.fail();
        }
    }
}

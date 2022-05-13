import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import pageobjects.BasicDataPage;
import pageobjects.ModuleSelectionPage;
import shared.*;

import java.time.Duration;

public class TariffCalculatorTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger();
    private static final String testTag = "TariffCalculatorTest";

    @Test
    @Tag(testTag)
    @DisplayName("End to end test to calculate user's content household insurance with base module")
    public void calculateTariffForContentHouseholdInsuranceWithBaseModule() {
        try {
            logger.info("<== Calculating tariff for content household insurance with base module selected ==>");

            // <== Basic Data Page ==>
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

            var age = basicDataPage.getAge();
            Assertions.assertTrue(age >= 18, "User is less than 18 years");

            basicDataPage.clickCalculateButton();

            // <== Module Selection Page ==>
            Thread.sleep(Duration.ofSeconds(5).toMillis());
            var modulesSelectionPage = new ModuleSelectionPage(driver);
            modulesSelectionPage.setValueForCoverageSuggestion(CoverageSuggestion.Recommended);
            modulesSelectionPage.setValueForPaymentSchedule(PaymentSchedule.HalfYearly);
            modulesSelectionPage.setValueForRetentionAmount(RetentionAmount.R1000);

            modulesSelectionPage.selectBaseModule();

            var isBaseInsuranceSumValid = modulesSelectionPage.isCalculatedBaseInsuranceSumValid(25);
            Assertions.assertTrue(isBaseInsuranceSumValid, "Incorrect insurance sum calculated for base module");

            var isBaseValuableSumValid = modulesSelectionPage.isCalculatedBaseValuableSumValid(25);
            Assertions.assertTrue(isBaseValuableSumValid, "Incorrect valuable sum calculated for base module");

            var totalInsuranceSum = modulesSelectionPage.getValueForTotalInsuranceSum();
            Assertions.assertEquals(25 * CommonConstants.ChargePerSquareMeter, totalInsuranceSum, "Incorrect insurance sum generated for user");

            if (!modulesSelectionPage.isAdditionalProtectionPackageSelected(ExtraInsuranceModule.ExtremeWeatherProtection)){
                modulesSelectionPage.toggleExtremeWeatherProtectionPackageState();
            }

            modulesSelectionPage.toggleBikeProtectionPackageState();
            modulesSelectionPage.toggleGlassProtectionBuildingPackageState();
            modulesSelectionPage.toggleGlassProtectionHouseholdItemsPackageState();
            modulesSelectionPage.toggleEmergencyServicePackageState();

            var shoppingCartItemsPrices = modulesSelectionPage.getShoppingCartItemsAmount();
            var totalPaymentAmount = modulesSelectionPage.getTotalAmountToBePaid();

            var totalShoppingCartItemsPrices = shoppingCartItemsPrices.stream().mapToDouble(Double::doubleValue).sum();

            Assertions.assertEquals(totalShoppingCartItemsPrices, totalPaymentAmount, "Sum of shopping cart items does not match total price");
        } catch (Exception e) {
            logger.error("An error occurred calculating for content household insurance with base module selected", e);
            Assertions.fail();
        }
    }

    @Test
    @Tag(testTag)
    @DisplayName("End to end test to calculate user's content household insurance with smart module")
    public void calculateTariffForContentHouseholdInsuranceWithSmartModule() {
        try {
            logger.info("<== Calculating tariff for content household insurance with smart module selected ==>");

            // <== Basic Data Page ==>
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

            var age = basicDataPage.getAge();
            Assertions.assertTrue(age >= 18, "User is less than 18 years");

            basicDataPage.clickCalculateButton();

            // <== Module Selection Page ==>
            Thread.sleep(Duration.ofSeconds(5).toMillis());
            var modulesSelectionPage = new ModuleSelectionPage(driver);
            modulesSelectionPage.setValueForCoverageSuggestion(CoverageSuggestion.Recommended);
            modulesSelectionPage.setValueForPaymentSchedule(PaymentSchedule.HalfYearly);
            modulesSelectionPage.setValueForRetentionAmount(RetentionAmount.R1000);

            modulesSelectionPage.selectSmartModule();

            var isSmartInsuranceSumValid = modulesSelectionPage.isCalculatedSmartInsuranceSumValid(25);
            Assertions.assertTrue(isSmartInsuranceSumValid, "Incorrect insurance sum calculated for smart module");

            var isSmartValuableSumValid = modulesSelectionPage.isCalculatedSmartValuableSumValid(25);
            Assertions.assertTrue(isSmartValuableSumValid, "Incorrect valuable sum calculated for smart module");

            var totalInsuranceSum = modulesSelectionPage.getValueForTotalInsuranceSum();
            Assertions.assertEquals(25 * CommonConstants.ChargePerSquareMeter, totalInsuranceSum, "Incorrect insurance sum generated for user");

            if (!modulesSelectionPage.isAdditionalProtectionPackageSelected(ExtraInsuranceModule.ExtremeWeatherProtection)){
                modulesSelectionPage.toggleExtremeWeatherProtectionPackageState();
            }

            modulesSelectionPage.toggleBikeProtectionPackageState();
            modulesSelectionPage.toggleGlassProtectionBuildingPackageState();
            modulesSelectionPage.toggleGlassProtectionHouseholdItemsPackageState();
            modulesSelectionPage.toggleEmergencyServicePackageState();

            var shoppingCartItemsPrices = modulesSelectionPage.getShoppingCartItemsAmount();
            var totalPaymentAmount = modulesSelectionPage.getTotalAmountToBePaid();

            var totalShoppingCartItemsPrices = shoppingCartItemsPrices.stream().mapToDouble(Double::doubleValue).sum();

            Assertions.assertEquals(totalShoppingCartItemsPrices, totalPaymentAmount, "Sum of shopping cart items does not match total price");
        } catch (Exception e) {
            logger.error("An error occurred calculating for content household insurance with smart module selected", e);
            Assertions.fail();
        }
    }

    @Test
    @Tag(testTag)
    @DisplayName("End to end test to calculate user's content household insurance with comfort module")
    public void calculateTariffForContentHouseholdInsuranceWithComfortModule() {
        try {
            logger.info("<== Calculating tariff for content household insurance with comfort module selected ==>");

            // <== Basic Data Page ==>
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

            var age = basicDataPage.getAge();
            Assertions.assertTrue(age >= 18, "User is less than 18 years");

            basicDataPage.clickCalculateButton();

            // <== Module Selection Page ==>
            Thread.sleep(Duration.ofSeconds(5).toMillis());
            var modulesSelectionPage = new ModuleSelectionPage(driver);
            modulesSelectionPage.setValueForCoverageSuggestion(CoverageSuggestion.Recommended);
            modulesSelectionPage.setValueForPaymentSchedule(PaymentSchedule.HalfYearly);
            modulesSelectionPage.setValueForRetentionAmount(RetentionAmount.R1000);

            modulesSelectionPage.selectComfortModule();

            var isComfortInsuranceSumValid = modulesSelectionPage.isCalculatedComfortInsuranceSumValid(25);
            Assertions.assertTrue(isComfortInsuranceSumValid, "Incorrect insurance sum calculated for comfort module");

            var isComfortValuableSumValid = modulesSelectionPage.isCalculatedComfortValuableSumValid(25);
            Assertions.assertTrue(isComfortValuableSumValid, "Incorrect valuable sum calculated for comfort module");

            var totalInsuranceSum = modulesSelectionPage.getValueForTotalInsuranceSum();
            Assertions.assertEquals(25 * CommonConstants.ChargePerSquareMeter, totalInsuranceSum, "Incorrect insurance sum generated for user");

            if (!modulesSelectionPage.isAdditionalProtectionPackageSelected(ExtraInsuranceModule.ExtremeWeatherProtection)){
                modulesSelectionPage.toggleExtremeWeatherProtectionPackageState();
            }

            modulesSelectionPage.toggleBikeProtectionPackageState();
            modulesSelectionPage.toggleGlassProtectionBuildingPackageState();
            modulesSelectionPage.toggleGlassProtectionHouseholdItemsPackageState();

            var shoppingCartItemsPrices = modulesSelectionPage.getShoppingCartItemsAmount();
            var totalPaymentAmount = modulesSelectionPage.getTotalAmountToBePaid();

            var totalShoppingCartItemsPrices = shoppingCartItemsPrices.stream().mapToDouble(Double::doubleValue).sum();

            //
            Assertions.assertEquals(totalShoppingCartItemsPrices, totalPaymentAmount, "Sum of shopping cart items does not match total price");
        } catch (Exception e) {
            logger.error("An error occurred calculating for content household insurance with comfort module selected", e);
            Assertions.fail();
        }
    }

    @Test
    @Tag(testTag)
    @DisplayName("End to end test to calculate user's content household insurance with premium module")
    public void calculateTariffForContentHouseholdInsuranceWithPremiumModule() {
        try {
            logger.info("<== Calculating tariff for content household insurance with premium module selected ==>");

            // <== Basic Data Page ==>
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

            var age = basicDataPage.getAge();
            Assertions.assertTrue(age >= 18, "User is less than 18 years");

            basicDataPage.clickCalculateButton();

            // <== Module Selection Page ==>
            Thread.sleep(Duration.ofSeconds(5).toMillis());
            var modulesSelectionPage = new ModuleSelectionPage(driver);
            modulesSelectionPage.setValueForCoverageSuggestion(CoverageSuggestion.Recommended);
            modulesSelectionPage.setValueForPaymentSchedule(PaymentSchedule.HalfYearly);
            modulesSelectionPage.setValueForRetentionAmount(RetentionAmount.R1000);

            modulesSelectionPage.selectPremiumModule();

            var isPremiumInsuranceSumValid = modulesSelectionPage.isCalculatedPremiumInsuranceSumValid(25);
            Assertions.assertTrue(isPremiumInsuranceSumValid, "Incorrect insurance sum calculated for premium module");

            var isPremiumValuableSumValid = modulesSelectionPage.isCalculatedPremiumValuableSumValid(25);
            Assertions.assertTrue(isPremiumValuableSumValid, "Incorrect valuable sum calculated for premium module");

            var totalInsuranceSum = modulesSelectionPage.getValueForTotalInsuranceSum();
            Assertions.assertEquals(25 * CommonConstants.PremiumChargePerSquareMeter, totalInsuranceSum * 2, "Incorrect insurance sum generated for user");
        } catch (Exception e) {
            logger.error("An error occurred calculating for content household insurance with premium module selected", e);
            Assertions.fail();
        }
    }
}

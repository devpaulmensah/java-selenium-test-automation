package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import shared.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ModuleSelectionPage extends BasePage {
    private static final Logger logger = LogManager.getLogger();
    private static final By paymentScheduleDropdownTrigger = By.cssSelector("nx-dropdown[formcontrolname='payment_schedule']");
    private static final By paymentScheduleDropDownItems = By.cssSelector("div#cdk-overlay-12 nx-dropdown-item");
    private static final By coverageSuggestionDropdownTrigger = By.cssSelector("nx-dropdown[formcontrolname='coverageSuggestion']");
    private static final By coverageSuggestionDropDownItems = By.cssSelector("div#cdk-overlay-11 nx-dropdown-item");
    private static final By totalInsuranceSumInput = By.cssSelector("input[formcontrolname='coverage']");
    private static final By retentionDropdownTrigger = By.cssSelector("nx-dropdown[formcontrolname='retention']");
    private static final By retentionDropDownItems = By.cssSelector("div#cdk-overlay-13 nx-dropdown-item");
    private static final By comparisonTableBaseAmount = By.cssSelector("div#nx-comparison-table-cell-0 .product-price");
    private static final By baseModuleSelectButton = By.id("product_HH001");
    private static final By comparisonTableSmartAmount = By.cssSelector("div#nx-comparison-table-cell-1 .product-price");
    private static final By smartModuleSelectButton = By.id("product_HH002");
    private static final By comparisonTableComfortAmount = By.cssSelector("div#nx-comparison-table-cell-2 .product-price");
    private static final By comfortModuleSelectButton = By.id("product_HH003");
    private static final By comparisonTablePremiumAmount = By.cssSelector("div#nx-comparison-table-cell-3 .product-price");
    private static final By premiumModuleSelectButton = By.id("product_HH004");
    private static final By baseInsuranceSum = By.xpath("//nx-comparison-table/div/div/div[1]/div[2]/nx-comparison-table-flex-row[1]/div/div[2]");
    private static final By smartInsuranceSum = By.xpath("//nx-comparison-table/div/div/div[1]/div[2]/nx-comparison-table-flex-row[1]/div/div[3]");
    private static final By comfortInsuranceSum = By.xpath("//nx-comparison-table/div/div/div[1]/div[2]/nx-comparison-table-flex-row[1]/div/div[4]");
    private static final By premiumInsuranceSum = By.xpath("//nx-comparison-table/div/div/div[1]/div[2]/nx-comparison-table-flex-row[1]/div/div[5]");

    // Valuables sums
    private static final By baseValuableSum = By.xpath("//nx-comparison-table/div/div/div[1]/div[2]/nx-comparison-table-flex-row[2]/div/div[2]");
    private static final By smartValuableSum = By.xpath("//nx-comparison-table/div/div/div[1]/div[2]/nx-comparison-table-flex-row[2]/div/div[3]");
    private static final By comfortValuableSum = By.xpath("//nx-comparison-table/div/div/div[1]/div[2]/nx-comparison-table-flex-row[2]/div/div[4]");
    private static final By premiumValuableSum = By.xpath("//nx-comparison-table/div/div/div[1]/div[2]/nx-comparison-table-flex-row[2]/div/div[5]");

    // Extra insurance packages
    private static final By extremeWeatherProtectionInsuranceCard = By.xpath("//module-options/div/div/nxt-option-card[1]");
    private static final By bikeProtectionInsuranceCard = By.xpath("//module-options/div/div/nxt-option-card[2]");
    private static final By glassProtectionHouseholdItemsInsuranceCard = By.xpath("//module-options/div/div/nxt-option-card[3]");
    private static final By glassProtectionBuildingInsuranceCard = By.xpath("//module-options/div/div/nxt-option-card[4]");
    private static final By emergencyServiceInsuranceCard = By.xpath("//module-options/div/div/nxt-option-card[5]");

    // Shopping cart items
    private static final By shoppingCartItems = By.cssSelector("#shoppingCartWrapper nxt-shopping-cart > div[role='list']");
    private static final By totalShoppingCartAmount = By.cssSelector("div.nxt-price-tag__value");

    public ModuleSelectionPage(WebDriver driver) {
        super(driver);
    }

    public Double getValueForTotalInsuranceSum() {
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info("Getting value for insurance sum amount");

            var value = getWebDriver().findElement(totalInsuranceSumInput).getAttribute("value").replace("€", "").trim();
            var convertedValue = new DecimalFormat("", new DecimalFormatSymbols(Locale.GERMANY)).parse(value);

            return Double.parseDouble(convertedValue.toString());
        } catch (Exception e) {
            logger.error("An error occurred getting value for insurance sum amount", e);
            return Double.NaN;
        }
    }

    public void setValueForPaymentSchedule(PaymentSchedule paymentSchedule) {
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info(String.format("Setting value for payment schedule - %s", paymentSchedule));

            var dropDownTriggerElement = getWebDriver().findElement(paymentScheduleDropdownTrigger);

            // Open dropdown trigger
            ((JavascriptExecutor) getWebDriver()).executeScript(clickElementScript, dropDownTriggerElement);

            var paymentScheduleOptions = getWebDriver().findElements(paymentScheduleDropDownItems);

            Thread.sleep(Duration.ofMillis(150).toMillis());

            // Select first item in list and click to set value
            ((JavascriptExecutor) getWebDriver()).executeScript(clickElementScript, paymentScheduleOptions.get(paymentSchedule.ordinal()));
        } catch (Exception e) {
            logger.error("An error occurred setting value for payment schedule", e);
        }
    }

    public void setValueForCoverageSuggestion(CoverageSuggestion coverageSuggestion) {
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info(String.format("Setting value for coverage suggestion - %s", coverageSuggestion));

            var dropDownTriggerElement = getWebDriver().findElement(coverageSuggestionDropdownTrigger);

            // Open dropdown trigger
            ((JavascriptExecutor) getWebDriver()).executeScript(clickElementScript, dropDownTriggerElement);

            var coverageSuggestionOptions = getWebDriver().findElements(coverageSuggestionDropDownItems);

            Thread.sleep(Duration.ofMillis(150).toMillis());

            // Select first item in list and click to set value
            ((JavascriptExecutor) getWebDriver()).executeScript(clickElementScript, coverageSuggestionOptions.get(coverageSuggestion.ordinal()));
        } catch (Exception e) {
            logger.error("An error occurred setting value for coverage suggestion", e);
        }
    }

    public void setValueForRetentionAmount(RetentionAmount retentionAmount) {
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info(String.format("Setting value for retention amount - %s", retentionAmount));

            var dropDownTriggerElement = getWebDriver().findElement(retentionDropdownTrigger);

            // Open dropdown trigger
            ((JavascriptExecutor) getWebDriver()).executeScript(clickElementScript, dropDownTriggerElement);

            var retentionAmountOptions = getWebDriver().findElements(retentionDropDownItems);

            Thread.sleep(Duration.ofMillis(150).toMillis());

            // Select first item in list and click to set value
            ((JavascriptExecutor) getWebDriver()).executeScript(clickElementScript, retentionAmountOptions.get(retentionAmount.ordinal()));
        } catch (Exception e) {
            logger.error("An error occurred setting value for retention", e);
        }
    }

    public void selectBaseModule() {
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info("Selecting base module");

            getWebDriver().findElement(baseModuleSelectButton).click();

        } catch (Exception e) {
            logger.error("An error occurred selecting base module", e);
        }
    }

    public void selectSmartModule() {
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info("Selecting smart module");

            getWebDriver().findElement(smartModuleSelectButton).click();

        } catch (Exception e) {
            logger.error("An error occurred selecting smart module", e);
        }
    }

    public void selectComfortModule() {
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info("Selecting comfort module");

            getWebDriver().findElement(comfortModuleSelectButton).click();

        } catch (Exception e) {
            logger.error("An error occurred selecting comfort module", e);
        }
    }

    public void selectPremiumModule() {
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info("Selecting premium module");

            getWebDriver().findElement(premiumModuleSelectButton).click();

        } catch (Exception e) {
            logger.error("An error occurred selecting premium module", e);
        }
    }

    public Double getInsuranceSumForModule(By moduleSelector) {
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info("Getting insurance sum for module");

            var value = getWebDriver().findElement(moduleSelector).getText().replace("€", "").trim();
            var convertedValue = new DecimalFormat("", new DecimalFormatSymbols(Locale.GERMANY)).parse(value);

            return Double.parseDouble(convertedValue.toString());
        } catch (Exception e) {
            logger.error("An error occurred getting insurance sum for module", e);
            return Double.NaN;
        }
    }

    public Boolean isCalculatedBaseInsuranceSumValid(Integer livingSpaceSize) {
        try {
            Thread.sleep(Duration.ofMillis(100).toMillis());
            logger.info("Checking validity of base insurance sum calculated");

            var calculatedInsuranceSum = getInsuranceSumForModule(baseInsuranceSum);

            return (calculatedInsuranceSum / livingSpaceSize) == CommonConstants.ChargePerSquareMeter;
        } catch (Exception e) {
            logger.error("An error occurred checking validity of base insurance sum calculated for user", e);
            return false;
        }
    }

    public Boolean isCalculatedSmartInsuranceSumValid(Integer livingSpaceSize) {
        try {
            Thread.sleep(Duration.ofMillis(100).toMillis());
            logger.info("Checking validity of smart insurance sum calculated");

            var calculatedInsuranceSum = getInsuranceSumForModule(smartInsuranceSum);

            return (calculatedInsuranceSum / livingSpaceSize) == CommonConstants.ChargePerSquareMeter;
        } catch (Exception e) {
            logger.error("An error occurred checking validity of smart insurance sum calculated for user", e);
            return false;
        }
    }

    public Boolean isCalculatedComfortInsuranceSumValid(Integer livingSpaceSize) {
        try {
            Thread.sleep(Duration.ofMillis(100).toMillis());
            logger.info("Checking validity of comfort insurance sum calculated");

            var calculatedInsuranceSum = getInsuranceSumForModule(comfortInsuranceSum);

            return (calculatedInsuranceSum / livingSpaceSize) == CommonConstants.ChargePerSquareMeter;
        } catch (Exception e) {
            logger.error("An error occurred checking validity of comfort insurance sum calculated for user", e);
            return false;
        }
    }

    public Boolean isCalculatedPremiumInsuranceSumValid(Integer livingSpaceSize) {
        try {
            Thread.sleep(Duration.ofMillis(100).toMillis());
            logger.info("Checking validity of premium insurance sum calculated");

            var calculatedInsuranceSum = getInsuranceSumForModule(premiumInsuranceSum);

            return (calculatedInsuranceSum / livingSpaceSize) == CommonConstants.PremiumChargePerSquareMeter;
        } catch (Exception e) {
            logger.error("An error occurred checking validity of premium insurance sum calculated for user", e);
            return false;
        }
    }

    public Double getValuableSumForModule(By moduleSelector) {
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info("Getting valuable sum for module");

            var value = getWebDriver().findElement(moduleSelector).getText().replace("€", "").trim();
            var convertedValue = new DecimalFormat("", new DecimalFormatSymbols(Locale.GERMANY)).parse(value);

            return Double.parseDouble(convertedValue.toString());
        } catch (Exception e) {
            logger.error("An error occurred getting valuable sum for module", e);
            return Double.NaN;
        }
    }

    public Boolean isCalculatedBaseValuableSumValid(Integer livingSpaceSize) {
        try {
            Thread.sleep(Duration.ofMillis(100).toMillis());
            logger.info("Checking validity of base valuable sum calculated");

            var calculatedInsuranceSum = getInsuranceSumForModule(baseInsuranceSum);
            var calculatedValuableSum = getValuableSumForModule(baseValuableSum);

            return ((calculatedInsuranceSum * CommonConstants.BaseValuableSumPercentage) == calculatedValuableSum)
                    && (calculatedValuableSum <= CommonConstants.MaximumBaseValuableSum);
        } catch (Exception e) {
            logger.error("An error occurred checking validity of base valuable sum calculated for user", e);
            return false;
        }
    }

    public Boolean isCalculatedSmartValuableSumValid(Integer livingSpaceSize) {
        try {
            Thread.sleep(Duration.ofMillis(100).toMillis());
            logger.info("Checking validity of smart valuable sum calculated");

            var calculatedInsuranceSum = getInsuranceSumForModule(smartInsuranceSum);
            var calculatedValuableSum = getValuableSumForModule(smartValuableSum);

            return ((calculatedInsuranceSum * CommonConstants.SmartValuableSumPercentage) == calculatedValuableSum)
                    && (calculatedValuableSum <= CommonConstants.MaximumSmartValuableSum);
        } catch (Exception e) {
            logger.error("An error occurred checking validity of smart valuable sum calculated for user", e);
            return false;
        }
    }

    public Boolean isCalculatedComfortValuableSumValid(Integer livingSpaceSize) {
        try {
            Thread.sleep(Duration.ofMillis(100).toMillis());
            logger.info("Checking validity of comfort valuable sum calculated");

            var calculatedInsuranceSum = getInsuranceSumForModule(comfortInsuranceSum);
            var calculatedValuableSum = getValuableSumForModule(comfortValuableSum);

            return ((calculatedInsuranceSum * CommonConstants.ComfortValuableSumPercentage) == calculatedValuableSum)
                    && (calculatedValuableSum <= CommonConstants.MaximumComfortValuableSum);
        } catch (Exception e) {
            logger.error("An error occurred checking validity of comfort valuable sum calculated for user", e);
            return false;
        }
    }

    public Boolean isCalculatedPremiumValuableSumValid(Integer livingSpaceSize) {
        try {
            Thread.sleep(Duration.ofMillis(100).toMillis());
            logger.info("Checking validity of premium valuable sum calculated");

            var calculatedInsuranceSum = getInsuranceSumForModule(premiumInsuranceSum);
            var calculatedValuableSum = getValuableSumForModule(premiumValuableSum);

            return ((calculatedInsuranceSum * CommonConstants.PremiumValuableSumPercentage) == calculatedValuableSum)
                    && (calculatedValuableSum <= CommonConstants.MaximumPremiumValuableSum);
        } catch (Exception e) {
            logger.error("An error occurred checking validity of premium valuable sum calculated for user", e);
            return false;
        }
    }

    public void toggleExtremeWeatherProtectionPackageState() {
        try {
            Thread.sleep(Duration.ofMillis(100).toMillis());
            logger.info("Toggling extra insurance for extreme weather protection state");

            getWebDriver().findElement(extremeWeatherProtectionInsuranceCard).findElement(By.cssSelector("button.nx-button")).click();
        } catch (Exception e) {
            logger.error("An error occurred toggling additional insurance for extreme weather protection state");
        }
    }

    public void toggleBikeProtectionPackageState() {
        try {
            Thread.sleep(Duration.ofMillis(100).toMillis());
            logger.info("Toggling extra insurance for bike protection state");

            getWebDriver().findElement(bikeProtectionInsuranceCard).findElement(By.cssSelector("button.nx-button")).click();
        } catch (Exception e) {
            logger.error("An error occurred toggling additional insurance for bike protection state");
        }
    }

    public void toggleGlassProtectionHouseholdItemsPackageState() {
        try {
            Thread.sleep(Duration.ofMillis(100).toMillis());
            logger.info("Toggling extra insurance for glass household items protection state");

            getWebDriver().findElement(glassProtectionHouseholdItemsInsuranceCard).findElement(By.cssSelector("button.nx-button")).click();
        } catch (Exception e) {
            logger.error("An error occurred toggling additional insurance for glass household items protection state");
        }
    }

    public void toggleGlassProtectionBuildingPackageState() {
        try {
            Thread.sleep(Duration.ofMillis(100).toMillis());
            logger.info("Toggling extra insurance for glass building protection state");

            getWebDriver().findElement(glassProtectionBuildingInsuranceCard).findElement(By.cssSelector("button.nx-button")).click();
        } catch (Exception e) {
            logger.error("An error occurred toggling additional insurance for glass building protection state");
        }
    }

    public void toggleEmergencyServicePackageState() {
        try {
            Thread.sleep(Duration.ofMillis(100).toMillis());
            logger.info("Toggling extra insurance for emergency services state");

            getWebDriver().findElement(emergencyServiceInsuranceCard).findElement(By.cssSelector("button.nx-button")).click();
        } catch (Exception e) {
            logger.error("An error occurred toggling additional insurance for emergency services state");
        }
    }

    public Boolean isAdditionalProtectionPackageSelected(ExtraInsuranceModule module) {
        try {
            Thread.sleep(Duration.ofMillis(100).toMillis());
            logger.info("Checking if extra insurance for extreme weather protection has selected");

            var extraModule = extremeWeatherProtectionInsuranceCard;

            extraModule = switch (module) {
                case ExtremeWeatherProtection -> extremeWeatherProtectionInsuranceCard;
                case BikeProtection -> bikeProtectionInsuranceCard;
                case EmergencyService -> emergencyServiceInsuranceCard;
                case GlassProtectionBuilding -> glassProtectionBuildingInsuranceCard;
                case GlassProtectionHouseholdItems -> glassProtectionHouseholdItemsInsuranceCard;
            };

            var classes = getWebDriver().findElement(extraModule).getAttribute("class");
            return classes.contains("is-checked");
        } catch (Exception e) {
            logger.error("An error occurred checking if additional insurance for extreme weather protection is selected");
            return false;
        }
    }

    public Double getTotalAmountToBePaid() {
        try {
            logger.info("Getting total insurance to be paid");

            Thread.sleep(Duration.ofSeconds(3).toMillis());
            var paymentSummaryElement = getWebDriver().findElement(shoppingCartItems).findElement(By.cssSelector("nxt-shopping-cart-row.is-summary"));

            var value = paymentSummaryElement.findElement(totalShoppingCartAmount).getText().replace("€", "").trim();
            var convertedValue = new DecimalFormat("", new DecimalFormatSymbols(Locale.GERMANY)).parse(value);

            return Double.parseDouble(convertedValue.toString());
        } catch (Exception e) {
            logger.error("An error occurred getting total insurance amount to be paid", e);
            return Double.NaN;
        }
    }

    public List<Double> getShoppingCartItemsAmount() {
        try {
            Thread.sleep(Duration.ofSeconds(2).toMillis());
            logger.info("Checking if extra insurance for extreme weather protection has selected");

            var prices = new ArrayList<Double>();
            var paymentSummaryElements = getWebDriver().findElement(shoppingCartItems).findElements(By.cssSelector("nxt-shopping-cart-row:not(.is-summary):not(.inclusive)"));

            for (var element : paymentSummaryElements) {
                Thread.sleep(Duration.ofMillis(300).toMillis());
                var isInclusiveElement = element.findElement(By.cssSelector("nxt-price-tag")).getText().equalsIgnoreCase("Inklusive");

                if (!isInclusiveElement) {
                    Thread.sleep(Duration.ofMillis(300).toMillis());
                    var value = element.findElement(totalShoppingCartAmount).getText().replace("€", "").trim();

                    var convertedValue = new DecimalFormat("", new DecimalFormatSymbols(Locale.GERMANY)).parse(value);

                    prices.add(Double.parseDouble(convertedValue.toString()));
                }
            }

            return prices;
        } catch (Exception e) {
            logger.error("An error occurred getting prices of individual prices of shopping items", e);
            return new ArrayList<>();
        }
    }
}

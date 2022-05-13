package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import shared.ApartmentFloorLevel;
import shared.HouseInhabitedStatus;
import shared.HouseType;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class BasicDataPage extends BasePage {
    private static final Logger logger = LogManager.getLogger();
    private static final By postCodeInput = By.cssSelector("input[formcontrolname='zip']");
    private static final By livingSpaceInput = By.cssSelector("input[formcontrolname='livingSpace']");
    private static final By birthDateInput = By.cssSelector("input[formcontrolname='birthdate']");
    private static final By streetNumberInput = By.cssSelector("input[formcontrolname='streetNumber']");
    private static final By houseInhabitedStatusDropDownTrigger = By.cssSelector("nx-dropdown[formcontrolname='houseInhabitedStatus']");
    private static final By streetNameDropDownTrigger = By.cssSelector("nx-dropdown[formcontrolname='street']");
    private static final By streetNameDropDown = By.cssSelector("div#cdk-overlay-6");
    private static final By streetNameSearchInput = By.cssSelector("input.nx-dropdown__filter-input");
    private static final By dropDownListElement = By.cssSelector("nx-dropdown-item");
    private static final By activeDropDownListElement = By.cssSelector("nx-dropdown-item.nx-dropdown-item--active");
    private static final By calculateTariffButton = By.cssSelector("button[nxbutton='emphasis']");
    private static final By radioInputSelector = By.cssSelector("nx-radio label div");
    private static final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");
    private static final By householdLocationRadioInput = By.cssSelector("nx-radio-group[formcontrolname='houseType'] div.group nx-radio");
    private static final By apartmentFloorRadioInput = By.cssSelector("nx-radio-group[formcontrolname='apartmentFloor'] div.group nx-radio");
    private static final By gotDamageRadioInputs = By.cssSelector("nx-radio-group[formcontrolname='gotDamage'] div.group nx-radio");
    private static final By houseDamageCountInput = By.cssSelector("nx-number-stepper[formcontrolname='houseDamageCount'] .nx-stepper__input-container input");
    private static final By houseDamageCountReduceButton = By.cssSelector("nx-number-stepper[formcontrolname='houseDamageCount'] .nx-stepper__input-container button.nx-stepper__down");
    private static final By houseDamageCountIncreaseButton = By.cssSelector("nx-number-stepper[formcontrolname='houseDamageCount'] .nx-stepper__input-container button.nx-stepper__up");
    private static final By bikeTheftCountInput = By.cssSelector("nx-number-stepper[formcontrolname='bikeDamageCount'] .nx-stepper__input-container input");
    private static final By bikeTheftCountReduceButton = By.cssSelector("nx-number-stepper[formcontrolname='bikeDamageCount'] .nx-stepper__input-container button.nx-stepper__down");
    private static final By bikeTheftCountIncreaseButton = By.cssSelector("nx-number-stepper[formcontrolname='bikeDamageCount'] .nx-stepper__input-container button.nx-stepper__up");
    private static final By householdDamagesErrorModal = By.cssSelector("div#cdk-overlay-4");
    private static final By householdNotPermanentlyInhabitedErrorModal = By.cssSelector("div#cdk-overlay-5");

    public BasicDataPage(WebDriver driver) {
        super(driver);
    }

    public void loadPage()  {
        logger.info("Opening browser to load page");
        getWebDriver().get("https://www.allianz.de/recht-und-eigentum/hausratversicherung/rechner/");
        getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void acceptCookies(){
        try {
            logger.info("Accepting cookies");

            getWebDriver().findElement(acceptCookiesButton).click();
        } catch (Exception e) {
            logger.error("An error occurred accepting cookies", e);
        }
    }

    public void setValueForPostCode(Integer postCode){
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info(String.format("Setting value for post code - %s", postCode));

            getWebDriver().findElement(postCodeInput).sendKeys(postCode.toString());
        } catch (Exception e) {
            logger.error("An error occurred setting value for post code", e);
        }
    }

    public void setValueForLivingSpace(Integer livingSpace){
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info(String.format("Setting value for living space - %s", livingSpace));

            getWebDriver().findElement(livingSpaceInput).sendKeys(livingSpace.toString());
        } catch (Exception e) {
            logger.error("An error occurred setting value for living space", e);
        }
    }

    public void setValueForBirthDate(String birthDate){
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info(String.format("Setting value for birth date - %s", birthDate));

            getWebDriver().findElement(birthDateInput).sendKeys(birthDate);
        } catch (Exception e) {
            logger.error("An error occurred setting value for birth date", e);
        }
    }

    public Integer getAge(){
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info("Getting value for user age");

            var birthDateValue = getWebDriver().findElement(birthDateInput).getAttribute("value");

            if (birthDateValue.isEmpty())
                return 0;

            var birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(birthDateValue);
            var calendar = Calendar.getInstance();
            calendar.setTime(birthDate);

            var year = calendar.get(Calendar.YEAR);
            var month = calendar.get(Calendar.MONTH) + 1;
            var date  = calendar.get(Calendar.DATE);
            var now = LocalDate.now();

            var age = Period.between(LocalDate.of(year, month, date), now);
            return age.getYears();
        } catch (Exception e) {
            logger.error("An error occurred getting user age with date of birth", e);
            return 0;
        }
    }

    public void setValueForStreetNumber(String streetNumber){
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info(String.format("Setting value for street number - %s", streetNumber));

            getWebDriver().findElement(streetNumberInput).sendKeys(streetNumber);
        } catch (Exception e) {
            logger.error("An error occurred setting value for street number", e);
        }
    }

    public void setValueForStreetName(String streetName){
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info(String.format("Setting value for street name - %s", streetName));

            var dropDownTriggerElement = getWebDriver().findElement(streetNameDropDownTrigger);

            // Open dropdown trigger
            ((JavascriptExecutor) getWebDriver()).executeScript(clickElementScript, dropDownTriggerElement);

            var streetsDropdown = getWebDriver().findElement(streetNameDropDown);

            // Select filter input and set value
            var filterSearchInput = streetsDropdown.findElement(streetNameSearchInput);
            filterSearchInput.sendKeys(streetName);

            Thread.sleep(Duration.ofMillis(150).toMillis());
            // Get dropdown items element
            var dropdownListElements = streetsDropdown.findElements(activeDropDownListElement);

            // Select first item in list and click to set value
            ((JavascriptExecutor) getWebDriver()).executeScript(clickElementScript, dropdownListElements.get(0));
        } catch (Exception e) {
            logger.error("An error occurred setting value for street", e);
        }
    }

    public void setValueForHouseholdLocation(HouseType householdLocation, ApartmentFloorLevel floorLevel){
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info(String.format("Setting value for household location - %s", householdLocation));

            var isDetachedHouse = householdLocation.toString().equalsIgnoreCase(HouseType.Detached.toString());

            var houseHoldInputs = getWebDriver().findElements(householdLocationRadioInput);
            var radioInput = houseHoldInputs.get(isDetachedHouse ? 0 : 1).findElement(radioInputSelector);

            ((JavascriptExecutor) getWebDriver()).executeScript(clickElementScript, radioInput);

            if (!isDetachedHouse)
                setValueForApartmentFloor(floorLevel);

        } catch (Exception e) {
            logger.error("An error occurred setting value for household location", e);
        }
    }

    private void setValueForApartmentFloor(ApartmentFloorLevel floorLevel){
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info(String.format("Setting value for apartment floor level - %s", floorLevel));

            var isGroundFloor = floorLevel.toString().equalsIgnoreCase(ApartmentFloorLevel.GroundFloor.toString());

            var apartmentFloorInputs = getWebDriver().findElements(apartmentFloorRadioInput);
            var radioInput = apartmentFloorInputs.get(isGroundFloor ? 0 : 1).findElement(radioInputSelector);

            ((JavascriptExecutor) getWebDriver()).executeScript(clickElementScript, radioInput);
        } catch (Exception e) {
            logger.error("An error occurred setting value for apartment floor level", e);
        }
    }

    public void setValueForGotDamages(Boolean gotDamages){
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info(String.format("Setting value for got damages - %s", gotDamages));

            var index = gotDamages ? 0 : 1;

            var gotDamagesInputs = getWebDriver().findElements(gotDamageRadioInputs);
            var radioInput = gotDamagesInputs.get(index).findElement(radioInputSelector);

            ((JavascriptExecutor) getWebDriver()).executeScript(clickElementScript, radioInput);
        } catch (Exception e) {
            logger.error("An error occurred setting value for damages", e);
        }
    }

    public void setValueForHouseDamageCount(Integer houseDamageCount){
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info(String.format("Setting value for house damage count - %s", houseDamageCount));

            getWebDriver().findElement(houseDamageCountInput).sendKeys(houseDamageCount.toString());
        } catch (Exception e) {
            logger.error("An error occurred setting value for house damage count", e);
        }
    }

    public Integer getValueForHouseDamageCount(){
        var value = getWebDriver().findElement(houseDamageCountInput).getAttribute("value");
        return Integer.parseInt(value);
    }

    public void decreaseHouseDamageCount(){
        try {
            logger.info("Decreasing house damage count");

            ((JavascriptExecutor) getWebDriver()).executeScript(clickElementScript, getWebDriver().findElement(houseDamageCountReduceButton));
        } catch (Exception e) {
            logger.error("An error occurred decreasing house damage count", e);
        }
    }

    public void increaseHouseDamageCount(){
        try {
            logger.info("Increasing house damage count");

            getWebDriver().findElement(houseDamageCountIncreaseButton).click();
        } catch (Exception e) {
            logger.error("An error occurred increasing house damage count", e);
        }
    }

    public void setValueForBikeTheftCount(Integer bikeDamageCount){
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info(String.format("Setting value for bike theft count - %s", bikeDamageCount));

            getWebDriver().findElement(bikeTheftCountInput).sendKeys(bikeDamageCount.toString());
        } catch (Exception e) {
            logger.error("An error occurred setting value for bike theft count", e);
        }
    }

    public Integer getValueForBikeTheftCount(){
        var value = getWebDriver().findElement(bikeTheftCountInput).getAttribute("value");
        return Integer.parseInt(value);
    }

    public void decreaseBikeTheftCount(){
        try {
            logger.info("Decreasing bike damage count");

            getWebDriver().findElement(bikeTheftCountReduceButton).click();
        } catch (Exception e) {
            logger.error("An error occurred decreasing bike theft count", e);
        }
    }

    public void increaseBikeTheftCount(){
        try {
            logger.info("Increasing bike theft count");

            getWebDriver().findElement(bikeTheftCountIncreaseButton).click();
        } catch (Exception e) {
            logger.error("An error occurred increasing bike theft count", e);
        }
    }

    public WebElement getHouseholdDamagesModalElement() {
        try {
            logger.info("Getting modal to indicate both bike theft and household damages have exceeded 4");

            return getWebDriver().findElement(householdDamagesErrorModal);
        } catch(Exception e){
            return null;
        }
    }

    public WebElement getHouseholdNotPermanentlyInhabitedErrorModalElement() {
        try {
            logger.info("Getting modal to indicate insurance tariff cannot be calculated for household not permanently inhabited");

            return getWebDriver().findElement(householdNotPermanentlyInhabitedErrorModal);
        } catch(Exception e){
            return null;
        }
    }

    public void setValueForHouseInhabitedStatus(HouseInhabitedStatus houseInhabitedStatus){
        try {
            Thread.sleep(Duration.ofMillis(150).toMillis());
            logger.info(String.format("Setting value for house inhabited - %s", houseInhabitedStatus));

            var dropDownTriggerElement = getWebDriver().findElement(houseInhabitedStatusDropDownTrigger);

            // Open dropdown trigger
            ((JavascriptExecutor) getWebDriver()).executeScript(clickElementScript, dropDownTriggerElement);

            var dropDownListElements = getWebDriver().findElement(By.cssSelector("div.nx-dropdown__panel-body"))
                    .findElements(dropDownListElement);

            var selectedInhabitedStatusElement = dropDownListElements.get(HouseInhabitedStatus.PermanentlyInhabited.equals(houseInhabitedStatus) ? 0 : 1);
            ((JavascriptExecutor) getWebDriver()).executeScript(clickElementScript, selectedInhabitedStatusElement);
        } catch (Exception e) {
            logger.error("An error occurred setting value for bike theft count", e);
        }
    }

    public void clickCalculateButton(){
        getWebDriver().findElement(calculateTariffButton).click();
    }
}

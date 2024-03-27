package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.*;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class Hooks {

    @Before("not @Registration")
    public void the_user_is_on_dbank_homepage(){
        getDriver().get("https://dbank-qa.wedevx.co/bank/login");
    }

    @Before("@Registration")
    public static void establishConnectionToDB(){
        DBUtils.establishConnection();
    }

    @After("not @Withdraw")
    public void afterEachScenario(Scenario scenario){
        Driver.takeScreenshot(scenario);
        Driver.closeDriver();
    }
    @After("@Registration")
    public static void closeConnectionToDB(){
        DBUtils.closeConnection();
    }
}

package thurAssessmentOrange.com.qa.thur.assessment.orange;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitionUsingOrangeHRMFeature {
	
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	
	OrangeSiteHomepage homepage;
	OrangeSiteLoggedInDashboard dashboard;
	OrangeSiteAddEmployee addEmployee;
	
	@Before
	public void setup() {
		report = new ExtentReports(Constants.REPORT_FOLDER_PATH + Constants.REPORT_FILE_PATH, true);
		test = report.startTest("Create employee and attempt login");
		test.log(LogStatus.INFO, "Initialising test");
		System.setProperty(Constants.GECKO_PROPERTY_NAME, Constants.GECKO_DRIVER_PATH);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(4, TimeUnit.SECONDS);
		driver.get(Constants.SITE_URL);
		test.log(LogStatus.INFO, "Navigating to site");
	}
	
	@Given("^the Add Employee Tab$")
	public void the_Add_Employee_Tab() {
		test.log(LogStatus.INFO, "attempting model homepage");
		homepage = new OrangeSiteHomepage(driver);
		homepage.enterUsername(Constants.SITE_USERNAME);
		homepage.enterPassword(Constants.SITE_PASSWORD);
		homepage.logOn();
		test.log(LogStatus.INFO, "Logged in to site");
		
		new WebDriverWait(driver, 10) .until(ExpectedConditions.presenceOfElementLocated(By.id("menu_pim_viewPimModule"))); 
		
		test.log(LogStatus.INFO, "Attempting to model dashboard");
		dashboard = new OrangeSiteLoggedInDashboard(driver);
		dashboard.navToAddEmployee();
		test.log(LogStatus.INFO, "Navigated to add employee tab");
	}

	@When("^I fill out the Employee Details correctly with firstname \"([^\"]*)\" lastname \"([^\"]*)\" employeeID \"([^\"]*)\"$")
	public void i_fill_out_the_Employee_Details_correctly_with_firstname_lastname_employeeID(String firstName, String lastName, String employeeID) {
		new WebDriverWait(driver, 10) .until(ExpectedConditions.presenceOfElementLocated(By.id("firstName"))); 
		test.log(LogStatus.INFO, "Attempting to model add employee page");
		addEmployee = new OrangeSiteAddEmployee(driver);
		test.log(LogStatus.INFO, "Entering firstname, lastname and ID");
		addEmployee.enterFirstName(firstName);
		addEmployee.enterSecondName(lastName);
		addEmployee.enterEmployeeID(employeeID);
	}

	@When("^I choose to create Login Details$")
	public void i_choose_to_create_Login_Details_with_username_password() {
		test.log(LogStatus.INFO, "Opening Create Login");
		addEmployee.openCreateLogin();
		
	}

	@When("^I fill out the Login Details correctly username \"([^\"]*)\" password \"([^\"]*)\"$")
	public void i_fill_out_the_Login_Details_correctly_username_password(String username, String password) {
		test.log(LogStatus.INFO, "Entering username and password");
		addEmployee.enterUsername(username);
		addEmployee.enterpassword(password);
		
	}

	@When("^I click the Save button$")
	public void i_click_the_Save_button() {
		addEmployee.saveLogin();
		test.log(LogStatus.INFO, "Saving details");
	}

	@Then("^I can see information about the user$")
	public void i_can_see_information_about_the_user(){
		if (addEmployee.getCurPage().equals("Personal Details")) {
			test.log(LogStatus.PASS, "Personal Details page was shown when user was created");
		}
		else {
			test.log(LogStatus.FAIL, "Personal Details page was not shown when user was created");
		}
		Assert.assertEquals("Personal Details", addEmployee.getCurPage());
		
	}
	
	@Then("^I Logout$")
	public void i_Logout() {
		test.log(LogStatus.INFO, "Logging out");
		addEmployee.logout();
	}

	@Given("^I login as the newly created user username \"([^\"]*)\" password \"([^\"]*)\"$")
	public void i_login_as_the_newly_created_user(String username, String password) {
		test.log(LogStatus.INFO, "Logging in as newly created user");
		homepage = new OrangeSiteHomepage(driver);
		homepage.enterUsername(username);
		homepage.enterPassword(password);
		homepage.logOn();
	}

	@When("^i go to the my info page$")
	public void i_go_to_the_my_info_page() {
		test.log(LogStatus.INFO, "navigating to my info page");
	}

	@Then("^the users details are displayed$")
	public void the_users_details_are_displayed() {
		dashboard = new OrangeSiteLoggedInDashboard(driver);
		dashboard.navToMyInfo();
		if ("Personal Details".equals(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div[1]/h1")).getText())) {
			test.log(LogStatus.PASS, "users details are displayed");
		}
		else {
			test.log(LogStatus.PASS, "users details are not displayed");
		}
		Assert.assertEquals("Personal Details", driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div[1]/h1")).getText());
	}
	
	@After
	public void teardown() {
		report.endTest(test);
		report.flush();
		driver.quit();
	}
}

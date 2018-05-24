package thurAssessmentOrange.com.qa.thur.assessment.orange;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrangeSiteAddEmployee {
	
	@FindBy (id="firstName")
	private WebElement firstNameTxt;
	
	@FindBy (id="lastName")
	private WebElement lastnameTxt;
	
	@FindBy (id="employeeId")
	private WebElement employeeIDTxt;
	
	@FindBy (id="chkLogin")
	private WebElement createLoginChkBox;
	
	@FindBy (id="btnSave")
	private WebElement saveBtn;
	
	@FindBy (xpath="//*[@id=\"welcome\"]")
	private WebElement userDropdown;
	
	
	private WebElement usernameTxt;
	private WebElement passwordTxt;
	private WebElement confirmPasswordTxt;
	
	
	private WebDriver driver;
	
	public OrangeSiteAddEmployee(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
		usernameTxt = (new WebDriverWait(driver, 10)) .until(ExpectedConditions.presenceOfElementLocated(By.id("user_name")));
		passwordTxt = (new WebDriverWait(driver, 10)) .until(ExpectedConditions.presenceOfElementLocated(By.id("user_password")));
		confirmPasswordTxt = (new WebDriverWait(driver, 10)) .until(ExpectedConditions.presenceOfElementLocated(By.id("re_password")));
	}
	
	public void enterFirstName(String firstName) {
		firstNameTxt.sendKeys(firstName);
	}
	
	public void enterSecondName(String lastName) {
		lastnameTxt.sendKeys(lastName);
	}
	
	public void enterEmployeeID(String employeeID) {
		employeeIDTxt.sendKeys(employeeID);
	}
	
	public void openCreateLogin() {
		createLoginChkBox.click();
	}
	
	public void enterUsername(String username) {
		usernameTxt.sendKeys(username);
	}
	
	public void enterpassword(String password) {
		passwordTxt.sendKeys(password);
		confirmPasswordTxt.sendKeys(password);
	}
	
	public void saveLogin() {
		saveBtn.click();
	}
	
	public String getCurPage() {
		try {
			return driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div[1]/h1")).getText();
		}
		catch(Exception e) {
			return "Unsuccessful create login";
		}
	}
	
	public void logout() {
		userDropdown.click();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/ul/li[2]/a")).click();
	}
	
}

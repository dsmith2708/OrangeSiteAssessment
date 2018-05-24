package thurAssessmentOrange.com.qa.thur.assessment.orange;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class OrangeSiteLoggedInDashboard {
	@FindBy (id="menu_pim_viewPimModule")
	private WebElement pimButton;
	
	@FindBy (id="menu_pim_addEmployee")
	private WebElement addEmployeeButton;
	
	@FindBy(id="menu_pim_Configuration")
	private WebElement configButton;
	
	@FindBy(id="menu_pim_viewMyDetails")
	private WebElement myInfoBtn;
	
	private WebDriver driver;
	
	public OrangeSiteLoggedInDashboard(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	}
	
	public void navToAddEmployee() {
		pimButton.click();
		Actions action = new Actions(driver);
		action.moveToElement(pimButton).moveToElement(configButton).click().perform();
		addEmployeeButton.click();
	}
	
	public void navToMyInfo() {
		myInfoBtn.click();
	}
}

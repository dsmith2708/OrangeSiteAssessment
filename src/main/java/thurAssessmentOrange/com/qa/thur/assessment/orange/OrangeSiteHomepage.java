package thurAssessmentOrange.com.qa.thur.assessment.orange;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class OrangeSiteHomepage {
	
	@FindBy (id="txtUsername")
	private WebElement usernameInput;
	
	@FindBy (id="txtPassword")
	private WebElement passwordInput;
	
	public OrangeSiteHomepage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	}
	
	public void enterUsername(String username) {
		usernameInput.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}
	
	public void logOn() {
		passwordInput.submit();
	}
	
	
}

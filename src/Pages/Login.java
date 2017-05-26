package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy; 
import org.openqa.selenium.support.PageFactory; 


public class Login {

	WebDriver driver;
	
	@FindBy(name = "username")
	WebElement emailField;
	
	@FindBy(name = "password")
	WebElement passwordField;
	
	@FindBy(className = "loginbtn")
	WebElement loginButton;
	
	public Login(WebDriver driver) 
	{ 
		this.driver = driver; 
		 PageFactory.initElements(driver, this); 
	} 

	public void enterEmail(String email)
	{
		emailField.sendKeys(Keys.chord(email));
	}
	
	public void enterPassword(String password)
	{
		passwordField.sendKeys(Keys.chord(password));
	}
	
	public void clickLogin()
	{
		loginButton.click();
	}
	
	
	
	
}

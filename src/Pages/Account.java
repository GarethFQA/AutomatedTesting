package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Account {
	WebDriver driver;
	
	@FindBy(className = "navbar-brand")
	WebElement homepageLogo;
	
	@FindBy(css = ".col-md-6.go-right.RTL")	
	WebElement userImageAndName;
	
	public Account(WebDriver driver) 
	{ 
		this.driver = driver; 
		 PageFactory.initElements(driver, this); 
	} 

	public void clickHomepageLogo()
	{
		homepageLogo.click();
	}
	
	public boolean isLoggedIn()
	{
		String userWelcome = this.userImageAndName.findElement(By.tagName("h3")).getText();
		boolean loggedInCorrectly = false;
		if (userWelcome.equals("Hi, John Smith"))
		{
			loggedInCorrectly = true;
		}
		return loggedInCorrectly;
	}
	
	
}

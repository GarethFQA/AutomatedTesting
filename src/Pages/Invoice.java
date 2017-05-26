package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Invoice {

	WebDriver driver;
	
	public Invoice(WebDriver driver) 
	{ 
		this.driver = driver; 
		 PageFactory.initElements(driver, this); 
	} 
	
	public String getPageTitle()
	{		
		return this.driver.getTitle();
	}
	
}

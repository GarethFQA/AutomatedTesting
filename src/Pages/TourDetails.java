package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy; 
import org.openqa.selenium.support.PageFactory; 

public class TourDetails {
	
	WebDriver driver;
	
	@FindBy(tagName = "Title")
	WebElement pageTitle;	
	
	@FindBy(css = ".btn.btn-block.btn-action.btn-lg")
	WebElement correctButton;
	

	public TourDetails(WebDriver driver) 
	{ 
		this.driver = driver; 
		 PageFactory.initElements(driver, this); 
	} 
	
	public String getPageTitle()
	{

		return this.driver.getTitle();
	}
	
	public void clickBookNowButton()
	{
		correctButton.click();
	}
	
	public WebElement findBookNowButton()
	{
		return this.correctButton;
	}
	
	
	public boolean isButtonVisible(WebElement bookingButton)
	{
		return bookingButton.isDisplayed();

	}
	
	public void scrollUntilButtonVisible(WebElement bookingButton) throws InterruptedException
	{
		int distanceToScroll = 0;
		boolean isButtonShown = this.isButtonVisible(bookingButton);
		JavascriptExecutor jse = (JavascriptExecutor)this.driver;
		//Thread.sleep(1000);
		while(!isButtonShown)
		{
			distanceToScroll += 180;
			jse.executeScript("scroll(0, " + distanceToScroll + ")");
			//Thread.sleep(1000);
			isButtonShown = this.isButtonVisible(bookingButton);
			
		}
	}
}

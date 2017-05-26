package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy; 
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait; 

public class Tours {
	
	WebDriver driver;
	
	@FindBy(className = "mt15")
	List<WebElement> tourDetailsButtons;
	
	@FindBy(className = "col-md-8")
	List<WebElement> tourDetailButtonContainerDiv;
	
	@FindBy(className = "mtb0")
	List<WebElement> tourNameLinkContainingSpan;
	
	
	
	
	public Tours(WebDriver driver) 
	{ 
		this.driver = driver; 
		 PageFactory.initElements(driver, this); 
	} 
	public int getToursButtonCount()
	{
		return tourDetailsButtons.size();
	}
	
	public void clickDetailsButton(int buttonNumber)
	{
		tourDetailsButtons.get(buttonNumber).click();
	}
	public boolean isButtonVisible(int buttonNumber)
	{
		return tourDetailsButtons.get(buttonNumber).isDisplayed();

	}
	
	public void scrollUntilButtonVisible(int buttonNumber) throws InterruptedException
	{
		int distanceToScroll = 0;
		boolean isButtonShown = this.isButtonVisible(buttonNumber);
		JavascriptExecutor jse = (JavascriptExecutor)this.driver;
		//Thread.sleep(1000);
		while(!isButtonShown)
		{
			distanceToScroll += 180;
			jse.executeScript("scroll(0, " + distanceToScroll + ")");
			//Thread.sleep(1000);
			isButtonShown = this.isButtonVisible(buttonNumber);
			
		}
	}
	public String getTourNameLinkText(int tourNumber)
	{
		String tourNameText = this.tourNameLinkContainingSpan.get(tourNumber).findElement(By.tagName("a")).getText();
		String endCharacters = tourNameText.substring(tourNameText.length() - 1);

		if(endCharacters.equals("…"))
		{
			tourNameText = tourNameText.substring(0, tourNameText.length() - 1);
		}
		
		return tourNameText;
				
	}
	
	public String getPageTitle()
	{
		return this.driver.getTitle();
	}

	//public void waitUntilClickable(int tourNumber)
	//{
		
	//	WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(tourDetailsButtons.get(tourNumber)));

	//}
}




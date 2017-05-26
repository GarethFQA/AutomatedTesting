package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
	
	WebDriver driver;

	
	@FindBy(xpath = "/html/body/nav[1]/div/div/div/ul/li[4]/a")
	WebElement navbarToursButton;
	
	@FindBy(xpath = "/html/body/div[4]/div[2]/div/ul/li[3]/a")
	WebElement searchbarToursButton;
	
	@FindBy(xpath = "//*[@id=\"TOURS\"]/div/form/div[5]/div/button")
	WebElement searchbarToursSearchSubmitButton;

	@FindBy(className = "select2-choice")
	WebElement searchbarToursSearchField;
	
	@FindBy(className = "select2-input")
	WebElement searchbarToursSearchTextBox;
	
	
	
	

	public Home(WebDriver driver) 
	{ 
		this.driver = driver; 
		 PageFactory.initElements(driver, this); 
	} 
	
	public void clickNavbarTours()
	{
		this.navbarToursButton.click();
	}

	public void clickSearchbarTours()
	{
		this.searchbarToursButton.click();
	}
	
	public void clickSearchbarToursSubmit()
	{
		this.searchbarToursSearchSubmitButton.click();
	}
	public void clickSearchbarToursField()
	{
		this.searchbarToursSearchField.click();
	}
	
	public void enterSearchText(String searchText) throws InterruptedException
	{
		this.searchbarToursSearchTextBox.sendKeys(Keys.chord(searchText));
		Thread.sleep(500);
		WebElement quickResult = driver.findElement(By.className("select2-results-dept-1"));
		quickResult.click();
	}
	
	public String getPageTitle()
	{
		return this.driver.getTitle();
	}

}

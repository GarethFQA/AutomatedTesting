package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TourBooking {
	
	WebDriver driver;
	
	@FindBy(id = "guesttab")
	WebElement guestSelectGuestForm;
	
	@FindBy(name = "firstname")
	WebElement firstNameField;
	
	@FindBy(name = "lastname")
	WebElement lastNameField;
	
	@FindBy(name = "email")
	WebElement emailField;
	
	@FindBy(name = "confirmemail")
	WebElement confirmEmailField;

	
	@FindBy(name = "logged")
	WebElement loggedInConfirmBookingButton;
	
	@FindBy(name = "guest")
	WebElement guestConfirmBookingButton;
	
	public TourBooking(WebDriver driver) 
	{ 
		this.driver = driver; 
		 PageFactory.initElements(driver, this); 
	} 
	
	public void clickConfirmBookingButton()
	{
		loggedInConfirmBookingButton.click();
	}
	
	public void clickGuestConfirmBookingButton()
	{
		guestConfirmBookingButton.click();
	}
	
	public void clickGuestBookingSelection()
	{
		guestSelectGuestForm.click();
	}
	
	public void enterDetails(String first, String last, String emailAddress)
	{
		this.firstNameField.sendKeys(Keys.chord(first));
		this.lastNameField.sendKeys(Keys.chord(last));
		this.emailField.sendKeys(Keys.chord(emailAddress));
		this.confirmEmailField.sendKeys(Keys.chord(emailAddress));
	}
	
	public boolean checkConfirmBookButtonPresent()
	{
		boolean confirmButtonPresent = false;

		try
		{
			if(guestConfirmBookingButton.getText().equals("CONFIRM THIS BOOKING"))
			{
				confirmButtonPresent = true;
			}
		}catch(NoSuchElementException e){
			if(loggedInConfirmBookingButton.getText().equals("CONFIRM THIS BOOKING"))
			{
				confirmButtonPresent = true;
			}		
		}
			return confirmButtonPresent;
			
		
	
	}

}

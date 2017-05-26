/*
package Tests;


import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import Pages.Login;
import Pages.Account;
import Pages.Home;
import Pages.Tours;
import Pages.TourDetails;
import Pages.TourBooking;
import Pages.Invoice;


public class LoginTest {

	WebDriver driver;
	Login loginPage;
	Tours toursPage;
	TourDetails tourDetailsPage;
	TourBooking tourBookingPage;
	Invoice invoicePage;
	Account accountPage;
	Home homePage;
	
	public static void main(String args[]) throws InterruptedException
	{
		LoginTest testLog = new LoginTest();
		//testLog.loginTestMeth();
		testLog.guestTourBooking();
		
		
		
		
	}
	
	public void guestTourBooking() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\geckodriver.exe");
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		this.driver = new ChromeDriver();
		
		this.driver.get("http://www.phptravels.net/");
		
		Thread.sleep(1000);
		
		homePage = new Home(this.driver);
		homePage.clickSearchbarTours();
		Thread.sleep(1000);
		homePage.clickSearchbarToursField();
		homePage.enterSearchText("new");
		homePage.clickSearchbarToursSubmit();
		
		Thread.sleep(1000);
		tourDetailsPage = new TourDetails(this.driver);
				
		tourDetailsPage.getPageTitle();
		tourDetailsPage.clickBookNowButton();
		
		Thread.sleep(1000);
		tourBookingPage = new TourBooking(this.driver);
		
		tourBookingPage.clickGuestBookingSelection();
		Thread.sleep(500);
		tourBookingPage.enterDetails("Bob", "Bobbins", "fake@nullandvoid-addyplace.com");
		
		tourBookingPage.clickGuestConfirmBookingButton();
		
		Thread.sleep(5000);
		
		invoicePage = new Invoice(this.driver);
		
		System.out.println(invoicePage.getPageTitle());
		

	}
	
	public void loginTestMeth() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\geckodriver.exe");
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		this.driver = new ChromeDriver();
		
		int tourNumber = 0;
		String tourNameOnListPage;
		
		
		this.driver.get("http://www.phptravels.net/login");
		
		
		
		//driver.manage().window().maximize();
		
		loginPage = new Login(this.driver);
		loginPage.enterEmail("user@phptravels.com");
		loginPage.enterPassword("demouser");
		loginPage.clickLogin();
		

		Thread.sleep(1000);
		
		accountPage = new Account(this.driver);
		accountPage.clickHomepageLogo();
		
		Thread.sleep(1000);
		
		homePage = new Home(this.driver);
		homePage.clickNavbarTours();
		
		Thread.sleep(1000);
		
		toursPage = new Tours(this.driver);		
		toursPage.scrollUntilButtonVisible(tourNumber);	
		tourNameOnListPage = toursPage.getTourNameLinkText(tourNumber);
		toursPage.clickDetailsButton(tourNumber);

		Thread.sleep(1000);
		tourDetailsPage = new TourDetails(this.driver);
		if(tourDetailsPage.getPageTitle().contains(tourNameOnListPage))
				{
			System.out.println("IT'S A MATCH!");
				}
		
		tourDetailsPage.scrollUntilButtonVisible(tourDetailsPage.findBookNowButton());
		
		System.out.println("button shows!");
		Thread.sleep(1000);
		
		
		
		
		
		System.out.println("Go Back");
		this.driver.navigate().back();
		Thread.sleep(1000);
		toursPage = new Tours(this.driver);

		
						
		tourNumber = 1;
		toursPage.scrollUntilButtonVisible(tourNumber);	
		tourNameOnListPage = toursPage.getTourNameLinkText(tourNumber);
		toursPage.getTourNameLinkText(tourNumber); 
		toursPage.clickDetailsButton(tourNumber);
		
		Thread.sleep(1000);
		tourDetailsPage = new TourDetails(this.driver);
		
		if(tourDetailsPage.getPageTitle().contains(tourNameOnListPage))
		{
	System.out.println("IT'S A MATCH!");
		}
		
		tourDetailsPage.getPageTitle();
		tourDetailsPage.clickBookNowButton();
		
		Thread.sleep(1000);
		tourBookingPage = new TourBooking(this.driver);
		
		tourBookingPage.clickConfirmBookingButton();
		
		Thread.sleep(5000);
		
		invoicePage = new Invoice(this.driver);
		
		System.out.println(invoicePage.getPageTitle());
		
		driver.close();
		/*
		System.out.println("Go Back");
		this.driver.navigate().back();
		Thread.sleep(1000);
		toursPage = new Tours(this.driver);

		
						
		tourNumber = 4;
		toursPage.scrollUntilButtonVisible(tourNumber);	
		toursPage.getTourNameLinkText(tourNumber); 
		toursPage.clickDetailsButton(tourNumber);
		
		
	*/
		
//	}
	
// }

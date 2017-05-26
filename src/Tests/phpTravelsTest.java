package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.Account;
import Pages.Home;
import Pages.Invoice;
import Pages.Login;
import Pages.TourBooking;
import Pages.TourDetails;
import Pages.Tours;

public class phpTravelsTest {
	
	WebDriver driver;
	Login loginPage;
	Tours toursPage;
	TourDetails tourDetailsPage;
	TourBooking tourBookingPage;
	Invoice invoicePage;
	Account accountPage;
	Home homePage;
	
	static ExtentReports report;
	ExtentTest test;


	
	
	@BeforeClass public static void allTestsPrep()
	{
		report  = new ExtentReports("PHPTravelsExtentReport.html", true);
	}
	@AfterClass public static void allTestCleanUp()
	{
		report.flush();
	}
	@Before public void testPreparations()
	{

	}
	@After public void testCleanUp()
	{
		driver = null;
		loginPage = null;
		toursPage = null;
		tourDetailsPage = null;
		tourBookingPage = null;
		invoicePage = null;
		accountPage = null;
		homePage = null;
		test = null;
	}
	
	@Test public void loginBrowseToursAndBook() throws InterruptedException
	{
		
		test = report.startTest("User Flow 1 - Login, browse tours, book tour as user.");
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\geckodriver.exe");
		
		this.driver = new ChromeDriver();
		
		int tourNumber = 0;
		String tourNameOnListPage;
		
	//load login page
		test.log(LogStatus.INFO, "Browser started");
		this.driver.get("http://www.phptravels.net/login");
		
				
	//wait for Login Page to load
		Thread.sleep(1000);		
		loginPage = new Login(this.driver);
		
	//enter details and login
		test.log(LogStatus.INFO, "Logging In");
		loginPage.enterEmail("user@phptravels.com");
		loginPage.enterPassword("demouser");
		loginPage.clickLogin();
		
	//wait for Account Page to load 
		Thread.sleep(1000);		
		accountPage = new Account(this.driver);

		
	//assert if logged in
		if(accountPage.isLoggedIn())
		{
			test.log(LogStatus.PASS, "Logged in and account page loaded - verifying user welcome message as \"Hi, John Smith\"");
		}
		else
		{
			test.log(LogStatus.FAIL, "Loggin failed");
		}
		assertTrue(accountPage.isLoggedIn());
		
		test.log(LogStatus.INFO, "Navigate to Home Page");
		accountPage.clickHomepageLogo();
		
	//wait for Home Page to load
		Thread.sleep(1000);		
		homePage = new Home(this.driver);
		
		if(homePage.getPageTitle().equals("PHPTRAVELS | Travel Technology Partner"))
		{
			test.log(LogStatus.PASS, "Home Page loaded - verifyed by page title");
		}
		else
		{
			test.log(LogStatus.FAIL, "Not on Homepage");
		}
		assertEquals("PHPTRAVELS | Travel Technology Partner", homePage.getPageTitle());
		
		test.log(LogStatus.INFO, "Navigating to all tours listings");
		homePage.clickNavbarTours();	
		
	//wait for Tours Page to load
		Thread.sleep(1000);
		toursPage = new Tours(this.driver);	
		
	//assert that page is loaded by the page title
		if(toursPage.getPageTitle().equals("Tours Listings"))
		{
			test.log(LogStatus.PASS, "Tour listings page loaded - verifying Title of the page");
		}
		else
		{
			test.log(LogStatus.FAIL, "Not on tour listings page");
		}
		assertEquals("Tours Listings", toursPage.getPageTitle());
		
		test.log(LogStatus.INFO, "Selecting a tour");
	//listings initially set hiiden, JS flags them as visible when they appear in window. 
	//Simulate user scrolling down page gradually until correct tour is visible then click details button
		toursPage.scrollUntilButtonVisible(tourNumber);	
		
	//Get the name of the tour from the listing, for assert test on next page
		tourNameOnListPage = toursPage.getTourNameLinkText(tourNumber);
		toursPage.clickDetailsButton(tourNumber);

	//wait for Tour Details Page to load
		Thread.sleep(1000);
		tourDetailsPage = new TourDetails(this.driver);
		
	//assert that tour details page loaded is the one selected on the Listings page
		if(tourDetailsPage.getPageTitle().contains(tourNameOnListPage))
		{
			test.log(LogStatus.PASS, "Tour Details page loaded first time - verifying Title of the page matches previous listing");
		}
		else
		{
			test.log(LogStatus.FAIL, "Not on tour details page");
		}
		assertTrue(tourDetailsPage.getPageTitle().contains(tourNameOnListPage));
		
		
		tourDetailsPage.scrollUntilButtonVisible(tourDetailsPage.findBookNowButton());		
		Thread.sleep(1000);
		

	//return to previous page, wait for load, overwrite object with new instance of Tours page
		test.log(LogStatus.INFO, "Returning to all tour listings via back button");
		this.driver.navigate().back();
		Thread.sleep(1000);
		toursPage = new Tours(this.driver);

	//assert that page is loaded by the page title
		if(toursPage.getPageTitle().equals("Tours Listings"))
		{
			test.log(LogStatus.PASS, "Tour listings page loaded - verifying Title of the page");
		}
		else
		{
			test.log(LogStatus.FAIL, "Not on tour listings page");
		}
		assertEquals("Tours Listings", toursPage.getPageTitle());
				
		
	//select different tour, scroll to, get tour name, click button to load next page
		test.log(LogStatus.INFO, "Selecting a different tour");
		tourNumber = 1;
		toursPage.scrollUntilButtonVisible(tourNumber);	
		tourNameOnListPage = toursPage.getTourNameLinkText(tourNumber);
		toursPage.getTourNameLinkText(tourNumber); 
		toursPage.clickDetailsButton(tourNumber);
		
	//wait for Tour Details Page to load
		Thread.sleep(1000);
		tourDetailsPage = new TourDetails(this.driver);
		
	//assert that tour details page loaded is the one selected on the Listings page
		if(tourDetailsPage.getPageTitle().contains(tourNameOnListPage))
		{
			test.log(LogStatus.PASS, "Tour Details page loaded second time - verifying Title of the page matches previous listing");
		}
		else
		{
			test.log(LogStatus.FAIL, "Not on tour details page");
		}
		assertTrue(tourDetailsPage.getPageTitle().contains(tourNameOnListPage));	

		test.log(LogStatus.INFO, "Choosing to book tour");
		tourDetailsPage.clickBookNowButton();
		
	//wait for Bookings page to load
		Thread.sleep(1000);
		tourBookingPage = new TourBooking(this.driver);
		
	//verify booking page loaded
		if(tourBookingPage.checkConfirmBookButtonPresent())
		{
			test.log(LogStatus.PASS, "Bookings Page loaded - verifying booking button present");
		}
		else
		{
			test.log(LogStatus.FAIL, "Not on bookings page");
		}
		assertTrue(tourBookingPage.checkConfirmBookButtonPresent());
		test.log(LogStatus.INFO, "Confirming booking");
		
		tourBookingPage.clickConfirmBookingButton();
		
	//wait for booking to process, Invoice Page to load
		Thread.sleep(5000);		
		invoicePage = new Invoice(this.driver);
		
	//verify Invoice Page loaded
		if(invoicePage.getPageTitle().equals("Invoice"))
		{
			test.log(LogStatus.PASS, "Booking Made - verifying Invoice page loaded");
		}
		else
		{
			test.log(LogStatus.FAIL, "Have not reached invoice page - not booked");
		}
		assertEquals("Invoice", invoicePage.getPageTitle());
		
		test.log(LogStatus.INFO, "User flow finished");
		report.endTest(test);

		driver.close();
		
	}
	
	@Test public void guestSearchToursAndBook() throws InterruptedException
	{

		test = report.startTest("User Flow 2 - Search specific tour, view tour, book as guest.");
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Administrator\\Desktop\\Selenium\\Selenium\\geckodriver.exe");		
		this.driver = new ChromeDriver();
		
	//go to homepage to start
		test.log(LogStatus.INFO, "Browser started");
		this.driver.get("http://www.phptravels.net/");
		
	//wait for page to load and create Home Page object
		Thread.sleep(1000);
		homePage = new Home(this.driver);
		
		if(homePage.getPageTitle().equals("PHPTRAVELS | Travel Technology Partner"))
		{
			test.log(LogStatus.PASS, "Home Page loaded - verifyed by page title");
		}
		else
		{
			test.log(LogStatus.FAIL, "Not on home page");
		}
		assertEquals("PHPTRAVELS | Travel Technology Partner", homePage.getPageTitle());
		
		
	//Click Tour button on search bar, click the search field to engage the JS loading of input field, enter text, click the inline search result, click search button
		test.log(LogStatus.INFO, "Searching for specific tour");
		homePage.clickSearchbarTours();
		Thread.sleep(1000);
		homePage.clickSearchbarToursField();
		homePage.enterSearchText("new");  // Search term should return the "Old and New Delhi City Tour" product
		homePage.clickSearchbarToursSubmit();
		
	//wait for page to load and create Tour Details Page object
		Thread.sleep(1000);
		tourDetailsPage = new TourDetails(this.driver);
		
	//verify the page loaded is for the expected product
		if(tourDetailsPage.getPageTitle().equals("Old and New Delhi City Tour"))
		{
			test.log(LogStatus.PASS, "Tour Details page loaded - verifying Title of the page matches intended search");
		}
		else
		{
			test.log(LogStatus.FAIL, "Not on tour details page");
		}
		assertEquals("Old and New Delhi City Tour", tourDetailsPage.getPageTitle());
		test.log(LogStatus.INFO, "Viewing tour and deciding to book");
		tourDetailsPage.clickBookNowButton();		
		
	//wait for page to load and create Tour Bookings Page object
		Thread.sleep(1000);
		tourBookingPage = new TourBooking(this.driver);
		
	//verify booking page loaded
		if(tourBookingPage.checkConfirmBookButtonPresent())
		{
			test.log(LogStatus.PASS, "Bookings Page loaded - verifying booking button present");
		}
		else
		{
			test.log(LogStatus.FAIL, "Not on bookings page");
		}
		assertTrue(tourBookingPage.checkConfirmBookButtonPresent());
	//Click the book as guest button, wait for JS to display details form, input details, click book button
		test.log(LogStatus.INFO, "Confirming booking by entering guest details");
		tourBookingPage.clickGuestBookingSelection();
		Thread.sleep(500);
		tourBookingPage.enterDetails("Bob", "Bobbins", "fake@nullandvoid-addyplace.com");
		tourBookingPage.clickGuestConfirmBookingButton();
		
	//wait for booking to process, Invoice Page to load
		Thread.sleep(5000);		
		invoicePage = new Invoice(this.driver);
		
	//verify Invoice Page loaded
		if(invoicePage.getPageTitle().equals("Invoice"))
		{
			test.log(LogStatus.PASS, "Booking Made - verifying Invoice page loaded");
		}
		else
		{
			test.log(LogStatus.FAIL, "Have not reached invoice page - not booked");
		}
		assertEquals("Invoice", invoicePage.getPageTitle());
		test.log(LogStatus.INFO, "User flow finished");
		
		report.endTest(test);	
		driver.close();
	}

}

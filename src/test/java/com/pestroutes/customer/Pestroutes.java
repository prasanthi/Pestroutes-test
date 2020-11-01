package com.pestroutes.customer;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Pestroutes {
	public static WebDriver driver;
	@Given("I sign in to pestroutes domain")
	public void sign_in_to_pestroutes_domain() throws Throwable {
		// Create a Chrome driver
		System.setProperty("webdriver.chrome.driver","resources/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Navigate to a Pestroutes page
		driver.get("https://qatest.pestroutes.com/");
		// Input Username and Password
		driver.findElement(By.id("inputUser")).sendKeys("interview_test");
        driver.findElement(By.id("inputPassword")).sendKeys("1235252");
        // Click on Login button
        driver.findElement(By.className("grayButton")).submit();
        // After Login make sure it navigates to overview page
        driver.findElement(By.cssSelector("#guestNav > div:nth-child(1) > a"));
	}

	@When("I create customer with {string}, {string}, and {string}")
	public void create_customer(String firstname, String lastname, String address) throws Throwable {
	    // Click on new customer button
		driver.findElement(By.cssSelector("#guestNav > div:nth-child(1) > a")).click();
		driver.findElement(By.className("customerPanel"));
		// Input first name and Last name
		driver.findElement(By.xpath(".//input[@name='fname']")).sendKeys(firstname);
		driver.findElement(By.xpath(".//input[@name='lname']")).sendKeys(lastname);
		// Split address -> area, zipcode
	    String[] address_split = address.split(",");
	    String area = address_split[0];
	    String zipcode = address_split[1];
	    // Input address
		driver.findElement(By.xpath(".//input[@name='address']")).sendKeys(area);
		driver.findElement(By.xpath(".//input[@name='zip']")).sendKeys(zipcode);
		// Validate populated address fields based on address entered
		String city = driver.findElement(By.xpath(".//input[@name='city']")).getAttribute("value");
		String state = driver.findElement(By.xpath(".//select[@name='state']")).getAttribute("value");
		String county = driver.findElement(By.xpath(".//select[@name='county']")).getAttribute("value");
		String countryID = driver.findElement(By.xpath(".//select[@name='countryID']")).getAttribute("value");
		if((city == "Dallas")&&(state == "TX")&&(county == "Collin")&&(countryID == "UnitedStates")) {
			System.out.println("Success");
		}
		// Click on save button
		driver.findElement(By.id("globalCustomerSaveButton")).click();
		// Check for overview tab
		driver.findElement(By.tagName("h3")).getText().equals("Account Overview");
	}

	@Then("I validate if customer name and address match in overview tab")
	public void validate_customer_details_in_overview_tab() {
		// Validate customer first and last name
		driver.findElement(By.className("ui-dialog-titlebar ")).getText().equals("Interview Test");
		// Validate address
		driver.findElement(By.tagName("div")).getText().equals("7575 frankford road, 75252");
		// close the browser
        driver.close();
        driver.quit();
	}

}

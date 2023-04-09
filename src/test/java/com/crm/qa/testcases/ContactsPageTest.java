package com.crm.qa.testcases;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	   
	public ContactsPageTest(){
			super();
			
	}
	
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
		}
	
	@Test(priority=1)
	public void verifyContactsPageLabel(){
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "contacts label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectContactsName() {
		contactsPage.selectContacts("test1 Test");
		
	}
	@Test(priority=3)
	public void selectmultipleContactsName() {
		contactsPage.selectContacts("test1 Test");
		contactsPage.selectContacts("Selmiya Mubashir");
	}
	@DataProvider
	public Object[][] getCRMData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
			
	}
	@Test(priority=4, dataProvider= "getCRMData")
	public void validateCreateNewContact(String title, String name, String Lastname, String company) {
		homePage.clickOnNewContactLink();
		//contactsPage.createNewcontacts("Mr.", "Man", "Mun", "PaintHouse");
		contactsPage.createNewContact(title, name, Lastname, company);
	}
	
	@AfterMethod  
	public void tearDown() {
		driver.quit();
	}
	
	
	
	
}

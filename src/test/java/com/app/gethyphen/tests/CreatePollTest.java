package com.app.gethyphen.tests;

import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.Test;
import com.app.gethyphen.testPages.CreatePollPage;
import com.app.gethyphen.testPages.LoginPage;
import com.app.gethyphen.testbase.TestBase;

public class CreatePollTest extends TestBase{
	
	private String userType = "Anonymous";
	private String pollType = "OPEN";
	private String groupType = "SF Bay Area";
	private String pollText = "Test 102";
	
	@Test(groups={"createPoll"}, dependsOnGroups = {"login"})
	public void startPoll() {
		
		CreatePollPage poll = PageFactory.initElements(driver, CreatePollPage.class);
		
		try {
			System.out.println(driver);
			poll.createPoll();
			poll.selectPollTypeUser(userType);
			poll.createPollType(pollType);
			poll.selectGroup(groupType);
			poll.clickSelect();
			poll.enterText(pollText);
			poll.publishPost();
			poll.logout();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	//("createPoll")
	@AfterGroups ("createPoll")
	public void logout() {
		CreatePollPage homepage = PageFactory.initElements(driver, CreatePollPage.class);
		try {
			homepage.logout();
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			loginPage.verifyLoginPage();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}

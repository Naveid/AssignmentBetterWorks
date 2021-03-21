package com.app.gethyphen.testPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CreatePollPage extends BaseTestPage{
	
	WebDriver driver;
	
	//to get options under Settings menu
	private String setOptions = "div[class='item-popover-option']";


	//@FindBy(css = "button[id='create-button']")
	@FindBy(id = "create-button")
	WebElement createBtn;
	
	@FindBy(css = "label[class^='ant-radio-wrapper']:contains('Anonymous')")
	WebElement radioBtnAnonymous;
	
	//@FindBy(css = "button[type='button']:contains('OPEN')")
	@FindBy(xpath = "//button[contains(.,'OPEN')]|//button[contains(.,'open')]")
	WebElement openBtn;

	// drop down to select 
	@FindBy(className="//input[@role='combobox']")
	WebElement selGroupComboBox;
	
	// Select button
	@FindBy(css = "button[type='submit']")
	WebElement selectBtn;
	
	//text field for post
	@FindBy(id="textAreaField")
	WebElement textArea;
	
	//Publish post
	@FindBy(css = "button[type='submit']")
	WebElement publishBtn;
	
	//Setting
	@FindBy(css = "span[id='settings-popover']")
	WebElement settings;
	
	//Setting menu
	@FindBy(css = "div[class^='popover-inner']")
	WebElement settingMenu;
	
	public CreatePollPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void createPoll() throws Exception{
		createBtn = wait(driver, 4, createBtn);
		if(createBtn.isEnabled()) {
			createBtn.click();
		}
		
	}
	
	// select user type - Anonymous, Official or Named
	public void selectPollTypeUser(String type) throws Exception{
		
		// check what type of poll is passed and click on radio button
		if(type.toLowerCase()=="anonymous") {
			radioBtnAnonymous = wait(driver, 4, radioBtnAnonymous);
			if(radioBtnAnonymous.isEnabled()) {
				radioBtnAnonymous.click();
			}
		}
	}
	
	// select poll choice of OPEN or Multiple Choice
	public void createPollType(String pollChoice) throws Exception{
		
		// check what type of poll is passed and click on radio button
		if(pollChoice.toUpperCase()=="OPEN") {
			//openBtn = wait(driver, 4, openBtn);
			if(openBtn.isEnabled()) {
				openBtn.click();
			}
		}
	}
	
	//select Group value from drop down
	public void selectGroup(String group) throws Exception{
		// wait for drop down to be enabled


		selGroupComboBox = wait(driver, 4, selGroupComboBox);
		selGroupComboBox = wait(driver, 4, selGroupComboBox);
		selGroupComboBox.sendKeys(group);
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.DOWN).build().perform();//press down arrow key
		actions.sendKeys(Keys.ENTER).build().perform();//press enter
	}



	public void clickSelect() throws Exception{
		if(selectBtn.isEnabled()) {
			selectBtn.click();
		}
	}
	
	// enter the poll text
	public void enterText(String text) throws Exception{
		textArea = wait(driver, 4, textArea);
		if(textArea.isEnabled()) {
			textArea.click();
			textArea.sendKeys(text);
		}
	}
	
	//click Publish post
	public void publishPost() throws Exception{
		if(publishBtn.isEnabled()) {
			publishBtn.click();
		}
	}
	//[class^='questionText']:contains('Test 101')
	//[class^='actionPanelShadow']>div[class^='verticalCentered row']>div[class^='col-xs-collapse-right']>div>span>span:contains('Test 101')
	
	// this method click on Setting -> Logout
	public void logout() throws Exception{

		//wait for Setting button to be present
		settings = wait(driver, 4, settings);
		
		if(settings.isEnabled()) {
			settings.click();
			implicitWait(driver, 2);
			
			//get all the items from Setting menu
			List<WebElement> list = driver.findElements(By.cssSelector(setOptions));
			
			//check for Logout option and click
			for(WebElement ele : list) {
				String logout = ele.getText().toString().toLowerCase();
				if(logout.equalsIgnoreCase("logout")) {
					// click logout
					ele.click();
				}
			}
		}
	}
}

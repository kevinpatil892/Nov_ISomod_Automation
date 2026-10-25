package com.isomod.pomclass; 

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	 public WebDriver driver;
	
	 @FindBy(xpath="//input[@id='user-name']")
     private WebElement userid;
	 
	 @FindBy(xpath="")
	 private List<WebElement> table;  //use for multiple elements fetching 
	 
	 @FindBy(xpath="//input[@id='password']")
     private WebElement password;
	 
	 @FindBy(xpath="//input[@id='login-button']")
     private WebElement loginbutton;
	 
	 @FindBy(xpath="//div[text()='Swag Labs']")
	 private WebElement pagetext;
	 
	 public LoginPage(WebDriver driver)
	 {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 public void usermethod(String user)
	 {
		 userid.sendKeys(user);
	 }
	 
	 public void passmethod(String pass)
	 {
		 password.sendKeys(pass);
	 }
	 
	 public void loginmethod()
	 {
		 loginbutton.click();
	 }
	 
	 public void textmethod()
	 {
			String text= pagetext.getText();
			System.out.println("Loginpage text is "+text);
	 }
}

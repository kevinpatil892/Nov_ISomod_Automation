package com.isomod.pomclass;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class HomePage {
	public WebDriver driver;

	@FindBy(xpath ="//button[@id='react-burger-menu-btn']")
	private WebElement menubutton;

	@FindBy(xpath = "//a[@id='logout_sidebar_link']")
	private WebElement logoutbutton;

	@FindBy(xpath = "//a[@id='about_sidebar_link']")
	private WebElement about;

	@FindBy(xpath = "(//html//body//button)[4]")
	private WebElement singleitem;

	@FindBy(xpath = "//html//body//button")
	private List<WebElement> allitem;

	@FindBy(xpath = "//html//body//select")
	private WebElement filter;

	@FindBy(xpath = "(//html//a//div)[1]")
	private WebElement verifyfilter;

	@FindBy(xpath = "(//html//body//div//a)[5]")
	private WebElement cartbutton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void menumethod() {
		menubutton.click();
	}

	public void logoutmethod() {
		logoutbutton.click();
	}

	public void aboutmethod() {
		about.click();
	}

	public void singleitemmethod() {
		singleitem.click();
	}

	public String getremovetext() {
		String removetext = singleitem.getText();
		return removetext;
//		  String acttext="Remove";
//		  Assert.assertEquals("failed", itemtext, acttext);
	}

	public void allitemadd() {
		for (int i = 2; i < allitem.size(); i++) {
			allitem.get(i).click();
		}
		System.out.println("All item added to cart");
	}

	public void allitemtext() {
		for (int i = 2; i < allitem.size(); i++) {

			if (allitem.get(i).getText().contains("Remove")) {
				System.out.println("test case passed");
			} else {
				System.out.println("failed");
			}
		}

	}

	public void filtermethod() {
		filter.click();
	}

	public void lowtohigh_method() throws InterruptedException {
		Select s = new Select(filter);
		s.selectByVisibleText("Price (low to high)");
		System.out.println("Filter applied");

		Thread.sleep(2000);
		String exptext = "Sauce Labs Onesie";
		String acttext = verifyfilter.getText();

		Assert.assertEquals("failed", exptext, acttext);

	}

	public void clickcartmethod() {
		cartbutton.click();
		System.out.println("moved to cart page");
	}
}

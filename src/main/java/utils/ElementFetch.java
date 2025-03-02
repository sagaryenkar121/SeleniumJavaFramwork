package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import base.baseTest;

public class ElementFetch {

	public JavascriptExecutor driver;

	public WebElement getWebElement(String indentifierType, String indentifierValue) {

		switch (indentifierType) {

		case "XPATH":
			return baseTest.driver.findElement(By.xpath(indentifierValue));

		case "CLASSNAME":
			return baseTest.driver.findElement(By.className(indentifierValue));

		case "CSSSelector":
			return baseTest.driver.findElement(By.cssSelector(indentifierValue));

		case "ID":
			return baseTest.driver.findElement(By.id(indentifierValue));

		case "LINKTEXT":
			return baseTest.driver.findElement(By.linkText(indentifierValue));

		case "NAME":
			return baseTest.driver.findElement(By.name(indentifierValue));

		case "PARTIALLINKTEXT":
			return baseTest.driver.findElement(By.partialLinkText(indentifierValue));

		case "TAGNAME":
			return baseTest.driver.findElement(By.tagName(indentifierValue));

		default:
			return null;

		}

	}

	public List<WebElement> getWebElements(String indentifierType, String indentifierValue) {

		switch (indentifierType) {

		case "XPATH":
			return baseTest.driver.findElements(By.xpath(indentifierValue));

		case "CLASSNAME":
			return baseTest.driver.findElements(By.className(indentifierValue));

		case "CSSSelector":
			return baseTest.driver.findElements(By.cssSelector(indentifierValue));

		case "ID":
			return baseTest.driver.findElements(By.id(indentifierValue));

		case "LINKTEXT":
			return baseTest.driver.findElements(By.linkText(indentifierValue));

		case "NAME":
			return baseTest.driver.findElements(By.name(indentifierValue));

		case "PARTIALLINKTEXT":
			return baseTest.driver.findElements(By.partialLinkText(indentifierValue));

		case "TAGNAME":
			return baseTest.driver.findElements(By.tagName(indentifierValue));

		default:
			return null;

		}
	}
}

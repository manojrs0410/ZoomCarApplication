package org.ZoomCar.seleniumbase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.ZoomCar.seleniumdesign.ZoomCar_Browser;
import org.ZoomCar.seleniumdesign.ZoomCar_Element;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Reporter;

public class ZoomCar_SeleniumBase extends Reporter implements ZoomCar_Element, ZoomCar_Browser {
	public WebDriverWait wait;    
	public static RemoteWebDriver driver;


	public void invokeApp(String browser, String url) {
		 try {
	            if(browser.equalsIgnoreCase("chrome")) {
	                driver = new ChromeDriver();
	            }else if(browser.equalsIgnoreCase("firefox")) {
	                driver = new FirefoxDriver();
	            }
	            driver.get(url);
	            driver.manage().window().maximize();
	            Thread.sleep(5000);
	            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
	        } catch (Exception e){
	         System.err.println(e);    
	        }
		
	}

	public void closeApp() {
		 try {
	            driver.close();
	        }catch (Exception e){
	             System.err.println(e);  
	             }
	}

	public void click(WebElement element) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        	wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
            reportStep("Clicked on the element", "pass");

        }catch(Exception e) {
            reportStep("Unable to click on the element", "pass");
            System.err.println(e);
        }
	}
	
	public void enterText(WebElement element, String data) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        	wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(data);
            reportStep("Entered "+data+" in the textbox", "pass");


        }catch(Exception e) {
        	reportStep("Unable to enter "+data+" in the textbox", "fail");
        	System.err.println(e);
        }
	}
	
	@Override
	public long takeSnap() {
        long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
        try {
        	File source = driver.getScreenshotAs(OutputType.FILE); 
        	FileUtils.copyFile(source, new File("./reports/images/"+number+".jpg"));
        } catch (WebDriverException e) {
            System.out.println("The browser has been closed.");
        } catch (IOException e) {
            System.out.println("The snapshot could not be taken");
        }
        return number;
    }

	public void tabKey(WebElement element, String data) {
		// TODO Auto-generated method stub
		
	}
	
	public void leftArrowKey(WebElement element) {

		element.sendKeys(Keys.ARROW_LEFT);		
		
	}
	
	public void rightArrowKey(WebElement element) {

		element.sendKeys(Keys.ARROW_RIGHT);
		
	}


	public void mouseOver(WebElement element) {
		// TODO Auto-generated method stub
		
	}

	public void enterKey(WebElement element) {
		// TODO Auto-generated method stub
		
	}

}


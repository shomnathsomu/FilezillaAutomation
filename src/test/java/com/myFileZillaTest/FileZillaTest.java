package com.myFileZillaTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.windows.WindowsDriver;

public class FileZillaTest {

	
	public static WindowsDriver<WebElement> driver = null;
	
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("app", "C:\\Program Files\\FileZilla FTP Client\\filezilla.exe");
		capabilities.setCapability("platformName", "Windows");
		capabilities.setCapability("deviceName", "WindowsPC");
		
		try {
			driver = new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723/"), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterMethod
    public static void quitDriver() {
        driver.closeApp();
        // driver.quit();
    }
	
	@Test(priority = 1, description = "Connect to a server")
    public void testFtpConnection() {
        WebElement host = driver.findElementByAccessibilityId("-31830");
        host.sendKeys(Credentials.gethostFtp());

        WebElement username = driver.findElementByAccessibilityId("-31829");
        username.sendKeys(Credentials.getusernameFtp());

        WebElement pass=driver.findElement(By.xpath("//Edit[@ClassName='Edit'][@Name='Password:']"));

        //WebElement password = driver.findElementByName("Password:");
        pass.click();
        pass.sendKeys(Credentials.getpasswordFtp());

        WebElement port = driver.findElement(By.xpath("//Edit[@ClassName='Edit'][@Name='Port:']"));
        port.sendKeys(Credentials.getportFtp());

        WebElement quickconnect = driver.findElementByAccessibilityId("-31944");
        quickconnect.click();
        
        // assert that the Not connected element is not present
        try {
            driver.findElement(By.name("Not connected."));
            Assert.assertTrue(false, "FTP connection failed"); // Use assertTrue to fail the test
        } catch (NoSuchElementException e) {
            // element is not present, test passes
            System.out.println("Connected to FTP server");
        }
        
    }
	
	@Test(priority = 2, description = "Traverse FileZilla")
    public void traverseMenu() {
        WebElement file = driver.findElement(By.name("File"));
        file.click();

        WebElement edit = driver.findElement(By.name("Edit"));
        edit.click();

        WebElement view = driver.findElement(By.name("View"));
        view.click();

        WebElement transfer = driver.findElement(By.name("Transfer"));
        transfer.click();

        WebElement server = driver.findElement(By.name("Server"));
        server.click();

        WebElement bookmarks = driver.findElement(By.name("Bookmarks"));
        bookmarks.click();

        WebElement help = driver.findElement(By.name("Help"));
        help.click();

        WebElement about = driver.findElement(By.name("About..."));
        about.click();

        // assert that the Not connected element is not present
        try {
            WebElement platformvalue = driver.findElement(By.name("64-bit system"));
            platformvalue.click();
        } catch (NoSuchElementException e) {
            Assert.fail("Platform value is not present");
        }

        WebElement okButton = driver.findElementByAccessibilityId("-31746");
        okButton.click();
    }
}

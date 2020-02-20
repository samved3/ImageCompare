package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

public class Basepage {
	static WebDriver driver;
	static String firefoxPath = "./driver/geckodriver.exe";
	static String ChromeexePath = "./driver/chromedriver80.exe";
	public static void launchwithchrome()

	 {
		 
		 System.setProperty("webdriver.chrome.driver", ChromeexePath);
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
//		  driver.get("https://www.google.com");
	 }
	
	public static void launchwithfirefox()
	{
		 System.setProperty("webdriver.gecko.driver", firefoxPath);
//		 DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//	        capabilities.setCapability("marionette", true);
	        driver = new FirefoxDriver();
	}
	public static void close()
{
	driver.close();
	}
public static void takescreenshot(String urltovisit)
{
	String name="";
	name=urltovisit.replaceAll("[/|:|.|?]", "_");
	if(name.length()>50)
	{
		name=name.substring(0,20);
	}

	driver.get(urltovisit);
	 Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE,true).withName(name).save("./screenshots");
	 
}

public static void comparescreenshot(String urltovisit,String expectedImageLocation) throws IOException

{driver.get(urltovisit);
String name="";
name=urltovisit.replaceAll("[/|:|.|?]", "_");
if(name.length()>50)
{
	name=name.substring(0,20);
}
	File expected=new File (expectedImageLocation);
BufferedImage expectedImageBuffered = ImageIO.read(expected);
	  Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE,true).equalsWithDiff(expectedImageBuffered,"./DifferenceImage/"+name);
	}
 
public static void comparescreenshotwithdeviation(String urltovisit,String expectedImage) throws IOException
{driver.get(urltovisit);
String name="";
name=urltovisit.replaceAll("[/|:|.|?]", "_");
if(name.length()>50)
{
	name=name.substring(0,20);
}
File expected=new File (expectedImage);
BufferedImage expectedImageBuffered = ImageIO.read(expected);
Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE,true).equalsWithDiff(expectedImageBuffered,"./DifferenceImage/"+name,0.9);
	
	}
	

}

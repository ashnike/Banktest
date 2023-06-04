package bankftest;

	import java.io.File;
	import java.io.IOException;
	import java.util.concurrent.TimeUnit;

	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.By;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;

	public class App {
	    public static void main(String[] args) throws InterruptedException, IOException {
	        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

	        ChromeOptions chromeOptions = new ChromeOptions();
	        chromeOptions.addArguments("--headless");
	        chromeOptions.addArguments("--no-sandbox");
	        chromeOptions.addArguments("--disable-dev-shm-usage");
	        chromeOptions.addArguments("--remote-allow-origins=*");
	        WebDriver driver = new ChromeDriver(chromeOptions);

	        driver.get("http://52.66.24.126:8081/contact.html");
	        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	        driver.findElement(By.cssSelector(".mail_section input[name='Name']")).sendKeys("Ashnil");
	        driver.findElement(By.cssSelector(".mail_section input[name='Phone Number']")).sendKeys("9999999999");
	        driver.findElement(By.cssSelector(".mail_section input[name='Email']")).sendKeys("ashnil@gmail.com");
	        driver.findElement(By.cssSelector(".mail_section textarea[name='Message']")).sendKeys("I have a query for money");

	        WebElement sendButton = driver.findElement(By.cssSelector(".send_bt a"));
                sendButton.click();


	        String message = driver.findElement(By.cssSelector(".send_bt p")).getText();
	        if (message.equals("Email Sent")) {
	            System.out.println("Script executed successfully");
	        } else {
	            System.out.println("Script failed");
	        }

	        System.out.println("Taking Screenshot as proof");
	        TakesScreenshot scrShot = ((TakesScreenshot) driver);
	        File screenShot = scrShot.getScreenshotAs(OutputType.FILE);
	        File destFile = new File("screenshot.png");
	        FileUtils.copyFile(screenShot, destFile);
	        System.out.println("Reports stored at: " + destFile.getAbsolutePath());

	        Thread.sleep(3000);
	        driver.quit();
	    }
	}

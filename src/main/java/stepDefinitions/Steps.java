package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class Steps {
	WebDriver driver;
	String username = "foo@bar.com"; //insert your username or email here
	String password = "foobar"; //insert your password here
	String wrongpassword = "foofoobar";//insert anything here
	
	// @Before Hooks are started before each scenario
	@Before
    public void beforeScenario(){
		// open geckodriver to open up mozilla firefox
	    System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.31.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// go to stockbit.com/login
		driver.get("https://stockbit.com/login");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
	// After each scenario ends, @After will start
	@After
    public void afterScenario(){
		driver.close();
    }
	
	@Given("I am on login page")
	public void i_am_on_login_page() {
		// check if we are on the login page
		driver.findElement(By.xpath("//button[@id='email-login-button']"));
	}

	@When("I insert my correct credentials")
	public void i_insert_my_correct_credentials() {
	    // insert correct username and password
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
	}

	@When("I insert my incorrect credentials")
	public void i_insert_my_incorrect_credentials() {
	    // insert either incorrect username and password. in this case i used wrong password.
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(wrongpassword);
	}

	@When("I clicked log in")
	public void i_clicked_log_in() {
	    // click the login button by searching the xpath
		driver.findElement(By.xpath("//p[contains(text(),'Login')]")).click();
		
		// wait for three seconds to wait the page completely loads
	    System.out.println("wait for 3 secs");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@Then("Login failed")
	public void login_failed() {
	    // find the wrong credentials alert
		driver.findElement(By.xpath("//div[@class='alert-red']"));
	}

	@Then("Login successfully")
	public void login_successfully() {
	    // wait for ten seconds to wait the page to completely loads up
	    System.out.println("wait for 10 secs");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {			
			// check if there are modal page (popup), e.g: new member popup to change profile picture
			if(driver.findElements(By.xpath("//div[@class='ant-modal-mask']")).size()>0) {
				// if there are popup, close the popup
				System.out.println("there are popup showing");
				driver.findElement(By.xpath("//body[@style='padding-right: 17px; overflow: hidden;']/div/div/div[@class='ant-modal-wrap announcement-modal']/div[@class='ant-modal']/div[@class='ant-modal-content']/div[@class='ant-modal-footer']/button[1]")).click();
			    System.out.println("skip clicked");
			    // after popup close up 
			    driver.findElement(By.xpath("//i[contains(text(),'Stream')]"));
			    System.out.println("successfully logged in");
			}
		} catch (UnhandledAlertException e){
			// if there are no popup, check if there are text that contains Stream
		}
		driver.findElement(By.xpath("//i[contains(text(),'Stream')]"));
	}
	
	@When("I post a comment")
	public void i_post_a_comment() {
	    // check if we can click the compose write or its obscured by modal mask
		try {
			driver.findElement(By.xpath("//div[@class='stream-compose-write']")).click();
		} catch (WebDriverException e) {
			//if it is, then using executeScript to click on the javascript button directly
		    System.out.println("wait for 10 secs");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement element = driver.findElement(By.xpath("//div[@class='stream-compose-write']"));
			((RemoteWebDriver) driver).executeScript("arguments[0].click();", element);
			System.out.println("successfully clicked the text area");
		}
		// check if we can click the text area or its obscured by modal mask
		try {
			driver.findElement(By.xpath("//textarea[@id='composetextarea']")).click();
		} catch (WebDriverException e) {
			//if it is, then using executeScript to click on the javascript button directly
		    System.out.println("wait for 15 secs");
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			WebElement element = driver.findElement(By.xpath("//textarea[@id='composetextarea']"));
			((RemoteWebDriver) driver).executeScript("arguments[0].click();", element);
			System.out.println("successfully clicked the text area");
		}
		// random string to write to the comment
		String rand = RandomStringUtils.random(10);
		driver.findElement(By.xpath("//textarea[@id='composetextarea']")).sendKeys(rand);
		// check if we can click the button or its obscured by modal mask
		try {
			driver.findElement(By.xpath("//button[contains(text(),'Post')]")).click();
		} catch (WebDriverException e) {
			//if it is, then using executeScript to click on the javascript button directly
		    System.out.println("wait for 15 secs");
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Post')]"));
			((RemoteWebDriver) driver).executeScript("arguments[0].click();", element);
			System.out.println("successfully clicked the post button");
		}
		
	}

	@Then("Comment successfully posted")
	public void comment_successfully_posted() {
	    // check if there are successfull notification popup
		driver.findElement(By.xpath("//span[contains(text(),'Post successful')]"));
	}
	
	@When("I go to my profile")
	public void i_go_to_my_profile() {
		// check if we can click the profile button or its obscured by modal mask
		try {
			driver.findElement(By.xpath("//span[@class='headermenu user']//a[@class='ant-dropdown-link']")).click();
		} catch (WebDriverException e) {
			//if it is, then using executeScript to click on the javascript button directly
		    System.out.println("wait for 15 secs");
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			WebElement element = driver.findElement(By.xpath("//span[@class='headermenu user']//a[@class='ant-dropdown-link']"));
			((RemoteWebDriver) driver).executeScript("arguments[0].click();", element);
			System.out.println("successfully clicked the post button");
		}
		driver.findElement(By.xpath("//body/div[@style='position: absolute; top: 0px; left: 0px; width: 100%;']/div/div[@class='ant-popover popoveruser ant-popover-placement-bottomRight']/div[@class='ant-popover-content']/div[@class='ant-popover-inner']/div/div[@class='ant-popover-inner-content']/div/div[@class='panel-box']/ul[@class='ant-menu ant-menu-vertical profile-user ant-menu-light ant-menu-root']/li[1]/ul[1]/li[1]")).click();
	}

	@When("I delete my latest comment")
	public void i_delete_my_latest_comment() {
	    // given that we have already written some stream comment, we will delete our latest stream
	    System.out.println("wait for 10 secs");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[4]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[5]/div[1]/div[1]/div[1]/div[2]/div[1]/span[2]")).click();
		System.out.println("wait for 3 secs");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='ant-popover popoverstream mainstream ant-popover-placement-bottomRight']//div[@class='ant-popover-content']//div[@class='ant-popover-inner']//div//div[@class='ant-popover-inner-content']//div//div[contains(@class,'popover-mainstream')]//div[contains(@class,'item-stream-popover-item')]//span[@class='popover-item-title'][contains(text(),'Delete Post')]")).click();
		driver.findElement(By.xpath("//button[@class='Modal-Delete-Own-Stream__delete']")).click();
	}
	
	@Then("^Stream comment will be deleted$")
	public void stream_comment_will_be_deleted() throws Throwable {
	    // check if there are deleted notification popup
		driver.findElement(By.xpath("//span[@class='text-message-alert']"));
	}

	
}

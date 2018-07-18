package edureka;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewTest {
	WebDriverWait wait;
	private WebDriver driver;

	@Test(priority=0)
	public void testEasy() throws IOException {
		driver.get("https://www.edureka.co/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	public String excelReading(int rowNum,int colNum) throws Exception {
		FileInputStream fis=new FileInputStream("C:\\Devbrat\\EdurekaTesting\\Testdata\\Testdata.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet("FIleUpload");
		Row row=sheet.getRow(rowNum);
		row.getCell(colNum).getStringCellValue();
		return row.getCell(colNum).getStringCellValue();		
	}
	@Test(priority=1)
	public void exceldata() throws Exception {
		driver.findElement(By.linkText("Log In")).click();
		driver.findElement(By.id("inputName")).sendKeys(excelReading(1,0));
		driver.findElement(By.id("pwd1")).sendKeys(excelReading(1,1));
		driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
		System.out.println("Data read successfully");
	}
	@Test(priority=2)
	public void actions() throws Exception {
		driver.findElement(By.xpath("//li[@class='dropdown userdrop hidden-sm hidden-xs']//span[@class='webinar-profile-name']")).click();
		driver.findElement(By.linkText("My Profile")).click();
		wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='personal_details']")));
		driver.findElement(By.xpath("//a[@id='personal_details']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='profile-photo-section']/a/i")));	
		WebElement element=driver.findElement(By.xpath("//div[@class='profile-photo-section']/a/i"));
		JavascriptExecutor executor=(JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		//Actions act = new Actions(driver);
		//act.moveToElement(driver.findElement(By.xpath("//div[@class='profile-photo-section']/a/i"))).click().build().perform();
		//driver.findElement(By.xpath("//div[@class='profile-photo-section']/a/i")).click();
		Thread.sleep(2000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-hidden='false']//div[@class='file-upload']/span")));
		//WebElement element1=driver.findElement(By.xpath("//div[@aria-hidden='false']//div[@class='file-upload']/span"));
		//element1.click();
		//executor.executeScript("arguments[0].click();", element1);
		//driver.findElement(By.xpath("//span[text()='Choose File']")).click();
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//div[@aria-hidden='false']//div[@class='file-upload']/span"))).click().build().perform();
		System.out.println("Clicked on upload a file");
		String filepath = "automation-process.jpg";
		System.out.println(filepath);
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "/Testdata/fileupload.exe" + " "+ filepath);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Upload'][not(contains(@class, 'disabled'))]")));
		driver.findElement(By.xpath("//button[text()='Upload'][not(contains(@class, 'disabled'))]")).click();
	}
	@BeforeTest
	public void beforeTest() {
		System.setProperty("WebDriver.chrome.driver", "/Edureka/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Browser launched successfully");
	}

	@AfterTest
	public void afterTest() {
		//driver.quit();
	}
}
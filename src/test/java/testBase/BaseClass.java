package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import utilities.ExtentReporterManager;

public class BaseClass 
{
	public static WebDriver driver;
	public Properties prop;
	@SuppressWarnings("deprecation")
	@BeforeClass(alwaysRun= true)
	//@BeforeClass(groups= {"sanity","Regression"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		FileReader file = new FileReader("./src/test/resources/config.properties");
		prop = new Properties();
		prop.load(file);
		
		if(prop.getProperty("execution_environment").equalsIgnoreCase("remote"))
		{
			System.out.println("------------------remote--------------------------");
			DesiredCapabilities ca= new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows"))
			{	
				ca.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				ca.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("no matching os found");
			}
			
//			switch(br.toLowerCase())
//			{	
//				
//				case "chrome" :driver= new ChromeDriver(); break;
//				case "edge" :driver= new EdgeDriver(); break;
//				case "firefox" :driver= new FirefoxDriver(); break;
//				default : System.out.println("invalid browser"); return;			
//			}
			switch(br.toLowerCase())
			{
				case "chrome" : ca.setBrowserName("chrome"); break;
				case "edge" : ca.setBrowserName("edge"); break;
				case "firefox" : ca.setBrowserName("firefox"); break;
				default : System.out.println("no matching browser found"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.29.228:4444/wd/hub"), ca);
		}
		if(prop.getProperty("execution_environment").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
				case "chrome" :driver= new ChromeDriver(); break;
				case "edge" :driver= new EdgeDriver(); break;
				case "firefox" :driver= new FirefoxDriver(); break;
				default : System.out.println("invalid browser"); return;			
			}			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("appurl"));			
	}

	
	@AfterClass(alwaysRun= true)
	//@AfterClass(groups= {"sanity","Regression"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json into String
		String jsonContent = FileUtils.readFileToString(new File(filePath),"UTF-8");
		
		//convert String to HashMap using Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String , String>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		
		return data;// the data have list of Hashmaps
		
	}
	
	
	public String captureScreen(String testCaseName)
	{
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		TakesScreenshot ts = (TakesScreenshot)driver;
		//File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File sourceFile =ts.getScreenshotAs(OutputType.FILE);
		
		String path = ExtentReporterManager.screenshotFolderPath+"//"+screenshotFile;	
		
		try
		{
			//sourceFile.renameTo(target);
			FileUtils.copyFile(sourceFile, new File(path));
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		return path;
	}
	
	

}

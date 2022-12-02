import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Metabase {
    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.chrome.driver", "F:\\Java\\java projects\\drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("debuggerAddress", "localhost:9999");

        WebDriver driver = new ChromeDriver(options);

        String parent = driver.getWindowHandle();

        //driver.get("https://metabase.masaischool.com/question/274-rac");

        //Thread.sleep(10000);

        WebElement studentCode = driver.findElement(By.xpath("//input[@placeholder='Student code']"));  // Student Code Input

        studentCode.sendKeys(Keys.CONTROL + "a");
        studentCode.sendKeys(Keys.DELETE);
        studentCode.sendKeys("fw22_0328");  // Student Code Input

        Thread.sleep(1000);

        WebElement assignementID = driver.findElement(By.xpath("//input[@placeholder='Assignement ID']"));  // Assignement ID Input

        assignementID.sendKeys(Keys.CONTROL + "a");
        assignementID.sendKeys(Keys.DELETE);
        assignementID.sendKeys("18280");   // Assignement ID Input

        Thread.sleep(2000);

        WebElement search = driver.findElement(By.xpath("//button[contains(@class,'circular')]")); // search

        search.click();  // search

        Thread.sleep(2000);

        HashSet<String> submissionLinks = new HashSet<>();

        List<WebElement> links = driver.findElements(By.xpath("//a[contains(@href,'http')]"));

        for(WebElement element : links){
          
           String elem = element.getText();
           if(elem.length()>0){ submissionLinks.add(elem); }

        }

        for(String link : submissionLinks){

            WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
            newTab.get(link);
            Thread.sleep(10000);
            newTab.close();
            driver.switchTo().window(parent);

        }
        
        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        fillForm(newTab, "fw22_0328", "18280");
        newTab.close();

        System.out.println(submissionLinks);
        System.out.println(submissionLinks.size());
        
    }

    public static void fillForm(WebDriver driver, String stCode, String AssignId) throws Exception {

        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSeSR4YzMfq8OjSW1mceOtrEU9dZLUNXA1bpNd54IO-wkwFMBg/viewform");

        Thread.sleep(5000);

        WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
        email.clear();
        email.sendKeys("adarsha.khatua@masaischool.com");

        List<WebElement> inputs = driver.findElements(By.xpath("//input[@type='text']"));

        WebElement studentCode = inputs.get(0);
        studentCode.clear();
        studentCode.sendKeys(stCode);

        WebElement codingAssignmentId = inputs.get(1);
        codingAssignmentId.clear();
        codingAssignmentId.sendKeys(AssignId);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});");  // scroll page 

        Thread.sleep(5000);

    }
}

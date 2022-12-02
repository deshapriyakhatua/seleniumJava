
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FillForm {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "F:\\Java\\java projects\\drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("debuggerAddress", "localhost:9999");

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSeSR4YzMfq8OjSW1mceOtrEU9dZLUNXA1bpNd54IO-wkwFMBg/viewform");

        Thread.sleep(5000);

        WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
        email.clear();
        email.sendKeys("adarsha.khatua@masaischool.com");

        List<WebElement> inputs = driver.findElements(By.xpath("//input[@type='text']"));

        WebElement studentCode = inputs.get(0);
        studentCode.clear();
        studentCode.sendKeys("");

        WebElement codingAssignmentId = inputs.get(1);
        codingAssignmentId.clear();
        codingAssignmentId.sendKeys("");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});");  // scroll page 

        Thread.sleep(5000);

    }
}

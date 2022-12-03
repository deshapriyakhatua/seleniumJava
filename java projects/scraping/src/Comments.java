import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Comments {
    
    public static void main(String[] args) throws Exception {


        System.setProperty("webdriver.chrome.driver", "F:\\Java\\java projects\\drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("debuggerAddress", "localhost:9999");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("F:\\Java\\java projects\\scraping\\src\\index.html");
        Thread.sleep(5000);
        driver.close();
    
    }
}


//chrome.exe --remote-debugging-port=9999 --user-data-dir="F:\Java\java projects\drivers\debuged"

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class App {
    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.chrome.driver", "F:\\Java\\java projects\\drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("debuggerAddress", "localhost:9999");

        WebDriver driver = new ChromeDriver(options);
        
        //driver.manage().window().maximize();

        //driver.get("https://mail.google.com/mail/u/0/#inbox");

        String[] emailAddress = new String[]{"kate@educba.com","info@ccfreshers.codingninjas.com","newsletters-noreply@linkedin.com","noreply@codingninjas.com","sbiinfosec@communications.sbi.co.in","noreply-maps-issues@google.com","PlatformNotifications-noreply@google.com","noreply-local-guides@google.com","captain@ratatype.com","academy@hello.interviewbit.com","financialexpress@publisher-news.com","vkscsk-space@quora.com","discuss@leetcode.com","practice_content@geeksforgeeks.org","info@mailer.jio.com","academy@scaler.com","yonobysbi@sbi.co.in","kickstart-noreply@google.com","info@educba.com","support@tastytrade.com","hackers@hackerrankmail.com","alerts@courses.codingninjas.com","community_marketing@hackerearth.com","harish@mail.codechef.com","info@swadcooking.com","no-reply@wacom.com"};
        
        Thread.sleep(5000);
        
        unreadAll(driver,emailAddress);

        //driver.quit();
             
    }
    public static void unreadAll(WebDriver driver,String[] emailAddress) throws InterruptedException {
        

        List<WebElement> emails = driver.findElements(By.xpath("//tr[contains(@class,'zA')]"));

        System.out.println(emails.size());
        
        for(int i=0; i<emails.size(); i++){

            WebElement checkbox = emails.get(i).findElement(By.xpath(".//div[@role='checkbox']"));
            WebElement name = emails.get(i).findElement(By.xpath(".//span[contains(@email,'@')]"));
            String string = name.getAttribute("email").trim();

            for(String str : emailAddress){
                if(str.equals(string) ){ checkbox.click(); break; }
            }

            Thread.sleep(50);
            //System.out.println(checkbox.getAttribute("aria-checked")+i);

        }
        
        Thread.sleep(3000);

        try {
            
            WebElement delete = driver.findElement(By.xpath("//div[@aria-label='Delete' or @title='Delete']"));

            delete.click();

            Thread.sleep(5000);
            
            unreadAll(driver,emailAddress);

        } catch (Exception e) { System.out.println(e); }
    

        

       try {
            

             WebElement nextPage = driver.findElement(By.xpath("//span[@class='Di']//descendant::div[3]"));

             nextPage.click();

            Thread.sleep(5000);
            
            unreadAll(driver,emailAddress);

            // WebElement totalMail = driver.findElement(By.xpath("//span[@class='ts'][3]"));

            // WebElement checkedMail = driver.findElement(By.xpath("//div[@aria-label='Show more messages']//span//span//span[2]"));

            // if(!totalMail.getText().equals(checkedMail.getText())){ unreadAll(driver,emailAddress); }
            // else{ System.out.println("Complete"); }
            

        } catch (Exception e) {
            System.out.println(e+"Done");
        }
    }
}


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

public class DataStudio {
    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.chrome.driver", "F:\\Java\\java projects\\drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("debuggerAddress", "localhost:9999");

        WebDriver driver = new ChromeDriver(options);
        
        //driver.manage().window().maximize();

        driver.get("https://datastudio.google.com/reporting/1e8fcf5c-8859-4a97-92f1-7ff49c191484/page/p_4qqhba7ayc");

        
        
        Thread.sleep(8000);

        List<WebElement> dropdowns = driver.findElements(By.xpath("//control-layout-wrapper"));  // coding IA names && RAC status 
        
        WebElement dropdown1 = dropdowns.get(0);  // coding IA name
        WebElement dropdown2 = dropdowns.get(1);  // RAC status dropDown

        dropdown1.click();  // coding IA name

        Thread.sleep(3000);

        List<WebElement> checkboxes = driver.findElements(By.xpath("//md-checkbox[@type='checkbox']"));  // coding IA names

        WebElement checkbox = checkboxes.get(0);  // -Coding IA Name

        checkbox.click();  // -Coding IA Name

        Thread.sleep(1000);

        WebElement searchBox = driver.findElement(By.xpath("//input[@id='input-2']"));  // Search Box

        searchBox.sendKeys("Adarsha Khatua");  // Search Box

        Thread.sleep(1000);

        WebElement checkbox1 = checkboxes.get(1);  // First Searched IA

        checkbox1.click();  // First Searched IA

        Thread.sleep(1000);

        WebElement rac = driver.findElement(By.xpath("//xap-nav-link[@id='p_4qqhba7ayc']"));  // RAC Button

        rac.click();  // RAC Button

        Thread.sleep(1000);

        dropdown2.click();  // RAC status dropDown

        Thread.sleep(1000);

        List<WebElement> checkboxes2 = driver.findElements(By.xpath("//md-checkbox[@role='checkbox']"));  // RAC status checkBoxes

        WebElement checkbox2 = checkboxes2.get(0);  // RAC status checkBox

        checkbox2.click();  // RAC status checkBox

        /////////////////////////////////////////////////////////////////////////////////////

        Thread.sleep(1000);

        WebElement checkbox3 = checkboxes2.get(2);  // RAC status No Submission checkBox

        checkbox3.click();  // RAC status No Submission checkBox

        Thread.sleep(1000);

        WebElement checkbox4 = checkboxes2.get(3);  // RAC status #ref checkBox

        checkbox4.click();  // RAC status #ref checkBox

        Thread.sleep(1000);

        ///////////////////////////////////////////////////////////////////////////////////////

        rac.click();  // RAC Button

        Thread.sleep(2000);

        List<WebElement> rows = driver.findElements(By.xpath("//div[contains(@class,'row block')]"));  // RAC Data Sheet

        HashSet<String> studentIds = new HashSet<>();  // Student Id Data

        for(WebElement row : rows){
            WebElement student = row.findElement(By.xpath(".//div[2]"));  // RAC Data Sheet row

            String studentId = student.getText().trim();    // Student Id
            if(studentId.length()>0){ studentIds.add(studentId); }

        }

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("document.querySelector('.tableBody').scrollTo({top: document.querySelector('.tableBody').scrollHeight, behavior: 'smooth'});");  // scroll page section

        Thread.sleep(5000);

        rows = driver.findElements(By.xpath("//div[contains(@class,'row block')]"));  // RAC Data Sheet

        for(WebElement row : rows){

            WebElement student = row.findElement(By.xpath(".//div[2]"));  // RAC Data Sheet row
            
            String studentId = student.getText().trim();   // Student Id
            if(studentId.length()>0){ studentIds.add(studentId); }

        }

        System.out.println(studentIds);
        System.out.println(studentIds.size()+" "+rows.size());
        System.out.println(studentIds.size() == rows.size());

        Thread.sleep(2000);

        driver.get("https://metabase.masaischool.com/question/274-rac");  // MeteBase website

        Thread.sleep(10000);


        for(String stCode : studentIds){

            metaBase(driver, stCode,"18166" ); //18280
            Thread.sleep(2000);

        }

        System.out.println("Done");
        
        //driver.quit();
             
    }

    public static void metaBase(WebDriver driver, String stCode, String assignId) throws Exception{

        String parent = driver.getWindowHandle();

        WebElement studentCode = driver.findElement(By.xpath("//input[@placeholder='Student code']"));  // Student Code Input

        studentCode.sendKeys(Keys.CONTROL + "a");
        studentCode.sendKeys(Keys.DELETE);
        studentCode.sendKeys(stCode);  // Student Code Input

        Thread.sleep(1000);

        WebElement assignementID = driver.findElement(By.xpath("//input[@placeholder='Assignement ID']"));  // Assignement ID Input

        assignementID.sendKeys(Keys.CONTROL + "a");
        assignementID.sendKeys(Keys.DELETE);
        assignementID.sendKeys(assignId);   // Assignement ID Input

        Thread.sleep(2000);

        WebElement search = driver.findElement(By.xpath("//button[contains(@class,'circular')]")); // search

        search.click();  // search

        Thread.sleep(2000);

        HashSet<String> submissionLinks = new HashSet<>();  // submission links

        List<WebElement> links = driver.findElements(By.xpath("//a[contains(@href,'http')]"));   // submission links

        for(WebElement element : links){
          
           String elem = element.getText();   // submission link
           if(elem.length()>0){ submissionLinks.add(elem); }  // submission link

        }

        for(String link : submissionLinks){

            WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);    // open submission links in new Tab
            newTab.get(link);    // open submission links in new Tab
            //JavascriptExecutor js = (JavascriptExecutor) newTab;  // scroll page
            //js.executeScript("window.scrollTo({top: 200, behavior: 'smooth'});");  // scroll page 
            Thread.sleep(5000);    // open submission links in new Tab
            newTab.close();    // open submission links in new Tab
            driver.switchTo().window(parent);    // open submission links in new Tab

        }
        
        Thread.sleep(1000);

        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);  // fill form
        fillForm(newTab, stCode, assignId);   // fill form
        newTab.close();  // fill form
        driver.switchTo().window(parent);  // fill form

        System.out.println(submissionLinks); // submission link
        System.out.println(submissionLinks.size()); // submission link

        
    }

    public static void fillForm(WebDriver driver, String stCode, String assignId) throws Exception {

        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSeSR4YzMfq8OjSW1mceOtrEU9dZLUNXA1bpNd54IO-wkwFMBg/viewform");   // fill form

        JavascriptExecutor js = (JavascriptExecutor) driver;  // scroll page

        js.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});");  // scroll page 

        Thread.sleep(2000);


        WebElement email = driver.findElement(By.xpath("//input[@type='email']"));  //input email address
        email.clear();  //input email address
        email.sendKeys("adarsha.khatua@masaischool.com");   //input email address

        List<WebElement> inputs = driver.findElements(By.xpath("//input[@type='text']"));  //  Student code & assignment id

        WebElement studentCode = inputs.get(0);  //  Student code
        studentCode.clear();  //  Student code
        studentCode.sendKeys(stCode);  //  Student code

        WebElement codingAssignmentId = inputs.get(1);  // assignment id
        codingAssignmentId.clear();  //   assignment id
        codingAssignmentId.sendKeys(assignId);  // assignment id

        Thread.sleep(4000);

    }
}


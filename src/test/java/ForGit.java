import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ForGit {

    @Test
   public void FirstTest() {


       WebDriver driver = new ChromeDriver();
       driver.get("http://youtube.co.uk");
       //  ScreenshotHelper.takeScreenShot(driver,"lobo");
       driver.close();

        System.out.println("testing");
       // commented on this code
        //pee

   }


}

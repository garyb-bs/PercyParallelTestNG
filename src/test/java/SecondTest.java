import io.percy.selenium.Percy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ONUR on 03.12.2016.
 */
public class SecondTest extends BaseTest {
    @Test
    public void GOOGLE4() throws InterruptedException {
        // Start the Percy server
        //Utils.startPercy();

        Thread.sleep(5000);

        getDriver().get("https://browserstack.com");

        // Take a snapshot using Percy
        Percy percy = new Percy(getDriver());
        percy.snapshot("Test Snapshot 2");

        // Stop the Percy server when done
        //Utils.stopPercy();
        //Utils.finalizePercyBuild();
    }
}
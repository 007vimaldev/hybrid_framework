package screenshot;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YouTubeLikeVideoScreenshot {

    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.youtube.com/");
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.sendKeys("selenium tutorial");
        searchBox.submit();
        WebElement firstVideo = driver.findElement(By.xpath("(//a[@id='thumbnail'])[1]"));
        firstVideo.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement likeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ytd-toggle-button-renderer[@aria-label='Like this video along with 43 million other people']")));
        likeButton.click();
        File screenshotFile = ((ChromeDriver) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("screenshot.png"));

        driver.quit();
    }
}

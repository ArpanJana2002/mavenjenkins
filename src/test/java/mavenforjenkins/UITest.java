package mavenforjenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
@Parameters("browser")
public void startBrowser(String browserName) {
    WebDriver driver;
    if (browserName != null && browserName.contains("Chrome")) {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--headless");
        opt.addArguments("--no-sandbox");
        opt.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(opt);
    } else if (browserName != null && browserName.contains("Edge")) {
        driver = new EdgeDriver();
    } else {
        throw new IllegalArgumentException("Unsupported browser: " + browserName);
    }

    driver.manage().window().maximize();
    driver.get("https://opensource-demo.orangehrmlive.com/");
    Assert.assertTrue(driver.getTitle().contains("Orange"), "Title does not match");
    driver.quit();
}

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class ProjectTest {


    @Test
    void testOne() throws InterruptedException {
        WebDriver driver = H.setupDriver();
        driver.get(H.GOOGLEURL);
        Thread.sleep(2000);
        driver.navigate().to(CuraHelper.CUREURL);
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
        driver.navigate().forward();
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    void testTwo() throws InterruptedException {
        WebDriver driver = H.setupDriver();
        driver.get(H.GOOGLEURL);
        String googleWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(CuraHelper.CUREURL);
        String cureTab = driver.getWindowHandle();
        driver.switchTo().window(googleWindow);
        driver.close();

        driver.switchTo().window(cureTab);
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(H.GOOGLEURL);
        driver.switchTo().window(cureTab);

        driver.manage().window().fullscreen();
        Thread.sleep(2000);
        driver.manage().window().minimize();
        Thread.sleep(2000);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.quit();
    }

    @Test
    void testThree() throws IOException, InterruptedException {
        WebDriver driver = H.setupDriver();
        driver.get(WikiHelper.PENGUINURL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Actions actions = new Actions(driver);
        WebElement doubleClick =
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(WikiHelper.PENGUINTITLEXPATH)));
        actions.doubleClick(doubleClick).perform();
        H.screenshot("Double click screenshot", driver);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement evolution = driver.findElement(By.id(WikiHelper.EVOLUTIONID));
        jse.executeScript(JavascriptHelper.SCROLLUNTIL, evolution);
        H.screenshot("Penguins evolution screenshot", driver);

        jse.executeScript(JavascriptHelper.SCROLLTOTHEEND);
        Thread.sleep(2000);
        H.screenshot("The end of the page", driver);

        driver.quit();
    }

    @Test
    void testFour() throws InterruptedException {
        WebDriver driver = H.setupDriver();
        driver.get(CuraHelper.CUREURL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(CuraHelper.MAKEAPPOINTMENTBTNID))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(CuraHelper.USERNAMEFIELDID))).sendKeys(CuraHelper.DEMOUSERNAME);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(CuraHelper.PASSWORDFIELDID))).sendKeys(CuraHelper.DEMOPASSWORD);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(CuraHelper.LOGINBTNID))).click();
        Select dropdown = new Select(driver.findElement(By.id(CuraHelper.FACILITYDROPDOWNID)));
        dropdown.selectByValue(CuraHelper.HONGKONGVALUE);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(CuraHelper.READMISSIONCHECKBOXID))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(CuraHelper.MEDICAIDCHECKBOXID))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(CuraHelper.VISITDATEFIELDID))).sendKeys("02/10/2022");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(CuraHelper.COMMENTFIELDID))).sendKeys("Happy Birthday Rossita <3");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(CuraHelper.BOOKAPPOINTMENTBTNID))).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CuraHelper.GOTOHOMEPAGEXPATH))).click();
        driver.quit();
    }

    @Test
    void testFive() throws IOException {
        WebDriver driver = H.setupDriver();
        driver.get(WikiHelper.PENGUINURL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(WikiHelper.SEARCHFIELDID))).sendKeys("Spider-Man");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(WikiHelper.SEARCHBTNID))).click();
        Assert.assertEquals(driver.getCurrentUrl(), WikiHelper.SPIDERMANURL);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement inOtherMedia = driver.findElement(By.id(WikiHelper.SPIDERMANINOTHERMEDIAID));
        jse.executeScript(JavascriptHelper.SCROLLUNTIL, inOtherMedia);
        H.screenshot("Spider-man in other media screenshot", driver);

        String spiderManInOtherMedia = driver.findElement(By.xpath(WikiHelper.INOTHERMEDIATXTXPATH)).getText();
        File s = new File(H.PROJECTFOLDERPATH + "Spider-Man in other media" + H.TXT);
        try {
            if (s.createNewFile()) {
                System.out.println("file was created");
            } else {
                System.out.println("file already exists");
            }
            FileWriter w = new FileWriter(s);
            w.write(spiderManInOtherMedia);
            w.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        driver.quit();
    }
}




import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class H {

    public static final String GOOGLEURL = "https://www.google.co.il/";

    public static final String PROJECTFOLDERPATH = "C:\\Users\\Asus-pc\\Desktop\\ProjectFolder\\";

    public static final String JPG = ".jpg";

    public static final String TXT = ".txt";




    public static ChromeDriver setupDriver(){
        System.setProperty("webdriver.chrome.driver","res\\chromedriver.exe");
        return new ChromeDriver();
    }


    public static void screenshot(String pictureName, WebDriver d) throws IOException {
        File file = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
        File file2 = new File(H.PROJECTFOLDERPATH + pictureName + H.JPG);
        FileUtils.copyFile(file,file2);
    }
}

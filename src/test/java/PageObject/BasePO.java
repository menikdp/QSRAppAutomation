package pageobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.PageFactory;
import utils.PropertyUtils;
import utils.WaitUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BasePO {
    public final static int IMPLICIT_WAIT = PropertyUtils.getIntegerProperty("implicitWait", 10);
    WaitUtils waitUtils;
    protected final AppiumDriver driver;

    protected BasePO(AppiumDriver driver) {
        this.driver = driver;
        initElements();
        loadProperties();
        waitUtils = new WaitUtils();
    }

    private void initElements() {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(IMPLICIT_WAIT)), this);
    }

    private void loadProperties() {

    }

    public void screenshot(String path_screenshot) throws IOException {
        File srcFile=driver.getScreenshotAs(OutputType.FILE);
        String filename = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        File targetFile=new File(path_screenshot + filename +".jpg");
        FileUtils.copyFile(srcFile,targetFile);
    }
}

package io.vana.InitDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class InitDriver {

    protected AppiumDriver driver;

    @BeforeClass(alwaysRun = true)
    public void openDriver(@Optional("appium") String runOn) {
        initDriver(runOn);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void initDriver(String runOn) {
        try {
            if(runOn.equalsIgnoreCase("appium")){
                    GridCapabilities gridCapabilities = new GridCapabilities();
                    //APK fil
                    File file = new File("apkFile/SATGT_v2.0.2_apkpure.com.apk");
                    //URL Appium server
                    URL url = new URL("http://127.0.0.1:4723/wd/hub");
                    driver = new AndroidDriver<MobileElement>(url,gridCapabilities.getAndroidCapabilities(file.getAbsolutePath()));
                    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                }


        } catch (Exception var5) {
            var5.printStackTrace();
            Assert.fail("fail to init");
        }
    }

}

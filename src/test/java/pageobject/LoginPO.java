package pageobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class LoginPO extends pageobject.BasePO {
    public LoginPO(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.innovecto.etalastic:id/image_onboarding") AndroidElement onboardingView;
    @AndroidFindBy(id = "com.innovecto.etalastic:id/button_next") AndroidElement onboardingNext;
    @AndroidFindBy(id = "com.innovecto.etalastic:id/button_skip") AndroidElement onboardingSkip;
    @AndroidFindBy(id = "com.innovecto.etalastic:id/text_onboarding_title") AndroidElement onboardingTitle;
    @AndroidFindBy(id = "com.innovecto.etalastic:id/login_content_customEditText_phone") AndroidElement username;
    @AndroidFindBy(id = "com.innovecto.etalastic:id/login_content_customEditText_pin") AndroidElement password;
    @AndroidFindBy(id = "com.innovecto.etalastic:id/login_button_action") AndroidElement loginButton;
    @AndroidFindBy(id = "com.innovecto.etalastic:id/button_ok") AndroidElement okButton;
    @AndroidFindBy(id = "com.innovecto.etalastic:id/general_success_dialog_constraint_main") AndroidElement infoGPS;
    @AndroidFindBy(id = "com.innovecto.etalastic:id/general_success_dialog_button") AndroidElement buttonOK;
    @AndroidFindBy(id = "com.innovecto.etalastic:id/general_toolbar_style_1_title")
    List<MobileElement> pilihOutlet;
    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup") List<MobileElement> listOutlet;

    public static utils.WaitUtils wait = new utils.WaitUtils();

    public void nextOnboarding() {
        if (onboardingView.isDisplayed()) {
            Assert.assertEquals(onboardingTitle.getText(), "Berjualan");
            System.out.println("Onboarding displayed");
            onboardingNext.click();
            Assert.assertEquals(onboardingTitle.getText(), "Pesan Barang");
            onboardingNext.click();
            Assert.assertEquals(onboardingTitle.getText(), "Cek Laporan Penjualan");
            onboardingNext.click();
            Assert.assertEquals(onboardingTitle.getText(), "Cek Laporan Stok");
            onboardingNext.click();
            Assert.assertEquals(onboardingTitle.getText(), "Tambah Modal");
            onboardingNext.click();
        } else {
            System.out.println("Onboarding not displayed");
        }
    }

    public void loginSuccessfully(String validEmail, String validPassword) throws IOException {
        username.clear();
        password.clear();
        username.sendKeys(validEmail);
        driver.hideKeyboard();
        password.sendKeys(validPassword);
        wait.staticWait(100);
    }

    public void gpsPermission() {
        try {
            infoGPS.isDisplayed();
            buttonOK.click();
        } catch (Exception e) {
            System.out.println("Info GPS not showed");
        }

        List<MobileElement> cekGeoPop = driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button"));
        Integer geoPopSize = cekGeoPop.size();
        if (geoPopSize != 0) {
            driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        }
    }

    public void checkOutlet() throws InterruptedException, IOException {
        //if users are assigned to several outlets
        wait.staticWait(3000);
        List<MobileElement> cekPilihOutlet = pilihOutlet;
        Integer outletSize = cekPilihOutlet.size();
        if (outletSize != 0) {
            System.out.println("Ada beberapa outlet");
            screenshot("screenshot/login/");
            getRandomOutlet().click();
            wait.staticWait(5000);
            screenshot("screenshot/login/");
        } else {
            System.out.println("Langsung ke beranda");
        }
    }

    private MobileElement getRandomOutlet() {
        //memilih salah satu outlet secara random setiap kali login
        Random  element = new Random();
        int indexElement = element.nextInt(listOutlet.size());
        MobileElement choosenIndex = listOutlet.get(indexElement);
        System.out.println("choosen index : " + choosenIndex);
        return choosenIndex;
    }

}

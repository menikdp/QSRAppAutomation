package testcase;

import pageobject.LoginPO;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestCase extends testcase.BaseTest {
    @Test
    public void test() throws IOException, InterruptedException {
        LoginPO loginPO = new LoginPO(driver);
        loginPO.nextOnboarding();
        loginPO.gpsPermission();
        loginPO.loginSuccessfully("menzkaffe", "000000");
        loginPO.checkOutlet();
    }

    @BeforeTest
    @Override
    public void setupPage() {

    }
}

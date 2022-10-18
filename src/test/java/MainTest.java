import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void userCanLogin()
    {
        LoginPage loginPage = new LoginPage();
        UserPage userPage = new UserPage();

        open("https://ok.ru");

        loginPage.login("technoPol3", "technoPolis2022");
        assertTrue(userPage.checkUsername("technoPol3 technoPol3"));
        clearBrowserCookies();
    }
}
class LoginPage
{
    private final SelenideElement loginField =$(By.xpath("//*[@id='field_email']"));
    private final SelenideElement passwordField =$(By.xpath("//*[@id=\"field_password\"]"));
    private final SelenideElement loginButton =$(By.xpath("//*[@id=\"anonymPageContent\"]/div/div[1]/div[2]/div/div[2]/div[2]/div[1]/form/div[4]/input"));


    public void login(String email, String pwd)
    {
        loginField.setValue(email);
        passwordField.setValue(pwd);
        loginButton.click();
    }
}
class UserPage
{
    private final String usernameXPath = "//*[@id=\"hook_Block_Navigation\"]/div/div/div[1]/a/div";
    private final SelenideElement usernameField = $(By.xpath(usernameXPath));
    public boolean checkUsername(String expectedUsername)
    {
        if(!$$(By.xpath(usernameXPath)).isEmpty())
            return expectedUsername.equals(usernameField.text());
        else
            return false;
    }
}


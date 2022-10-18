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
    private final SelenideElement loginField =$(By.xpath("//input[@id='field_email']"));
    private final SelenideElement passwordField =$(By.xpath("//input[@id='field_password']"));
    private final SelenideElement loginButton =$(By.xpath("//div[@class='login-form-actions']/input"));


    public void login(String email, String pwd)
    {
        loginField.setValue(email);
        passwordField.setValue(pwd);
        loginButton.click();
    }
}
class UserPage
{
    private final String usernameXPath = "//a[@data-l='t,userPage']/div";
    private final SelenideElement usernameField = $(By.xpath(usernameXPath));
    public boolean checkUsername(String expectedUsername)
    {
        if(!$$(By.xpath(usernameXPath)).isEmpty())
            return expectedUsername.equals(usernameField.text());
        else
            return false;
    }
}


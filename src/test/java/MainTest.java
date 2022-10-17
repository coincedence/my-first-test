import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MainTest {

    @Test
    void userCanLogin()
    {
        LoginPage loginPage = new LoginPage();
        loginPage.login("gkrivov12@gmail.com", "", "Георгий Кривов", true);
        loginPage.login("gkrivov12@gmail.com", "123456", "Георгий Кривов", false);
    }



}
class LoginPage
{
    private final String usernameXPath = "//*[@id=\"hook_Block_Navigation\"]/div/div/div[1]/a/div";
    private final SelenideElement loginField =$(By.xpath("//*[@id='field_email']"));
    private final SelenideElement passwordField =$(By.xpath("//*[@id=\"field_password\"]"));
    private final SelenideElement loginButton =$(By.xpath("//*[@id=\"anonymPageContent\"]/div/div[1]/div[2]/div/div[2]/div[2]/div[1]/form/div[4]/input"));
    private final SelenideElement usernameField = $(By.xpath(usernameXPath));

    public void login(String email, String pwd, String expectedUsername, boolean expectedResult)
    {

        open("https://ok.ru");

        loginField.setValue(email);
        passwordField.setValue(pwd);
        loginButton.click();

        if(!$$(By.xpath(usernameXPath)).isEmpty())
            assertEquals(expectedUsername, usernameField.text());
        else
            assertFalse(expectedResult);
        clearBrowserCookies();
    }
}

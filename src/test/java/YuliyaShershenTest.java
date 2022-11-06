import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class YuliyaShershenTest {

    //TC_1_1  - Тест кейс:
    //1. Открыть страницу https://openweathermap.org/
    //2. Набрать в строке поиска город Paris
    //3. Нажать пункт меню Search
    //4. Из выпадающего списка выбрать Paris, FR
    //5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagTextWhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFrChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']//li//span[text() = 'Paris, FR ']")
        );
        parisFrChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.close();
        driver.quit();
    }

    /*
    TC_11_01
1.  Открыть базовую ссылку
2.  Нажать на пункт меню Guide
3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
     */

    @Test
    public void testGuide() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(1000);

        //xpath   "//*[@id=\"desktop-menu\"]/ul/li[1]/a"
        WebElement searchButton = driver.findElement(
                By.xpath("//a[@href='/guide']"));

        searchButton.click();
        Thread.sleep(1000);

        String actualResultTitle = driver.getTitle();

        Assert.assertEquals(actualResultTitle, expectedResultTitle);

        String actualResultUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);

        driver.close();
        driver.quit();
    }

    /*
    TC_11_02
1.  Открыть базовую ссылку
2.  Нажать на единицы измерения Imperial: °F, mph
3. Подтвердить, что температура для города показана в Фарингейтах

     */

    @Test
    public void testTempFahrenheit_WhenChoiceF() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String  expectedResult = "°F";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(1000);

        WebElement searchTypeOfTemperature = driver.findElement(
                By.xpath("//div[text() = 'Imperial: °F, mph']")
        );
        searchTypeOfTemperature.click();

        WebElement tempF = driver.findElement(By.xpath("//div[@class='current-temp']/span"));
        String tempInF = tempF.getText();
        String actualResult = tempInF.substring(tempInF.length()-2);

       // Boolean actualResult = searchTypeOfTemperature.isDisplayed();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }


/*
TC_11_03
1.  Открыть базовую ссылку
2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
We also use non-essential cookies to help us improve our services. Any data collected is anonymised.
You can allow all cookies or manage them individually.”
3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
 */

@Test
public void testCookies() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
    WebDriver driver = new ChromeDriver();
    String url = "https://openweathermap.org/";

    String expectedResultCookieText = "We use cookies which are essential for the site to work.\n" +
            "We also use non-essential cookies to help us improve our services. Any data collected is anonymised.\n" +
            "You can allow all cookies or manage them individually.";
    String button1 = "Allow all";
    String button2 = "Manage cookies";

    driver.get(url);
    driver.manage().window().maximize();
    Thread.sleep(1000);


    WebElement searchCookiesMessage = driver.findElement(By.className("stick-footer-panel"));
    String actualResult1 = searchCookiesMessage.getText();

//    WebElement buttonAllowAll = driver.findElement(By.xpath(""));
//    WebElement buttonManageCookies = driver.findElement(By.xpath(""));

//    Assert.assertEquals(buttonAllowAll.getText(), button1);
//    Assert.assertEquals(buttonManageCookies.getText(), button2);
//    Assert.assertEquals(actualResult1, expectedResultCookieText);

    //String cookiesMessage = driver.switchTo().alert().getText();

    driver.quit();
    }

    /*
   TC_11_05
1. Открыть базовую ссылку
2. Нажать пункт меню Support → Ask a question
3. Заполнить поля Email, Subject, Message
4. Не подтвердив CAPTCHA, нажать кнопку Submit
5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     */

    @Test
    public void testLaLa() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/irlia/Desktop/QA Study/Java/WebDriverChrome/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String email = "test@test.com";
        String subject = "Other";
        String message = "lalala";
        String expectedResult9 = "IreCAPTCHA verification failed, please try again.";

        driver.manage().window().maximize();

        driver.get(url);
        Thread.sleep(5000);

        WebElement menuSupport = driver.findElement(
                By.xpath("//div[@id='support-dropdown']"
                ));

        menuSupport .click();
        Thread.sleep(5000);
        WebElement dropDownAskQuestion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown']/li/a[@href='https://openweathermap.org/questions']"
                ));

        dropDownAskQuestion .click();

        Thread.sleep(5000);
        WebElement emailField = driver.findElement(
                By.id("question_for_email"
                ));

        emailField.click();
        emailField.sendKeys(email);


        driver.quit();
    }

    //    TC_11_06
//    1.  Открыть базовую ссылку
//    2.  Нажать пункт меню Support → Ask a question
//    3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
//    4.  Оставить пустым поле Email
//    5.  Заполнить поля  Subject, Message
//    6.  Подтвердить CAPTCHA
//    7.  Нажать кнопку Submit
//    8.  Подтвердить, что в поле Email пользователю будет показана ошибка "can't be blank"

    @Test
    public void testErrorEmail() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Programs\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String subject = "Other";
        String message = "Hi Lilu we are waiting for you";

        String expectedResult = "can't be blank";


        driver.get(url);

        Thread.sleep(5000);
        driver.manage().window().maximize();

        WebElement clickOnSupport = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        clickOnSupport.click();

        String originalWindow = driver.getWindowHandle();
        Thread.sleep(4000);
        WebElement selectSubmenu_AskAQuestion = driver.findElement(By.xpath(
                "//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']"));
        selectSubmenu_AskAQuestion.click();

        Thread.sleep(4500);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Thread.sleep(3000);

        WebElement enterSubject = driver.findElement(By.xpath(
                "//select[@class='form-control select required']"));

        enterSubject.click();

        enterSubject.sendKeys(subject);

        Thread.sleep(4000);

        WebElement enterMessage = driver.findElement(By.xpath(
                "//textarea[@class='form-control text required']"));
        enterMessage.click();
        enterMessage.sendKeys(message);

        Thread.sleep(5000);

        String window2 = driver.getWindowHandle();

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));

        WebElement enterCaptcha = driver.findElement(By.xpath(
                "//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked "
                        + "rc-anchor-checkbox']"));
        enterCaptcha.click();

        Thread.sleep(10000);

        driver.switchTo().window(window2);

        WebElement pressSubmit = driver.findElement(By.xpath(
                "//input[@data-disable-with='Create Question form']"));
        pressSubmit.click();

        WebElement confirmErrorEmail = driver.findElement(By.xpath("//span[@class='help-block']"));

        String actualResult = confirmErrorEmail.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void test8() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/irlia/Desktop/QA Study/Java/WebDriverChrome/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        driver.get(url);

        Thread.sleep(2000);

      //  WebElement imageBanner = driver.findElement(By.xpath());


    }
    // last task WebElement//a[contains(@class, 'btn_block orange round') " +
    //                    "or contains(@class, 'ow-btn round btn-orange') ]")).size();
}



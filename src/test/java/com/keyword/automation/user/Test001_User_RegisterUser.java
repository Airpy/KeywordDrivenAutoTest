package com.keyword.automation.user;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.common.Constants;
import com.keyword.automation.customer.LoginKeyword;
import com.keyword.automation.customer.MenuKeyword;
import org.openqa.selenium.By;


public class Test001_User_RegisterUser {
    public static void main(String[] args) throws InterruptedException {
        LoginKeyword.loginSystem("Chrome", Constants.TEST_URL);
        MenuKeyword.selectMenu("档案", "基本单位");
        ElementKeyword.clickElement(By.xpath("//*[@id='queryForm']/div/a/span/span"));
        Thread.sleep(10000);
        BrowserKeyword.browserQuit();
//        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + Constants.CHROME_PATH_WIN);
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.zhoupu123.com/saas/tologin");
//        driver.findElement(By.id("userName")).sendKeys("18010001001");
//        driver.findElement(By.id("pwd")).sendKeys("aA111111");
//        driver.findElement(By.id("login_id")).click();
//        driver.findElement(By.xpath(".//span[text()='档案']/parent::div/parent::div")).click();
//        driver.findElement(By.xpath(".//div[text()='商品档案']/parent::div")).click();

//        driver.quit();
    }
}

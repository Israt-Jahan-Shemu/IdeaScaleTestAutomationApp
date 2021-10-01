package com.isratshimu.Campaign;

import com.isratshimu.Base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class CreateCampaign extends TestBase {
    public static final String campaignName = "Campaign By IsratJahanShemu";
    public static final String ideaTitle = "Idea of IsratJahanShemu";
    public static final String BASE_URL = "https://trialqa.ideascale.com/";

    @BeforeMethod
    public void initSetUp() {
        launchWebBrowser();
    }

    @AfterMethod
    public void tearDown() {
//        closeBrowser();
    }

    @Test
    public static void createCampaign() {
        login();
        createNewCampaign();
    }

    @Test
    public static void createIdea() {
        login();
        createNewCampaign();
        createNewIdea();
    }

    @Test
    public static void upvoteTheIdea() {
        login();
        createNewCampaign();
        createNewIdea();
        upvoteAndCommentOnIdea();
    }

    public static void login() {
        driver.get(BASE_URL);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        /*Accept Cookies*/
        driver.findElement(By.cssSelector("button[class='btn btn-primary flex-fill json-link']")).click();
        driver.get(BASE_URL);

        WebElement Email = driver.findElement(By.id("login-email"));
        Email.sendKeys("trialqa.ideascale@gmail.com");

        WebElement Password = driver.findElement(By.id("login-password"));
        Password.sendKeys("a@123456#");

        WebElement LoginBtn = driver.findElement(By.cssSelector("button[class='btn btn-xl btn-primary btn-block ga-tracker disabled-when-loading btn-captcha'"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
        LoginBtn.click();
    }

    public static void createNewCampaign() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));

        /*Click Trial QA Dropdown*/
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("topbar-avatar")))).click();

        /*Click Community Settings*/
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"utb-user-menu\"]/ul/li[2]/a")))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        /*Get all the current tabs open*/
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        /*Click Engagement*/
        driver.findElement(By.cssSelector("#heading-engagement > h4 > a")).click();

        /*Click Campaigns*/
        driver.findElement(By.xpath("//*[@id=\"main-nav-engagement\"]/div")).click();

        /*Click Add New Campaign*/
        By campaign = By.xpath("/html/body/div[5]/div[1]/div/section/div[2]/div[1]/a[2]");
        WebDriverWait waitForCampaign = new WebDriverWait(driver, Duration.ofSeconds(25));
        WebElement campaignElement = waitForCampaign.until(ExpectedConditions.elementToBeClickable(campaign));
        campaignElement.click();
//        waitForCampaign.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/section/div[2]/div[1]/a[2]")))).click();


        /*Provide Campaign Name*/
        WebElement Name = driver.findElement(By.id("category-name-field"));
        Name.sendKeys(campaignName);

        /*Click dropdown right to Save button*/
        driver.findElement(By.xpath("//*[@id=\"tab-campaign\"]/div[2]/div/div/button")).click();

        /*Click Save and Continue*/
        driver.findElement(By.xpath("//*[@id=\"tab-campaign\"]/div[2]/div/div/div/button")).click();

        /*Click Schedule*/
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"tab-campaign\"]/div[1]/ul/li[7]")))).click();
        /*Launch Immediately*/
        driver.findElement(By.xpath("//*[@id=\"campaign-form\"]/div/div[2]/section[1]/div[2]/div[2]/button")).click();
        /*Back to Home*/
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public static void createNewIdea() {
        /*Get all the current tabs open*/
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));

        driver.switchTo().window(tabs.get(0));
        driver.get("https://trialqa.ideascale.com/a/idea?templateId=0");

        /*Submit your Idea*/

        /*Set Title*/
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("idea-title-input")))).sendKeys(ideaTitle);

        /*Select Campaign*/
        driver.findElement(By.id("select2-idea-campaign-value-container")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//li[contains(text(),'" + campaignName + "')]")))).click();

        /*Set Description*/
//        WebElement Description = driver.findElement(By.id("idea-desc-value"));
//        Description.sendKeys("Idea of Israt Jahan Shemu ");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("idea-desc-value")))).click();

        /*Submit Form*/
//        WebElement submitButtonElement = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Submit')]"))));
//        submitButtonElement.click();

    }

    public static void upvoteAndCommentOnIdea() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
        /**
         * Upvote
         */
        driver.get("https://trialqa.ideascale.com/a/idea?templateId=0");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"official-header-row\"]/div/div/nav/div/a[2]")))).click();

        String href = driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[2]/div/div/div[2]/article[7]/header/h2/a")).getAttribute("href");
        driver.get(href);

        // wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@title='Idea of IsratJahanShemu']")))).click();
        // wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@title='You have voted']")))).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"comment-text\"]")))).sendKeys("Nice Idea");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/div/article/div[7]/div/div/section[1]/div/div/form/div[5]/input")))).click();
    }
}
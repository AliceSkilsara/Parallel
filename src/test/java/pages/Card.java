package pages;

import additional.FileReader;
import additional.LoadProperties;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Card {

    @FindBy(id = "dataCard")
    private SelenideElement card;

    @FindBy(css = "#dataCard h5.card-title")
    private SelenideElement cardTitle;

    @FindBy(css = "#dataCard textarea.form-control")
    public SelenideElement cardText;

    @FindBy(xpath = "//*[@id='dataCard']//button[contains(text(),'Download info')]")
    private SelenideElement downloadFileButton;

    @FindBy(id = "heroImage")
    private SelenideElement cardImage;

    @FindBy(xpath = "//*[@id='dataCard']//button[contains(text(),'Move to saved')]")
    private SelenideElement movedToSavedButton;

    @FindBy(xpath = "//*[@id='dataCard']//button[contains(text(),'Removed from saved')]")
    private SelenideElement removedFromSavedButton;

    @FindBy(css = "#dataCard div.ui-slider")
    private SelenideElement scale;

    @FindBy(css = "#dataCard span.ui-slider-handle")
    private SelenideElement scaleSlider;

    @FindBy(css = "#dataCard div.card-body >p.card-text")
    private SelenideElement shortDescription;

    private Properties properties = LoadProperties.init();


    public void changeScale(int percent) {


        double p = (double) percent / 100;
        double k = p / 10;
        double w = (double) scale.getSize().width;


        Actions actions = new Actions(getWebDriver());

        for (int i = 0; i < 11; i++) {
            actions.dragAndDropBy(scaleSlider, (int) Math.round(w * k), 0);
        }

        actions.build().perform();

    }

    public String getImageSize(){
        String style = cardImage.getAttribute("style");
        String pattern = "(\\d+)";
        Pattern pat = Pattern.compile(pattern);
        Matcher m = pat.matcher(style);
        m.find();
        return m.group(0)+"x"+m.group(1);
    }


    public void deleteFile(String path) {
        File downloadedFile = new File(System.getProperty("user.dir") + path);
        downloadedFile.delete();


    }

    public void downloadFile() {
        downloadFileButton.click();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean checkImage() {
        boolean loaded = !(cardImage.getAttribute("naturalWidth").equals("0"));
        return loaded;
    }

    public boolean checkTitle(String title) {
        return cardTitle.getText().equals(title);
    }

    public boolean checkImageSrc(String src) {
        return cardImage.getAttribute("src").equals(properties.getProperty("url") + src);
    }

    public void scrollTextAreaToTheEnd() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        js.executeScript("var textarea = document.querySelector('textarea');\n" +
                "textarea.scrollTop = textarea.scrollHeight;");

    }

    public void scrollTextAreaToTheMiddle() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        js.executeScript("var textarea = document.querySelector('textarea');\n" +
                "textarea.scrollTop = textarea.scrollHeight/2;");

    }


    public boolean checkShortDescription(String file) {
        String path = properties.getProperty("short.descrtiption.path") + file;
        String fileText = FileReader.readFile(path);
        return fileText.equals(shortDescription.getText());
    }

    public boolean MovedToSaveButtonIsDisabled() {
        return movedToSavedButton.is(Condition.disabled);
    }

    public void waitForPageLoad() {
        card.waitUntil(Condition.visible, 10000);
    }

    public void clickMoveToSavedButton() {
        movedToSavedButton.click();
    }

    public void clickRemovedFromSavedButton() {
        removedFromSavedButton.click();
    }
}


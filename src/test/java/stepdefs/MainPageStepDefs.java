package stepdefs;

import additional.TestContext;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import additional.FileReader;
import pages.MainPage;
import pages.Card;

import java.io.File;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;

public class MainPageStepDefs {
    MainPage mainPage = page(MainPage.class);
    Card card = page(Card.class);


    @Then("^main page is opened$")
    public void mainPageIsOpened() {
        mainPage.waitForPageLoad();
    }

    @When("^user clicks Advertiser block$")
    public void userClicksAdvertiserBlock() {
        mainPage.waitForPageLoad();
        mainPage.clickAdverisers();
    }

    @Then("^Advertisers list is shown$")
    public void advertisersListIsShown() {
        List<String> expectedList = mainPage.getExpectedAdvertiserList();
        Assert.assertEquals(expectedList, mainPage.textInElements(mainPage.advertisers));
    }

    @When("^user clicks Publishers block$")
    public void userClicksPublishersBlock(){
        mainPage.waitForPageLoad();
        mainPage.clickPublishers();
    }

    @Then("^Publishers list is shown$")
    public void publishersListIsShown(){
        List<String> expectedList = mainPage.getExpectedPublisherList();
        Assert.assertEquals(expectedList, mainPage.textInElements(mainPage.publishers));
    }

    @When("^user clicks Top level clients block$")
    public void userClicksTopLevelClientsBlock(){
        mainPage.waitForPageLoad();
        mainPage.clickTopLevelClients();
    }

    @Then("^Top level clients list is shown$")
    public void topLevelClientsListIsShown(){
        List<String> expectedList = mainPage.getExpectedTopLevelClientsList();
        Assert.assertEquals(expectedList, mainPage.textInElements(mainPage.topLevelClients));
    }

    @When("^user double clicks Advertiser block$")
    public void userDoubleClicksAdvertiserBlock(){
        mainPage.doubleClickOnAdvertiserButton();
    }

    @And("^user clicks \"([^\"]*)\" Advertiser$")
    public void userClicksAdvertiser(String adver){
        SelenideElement adverElement;
        switch (adver) {
            case "random":
                adverElement = mainPage.getRandomElementFromCollection(mainPage.advertisers);
                break;
            default:
                adverElement = mainPage.findAdvertiser(adver);
        }
        TestContext.setSharedVariable("adver", adverElement.getText());
        adverElement.click();
        card.waitForPageLoad();
    }

    @And("^user clicks \"([^\"]*)\" saved Advertiser$")
    public void userClicksSavedAdvertiser(String adver){
        SelenideElement adverElement;
        switch (adver) {
            case "random":
                adverElement = mainPage.getRandomElementFromCollection(mainPage.savedAdvertisers);
                break;
            default:
                adverElement = mainPage.findSavedAdvertiser(adver);
        }
        TestContext.setSharedVariable("adver", adverElement.getText());
        adverElement.click();
        card.waitForPageLoad();
    }

    @And("^user downloads file$")
    public void userDownloadsFile(){
        card.downloadFile();
    }

    @Then("^download file and text on the site is equal$")
    public void downloadFileAndTextOnTheSiteIsEqual() {
        String path = File.separator + "build" + File.separator + "downloads" + File.separator + "data.txt";
        String file = FileReader.readFile(path);
        card.deleteFile(path);
        Assert.assertEquals(file, card.cardText.getText().replaceAll("\n", ""));
    }

    @And("^user clicks \"([^\"]*)\" Publisher$")
    public void userClicksPublisher(String pub){
        SelenideElement pubElement;
        switch (pub) {
            case "random":
                pubElement = mainPage.getRandomElementFromCollection(mainPage.publishers);
                break;
            default:
                pubElement = mainPage.findPublisher(pub);
        }
        TestContext.setSharedVariable("pub", pubElement.getText());
        pubElement.click();
        card.waitForPageLoad();
    }

    @And("^user clicks \"([^\"]*)\" saved Publisher$")
    public void userClicksSavedPublisher(String pub){
        SelenideElement pubElement;
        switch (pub) {
            case "random":
                pubElement = mainPage.getRandomElementFromCollection(mainPage.savedPublishers);
                break;
            default:
                pubElement = mainPage.findSavedPublisher(pub);
        }
        TestContext.setSharedVariable("pub", pubElement.getText());
        pubElement.click();
        card.waitForPageLoad();
    }

    @And("^user clicks \"([^\"]*)\" Client$")
    public void userClicksClient(String client){
        SelenideElement clientElement;
        switch (client) {
            case "random":
                clientElement = mainPage.getRandomElementFromCollection(mainPage.topLevelClients);
                break;
            default:
                clientElement = mainPage.findTopLevelClient(client);
        }
        TestContext.setSharedVariable("client", clientElement.getText());
        clientElement.click();
        card.waitForPageLoad();
    }

    @And("^user clicks \"([^\"]*)\" saved Client$")
    public void userClicksSavedClient(String client){
        SelenideElement clientElement;
        switch (client) {
            case "random":
                clientElement = mainPage.getRandomElementFromCollection(mainPage.savedTopLevelClients);
                break;
            default:
                clientElement = mainPage.findSavedTopLevelClient(client);
        }
        TestContext.setSharedVariable("client", clientElement.getText());
        clientElement.click();
        card.waitForPageLoad();
    }

    @When("^user double clicks Publishers block$")
    public void userDoubleClicksPublishersBlock(){
        mainPage.doubleClickOnPublisherButton();
    }

    @When("^user double clicks Top level clients block$")
    public void userDoubleClicksTopLevelClientsBlock(){
        mainPage.doubleClickOnTopLevelClientButton();
    }


    @Then("^Advertisers list isn't shown$")
    public void advertisersListIsnTShown(){
        mainPage.waitUntilSubTreeBlockIsHidden("Advetisers");
    }

    @And("^\"([^\"]*)\" is added to cookie \"([^\"]*)\"$")
    public void isAddedToCookies(String value, String cookie){
        Assert.assertTrue(mainPage.checkCookie(cookie, value));
    }

    @And("^\"([^\"]*)\" isn't added to cookie \"([^\"]*)\"$")
    public void isnTExistedInCookie(String value, String cookie){
        Assert.assertFalse(mainPage.checkCookie(cookie, value));
    }

    @Then("^Publishers list isn't shown$")
    public void publishersListIsnTShown(){
        mainPage.waitUntilSubTreeBlockIsHidden("Publishers");
    }

    @Then("^top level clients list isn't shown$")
    public void topLevelClientsListIsnTShown(){
        mainPage.waitUntilSubTreeBlockIsHidden("Top level clients");
    }

    @And("^card image is shown$")
    public void cardImageIsShown(){
        Assert.assertTrue(card.checkImage());
    }

    @Then("^card title is \"([^\"]*)\"$")
    public void cardTitleIs(String title){
        Assert.assertTrue(card.checkTitle(title));
    }

    @And("^card image is on ([^\"]*)$")
    public void cardImageIsOn(String src){
        Assert.assertTrue(card.checkImageSrc(src));
    }

    @And("^card description is equal to ([^\"]*)$")
    public void cardDescriptionIsEqualToDescription(String file){
        Assert.assertTrue(card.checkShortDescription(file));
    }

    @Then("^Moved to save button is disabled$")
    public void movedToSaveButtonIsDisabled(){
        Assert.assertTrue(card.MovedToSaveButtonIsDisabled());
    }

    @And("^user scrolls textarea to the middle$")
    public void userScrollsTextareaToTheMiddle(){
        card.scrollTextAreaToTheMiddle();
    }

    @And("^user scrolls textarea to the end$")
    public void userScrollsTextareaToTheEnd(){
        card.scrollTextAreaToTheEnd();
    }

    @Then("^Moved to save button is enabled$")
    public void movedToSaveButtonIsEnabled(){
        Assert.assertFalse(card.MovedToSaveButtonIsDisabled());
    }

    @Then("^user clicks move to saved button$")
    public void userClicksMoveToSavedButton(){
        card.clickMoveToSavedButton();
    }

    @And("^user clicks Advertiser in Saved articles$")
    public void userClicksAdvertiserInSavedArticles(){
        mainPage.clickAdvertisersButtonInSavedBlock();
    }

    @And("^user clicks Publisher in Saved articles$")
    public void userClicksPublisherInSavedArticles(){
        mainPage.clickPublishersButtonInSavedBlock();
    }

    @And("^user clicks Top level clients in Saved articles$")
    public void userClicksTopLevelClientsInSavedArticles(){
        mainPage.clickTopLevelButtonInSavedBlock();
    }

    @When("^user clicks avatar icon$")
    public void userClicksAvatarIcon() {
        mainPage.clickAvatar();
    }

    @And("^user increases image scale on (\\d+)%$")
    public void userIncreasesImageScaleTo(int arg0){
        card.changeScale(arg0);
    }

    @Then("^image size is ([^\"]*)$")
    public void imageSizeIsX(String s){
        Assert.assertEquals(card.getImageSize(), s);
    }

    @And("^user decreases image scale on (\\d+)%$")
    public void userDecreasesImageScaleTo(int arg0){
        card.changeScale(-arg0);
    }


    @Then("^selected Advertiser ([^\"]*) shown in Saved Advertiser Articles$")
    public void selectedAdvertiserIsShownInSavedAdvertiserArticles(String is){
        String arg0 = (String) TestContext.getSharedVariable("adver");
        if (is.equals("is"))
            Assert.assertTrue(mainPage.textInElements(mainPage.savedAdvertisers).contains(arg0));
        else
            Assert.assertFalse(mainPage.textInElements(mainPage.savedAdvertisers).contains(arg0));
    }

    @And("^selected Advertiser ([^\"]*) shown in Advertiser Articles to read$")
    public void selectedAdvertiserIsnTShownInAdvertiserArticlesToRead(String is){
        String arg0 = (String) TestContext.getSharedVariable("adver");
        if (is.equals("is"))
            Assert.assertTrue(mainPage.textInElements(mainPage.advertisers).contains(arg0));
        else
            Assert.assertFalse(mainPage.textInElements(mainPage.advertisers).contains(arg0));
    }

    @And("^selected Advertiser ([^\"]*) added to cookie \"([^\"]*)\"$")
    public void selectedAdvertiserIsAddedToCookie(String is, String cookie){
        String value = (String) TestContext.getSharedVariable("adver");
        if (is.equals("is"))
            Assert.assertTrue(mainPage.checkCookie(cookie, value));
        else
            Assert.assertFalse(mainPage.checkCookie(cookie, value));
    }

    @Then("^selected Publisher ([^\"]*) shown in Saved Publisher Articles$")
    public void selectedPublisherIsShownInSavedArticles(String is){
        String arg0 = (String) TestContext.getSharedVariable("pub");
        if (is.equals("is"))
            Assert.assertTrue(mainPage.textInElements(mainPage.savedPublishers).contains(arg0));
        else
            Assert.assertFalse(mainPage.textInElements(mainPage.savedPublishers).contains(arg0));
    }

    @And("^selected Publisher ([^\"]*) shown in Publisher Articles to read$")
    public void selectedPublisherIsnTShownInArticlesToRead(String is){
        String arg0 = (String) TestContext.getSharedVariable("pub");
        if (is.equals("is"))
            Assert.assertTrue(mainPage.textInElements(mainPage.publishers).contains(arg0));
        else
            Assert.assertFalse(mainPage.textInElements(mainPage.publishers).contains(arg0));
    }

    @And("^selected Publisher ([^\"]*) added to cookie \"([^\"]*)\"$")
    public void selectedPublisherIsAddedToCookie(String is, String cookie){
        String value = (String) TestContext.getSharedVariable("pub");
        if (is.equals("is"))
            Assert.assertTrue(mainPage.checkCookie(cookie, value));
        else
            Assert.assertFalse(mainPage.checkCookie(cookie, value));
    }

    @Then("^selected Client ([^\"]*) shown in Saved Top level clients Articles$")
    public void selectedClientIsShownInSavedTopLevelClientsArticles(String is){
        String arg0 = (String) TestContext.getSharedVariable("client");
        if (is.equals("is"))
            Assert.assertTrue(mainPage.textInElements(mainPage.savedTopLevelClients).contains(arg0));
        else
            Assert.assertFalse(mainPage.textInElements(mainPage.savedTopLevelClients).contains(arg0));
    }

    @And("^selected Client ([^\"]*) shown in Top Level clients Articles to read$")
    public void selectedClientIsnTShownInTopLevelClientsArticlesToRead(String is){
        String arg0 = (String) TestContext.getSharedVariable("client");
        if (is.equals("is"))
            Assert.assertTrue(mainPage.textInElements(mainPage.topLevelClients).contains(arg0));
        else
            Assert.assertFalse(mainPage.textInElements(mainPage.topLevelClients).contains(arg0));
    }

    @And("^selected Client ([^\"]*) added to cookie \"([^\"]*)\"$")
    public void selectedClientIsAddedToCookie(String is, String cookie){
        String value = (String) TestContext.getSharedVariable("client");
        if (is.equals("is"))
            Assert.assertTrue(mainPage.checkCookie(cookie, value));
        else
            Assert.assertFalse(mainPage.checkCookie(cookie, value));
    }

    @And("^user clicks Remove from saved button$")
    public void userClicksRemoveFromSavedButton(){
        card.clickRemovedFromSavedButton();
    }

    @And("^random Advertiser ([^\"]*) added in cookie \"([^\"]*)\"$")
    public void randomAdvertiserIsnTAddedInCookie(String is, String cookie){
        String value = (String) TestContext.getSharedVariable("adver");
        if (is.equals("is"))
            Assert.assertTrue(mainPage.checkCookie(cookie, value));
        else
            Assert.assertFalse(mainPage.checkCookie(cookie, value));
    }

    @And("^user clicks Saved Advertiser block$")
    public void userClicksSavedAdvertiserBlock(){
        mainPage.clickAdvertisersButtonInSavedBlock();
    }

    @And("^user clicks Saved Publisher block$")
    public void userClicksSavedPublisherBlock(){
        mainPage.clickPublishersButtonInSavedBlock();
    }

    @And("^user clicks Saved Top level clients block$")
    public void userClicksSavedTopLevelClientsBlock(){
        mainPage.clickTopLevelButtonInSavedBlock();
    }

    @And("^selected Advertiser is added to cookie \"([^\"]*)\" once$")
    public void selectedAdvertiserIsAddedToCookieOnce(String arg0){
        String value = (String) TestContext.getSharedVariable("adver");
        Assert.assertFalse(mainPage.checkCookie(arg0,value+","+value));
    }

    @And("^Saved Advertiser list hasn't duplicates$")
    public void advertiserListHasnTDuplicates(){
       Assert.assertTrue(mainPage.checkForDuplicates(mainPage.savedAdvertisers));
    }
}

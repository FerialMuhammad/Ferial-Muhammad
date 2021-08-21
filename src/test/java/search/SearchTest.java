package search;

import base.BaseTest;
import com.google.common.base.CharMatcher;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchResultPage;

public class SearchTest extends BaseTest {
    private String keyWord;


    @Test
    public void suggestionsRelevanceTest(){
        keyWord = "dog";
        Assert.assertTrue(homePage.get1stSuggestion(keyWord).toLowerCase().contains(keyWord));
    }

    @Test
    public void oneWordAutoCorrectSuggestionTest(){
        keyWord = "srceen";
        Assert.assertTrue(homePage.get1stSuggestion(keyWord).toLowerCase().contains("screen"));
    }

    @Test
    public void twoWordAutoCorrectSuggestionTest(){
        keyWord = "balck srceen";
        Assert.assertTrue(homePage.get1stSuggestion(keyWord).toLowerCase().contains("black screen"));
    }

    @Test
    public void oneWordSearchResultRelevanceTest(){
        keyWord = "straw";
        SearchResultPage searchResultPage = homePage.searchWithKeyword(keyWord);
        Assert.assertFalse(searchResultPage.getResults(keyWord).isEmpty());
    }

    @Test
    public void twoWordSearchResultRelevanceTest(){
        keyWord = "straw hat";
        SearchResultPage searchResultPage = homePage.searchWithKeyword(keyWord);
        Assert.assertFalse(searchResultPage.getResults(keyWord).isEmpty());
    }

    @Test
    public void searchResultMaintainedTest(){
        keyWord = "book";
        SearchResultPage searchResultPage = homePage.searchWithKeyword(keyWord);
        Assert.assertEquals(searchResultPage.openResult(keyWord),keyWord);
    }

    @Test
    public void clearSearchBoxTest(){
        keyWord = "clear";
        SearchResultPage searchResultPage = homePage.searchWithKeyword(keyWord);
        Assert.assertTrue(searchResultPage.clearSearchbox().isEmpty());
    }

    @Test
    public void searchWithLinkTest(){
        String url = "https://github.com/instabug";
        SearchResultPage searchResultPage = homePage.searchWithLink(url);
        Assert.assertTrue(searchResultPage.checkPresenceOfLink(url));
    }

    @Test
    public void numberOfResultsTest(){
        keyWord = "Sea";
        SearchResultPage searchResultPage = homePage.searchWithKeyword(keyWord);
        Assert.assertTrue(Long.parseLong(CharMatcher.inRange('0', '9').retainFrom(searchResultPage.getNumberOfResults())) > 1);
    }

    @Test
    public void autoCorrectResultTest(){
        keyWord = "intsaubg";
        SearchResultPage searchResultPage = homePage.searchWithKeyword(keyWord);
        Assert.assertEquals(searchResultPage.autoCorrectSearchResult().toLowerCase(),"instabug");
    }
    @Test
    public void searchByNumbersTest(){
        keyWord = "123";
        SearchResultPage searchResultPage = homePage.searchWithKeyword(keyWord);
        Assert.assertFalse(searchResultPage.getResults(keyWord).isEmpty());
    }
    @Test
    public void searchBySpecialCharacterTest(){
        keyWord = "$";
        SearchResultPage searchResultPage = homePage.searchWithKeyword(keyWord);
        Assert.assertFalse(searchResultPage.getResults(keyWord).isEmpty());
    }
    @Test
    public void searchByArabicTest(){
        keyWord = "طائر";
        SearchResultPage searchResultPage = homePage.searchWithKeyword(keyWord);
        Assert.assertFalse(searchResultPage.getResults(keyWord).isEmpty());
    }
    @Test
    public void searchByUnmatchedKeyword(){
        keyWord = "ejcbg.kdfjfv";
        SearchResultPage searchResultPage = homePage.searchWithKeyword(keyWord);
        Assert.assertEquals(searchResultPage.getUnmatchedResult(),"Your search - "+keyWord+" - did not match any documents.");
    }

}

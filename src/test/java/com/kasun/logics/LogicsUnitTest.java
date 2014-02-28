package com.kasun.logics;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LogicsUnitTest {

    Logics logics = new Logics();

    @Test(dataProvider = "addDataProvider")
    public void testSplitSentence(String sentence, String[] expected) {
        String[] words = logics.splitSentence(sentence);
        Assert.assertEquals(expected, words);
    }

    @DataProvider(name = "addDataProvider")
    public Object[][] getDataProvider() {
        String[] test1 = { "I", "am", "going", "to", "a", "school" };
        String[] test2 = { "She", "is", "eating", "many", "foods" };
        return new Object[][] { { "I am going to a school", test1 }, { "She is eating many foods", test2 } };
    }

}

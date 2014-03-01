package com.kasun.logics.translate;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.kasun.logics.translate.LogicsPresentContinuesLogics;

public class LogicsPresentContinuesUnitTest {

    LogicsPresentContinuesLogics logicsPresentContinuesLogics = new LogicsPresentContinuesLogics();

    @Test(dataProvider = "addDataProvider")
    public void testSplitSentence(String sentence, String[] expected) {
        String[] words = logicsPresentContinuesLogics.splitSentence(sentence);
        Assert.assertEquals(expected, words);
    }

    @DataProvider(name = "addDataProvider")
    public Object[][] getDataProvider() {
        String[] test1 = { "I", "am", "going", "to", "a", "school" };
        String[] test2 = { "She", "is", "eating", "many", "foods" };
        return new Object[][] { { "I am going to a school", test1 }, { "She is eating many foods", test2 } };
    }

    @Test(dataProvider = "addDataProviderToCheckTence")
    public void testGetTence(String sentence, int expected) {
        int tence = logicsPresentContinuesLogics.getTence(sentence);
        Assert.assertEquals(expected, tence);
    }

    @DataProvider(name = "addDataProviderToCheckTence")
    public Object[][] getDataProviderForTence() {
        return new Object[][] { { "I am going to a school", 0 }, { "She was eating many foods", 1 } };
    }

}

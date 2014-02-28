package com.kasun.translate.sentences;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.kasun.translate.sentences.TranslatePresentCountinues;

public class TranslatePresentCountinuesUnitTest {

    TranslatePresentCountinues translatePresentCountinues = new TranslatePresentCountinues();

    @Test(dataProvider = "addDataProvider")
    public void testGetObjectValue(String sentence, String expected) {

        translatePresentCountinues.setSentence(sentence);
        String objectvalue = translatePresentCountinues.getObjectValue(sentence);
        Assert.assertEquals(expected, objectvalue);
    }

    @DataProvider(name = "addDataProvider")
    public Object[][] getDataProvider() {
        return new Object[][] { { "I am going to a school", "a" }, { "He is going to an ante", "an" },
                { "she is eating many foods", "many" }, { "I am going to home", "" } };
    }
}

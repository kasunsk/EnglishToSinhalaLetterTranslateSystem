package com.kasun.logics.translate;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.kasun.logics.translate.ContinuesLogics;

public class LogicsContinuesUnitTest {

    ContinuesLogics continuesLogics = new ContinuesLogics();

    @Test(dataProvider = "addDataProvider")
    public void testSplitSentence(String sentence, String[] expected) {
        String[] words = continuesLogics.splitSentence(sentence);
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
        int tence = continuesLogics.getTence(sentence);
        Assert.assertEquals(expected, tence);
    }

    @DataProvider(name = "addDataProviderToCheckTence")
    public Object[][] getDataProviderForTence() {
        return new Object[][] { { "I am going to a school", 0 }, { "She was eating many foods", 1 } };
    }
    
    @Test(dataProvider = "addDataProviderToCheckObjects")
    public void testSetAllObjectToArray(String sentence, ArrayList<String> expected){
        ArrayList<String> list = continuesLogics.setAllObjectToArrayList(sentence);
        Assert.assertEquals(expected, list);
    }
    
    @DataProvider(name = "addDataProviderToCheckObjects")
    public Object[][] getDataProviderForObject() {
        String test1 = "I am going to school";
        String test2 = "He is going to home tomorow";
        
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("school");
        
        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("home");
        list2.add("tomorow");
        
        return new Object[][]{ {test1,list1 }, {test2,list2} };
    }

}

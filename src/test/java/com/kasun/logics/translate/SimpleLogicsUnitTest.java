package com.kasun.logics.translate;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SimpleLogicsUnitTest {
    
    @Test(dataProvider = "addDataProvider")
    public void testSetSubjectValue(String sentence,int expected){
        SimpleLogics simpleLogics = new SimpleLogics();
        simpleLogics.setSubjectValue(sentence);
        int subjectValue = simpleLogics.getSubjectValue();
        System.out.println("subjectValue "+subjectValue);
        System.out.println("expected "+expected);
        Assert.assertEquals(expected, subjectValue);
    }
    
    @DataProvider(name = "addDataProvider")
    public Object[][] getDataProvider() {
        return new Object[][] { { "He goes home", 0 }, { "They eat rice", 1 },{ "Fathers eat rice", 1 } };
    }

}

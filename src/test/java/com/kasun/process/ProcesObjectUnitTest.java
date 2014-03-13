package com.kasun.process;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.kasun.process.ProcesObject;

public class ProcesObjectUnitTest {

     @Test(dataProvider = "addDataProvider")
     public void testIsContinues(String sentence, boolean expected) {
     ProcesObject procesObject = new ProcesObject();
     boolean actual = procesObject.isContinues(sentence);
     Assert.assertEquals(expected, actual);
     }
    
     @DataProvider(name = "addDataProvider")
     public Object[][] getDataProvider() {
     return new Object[][] { { "I am going to a school", true }, { "She is a girl", false }, { "He is going home", true } };
     }
    
}

package com.kasun.run;

import com.kasun.translate.sentences.TranslateCountinues;
import com.kasun.translate.sentences.TranslateSimple;

public class Run {

    public static void main(String[] args) {

        TranslateSimple translateSimple = new TranslateSimple();

        String sentence = "Farmers eat rice";

        System.out.println(translateSimple.getSinhalaMeaning(sentence));

    }
}

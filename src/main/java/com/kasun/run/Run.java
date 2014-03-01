package com.kasun.run;

import com.kasun.translate.sentences.TranslatePresentCountinues;

public class Run {

    public static void main(String[] args) {

        TranslatePresentCountinues translatePresentCountinues = new TranslatePresentCountinues();

        String sentence = "Teacher was reading a book";

        System.out.println(translatePresentCountinues.getSinhalaMeaning(sentence));

    }
}

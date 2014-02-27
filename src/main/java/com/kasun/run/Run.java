package com.kasun.run;

import com.kasun.translate.Translate;

public class Run {

    public static void main(String[] args) {

        Translate translate = new Translate();

        String sentence = "Teacher is reading book";

        System.out.println(translate.translate(sentence));
    }
}

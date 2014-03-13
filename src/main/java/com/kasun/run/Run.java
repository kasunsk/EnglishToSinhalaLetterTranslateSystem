package com.kasun.run;

import java.util.Scanner;

import com.kasun.sentenceDitector.SentenceDitector;
import com.kasun.translate.sentences.TranslateCountinues;
import com.kasun.translate.sentences.TranslateSimple;

public class Run {

    public static void main(String[] args) {

        TranslateSimple translateSimple = new TranslateSimple();
        TranslateCountinues translateCountinues = new TranslateCountinues();
        SentenceDitector sentenceDitector = new SentenceDitector();

        String sentence = "hi";

        while (!(sentence.equals(" "))) {

            System.out.print("Enter English sentence here : ");
            @SuppressWarnings("resource")
            Scanner scan = new Scanner(System.in);
            sentence = scan.nextLine();

            String meaning;
//            if (sentenceDitector.ditectSentence(sentence).equals("simple")) {
//                meaning = translateSimple.getSinhalaMeaning(sentence);
//            } else {
                meaning = translateCountinues.getMeaning(sentence);
         //   }
            System.out.print("Meaning is : ");
            System.out.println(meaning);
        }
    }
}

package com.kasun.translate.sentences;

import com.kasun.logics.Logics;
import com.kasun.translate.Translate;

public class TranslatePresentCountinues implements Translate {

    private String[] englishSubject = { "I", "He", "Teacher", "Student", "You", "Mother", "Father", "Farmer" };
    private String[] sinhalaSubject = { "මම", "ඔහු", "ගුරුවරයා", "ළමයා", "ඔබ", "අම්මා", "තාත්තා", "ගොවියා" };

    private String[] englishSubjectPlurel = { "We", "They" };
    private String[] sinhalaSubjectPlurel = { "අපි", "ඔව්න්" };

    private String[] englishVerb = { "coming", "eating", "drinking", "riding", "runing", "kissing", "reading", "going" };
    private String[] sinhalaVerb = { "යමින්", "කමින්", "බොමින්", "පදිමින්", "දුවමින්", "හාදු දෙමින්", "කියවමින්", "යමින්" };

    private String[] englishObject = { "home", "school", "rice", "bicycle", "book", "him", "her", "me" };
    private String[] sinhalaObject = { "ගෙදර", "පාසල්", "බත්", "බයිසිකලය", "පොත", "ඔහුට", "ඇයට", "මට" };

    private String[] objectValues = { "a", "an", "the", "many", "more" };

    private String[] sentence = null;

    String wordSubject = "";
    String wordVerb = "";
    String wordObject = "";

    private int subjectValue = 0;

    Logics logic = new Logics();

    public void setSentence(String sntnce) {
        String[] sentence = logic.sentenceDivide(sntnce);
        this.sentence = sentence;
    }

    public String getSubjectTranslated() {
        int i = 0;
        while (englishSubject.length > i) {
            if (sentence[0].equals(englishSubject[i])) {
                wordSubject = sinhalaSubject[i];
                break;
            }
            i++;
        }
        i = 0;
        while (englishSubjectPlurel.length > i) {
            if (sentence[0].equals(englishSubjectPlurel[i])) {
                this.subjectValue = 1;
                wordSubject = sinhalaSubjectPlurel[i];
                System.out.println("subjectValue : " + this.subjectValue);
                break;
            }
            i++;
        }
        return wordSubject;
    }

    public String getVerbTranslated() {
        int i = 0;
        while (englishVerb.length > i) {
            if (sentence[1].equals(englishVerb[i])) {
                wordVerb = sinhalaVerb[i];
                break;
            }
            i++;
        }
        return wordVerb;
    }

    public String getObjectTranslated() {
        int i = 0;
        while (englishObject.length > i) {
            if (sentence[2].equals(englishObject[i])) {
                wordObject = sinhalaObject[i];
                break;
            }
            i++;
        }
        return wordObject;
    }

    public String getObjectValue(String sntnce) {
        String[] sentenc = logic.splitSentence(sntnce);
        int j = 0;
        while (j < sentenc.length) {
            if (sentenc[j].equals(sentence[2])) {
                break;
            }
            j++;
        }
        int i = 0;
        while (i < objectValues.length) {
            if (sentenc[j - 1].equals(objectValues[i])) {
                return objectValues[i];
            }
            i++;
        }
        return "";
    }

    @Override
    public String getSinhalaMeaning(String sentec) {
        TranslatePresentCountinues translatePresentCountinues = new TranslatePresentCountinues();
        translatePresentCountinues.setSentence(sentec);
        String objectValue = translatePresentCountinues.getObjectValue(sentec);
        String objectValueDefiner = "";
        String sentenceEnd = "සිටියි";

        if (translatePresentCountinues.sentence[0].equals("I")) {
            sentenceEnd = "සිටින්නෙමි";
        } else if (translatePresentCountinues.sentence[0].equals("We")) {
            sentenceEnd = "සිටින්නෙමු";
        } else if (subjectValue == 1) {
            sentenceEnd = "සිටිති";
        }

        System.out.println("subjectValue : " + this.subjectValue);

        if (objectValue.equals("a") || objectValue.equals("an")) {
            objectValueDefiner = "ක්";
        }

        String sinhalaMeaning = translatePresentCountinues.getSubjectTranslated() + " "
                + translatePresentCountinues.getObjectTranslated() + objectValueDefiner + " "
                + translatePresentCountinues.getVerbTranslated() + " " + sentenceEnd + ".";

        return sinhalaMeaning;
    }
}

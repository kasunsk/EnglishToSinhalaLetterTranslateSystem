package com.kasun.translate;

import com.kasun.logics.Logics;

public class Translate {

    private String[] englishSubject = { "I", "He", "Teacher", "Student", "You", "Mother", "Father", "Farmer" };
    private String[] sinhalaSubject = { "මම", "ඔහු", "ගුරුවරයා", "ළමයා", "ඔබ", "අම්මා", "තාත්තා", "ගොවියා" };

    private String[] englishVerb = { "coming", "eating", "drinking", "riding", "runing", "kissing", "reading", "going" };
    private String[] sinhalaVerb = { "යමින්", "කමින්", "බොමින්", "පදිමින්", "දුවමින්", "හාදු දෙමින්", "කියවමින්", "යමින්" };

    private String[] englishObject = { "home", "school", "rice", "bicycle", "book", "him", "her", "me" };
    private String[] sinhalaObject = { "ගෙදර", "පාසල්", "බත්", "බයිසිකලය", "පොත", "ඔහුට", "ඇයට", "මට" };

    Logics logic = new Logics();

    public String translate(String sntence) {
        String[] sentence = logic.sentenceDivide(sntence);
        
        String wordSubject = "";
        String wordVerb = "";
        String wordObject = "";

        int i = 0;
        while (englishSubject.length > i) {
            if (sentence[0].equals(englishSubject[i])) {
                wordSubject = sinhalaSubject[i];
                break;
            }
            i++;
        }
        
        i = 0;
        while (englishVerb.length > i) {
            if (sentence[1].equals(englishVerb[i])) {
                wordVerb = sinhalaVerb[i];
                break;
            }
            i++;
        }
        
        i = 0;
        while (englishObject.length > i) {
            if (sentence[2].equals(englishObject[i])) {
                wordObject = sinhalaObject[i];
                break;
            }
            i++;
        }
        i=0;

        return (wordSubject+" "+wordObject+" "+wordVerb+" සිටිනවා");
    }

}

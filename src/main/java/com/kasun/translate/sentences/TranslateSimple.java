package com.kasun.translate.sentences;

import com.kasun.logics.translate.SimpleLogics;
import com.kasun.translate.Translate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslateSimple implements Translate {
    
    private static final Logger log = LoggerFactory.getLogger(TranslateSimple.class);

    private String[] englishSubject = { "I", "He", "She", "Teacher", "Student", "You", "Mother", "Father", "Farmer" };
    private String[] sinhalaSubject = { "මම", "ඔහු", "ඇය", "ගුරුවරයා", "ළමයා", "ඔබ", "අම්මා", "තාත්තා", "ගොවියා" };
    private String[] sinhalaSubjectPlurelComons = { "", "", "", "ගුරුවරු", "ළමයි", "ඔබලා", "අම්මාවරු", "පියවරු", "ගොවියෝ" };

    private String[] englishSubjectPlurel = { "We", "They" };
    private String[] sinhalaSubjectPlurel = { "අපි", "ඔව්න්" };

    private String[] englishObject = { "home", "school", "rice", "bicycle", "book", "him", "her", "me", "apple" };
    private String[] sinhalaObject = { "ගෙදර", "පාසල්", "බත්", "බයිසිකලය", "පොත", "ඔහුට", "ඇයට", "මට", "ඇපල් ගෙඩිය" };

    String wordSubject = "";
    String wordVerb = "";
    String wordObject = "";

    private String[] sentence = null;

    private int subjectValue = 0;
    private int tence = 0;

    SimpleLogics simpleLogics = new SimpleLogics();

    public void setSentence(String sntnce) {
        String[] sentence = simpleLogics.sentenceDivide(sntnce);
        this.sentence = sentence;
    }

    public String getSubjectTranslated(String senten) {
        SimpleLogics simpleLogics = new SimpleLogics();
        simpleLogics.setSubjectValue(senten);
        String subject = simpleLogics.getSubject();
        subjectValue = simpleLogics.getSubjectValue();
        
        log.info("Subject Value in TranslateSimple "+subjectValue);
        
        int i = 0;
        while (englishSubject.length > i) {
            if (subject.equals(englishSubject[i])) {
                if (subjectValue == 0) {
                    wordSubject = sinhalaSubject[i];
                } else if (subjectValue == 1) {
                    wordSubject = sinhalaSubjectPlurelComons[i];
                }
                break;
            }
            i++;
        }
        i = 0;
        while (englishSubjectPlurel.length > i) {
            if (subject.equals(englishSubjectPlurel[i])) {
                this.subjectValue = 1;
                wordSubject = sinhalaSubjectPlurel[i];
                break;
            }
            i++;
        }
      //  SimpleLogics.SubjectValue = subjectValue;
        return wordSubject;
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

    @Override
    public  String getSinhalaMeaning(String sentec) {
        simpleLogics.setSubjectValue(sentec);        
        simpleLogics.setTence(sentec);
        setSentence(sentec);
        tence = simpleLogics.getTence(sentec);
       // getSubjectTranslated(sentec);
        String objectValueDefiner = "";
        String sinhalaMeaning = getSubjectTranslated(sentec) + " " + getObjectTranslated()
                + objectValueDefiner + " " + simpleLogics.getVerbMeaning() + ".";
        simpleLogics.setSimpleLogicsSubjectValue(0);
        return sinhalaMeaning;
    }
}

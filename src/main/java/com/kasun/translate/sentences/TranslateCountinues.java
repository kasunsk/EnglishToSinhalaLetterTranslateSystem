package com.kasun.translate.sentences;

import java.util.ArrayList;

import com.kasun.logics.translate.ContinuesLogics;
import com.kasun.process.ProcesObject;
import com.kasun.process.ProcesSubject;
import com.kasun.process.ProsesVerb;
import com.kasun.sentence.Sentence;
import com.kasun.translate.Translate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslateCountinues implements Translate {

    private static final Logger log = LoggerFactory.getLogger(TranslateCountinues.class);

    private String[] englishSubject = { "I", "He", "She", "Teacher", "Student", "You", "Mother", "Father", "Farmer" };
    private String[] sinhalaSubject = { "මම", "ඔහු", "ඇය", "ගුරුවරයා", "ළමයා", "ඔබ", "අම්මා", "තාත්තා", "ගොවියා" };

    private String[] englishSubjectPlurel = { "We", "They" };
    private String[] sinhalaSubjectPlurel = { "අපි", "ඔව්න්" };

    private String[] preposisions = { "to", "for", "from", "on", "in", "with", "about","" };
    private String[] proposisionsMeaning = { "ට", " වෙනුවෙන්", " සිට", " මත", " තුල", " සමග", " පිලිබදව","" };

    public static String[] englishVerb = { "coming", "eating", "drinking", "riding", "runing", "kissing", "reading", "going","talking","writing" };
    private String[] sinhalaVerb = { "එමින්", "කමින්", "බොමින්", "පදිමින්", "දුවමින්", "හාදු දෙමින්", "කියවමින්", "යමින්","කතා කරමින්","ලියමින්" };
    private String[] sinhalaVerbNormal = { "එයි", "කයි", "බොයි", "පදියි", "දුවයි", "හාදු දෙයි", "කියවයි", "යයි","කතා කරයි"," ලියයි" };
    private String[] sinhalaVerbNormalPlurel = {"එති", "කති","බොති","පදිති", "දුවති", "හදු දෙති","කියවති", "යති","කතා කරති"," ලියති"};

    private String[] englishObject = { "home", "school", "rice", "bicycle", "book", "him", "her", "me", "apple", "mother","eat","talk"};
    private String[] sinhalaObject = { "ගෙදර", "පාසල", "බත්", "බයිසිකලය", "පොත", "ඔහුට", "ඇයට", "මට", "ඇපල් ගෙඩිය", "මව","කන්න","කතා කරන්න"};

    private String[] englishTime = { "tomorow" };
    private String[] sinhalaTime = { "හෙට" };

    private String[] objectValues = { "a", "an", "the", "many", "more" };

    private String[] sentence = null;

    private Sentence sentenceOb = null;

    String wordSubject = "";
    String wordVerb = "";
    String wordObject = "";

    private int subjectValue = 0;
    private int tence = 0;

    ContinuesLogics logic = new ContinuesLogics();

    public void setSentence(String sntnce) {
        String[] sentence = logic.sentenceDivide(sntnce);
        this.sentence = sentence;
    }

    public void setSen(Sentence sen) {
        this.sentenceOb = sen;
    }

    public void setTence(String sntnce) {
        this.tence = logic.getTence(sntnce);
    }

    public String combinObjectAndProp() {
        String meaning = "";
        String[] obj = getObjectSinhalaMeaning();
        String[] pro = proposisionsMean();
        String[] mean = new String[pro.length];
        int i = mean.length - 1;
        int j = 0;
        while (0 <= i) {
            mean[j] = obj[i] + pro[i];
            meaning = meaning + mean[j] + " ";
            j++;
            i--;
        }

        return meaning;
    }

    @SuppressWarnings("unused")
    public String[] proposisionsMean() {
        String[] prpo1 = new String[sentenceOb.getObject().size()];
        String[] prpo = new String[sentenceOb.getPropsision().size()];
        sentenceOb.getPropsision().toArray(prpo);
        String[] propMeans = new String[prpo1.length];
        
        int z = 0;
        int m = 0;
        while(z < prpo1.length - prpo.length){
            prpo1[z] = "";
            z++;
        }
        while(z<prpo1.length){
            prpo1[z] = prpo[m];
            m++;
            z++;
        }
        int i = 0;
        int j = 0;
        int k = 0;
        if (prpo != null) {
            while (i < prpo1.length) {
                while (j < preposisions.length) {
                    if (prpo1[i].equals(preposisions[j])) {
                        propMeans[k] = proposisionsMeaning[j];
                        log.info("propMeans " + propMeans[k]);
                        k++;
                    }
                    j++;
                }
                j = 0;
                i++;
            }
        } else {
            propMeans = new String[0];
            propMeans[0] = "";
        }
        return propMeans;
    }

    public String getTimeMeaningFromObject() {
        ArrayList<String> list = new ArrayList<String>();
        list = sentenceOb.getTimes();
        String[] times = new String[list.size()];
        list.toArray(times);

        String timeMeaning = "";

        int j = 0;
        int i = 0;
        while (i < times.length) {
            while (j < englishTime.length) {
                if (times[i].equals(englishTime[j])) {
                    timeMeaning = sinhalaTime[j];
                }
                j++;
            }
            j = 0;
            i++;
        }

        return timeMeaning;
    }

    public String getMeaning(String sentence) {
        ProcesObject procesObject = new ProcesObject();
        ProsesVerb prosesVerb = new ProsesVerb();
        Sentence senten = logic.splitAndMakeSentence(sentence);
        setSen(senten);
        // String objectValue = getObjectValue(sentence);
        String objectValueDefiner = "";
        String sentenceEnd = getSentenceEnd(sentence);
        String sinhalaMeaning;
        
        ProcesSubject procesSubject = new ProcesSubject();

     //   String[] sinhalaMeanOfOb = getObjectSinhalaMeaning();

        // if (objectValue.equals("a") || objectValue.equals("an")) {
        // objectValueDefiner = "ක්";
        // }
        if(!(procesObject.isContinues(sentence))){
            sentenceEnd = "";
        }

        if (isTimeInObject()) {
            sinhalaMeaning = getSubjectMeaning() + " " + getTimeMeaningFromObject() + " " + procesObject.objMean(sentence)
                    + objectValueDefiner + prosesVerb.verbMeanOfSentence(sentence)+ ".";
        } 
//        else {
//            sinhalaMeaning = getSubjectMeaning() + " " +procesObject.objMean(sentence) + objectValueDefiner + getVerbMeaning() + " "
//                    + sentenceEnd + ".";
//        }
        
        else {
            sinhalaMeaning = procesSubject.getSubjectMean(sentence)  +procesObject.objMean(sentence)  + prosesVerb.verbMeanOfSentence(sentence) ;

        }

        senten = null;
        
        subjectValue = 0;
        tence = 0;
        
        wordSubject = "";
        wordVerb = "";
        wordObject = "";

        return sinhalaMeaning;
    }

    public String getSubjectMeaning() {
        int i = 0;
        while (englishSubject.length > i) {
            if (sentenceOb.getSubject().equals(englishSubject[i])) {
                wordSubject = sinhalaSubject[i];
                break;
            }
            i++;
        }
        i = 0;
        while (englishSubjectPlurel.length > i) {
            if (sentenceOb.getSubject().equals(englishSubjectPlurel[i])) {
                this.subjectValue = 1;
                wordSubject = sinhalaSubjectPlurel[i];
                break;
            }
            i++;
        }
        return wordSubject;
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
                break;
            }
            i++;
        }
        return wordSubject;
    }

    public boolean isTimeInObject() {
        int i = 0;
        int j = 0;
        ArrayList<String> list = new ArrayList<String>();
        list = sentenceOb.getObject();
        String[] objects = new String[list.size()];
        list.toArray(objects);

        while (i < objects.length) {
            while (j < englishTime.length) {
                if (objects[i].equals(englishTime[j])) {
                    return true;
                }
                j++;
            }
            j = 0;
            i++;
        }
        return false;
    }

    public String getVerbMeaning() {

        int i = 0;

        while (englishVerb.length > i) {
            if (sentenceOb.getVerb().equals(englishVerb[i])) {
                if (isTimeInObject() && subjectValue==0) {
                    wordVerb = sinhalaVerbNormal[i];
                    break;
                }else if(isTimeInObject() && subjectValue==1){
                    wordVerb = sinhalaVerbNormalPlurel[i];
                    break;
                } else {
                    wordVerb = sinhalaVerb[i];
                    break;
                }
            }
            i++;
        }
        return wordVerb;
    }

    public String getObjectMeaning(String word) {
        int i = 0;
        while ( i < englishObject.length) {
            log.info("i "+i);
            log.info("Check "+englishObject[i]);
            if (word.equals(englishObject[i])) {
                wordObject = sinhalaObject[i];
                break;
            }
            i++;
        }
        return wordObject;
    }

    public String[] getObjectSinhalaMeaning() {
        ArrayList<String> list = new ArrayList<String>();
        list = sentenceOb.getObject();
        String[] objects = new String[list.size()];
        list.toArray(objects);

        String[] sinMeanOfObjects = new String[objects.length];

        int i = 0;
        while (i < objects.length) {
            if (isATime(objects[i])) {
                sinMeanOfObjects[i] = "";
            } else {
                sinMeanOfObjects[i] = getObjectMeaning(objects[i]);
            }
            i++;
        }
        return sinMeanOfObjects;
    }

    public boolean isATime(String word) {
        int i = 0;
        while (i < englishTime.length) {
            if (word.equals(englishTime[i])) {
                return true;
            }
            i++;
        }
        return false;
    }

    @Override
    public String getSinhalaMeaning(String sentec) {
        return null;
    }

    public String getSentenceEnd(String sentec) {
        setSentence(sentec);
        setTence(sentec);
        getSubjectTranslated();

        int tence = this.tence;

        String sentenceEnd = "සිටියි";

        if (sentence[0].equals("I") && tence == 0) {
            sentenceEnd = "සිටින්නෙමි";
        } else if (sentence[0].equals("I") && tence == 1) {
            sentenceEnd = "සිටියෙමි";
        } else if (sentence[0].equals("We") && tence == 1) {
            sentenceEnd = "සිටියෙමු";
        } else if (sentence[0].equals("We") && tence == 0) {
            sentenceEnd = "සිටින්නෙමු";
        } else if (subjectValue == 1 && tence == 1) {
            sentenceEnd = "සිටියෝය";
        } else if (subjectValue == 1 && tence == 0) {
            sentenceEnd = "සිටිති";
        } else if (tence == 1) {
            sentenceEnd = "සිටියේය";
        }
        return sentenceEnd;
    }

}

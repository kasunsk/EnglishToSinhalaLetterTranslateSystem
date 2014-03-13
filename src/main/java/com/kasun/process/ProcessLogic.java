package com.kasun.process;

import java.util.ArrayList;

import com.kasun.logics.translate.ContinuesLogics;

public class ProcessLogic {

    private String[] beWorbs = { "is", "am", "are", "was", "were", "be","will" };
    
    private String[] hasHv ={"has","have","had"};
    private String[] hasHvMean  = {"ඇත", "ඇත","තිබුනා"};
    
    private String[] pastPari = {"gone","come","eatten","drunk","read"};
    private String[] pastPariMean = {"ගොස්"," පැමින"," කා","බී" ,"කියවා"};

    private String[] preposisions = { "to", "for", "from", "on", "in", "with", "about", "" };
    private String[] proposisionsMeaning = { "ට", " වෙනුවෙන්", " සිට", " මත", " තුල", " සමග", " පිලිබදව", "" };
    
    private String[] englishSubject = { "I", "He", "She", "Teacher", "Student", "You", "Mother", "Father", "Farmer" };
    private String[] sinhalaSubject = { "මම", "ඔහු", "ඇය", "ගුරුවරයා", "ළමයා", "ඔබ", "අම්මා", "තාත්තා", "ගොවියා" };

    private String[] englishObject = { "home", "school", "rice", "bicycle", "book", "books", "him", "her", "me", "apple",
            "mother", "eat", "talk", "father", "eat", "letter", "write", "teacher", "and", "eating", "reading", "writing" };
    private String[] sinhalaObject = { "ගෙදර", "පාසල", "බත්", "බයිසිකලය", "පොත", "පොත්", "ඔහුට", "ඇයට", "මා", "ඇපල් ගෙඩිය", "මව",
            "කන්න", "කතා කරන්න", "පියා", "කන්න", "ලියුම", "ලියන්න", "ගුරුවරයා", "සහා", "කමින්", "කියවමින්", "ලියමින්" };

    public static String[] englishVerbWithIng = { "coming", "eating", "drinking", "riding", "runing", "kissing", "reading",
            "going", "talking", "writing" };

    private String[] sm = { "his", "her", "their", "my" };
    private String[] smMean = { "ඔහුගේ", "ඇයගේ", "ඔව්න්ගේ", "මගේ" };

    private String[] englishTime = { "tomorow" };
    private String[] sinhalaTime = { "හෙට" };

    private String[] objectValues = { "a", "an", "many", "more", "the" };
    private String[] objectValuesMeaning = { "ක්", "ක්", "ගොඩාක්", "ගොඩාක්", "" };

    private String[] adjectives = { "small", "big", "litle", "huge" };
    private String[] adjectivesMeaning = { "කුඩා", "ලොකු", "පොඩ්ඩක්", "විශාල" };

    public String ownerShipMean(String word) {
        String mean = "";
        int i = 0;
        while (i < sm.length) {
            if (word.equals(sm[i])) {
                mean = smMean[i];
            }
            i++;
        }
        return mean;
    }
    
    public boolean isPastParticiple(String word){
        int i = 0;
        while(i < pastPari.length){
            if(word.equals(pastPari[i])){
                return true;
            }
            i++;
        }
        return false;
    }
    
    public boolean isPasiveVoice(String sentence){
        
        ContinuesLogics continuesLogics = new ContinuesLogics();
        String[] words = continuesLogics.splitSentence(sentence);
        
        int i = 0;
        while(i < words.length - 1){
            if(isAHvHs(words[i]) && isPastParticiple(words[i+1])){
                return true;
            }
            i++;
        }
        return false;    
    }

    public boolean isABeVerb(String word) {
        int i = 0;
        while (i < beWorbs.length) {
            if (word.equals(beWorbs[i])) {
                return true;
            }
            i++;
        }
        return false;
    }
    
    public boolean isAHvHs(String word){
        int i = 0;
        while(i < hasHv.length ){
            if(word.equals(hasHv[i])){
                return true;
            }
            i++;
        }
        return false;
    }

    public int beDitector(String sentence) {
        ContinuesLogics continuesLogics = new ContinuesLogics();
        String[] words = continuesLogics.splitSentence(sentence);
        int i = 0;
        while (i < words.length) {
            if (isABeVerb(words[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int prepoDitector(String sentence) {
        ContinuesLogics continuesLogics = new ContinuesLogics();
        String[] words = continuesLogics.splitSentence(sentence);
        int i = 0;
        int j = 0;
        while (i < words.length) {
            while (j < preposisions.length) {
                if (words[i].equals(preposisions[j])) {
                    return i;
                }
                j++;
            }
            j = 0;
            i++;
        }
        return 0;
    }

    public boolean isContinues(String sentence) {
        ContinuesLogics continuesLogics = new ContinuesLogics();
        String[] words = continuesLogics.splitSentence(sentence);
        int beWorb = beDitector(sentence);
        if (beWorb != -1) {
            if (isAVerbIng(words[beWorb + 1])) {
                return true;
            }
        }
        return false;
    }

    public boolean isOwnerShip(String word) {
        int i = 0;
        while (i < sm.length) {
            if (word.equals(sm[i])) {
                return true;
            }
            i++;
        }
        return false;
    }

    public String getObjectMeaning(String word) {
        int i = 0;
        String wordObject = "";
        while (i < englishObject.length) {
            if (word.equals(englishObject[i])) {
                wordObject = sinhalaObject[i];
                break;
            }
            i++;
        }
        return wordObject;
    }
    
    public String getAnyWordMeaning(String word){
        int i = 0;
        while(i < englishSubject.length){
            if(word.equals(englishSubject[i])){
               return sinhalaSubject[i];
            }
            i++;
        }
        i = 0;
        while(i < englishObject.length){
            if(word.equals(englishObject[i])){
               return sinhalaObject[i];
            }
            i++;
        }
        return "word not in database";
    }

    public boolean isPreposision(String word) {
        int i = 0;
        while (i < preposisions.length) {
            if (preposisions[i].equals(word)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean isAVerbIng(String word) {
        int i = 0;
        while (i < englishVerbWithIng.length) {
            if (word.equals(englishVerbWithIng[i])) {
                return true;
            }
            i++;
        }
        return false;
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

    public String propMean(String word) {
        int i = 0;
        while (i < preposisions.length) {
            if (word.equals(preposisions[i])) {
                return proposisionsMeaning[i];
            }
            i++;
        }
        return null;
    }

    public boolean isObVal(String word) {
        int i = 0;
        while (i < objectValues.length) {
            if (word.equals(objectValues[i])) {
                return true;
            }
            i++;
        }

        return false;
    }

    public String objValMean(String word) {
        String mean = "";
        int i = 0;
        while (i < objectValues.length) {
            if (word.equals(objectValues[i])) {
                mean = objectValuesMeaning[i];
            }
            i++;
        }
        return mean;
    }

    public int andPosision(ArrayList<String> object) {
        String[] objects = new String[object.size()];
        object.toArray(objects);
        int i = 0;
        while (i < objects.length) {
            if (objects[i].equals("and")) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean isIngVerb(String word) {
        int i = 0;
        while (i < englishVerbWithIng.length) {
            if (word.equals(englishVerbWithIng[i])) {
                return true;
            }
            i++;
        }
        return false;
    }

}

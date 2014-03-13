package com.kasun.process;

import java.util.ArrayList;

import com.kasun.logics.translate.ContinuesLogics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcesObject {

    private static final Logger log = LoggerFactory.getLogger(ProcesObject.class);

    private String[] beWorbs = { "is", "am", "are", "was", "were", "be" };
    
    private String[] hasHv ={"has","have","had"};
    private String[] hasHvMean  = {"තියෙනවා","තියෙනවා"," තිබුනා"};

    private String[] preposisions = { "to", "for", "from", "on", "in", "with", "about", "" };
    private String[] proposisionsMeaning = { "ට", " වෙනුවෙන්", " සිට", " මත", " තුල", " සමග", " පිලිබදව", "" };

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

    public ArrayList<String> makeObjectsAsList(String sentence) {
        ContinuesLogics continuesLogics = new ContinuesLogics();
        ProcessLogic processLogic = new ProcessLogic();
        ArrayList<String> obj = new ArrayList<String>();
        String[] words = continuesLogics.splitSentence(sentence);
        boolean check = true;
        int ch = 0;

        int i = 0;

        if (isContinues(sentence) || processLogic.isPasiveVoice(sentence)) {
            while (i < words.length) {
                if ((continuesLogics.isAVerbIng(words[i]) || processLogic.isPastParticiple(words[i])) && check) {
                    ch = 1;
                }
                if (!check) {
                    if (!(continuesLogics.isATime(words[i]))) {
                        obj.add(words[i]);
                    }
                }
                if (ch == 1) {
                    check = false;
                }
                i++;
            }
        } else {
            i = 0;
            while (i < words.length) {
                if (isABeVerb(words[i])  && check) {
                    ch = 1;
                }
                if (!check) {
                    if (!(continuesLogics.isATime(words[i]))) {
                        obj.add(words[i]);
                    }
                }
                if (ch == 1) {
                    check = false;
                }
                i++;
            }

        }
        return obj;
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

    public boolean isAndInObjects(String sentence) {
        ArrayList<String> obj = makeObjectsAsList(sentence);
        String[] objects = new String[obj.size()];
        obj.toArray(objects);
        int i = 0;
        while (i < objects.length) {
            if (objects[i].equals("and")) {
                return true;
            }
            i++;
        }
        return false;
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

    public ArrayList<String> veryfyAnd(String sentence) {
        ArrayList<String> obj = makeObjectsAsList(sentence);
        int remAndPosistion = andPosision(obj);
        ArrayList<String> modifiedObj = new ArrayList<String>();
        if (isAndInObjects(sentence) && !isIngVerb(obj.get(remAndPosistion + 1))) {
            String[] objects = new String[obj.size()];
            obj.toArray(objects);

            int count = 0;
            int i = andPosision(obj);
            int end = i + 1;
            int j = i - 1;
            int k = i + 1;

            ArrayList<String> beforAnd = new ArrayList<String>();
            ArrayList<String> afterAnd = new ArrayList<String>();

            ArrayList<String> beforAndM = new ArrayList<String>();
            ArrayList<String> afterAndM = new ArrayList<String>();

            while ((0 <= j) && !(isPreposision(objects[j]) || isABeVerb(objects[j]) || isIngVerb(objects[j]))) {
                beforAnd.add(objects[j]);
                j--;
                count++;
            }
            if ((0 <= j) && isPreposision(objects[j])) {
                beforAnd.add(objects[j]);
                count++;
            }

            j = 0;
            while (k < objects.length && !(isPreposision(objects[k]))) {
                end++;
                afterAnd.add(objects[k]);
                k++;
            }
            j = beforAnd.size() - 1;
            while (0 <= j) {
                beforAndM.add(beforAnd.get(j));
                j--;
            }
            k = 1;
            if (isPreposision(beforAnd.get(beforAnd.size() - 1))) {
                afterAndM.add(beforAnd.get(beforAnd.size() - 1));
            }
            k = 0;
            while (k < afterAnd.size()) {
                afterAndM.add(afterAnd.get(k));
                k++;
            }
            i = 0;
            ArrayList<String> modified = new ArrayList<String>();
            while (i < beforAndM.size()) {
                modified.add(beforAndM.get(i));
                i++;
            }
            i = 0;
            while (i < afterAndM.size()) {
                modified.add(afterAndM.get(i));
                i++;
            }

            i = 0;
            int start = andPosision(obj) - count;

            while (i <= start - 1) {
                modifiedObj.add(obj.get(i));
                i++;
            }
            i = 0;
            while (i < afterAndM.size()) {
                modifiedObj.add(afterAndM.get(i));
                start++;
                i++;
            }
            i = 0;
            if (!isIngVerb(obj.get(remAndPosistion + 1))) {
                modifiedObj.add("and");
            }
            while (i < beforAndM.size()) {
                modifiedObj.add(beforAndM.get(i));
                i++;
            }
            while (end < objects.length) {
                modifiedObj.add(objects[end]);
                log.info("objects[end]) " + objects[end]);
                end++;
            }

        } else {
            modifiedObj = obj;
        }
        return modifiedObj;
    }

    public String objMean(String sentence) {

        ArrayList<String> obj = veryfyAnd(sentence);
        String[] objects = new String[obj.size()];

        obj.toArray(objects);
        String mean = "";

        int i = 0;

        while (i < objects.length) {
            if (isPreposision(objects[i]) && !(isOwnerShip(objects[i + 1]))) {
                mean = getObjectMeaning(objects[i + 1]) + propMean(objects[i]) + " " + mean;
                i++;
            } else if (isPreposision(objects[i]) && isOwnerShip(objects[i + 1])) {
                mean = " " + ownerShipMean(objects[i + 1]) + " " + getObjectMeaning(objects[i + 2]) + propMean(objects[i]) + " "
                        + mean;
                i = i + 2;
            } else if (isObVal(objects[i])) {
                if (objects[i].equals("a") || objects[i].equals("an")) {
                    mean = getObjectMeaning(objects[i + 1]) + objValMean(objects[i]) + " " + mean;
                    i++;
                } else {
                    mean = " " + getObjectMeaning(objects[i + 1]) + " " + objValMean(objects[i]) + " " + mean;
                    i++;
                }
            } else if (isOwnerShip(objects[i])) {
                mean = ownerShipMean(objects[i]) + " " + getObjectMeaning(objects[i + 1]) + " " + mean;
                i++;
            } else {
                mean = getObjectMeaning(objects[i]) + " " + mean;
            }
            i++;
        }
        log.info("Object Meaning : " + mean);
        return mean;
    }
}

package com.kasun.logics.translate;

import java.util.ArrayList;
import java.util.List;

import com.kasun.logics.Logics;
import com.kasun.sentence.Sentence;
import com.kasun.translate.sentences.TranslateCountinues;
import com.kasun.translate.sentences.TranslateSimple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContinuesLogics implements Logics {

    private static final Logger log = LoggerFactory.getLogger(ContinuesLogics.class);

    private String[] beWorbs = { "is", "am", "are", "was", "were", "be" };
    private String[] preposisions = { "to", "for", "from", "on", "in", "with", "about" };
    private String[] proposisionsMeaning = { "ට්", " වෙනුවෙන්", " සිට", " ", " තුල", " සමග", " පිලිබදව" };

    public static String[] englishVerbWithIng = { "coming", "eating", "drinking", "riding", "runing", "kissing", "reading",
            "going", "writing","talking" };

    private String[] englishTime = { "tomorow" };
    private String[] sinhalaTime = { "හෙට" };

    Sentence sentence = new Sentence();

    @Override
    public String[] splitSentence(String sentence) {
        String words[];
        words = sentence.split(" ");
        return words;
    }

    public boolean isATime(String word) {
        if (word == null) {
            return false;
        }
        int i = 0;
        while (i < englishTime.length) {
            if (word.equals(englishTime[i])) {
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

    public Sentence splitAndMakeSentence(String sentenc) {
        String[] words = splitSentence(sentenc);
        sentence.setSubject(words[0]);
        sentence.setBeVerb(words[1]);
        sentence.setVerb(words[2]);
        sentence.setObject(getObjectsFromSentence(sentenc));
        sentence.setPropsision(getPreposisionsFromSentence(sentenc));
        sentence.setTimes(getTimeFromSentence(sentenc));
        return sentence;
    }

    public ArrayList<String> getPreposisionsFromSentence(String senten) {
        String[] words = splitSentence(senten);
        ArrayList<String> prepo = new ArrayList<String>();
        int i = 0;
        while (i < words.length) {
            if (isPreposision(words[i])) {
                prepo.add(words[i]);
            }
            i++;
        }
        return prepo;
    }

    public ArrayList<String> getTimeFromSentence(String sentenc) {
        String[] words = splitSentence(sentenc);
        ArrayList<String> time = new ArrayList<String>();
        int i = 0;
        while (i < words.length) {
            if (isATime(words[i])) {
                time.add(words[i]);
                i++;
            }
            i++;
        }
        return time;
    }

    public ArrayList<String> getObjectsFromSentence(String sentenc) {
        String[] words = splitSentence(sentenc);
        ArrayList<String> obj = new ArrayList<String>();
        int i = 0;
        while (i < words.length) {
            if (isContinuesVerb(words[i])) {
                i++;
                break;
            }
            i++;
        }
        while (i < words.length) {
            if (!(isPreposision(words[i])) || !(isATime(words[i]))) {
                obj.add(words[i]);
            }
            i++;
        }

        return obj;
    }

    public boolean isContinuesVerb(String word) {
        String[] verbs = TranslateCountinues.englishVerb;
        int i = 0;
        while (i < verbs.length) {
            if (word.equals(verbs[i])) {
                return true;
            }
            i++;
        }
        return false;
    }

    public String[] getAllObjects(String sentenc) {
        int i = 0;
        int j = 0;
        int k = 0;
        String[] words = splitSentence(sentenc);
        String[] sen = new String[getNumberOfPropesitions(sentenc)];
        while (i < words.length) {
            while (j < preposisions.length) {
                if (words[i].equals(preposisions[j])) {
                    sen[k] = words[j + 1];
                    k++;
                }
            }
        }
        log.info("getAllObjects mathed finished");
        return sen;
    }

    public ArrayList<String> setAllObjectToArrayList(String senten) {

        String[] words = splitSentence(senten);
        int prepo = toDetectort(senten);
        ArrayList<String> list = new ArrayList<String>();
        int i = prepo;

        while (i < words.length) {
            if (!(isPreposision(words[i]))) {
                list.add(words[i]);
            }
            i++;
        }
        return list;
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

    @Override
    public int getTence(String sentence) {
        String words[] = sentence.split(" ");
        int propsitionsLocation = sentenceDividerNumbers(sentence)[0];
        String propsition = words[propsitionsLocation];
        if (propsition.equals("is") || propsition.equals("are") || propsition.equals("am")) {
            return 0;
        } else if (propsition.equals("was") || propsition.equals("were")) {
            return 1;
        } else {
            return 2;
        }
    }

    public int beDitector(String sentence) {
        String[] sntence = splitSentence(sentence);
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < beWorbs.length) {
            while (j < sntence.length) {
                if (beWorbs[i].equals(sntence[j])) {
                    k++;
                    break;
                }
                j++;
            }
            if (k == 1) {
                break;
            }
            i++;
            j = 0;
        }
        return j;
    }

    public int[] sentenceDividerNumbers(String sentence) {
        int i = beDitector(sentence);
        int j = toDetectort(sentence);
        int[] arr = { i, j };

        return arr;
    }

    public int getNumberOfPropesitions(String sentence) {
        String[] sntence = splitSentence(sentence);
        int count = 0;
        int i = 0;
        int j = 0;
        while (i < preposisions.length) {
            while (j < sntence.length) {
                if (preposisions[i].equals(sntence[j])) {
                    count++;
                }
                j++;
            }
            i++;
            j = 0;
        }
        return count;
    }

    @Override
    public int toDetectort(String sentence) {
        String[] sntence = splitSentence(sentence);
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < preposisions.length) {
            while (j < sntence.length) {
                if (preposisions[i].equals(sntence[j])) {
                    k++;
                    break;
                }
                j++;
            }
            if (k == 1) {
                break;
            }
            i++;
            j = 0;
        }
        return j;
    }

    @Override
    public String[] sentenceDivide(String sentence) {
        String[] arr1 = new String[3];
        int[] arr = sentenceDividerNumbers(sentence);
        String[] sntence = splitSentence(sentence);
        int i = 0;
        while (i < arr[0]) {
            i++;
        }
        arr1[0] = sntence[0];

        arr1[1] = sntence[i + 1];

        i = arr[1] + 1;

        while (i < sntence.length) {
            i++;
        }
        arr1[2] = sntence[i - 1];
        return arr1;
    }

}

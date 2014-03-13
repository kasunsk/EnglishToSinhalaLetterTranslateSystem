package com.kasun.process;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kasun.logics.translate.ContinuesLogics;

public class ProsesVerb {

    private static final Logger log = LoggerFactory.getLogger(ProsesVerb.class);

    public static String[] englishVerb = { "coming", "eating", "drinking", "riding", "runing", "kissing", "reading", "going",
            "talking", "writing" };
    private String[] sinhalaVerb = { "එමින්", "කමින්", "බොමින්", "පදිමින්", "දුවමින්", "හාදු දෙමින්", "කියවමින්", "යමින්",
            "කතා කරමින්", "ලියමින්" };
    
    private String[] pastPari = {"gone","come","eatten","drunk","read"};
    private String[] pastPariMean = {"ගොස්"," පැමින"," කා","බී" ,"කියවා"};

    private String[] hasHv = { "has", "have", "had" };
    private String[] hasHvMean = { "තියෙනවා", "තියෙනවා", " තිබුනා" };

    private String[] beVerbs = { "is", "are", "was", "were", "am", "will", "be" };
    private String[] beVerbsMean = { "සිටියි", "සිටිති", "සිටියේය", "සිටියෝය", "සිටිමි" };

    public ArrayList<String> verbsInToArrayList(String sentence) {
        ProcessLogic processLogic = new ProcessLogic();
        ContinuesLogics continuesLogics = new ContinuesLogics();
        String[] words = continuesLogics.splitSentence(sentence);

        ArrayList<String> verbsAsArrayList = new ArrayList<String>();
        int i = 0;
        while (i < words.length && !(processLogic.isPreposision(words[i]))) {
            if (processLogic.isABeVerb(words[i])) {
                verbsAsArrayList.add(words[i]);
            }
            if (processLogic.isIngVerb(words[i])) {
                verbsAsArrayList.add(words[i]);
                break;
            }
            if (processLogic.isAHvHs(words[i])) {
                verbsAsArrayList.add(words[i]);
            }
            if (processLogic.isPastParticiple(words[i])) {
                verbsAsArrayList.add(words[i]);
                break;
            }
            i++;
        }
        return verbsAsArrayList;
    }

    public String verbMeanOfSentence(String sentence) {

        ProcessLogic processLogic = new ProcessLogic();
        ContinuesLogics continuesLogics = new ContinuesLogics();

        ArrayList<String> verbs = verbsInToArrayList(sentence);
        String[] verb = new String[verbs.size()];
        verbs.toArray(verb);
        String mean = "";
        if (1 < verb.length) {
            int j = verb.length - 1;
            int i = 0;
            if (processLogic.isContinues(sentence)) {
                while (i < verb.length) {
                    mean = mean + " " + getMeaningOfAVerb(verb[j]);
                    j--;
                    i++;
                }
            } else {
                while (i < verb.length) {
                    mean = mean + " " + getMeaningOfPasveAVerb(verb[j]);
                    j--;
                    i++;
                }
            }
            log.info("Verb Meaning : " + mean);
        }
        return mean;
    }

    public String getMeaningOfAVerb(String Verb) {
        ProcessLogic processLogic = new ProcessLogic();
        int i = 0;
        if (processLogic.isIngVerb(Verb)) {
            while (i < englishVerb.length) {
                if (Verb.equals(englishVerb[i])) {
                    return sinhalaVerb[i];
                }
                i++;
            }
            i = 0;
        } else if (processLogic.isABeVerb(Verb)) {
            while (i < beVerbs.length) {
                if (Verb.equals(beVerbs[i])) {
                    return beVerbsMean[i];
                }
                i++;
            }
        }
        return "";
    }
    public String getMeaningOfPasveAVerb(String verb){
        ProcessLogic processLogic = new ProcessLogic();
        int i = 0;
        if (processLogic.isAHvHs(verb)) {
            while (i < hasHv.length) {
                if (verb.equals(hasHv[i])) {
                    return hasHvMean[i];
                }
                i++;
            }
            i = 0;
        } else if (processLogic.isPastParticiple(verb)) {
            while (i < pastPari.length) {
                if (verb.equals(pastPari[i])) {
                    return pastPariMean[i];
                }
                i++;
            }
        }
        return "";
    }
}

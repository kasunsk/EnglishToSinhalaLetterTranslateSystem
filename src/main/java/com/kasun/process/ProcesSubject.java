package com.kasun.process;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kasun.logics.translate.ContinuesLogics;

public class ProcesSubject {

    private static final Logger log = LoggerFactory.getLogger(ProcesSubject.class);

    public ArrayList<String> subAsArrayList(String sentence) {
        ContinuesLogics continuesLogics = new ContinuesLogics();
        String[] words = continuesLogics.splitSentence(sentence);

        ArrayList<String> subAsArrayList = new ArrayList<String>();

        ProcessLogic processLogic = new ProcessLogic();

        int i = 0;
        while (i < words.length && !(processLogic.isABeVerb(words[i])) && !(processLogic.isAHvHs(words[i]))) {
            subAsArrayList.add(words[i]);
            i++;
        }

        return subAsArrayList;
    }

    public String getSubjectMean(String sentence) {
        ProcessLogic processLogic = new ProcessLogic();

        ArrayList<String> subAsArrayList = new ArrayList<String>();
        subAsArrayList = subAsArrayList(sentence);
        String[] arr = new String[subAsArrayList.size()];
        subAsArrayList.toArray(arr);

        String mean = "";

        int i = 0;

        while (i < arr.length) {
            if (processLogic.isOwnerShip(arr[i])) {
                mean = mean + " " + processLogic.ownerShipMean(arr[i]) + " " + processLogic.getAnyWordMeaning(arr[i + 1]) + " ";
                i++;
            } else {
                mean = mean + " " + processLogic.getAnyWordMeaning(arr[i]) + " ";
            }
            i++;
        }
        log.info("Subject Meaning : " + mean);
        return mean;
    }
}

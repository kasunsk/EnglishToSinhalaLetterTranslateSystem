package com.kasun.sentenceDitector;

import com.kasun.logics.translate.ContinuesLogics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SentenceDitector {
    
    private static final Logger log = LoggerFactory.getLogger(SentenceDitector.class);
    
    public String ditectSentence(String sentence){
        ContinuesLogics ContinuesLogics = new ContinuesLogics();
        int detect = ContinuesLogics.beDitector(sentence);
        if (detect == 0){
            return "simple"; 
        }
        return "continues";
    }

}

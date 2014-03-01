package com.kasun.logics.translate;

import com.kasun.logics.Logics;

public class LogicsPresentContinuesLogics implements Logics{

    private String[] propsitions = { "is", "am", "are", "was", "were", "be" };
    private String[] toect = { "to", "for", "from" };
    
    @Override
    public String[] splitSentence(String sentence) {
        String words[];
        words = sentence.split(" ");
        return words;
    }
    
    @Override
    public int getTence(String sentence){
        LogicsPresentContinuesLogics logicsPresentContinuesLogics = new LogicsPresentContinuesLogics();
        String words [] =  sentence.split(" ");
        int propsitionsLocation = logicsPresentContinuesLogics.sentenceDividerNumbers(sentence)[0];
        String propsition = words[propsitionsLocation];
        if(propsition.equals("is") || propsition.equals("are") || propsition.equals("am")){
            return 0;
        }else if(propsition.equals("was") || propsition.equals("were")){
            return 1;
        }else{
            return 2;
        }
    }

    public int beDitector(String sentence) {
        String[] sntence = splitSentence(sentence);
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < propsitions.length) {
            while (j < sntence.length) {
                if (propsitions[i].equals(sntence[j])) {
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

    @Override
    public int toDetectort(String sentence) {
        String[] sntence = splitSentence(sentence);
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < toect.length) {
            while (j < sntence.length) {
                if (toect[i].equals(sntence[j])) {
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

package com.kasun.logics.translate;

import com.kasun.logics.Logics;

public class PresentSimpleLogics implements Logics {

    private String[] toect = { "to", "for", "from" };

    private String[] verbsPrasent = { "go", "come", "eat", "run", "read" };
    private String[] verbsPast = { "went", "came", "ate", "red" };

    private String[] verbsPrasentSingulerMeaning = { "යයි", "එයි", "කයි", "දුවයි", "කියවයි" };
    private String[] verbsPrasentPlurelMeaning = { "යති", "එති", "කති", "දුවති", "කියවති" };

    String verbMeaning;

    int tence = 0;
    int SubjectValue = 0;

    // PresentSimpleLogics presentSimpleLogics = new PresentSimpleLogics();

    public void setSubjectValue(String sentence) {
        String[] words = splitSentence(sentence);
        String subject = words[0];
        String finalCharactor = subject.substring(subject.length() - 1, subject.length());
        String twoFinalCharactor = subject.substring(subject.length() - 2, subject.length());
        if (finalCharactor.equals("s") || twoFinalCharactor.equals("es")) {
            this.SubjectValue = 1;
        }
    }

    @Override
    public String[] splitSentence(String sentence) {
        String words[];
        words = sentence.split(" ");
        return words;
    }

    @Override
    public int getTence(String sentence) {
        return 0;
    }

    public void setTence(String sentence) {
        String[] words = splitSentence(sentence);
        int i = 0;
        while (i < verbsPast.length) {
            if (words[1].equals(verbsPrasent[i])) {
                if (SubjectValue == 0) {
                    this.verbMeaning = verbsPrasentSingulerMeaning[i];
                } else if (SubjectValue == 1) {
                    this.verbMeaning = verbsPrasentPlurelMeaning[i];
                }
                this.tence = 1;
                break;
            }
            i++;
        }
    }

    @Override
    public int toDetectort(String sentence) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String[] sentenceDivide(String sentence) {
        // TODO Auto-generated method stub
        return null;
    }
}

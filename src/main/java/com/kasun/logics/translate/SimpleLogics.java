package com.kasun.logics.translate;

import com.kasun.logics.Logics;
import com.kasun.translate.sentences.TranslateSimple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLogics implements Logics {

    private static final Logger log = LoggerFactory.getLogger(TranslateSimple.class);

    private String[] toect = { "to", "for", "from" };

    private String[] verbsPrasent = { "go", "come", "eat", "run", "read" };
    private String[] verbsPast = { "went", "came", "ate", "red" };

    private String[] verbsPrasentSingulerMeaning = { "යයි", "එයි", "කයි", "දුවයි", "කියවයි" };
    private String[] verbsPrasentPlurelMeaning = { "යති", "එති", "කති", "දුවති", "කියවති" };
    private String[] verbsPastSingulerMeaning = { "ගියේය", "අවේය", "කෑවේය", "දිව්වේය", "කියෙව්වේය" };
    private String[] verbsPastPlurelMeaning = { "ගියෝය", "ආවෝය", "කෑවෝය", "දිව්වෝය", "කියෙව්වෝය" };

    String verbMeaning = "kasun";
    String Subject = "SuBJect";

    int tence = 0;
    int SubjectValue = 0;

    public void setSubjectValue(String sentence) {
        String[] words = splitSentence(sentence);
        String subject = words[0];
        String finalCharactor = subject.substring(subject.length() - 1, subject.length());
        String twoFinalCharactor = subject.substring(subject.length() - 2, subject.length());
        if (finalCharactor.equals("s"))// || twoFinalCharactor.equals("es") || subject.equals("They")) {
        {
            this.Subject = subject.substring(0, subject.length() - 1);
            this.SubjectValue = 1;
        } else if (twoFinalCharactor.equals("es")) {
            this.Subject = subject.substring(0, subject.length() - 2);
            this.SubjectValue = 1;
        } else if (subject.equals("They")) {
            this.Subject = "They";
            this.SubjectValue = 1;
        } else {
            this.Subject = subject;
        }
    }

    public String getSubject() {
        return this.Subject;
    }

    public int getSubjectValue() {
        return this.SubjectValue;
    }

    public void setSubjectValue(int value) {
        this.SubjectValue = value;
    }

    @Override
    public String[] splitSentence(String sentence) {
        String words[];
        words = sentence.split(" ");
        return words;
    }

    @Override
    public int getTence(String sentence) {
        return this.tence;
    }

    public void setTence(String sentence) {
        String[] words = splitSentence(sentence);

        log.info("Subject Value in SimpleLogics " + SubjectValue);

        String verb = words[1];
        String verbEnd = verb.substring(verb.length() - 1, verb.length());
        String verbEndPlurel = verb.substring(verb.length() - 2, verb.length());
        if (verbEndPlurel.equals("es")) {
            if (SubjectValue == 1) {
                System.out.println("Your Sentence look likes wrong");
            }
            verb = verb.substring(0, verb.length() - 2);
        } else if (verbEnd.equals("s")) {
            if (SubjectValue == 1) {
                System.out.println("Your Sentence looks like wrong...");
            }
            verb = verb.substring(0, verb.length() - 1);
        }

        int i = 0;
        while (i < verbsPrasent.length) {
            if (verb.equals(verbsPrasent[i])) {
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
        i = 0;
        while (i < verbsPast.length) {
            if (verb.equals(verbsPast[i])) {
                if (SubjectValue == 0) {
                    this.verbMeaning = verbsPastSingulerMeaning[i];
                } else if (SubjectValue == 1) {
                    this.verbMeaning = verbsPastPlurelMeaning[i];
                }
                this.tence = 0;
                break;
            }
            i++;
        }
    }

    public String getVerbMeaning() {
        return this.verbMeaning;
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
        String[] sntence = splitSentence(sentence);
        int i = toDetectort(sentence);
        arr1[0] = sntence[0];

        arr1[1] = sntence[2];

        if (i != 0) {
            arr1[2] = sntence[i + 1];
        } else {
            arr1[2] = sntence[2];
        }
        return arr1;
    }

    public String getVerbSinhalaMeaning() {
        return this.verbMeaning;
    }

    public void setSimpleLogicsSubjectValue(int subjectvalue) {
        this.SubjectValue = subjectvalue;
    }
}

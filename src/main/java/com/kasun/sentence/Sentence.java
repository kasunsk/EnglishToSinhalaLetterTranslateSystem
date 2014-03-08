package com.kasun.sentence;

import java.util.ArrayList;

public class Sentence {
    private String subject = null;
    private String beVerb = null;
    private String verb = null;
    private ArrayList<String> object = null;
    private ArrayList<String> propsision = null;
    private ArrayList<String> times = null;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public ArrayList<String> getPropsision() {
        return propsision;
    }

    public void setPropsision(ArrayList<String> propsision) {
        this.propsision = propsision;
    }

    public String getBeVerb() {
        return beVerb;
    }

    public void setBeVerb(String beVerb) {
        this.beVerb = beVerb;
    }

    public ArrayList<String> getObject() {
        return object;
    }

    public void setObject(ArrayList<String> object) {
        this.object = object;
    }

    public ArrayList<String> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<String> times) {
        this.times = times;
    }
    
}

package com.stfalcon.lostandfound;

import java.util.ArrayList;

/**
 * Created by Acer2 on 28.03.2015.
 */
public class Faq {
    private int id;
    private String question, answer, enabled;
    private ArrayList<Translation> translationList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public ArrayList<Translation> getTranslationList() {
        return translationList;
    }

    public void setTranslationList(ArrayList<Translation> translationList) {
        this.translationList = translationList;
    }
}

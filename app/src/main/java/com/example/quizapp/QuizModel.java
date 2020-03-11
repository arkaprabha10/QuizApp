package com.example.quizapp;

public class QuizModel {

    private int mq;
    private String ans;

    public QuizModel(int mq, String ans) {
        this.mq = mq;
        this.ans = ans;
    }

    public int getMq() {
        return mq;
    }

    public void setMq(int mq) {
        this.mq = mq;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}

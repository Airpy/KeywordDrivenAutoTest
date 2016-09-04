package com.keyword.automation.test;

/**
 * Created by Amio on 2016/9/4.
 */
public class Score {
    private double chinese;
    private double math;

    public double getChinese() {
        return chinese;
    }

    public void setChinese(double chinese) {
        this.chinese = chinese;
    }

    public double getMath() {
        return math;
    }

    public void setMath(double math) {
        this.math = math;
    }

    @Override
    public String toString() {
        return "Score{" +
                "chinese=" + chinese +
                ", math=" + math +
                '}';
    }
}

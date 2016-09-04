package com.keyword.automation.test;

import java.util.List;

/**
 * Created by Amio on 2016/9/4.
 */
public class Person {
    private List<Student> studentList;
    private Score score;

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Person{" +
                "studentList=" + studentList +
                ", score=" + score +
                '}';
    }
}

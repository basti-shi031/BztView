package com.bzt.bztview.bean;

/**
 * 学生类Bean
 * Created by SHIBW-PC on 2015/12/1.
 */
public class Student {

    private String name;
    private String number;

    public Student() {
    }

    public Student(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {

        return name;
    }

    public String getNumber() {
        return number;
    }
}

package com.example.drawer.lecturers;

public class LecModel {
    String name, g, qual,num;

    public LecModel() {
    }

    public LecModel(String name, String g, String qual, String num) {
        this.name = name;
        this.g = g;
        this.qual = qual;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getQual() {
        return qual;
    }

    public void setQual(String qual) {
        this.qual = qual;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}

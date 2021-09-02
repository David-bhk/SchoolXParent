package com.example.drawer.Result;

public class ResultModel {
    String sub;
    String cu;
//getters and setters
    public void setSub(String sub) {
        this.sub = sub;
    }

    public void setCu(String cu) {
        this.cu = cu;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    String marks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    String id;
//empty constructor
    public ResultModel(){
    }
//constructor with parameters
    public ResultModel(String sub, String cu, String marks, String id) {
        this.sub = sub;
        this.cu = cu;
        this.marks = marks;
        this.id = id;
    }

    public String getSub() {
        return sub;
    }

    public String getCu() {
        return cu;
    }

    public String getMarks() {
        return marks;
    }
}

package com.example.drawer.timetable;

public class User {

    String start, end, cN;

    public User() {
    }

    public User(String start, String end, String cN) {
        this.start = start;
        this.end = end;
        this.cN = cN;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getcN() {
        return cN;
    }

    public void setcN(String cN) {
        this.cN = cN;
    }
}


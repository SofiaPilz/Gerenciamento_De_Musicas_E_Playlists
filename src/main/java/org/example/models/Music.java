package org.example.models;

public class Music {

    public String title;
    public String single;
    public Integer time;

    public Music() {
    }

    public Music(String title, String single, Integer time) {
        this.title = title;
        this.single = single;
        this.time = time;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}

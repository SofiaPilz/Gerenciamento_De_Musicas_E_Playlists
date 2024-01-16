package org.example.models;

import java.util.Objects;

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


    @Override
    public String toString() {
        return "Music{" +
                "title='" + title + '\'' +
                ", single='" + single + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Music music)) return false;
        return Objects.equals(getTitle(), music.getTitle()) && Objects.equals(getSingle(), music.getSingle()) && Objects.equals(getTime(), music.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getSingle(), getTime());
    }
}

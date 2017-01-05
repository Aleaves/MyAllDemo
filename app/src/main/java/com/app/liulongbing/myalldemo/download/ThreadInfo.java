package com.app.liulongbing.myalldemo.download;

/**
 * Created by liulongbing on 16/12/27.
 */

public class ThreadInfo {

    private int id;

    private String url;

    private int start;

    private int end;

    private int finished;

    public ThreadInfo() {

    }

    public ThreadInfo(int id, int start, String url, int end, int finished) {
        this.id = id;
        this.start = start;
        this.url = url;
        this.end = end;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }


}

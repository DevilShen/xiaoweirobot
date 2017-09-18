package com.example.xiaoweirobot;

/**
 * Created by Administrator on 2017/9/18.
 */

public class Msg {
    public static final int SEND = 1;
    public static final int RECEIVED = 2;
    private String content;
    private int flag;
    private String time;

    public Msg(String content, int flag, String time) {
        this.content = content;
        this.flag = flag;
        this.time = time;
    }

    public Msg() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

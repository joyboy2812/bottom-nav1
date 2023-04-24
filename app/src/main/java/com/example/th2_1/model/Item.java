package com.example.th2_1.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String title, content, date, status;
    private boolean collaborate;

    public Item() {
    }

    public Item(int id, String title, String content, String date, String status, boolean collaborate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
        this.collaborate = collaborate;
    }

    public Item(String title, String content, String date, String status, boolean collaborate) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
        this.collaborate = collaborate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCollaborate() {
        return collaborate;
    }

    public void setCollaborate(boolean collaborate) {
        this.collaborate = collaborate;
    }
}

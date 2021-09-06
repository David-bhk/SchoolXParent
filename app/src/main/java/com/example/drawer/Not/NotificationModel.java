package com.example.drawer.Not;

public class NotificationModel {
    String id;
    String title;
    String content;
    String docId;

    public NotificationModel() {
    }


    public NotificationModel(String id, String title, String content, String docId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.docId = docId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }
}

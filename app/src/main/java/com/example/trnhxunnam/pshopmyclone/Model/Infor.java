package com.example.trnhxunnam.pshopmyclone.Model;

import io.realm.RealmObject;

public class Infor {

    private String title;
    private String link;
    private String image;

    public Infor(String title, String link, String image) {
        this.title = title;
        this.link = link;
        this.image = image;
    }

    public Infor(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}

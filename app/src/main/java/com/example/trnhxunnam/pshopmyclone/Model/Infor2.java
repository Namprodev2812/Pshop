package com.example.trnhxunnam.pshopmyclone.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Infor2 extends RealmObject {

    @PrimaryKey
    public   String id;
    public   String kind;
    public   String title;
    public   String link;
    public   String image;

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
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

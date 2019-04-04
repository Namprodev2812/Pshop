package com.example.trnhxunnam.pshopmyclone.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonHangfull {
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("Madonhang")
    @Expose
    private String madonhang;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Price")
    @Expose
    private Double price;
    @SerializedName("Indexsum")
    @Expose
    private int indexsum;
    @SerializedName("Sumprice")
    @Expose
    private Double sumprice;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(String madonhang) {
        this.madonhang = madonhang;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getIndexsum() {
        return indexsum;
    }

    public void setIndexsum(int indexsum) {
        this.indexsum = indexsum;
    }

    public Double getSumprice() {
        return sumprice;
    }

    public void setSumprice(Double sumprice) {
        this.sumprice = sumprice;
    }
}

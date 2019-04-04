package com.example.trnhxunnam.pshopmyclone.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonHang {
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("Madonhang")
    @Expose
    private String madonhang;
    @SerializedName("User")
    @Expose
    private String user;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Indexsum")
    @Expose
    private int indexsum;
    @SerializedName("Sumprice")
    @Expose
    private double sumprice;
    @SerializedName("Timem")
    @Expose
    private String timem;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getIndexsum() {
        return indexsum;
    }

    public void setIndexsum(int indexsum) {
        this.indexsum = indexsum;
    }

    public double getSumprice() {
        return sumprice;
    }

    public void setSumprice(double sumprice) {
        this.sumprice = sumprice;
    }

    public String getTimem() {
        return timem;
    }

    public void setTimem(String timem) {
        this.timem = timem;
    }
}

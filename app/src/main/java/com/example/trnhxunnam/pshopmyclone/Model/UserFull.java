package com.example.trnhxunnam.pshopmyclone.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserFull {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("Taikhoan")
    @Expose
    private String taikhoan;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Hinhanh")
    @Expose
    private String hinhanh;
    @SerializedName("Diachi")
    @Expose
    private String diachi;
    @SerializedName("Email")
    @Expose
    private String email;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package com.example.trnhxunnam.pshopmyclone.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User{
    @SerializedName("ID")
    @Expose
    private int mid;

    public int getId() {
        return mid;
    }

    public void setId(int id) {
        this.mid = id;
    }

    @SerializedName("Taikhoan")
    @Expose
    private String mtaikhoan;

    @SerializedName("Matkhau")
    @Expose
    private String mmatkhau;

    public String getTaiKhoan() {
        return mtaikhoan;
    }

    public void setTaiKhoan(String taikhoan) {
        this.mtaikhoan = taikhoan;
    }

    public String getMatkhau() {
        return mmatkhau;
    }

    public void setMatkhau(String matkhau) {
        this.mmatkhau = matkhau;
    }
}

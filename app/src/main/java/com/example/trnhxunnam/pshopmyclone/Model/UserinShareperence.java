package com.example.trnhxunnam.pshopmyclone.Model;

public class UserinShareperence {

    private String mtaikhoan;
    private String memail;
    private String murl;

    public UserinShareperence(String taikhoan, String email, String url) {
        this.mtaikhoan = taikhoan;
        this.memail = email;
        this.murl = url;
    }

    public String getTaikhoan() {
        return mtaikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.mtaikhoan = taikhoan;
    }

    public String getEmail() {
        return memail;
    }

    public void setEmail(String email) {
        this.memail = email;
    }

    public String getUrl() {
        return murl;
    }

    public void setUrl(String url) {
        this.murl = url;
    }


}

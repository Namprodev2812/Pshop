package com.example.trnhxunnam.pshopmyclone.Model;

import java.util.HashMap;

public class AutoLoadNavigationBar {

    private static AutoLoadNavigationBar instance;
    private int INDEX = 0;

    private AutoLoadNavigationBar() {

    }

    public static AutoLoadNavigationBar getInstance() {
        if (instance == null) {
            instance = new AutoLoadNavigationBar();
        }
        return instance;
    }

    public int getINDEX() {
        return INDEX;
    }

    public void setINDEX(int INDEX) {
        this.INDEX = INDEX;
    }

}

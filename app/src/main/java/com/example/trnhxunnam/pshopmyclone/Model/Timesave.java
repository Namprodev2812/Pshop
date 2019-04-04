package com.example.trnhxunnam.pshopmyclone.Model;

public class Timesave {
    int day;
    int month;
    int year;
    int dayleft;
    int dayright;

    public Timesave(int day, int month, int year, int dayleft, int dayright) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.dayleft = dayleft;
        this.dayright = dayright;
    }

    public Timesave( int month, int year, int dayleft, int dayright) {

        this.month = month;
        this.year = year;
        this.dayleft = dayleft;
        this.dayright = dayright;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDayleft() {
        return dayleft;
    }

    public void setDayleft(int dayleft) {
        this.dayleft = dayleft;
    }

    public int getDayright() {
        return dayright;
    }

    public void setDayright(int dayright) {
        this.dayright = dayright;
    }
}

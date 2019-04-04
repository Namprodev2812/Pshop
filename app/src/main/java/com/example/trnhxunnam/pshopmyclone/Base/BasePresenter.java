package com.example.trnhxunnam.pshopmyclone.Base;

public class BasePresenter<T extends BaseView> {

    T view;
    public T getView(){
        return  view;
    }

    public void setView(BaseView view) {
        this.view = (T) view;
    }

}
package com.example.trnhxunnam.pshopmyclone.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

public class BaseFragment <T extends BasePresenter> extends Fragment implements BaseView {

    public T presenter;
    public T getPresenter(){
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Log.e("PresenterBBB",""+presenter) ;
            initParameter();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Presenter"," ERROR__"+e);
        }
    }
    @SuppressWarnings("unchecked")
    public void initParameter() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, java.lang.InstantiationException {
        //Log.e("initParameter","is loading initParameter") ;

        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        String parameterClassName = pt.getActualTypeArguments()[0].toString().split("\\s")[1];

        //Log.e("parameterClassName",""+parameterClassName) ;
        //Log.e("getPresenteBEFORE",""+presenter) ;

        this.presenter = (T) Class.forName(parameterClassName).getConstructor().newInstance();
        this.presenter.setView(this);
        //Constructor <?> constructor = Class.forName(parameterClassName).getConstructor();
        //presenter = (T) Class.forName(parameterClassName).getConstructor().newInstance();
        //presenter = (T) Class.forName(parameterClassName).getConstructor().newInstance();

        //Log.e("getPresenterTHEN",""+presenter) ;
        //Log.e("getPresenterTHEN","aaaaa") ;
        //return (T) Class.forName(parameterClassName).newInstance();


        //Class<?> c = Class.forName(parameterClassName);
        //Constructor<?> cons = c.getConstructor();
        //Constructor<?> cons = c.getDeclaredConstructor();
        //cons.setAccessible(true);
        //Object object = cons.newInstance();
        Log.e("getPresenteBEFORE",""+this.presenter) ;
    }
}

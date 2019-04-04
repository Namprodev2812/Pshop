package com.example.trnhxunnam.pshopmyclone.Model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MyRealm {

    public Realm myrealm;
    RealmConfiguration config;
    private static MyRealm myRealmdone;

    private MyRealm() {

    }

    public static MyRealm getInstance() {
        if (myRealmdone == null) {
            myRealmdone = new MyRealm();
        }
        return myRealmdone;
    }

    public void configRealm(Context context) {
        Realm.init(context);
        config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .schemaVersion(42)
                .build();
        Log.e("namefile: ", "" + config.getRealmFileName());
        Realm.setDefaultConfiguration(config);
//        Realm realm = Realm.getInstance(config);
//        myrealm = realm;
    }

    public void getRealmConfig(){
        myrealm =  Realm.getInstance(config);
    }

    public void closeRealm() {
        myrealm.close();
    }

    public void createData1(ArrayList<Infor> arrayList, String rss) {
/*
        for (int y = 0; y < arrayList.size(); y++) {
            myRealm.beginTransaction();
            Infor2 infor2 = myRealm.createObject(Infor2.class, UUID.randomUUID().toString()); // neu id : String thi se auto imcrement
            infor2.setTitle(arrayList.get(y).getTitle());
            infor2.setImage(arrayList.get(y).getLink());
            infor2.setLink(arrayList.get(y).getLink());
            infor2.setKind(rss);
            myRealm.commitTransaction();
        }
*/
        for (int i = 0; i < arrayList.size(); i++) {
//            myRealm.beginTransaction();
            final Infor2 infor2 = new Infor2();
            infor2.setId(rss + i);
            infor2.setTitle(arrayList.get(i).getTitle());
            infor2.setImage(arrayList.get(i).getLink());
            infor2.setLink(arrayList.get(i).getLink());
            infor2.setKind(rss);
            myrealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(infor2);
                }
            });

            myrealm.close();
//            myRealm.commitTransaction();
        }
    }
    public ArrayList<Infor> getData(String rss,Context context) {
        //myrealm.beginTransaction();   When you read realm ,, you don't need (begin and comit) Transaction
        RealmResults<Infor2> infor2 = myrealm.where(Infor2.class).equalTo("kind", rss).findAll();
        Toast.makeText(context, ""+infor2.size(), Toast.LENGTH_SHORT).show();
        ArrayList<Infor> arrayList = new ArrayList<>();
        for (int i = 0; i < infor2.size(); i++) {

            Infor infor = new Infor();
            infor.setTitle(infor2.get(i).getTitle());
            infor.setImage(infor2.get(i).getImage());
            infor.setLink(infor2.get(i).getLink());
            Log.e("titleRSS ",""+infor2.get(i).getTitle());
            arrayList.add(infor);
        }
        //myrealm.commitTransaction();
        myrealm.close();
        return arrayList;
    }
}

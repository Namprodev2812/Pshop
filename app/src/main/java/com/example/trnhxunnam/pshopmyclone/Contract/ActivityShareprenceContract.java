package com.example.trnhxunnam.pshopmyclone.Contract;
import android.content.Context;

import com.example.trnhxunnam.pshopmyclone.Base.BaseView;

public interface ActivityShareprenceContract {
    interface LSView extends BaseView {
        void getSoLuongGioHangProcess(int valuesum);
        void getOffAppProcess(int value);
        void getValueLoginProcess(int value);
    }

    interface  LSPresenter{
        void getValueLogin(Context context);
        void getSoLuongGioHang();
        void getOffApp(Context context);
        void setOffApp(Context context,int value);
    }
}

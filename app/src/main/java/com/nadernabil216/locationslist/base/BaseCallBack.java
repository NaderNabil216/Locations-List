package com.nadernabil216.locationslist.base;

public interface BaseCallBack {

    void initialization();

    void showSnackBar(String msg);

    void showProgressDialog(boolean showProgress);

    void showSnakeBarWithActionToFinishActivity(String msg);

    void finishActivity();

}

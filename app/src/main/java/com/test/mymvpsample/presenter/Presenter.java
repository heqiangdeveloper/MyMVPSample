package com.test.mymvpsample.presenter;

import android.view.View;

import com.test.mymvpsample.view.MainView;

public interface Presenter {
    void attachView(MainView view);
    void detechView();
}

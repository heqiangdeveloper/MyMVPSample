package com.test.mymvpsample.view;

import com.test.mymvpsample.model.MainModelBean;

/*
*  MVP之V,处理业务需要哪些方法
 */
public interface MainView {
    void showData(MainModelBean mainModelBean);
    void showProgress();
    void hideProgress();
}

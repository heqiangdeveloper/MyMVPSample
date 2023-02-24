package com.test.mymvpsample.presenter;

import com.test.mymvpsample.model.MainModelBean;

/*
* 此接口作用是连接model
 */
public interface IMainPresenter {
    void loadDataSuccess(MainModelBean mainModelBean);
    void loadDataFailure();
}

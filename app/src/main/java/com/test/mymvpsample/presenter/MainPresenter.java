package com.test.mymvpsample.presenter;

import android.view.View;

import com.test.mymvpsample.model.MainModel;
import com.test.mymvpsample.model.MainModelBean;
import com.test.mymvpsample.view.MainView;

/*
* MVP之P,是view和model之间的桥梁，他从model层检索数据后，返回给View
 */
public class MainPresenter implements Presenter,IMainPresenter{
    private MainView mMainView;
    private MainModel mMainModel;

    public MainPresenter(MainView mMainView) {
        attachView(mMainView);
        mMainModel = new MainModel(this);
    }

    public void loadData(){
        mMainView.showProgress();
        mMainModel.loadData();
    }

    @Override
    public void loadDataSuccess(MainModelBean mainModelBean) {
        mMainView.showData(mainModelBean);
        mMainView.hideProgress();
    }

    @Override
    public void loadDataFailure() {
        mMainView.hideProgress();
    }

    @Override
    public void attachView(MainView view) {
        this.mMainView = view;
    }

    @Override
    public void detechView() {
        this.mMainView = null;
    }
}

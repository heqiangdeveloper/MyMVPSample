package com.test.mymvpsample.model;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.test.mymvpsample.presenter.IMainPresenter;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/*
* MVP之M,业务具体处理，包括存储，检索，操纵数据等
 */
public class MainModel {
    private static final String TAG = "MainModel";
    private IMainPresenter mIMainPresenter;

    public MainModel(IMainPresenter mIMainPresenter) {
        this.mIMainPresenter = mIMainPresenter;
    }

    public void loadData(){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("http://www.weather.com.cn/adat/sk/101010100.html",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    MainModelBean mainModelBean = new MainModelBean();
                    JSONObject weatherinfo = response.getJSONObject("weatherinfo");
                    mainModelBean.setCity(weatherinfo.getString("city"));
                    mainModelBean.setWd(weatherinfo.getString("WD"));
                    mainModelBean.setWs(weatherinfo.getString("WS"));
                    mainModelBean.setTime(weatherinfo.getString("time"));
                    mIMainPresenter.loadDataSuccess(mainModelBean);
                }catch (Exception e){
                    Log.d(TAG,"loadData fail: " + e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                mIMainPresenter.loadDataFailure();
            }
        });
    }
}

package com.test.mymvpsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.test.mymvpsample.model.MainModelBean;
import com.test.mymvpsample.presenter.MainPresenter;
import com.test.mymvpsample.view.MainView;

/*
*  实现View里的方法
 */
public class MainActivity extends AppCompatActivity implements MainView,View.OnClickListener {
    private TextView text;
    private ProgressBar progressBar;
    private MainPresenter mMainPresenter;
    private Button refreshBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        text = (TextView) findViewById(R.id.text);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        refreshBt = (Button) findViewById(R.id.button);
        refreshBt.setOnClickListener(this);
        mMainPresenter = new MainPresenter(this);

        //异步线程开始请求数据
        startLoadData();
    }

    private void startLoadData(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMainPresenter.loadData();
            }
        },2000);
    }

    @Override
    public void showData(MainModelBean mainModelBean) {
        String showData = getResources().getString(R.string.city) + mainModelBean.getCity() + "\n" +
                getResources().getString(R.string.wd) + mainModelBean.getWd() + "\n" +
                getResources().getString(R.string.ws) + mainModelBean.getWs() + "\n" +
                getResources().getString(R.string.time) + mainModelBean.getTime() + "\n" +
                "刷新时间：" + System.currentTimeMillis();
        text.setText(showData);
    }

    @Override
    public void onClick(View v) {
        startLoadData();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detechView();
    }
}
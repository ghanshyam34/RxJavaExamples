package com.gs.rxjavaexample;
import android.os.Bundle;

import com.gs.rxjavaexample.base.BaseActivity;
import com.gs.rxjavaexample.model.TestApi;
import com.gs.rxjavaexample.remote.ApiResponseListener;
import com.gs.rxjavaexample.utils.Logger;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callApi();
    }

    public void callApi(){

        new TestApi().callApi(new ApiResponseListener() {
            @Override
            public void onSuccess(Object response) {
                Logger.i("response_success",""+response);
            }

            @Override
            public void onFaillure(String response) {
                Logger.i("response_faillure",""+response);
            }
        });
    }
}

package com.gs.rxjavaexample.model;
import com.gs.rxjavaexample.base.BaseModel;
import com.gs.rxjavaexample.remote.ApiClient;
import com.gs.rxjavaexample.remote.ApiResponseListener;
import com.gs.rxjavaexample.utils.Logger;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
/**
 * Created by Ghanshyam.
 */
public class TestApi extends BaseModel<ApiResponseListener,Response<ResponseBody>> {

    public static final String TAG = TestApi.class.getSimpleName();

    @Override
    protected Observable<Response<ResponseBody>> getApi(Object... requestParam) {
        return ApiClient.getWebApiService().testApi();
    }

    public void callApi(ApiResponseListener apiResponseListe){
        apiRequest();
        setCallbackListener(apiResponseListe);
    }

    @Override
    public void parseResponseBody(String response) {
        Logger.w(TAG, " Response body == " + response);
    }
}

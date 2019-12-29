package com.gs.rxjavaexample.base;
import com.gs.rxjavaexample.remote.ApiResponseListener;
import com.gs.rxjavaexample.utils.CommonUtils;
import com.gs.rxjavaexample.utils.ErrorUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;
/**
 * Created by Ghanshyam
 */
public abstract class BaseModel<T, K> {
    public void apiRequestBody(Object...requestParam) {
        try {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(
            getApi(requestParam)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<K>() {
                        @Override
                        public void accept(K resp) throws Exception {
                            if(resp instanceof Response){
                                Response<ResponseBody> response = (Response<ResponseBody>)resp;
                                prepareBody(response);
                            }else if(resp != null){
                                ((ApiResponseListener)getCallbackListener()).onSuccess(resp);
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            ((ApiResponseListener)getCallbackListener()).onFaillure(throwable.getMessage());
                        }
                    }));

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void apiRequest(Object... requestParam) {
        try {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(
                    getApi(requestParam)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(this::handleResults, this::handleError));

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void prepareBody(Response<ResponseBody> response) {
        if (response != null && response.isSuccessful() && response.code() == 200) {
            parseResponseBody(CommonUtils.getResponseBody(response));
        } else {
            ((ApiResponseListener) getCallbackListener()).onFaillure(ErrorUtils.getHtttpCodeError(response.code()));
        }
    }

    private void handleResults(K resp) {
        if (resp instanceof Response) {
            Response<ResponseBody> response = (Response<ResponseBody>) resp;
            prepareBody(response);
        } else if (resp != null) {
            ((ApiResponseListener) getCallbackListener()).onSuccess(resp);
        }
    }

    private void handleError(Throwable throwable) {
        ((ApiResponseListener) getCallbackListener()).onFaillure(throwable.getMessage());
    }

    T callbackListener;

    public void setCallbackListener(T callbackListener) {
        this.callbackListener = callbackListener;
    }

    protected abstract Observable<K> getApi(Object... requestParam);

    protected abstract void parseResponseBody(String response);

    protected T getCallbackListener() {
        return this.callbackListener;
    }
}

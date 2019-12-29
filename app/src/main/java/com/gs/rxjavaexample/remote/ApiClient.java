package com.gs.rxjavaexample.remote;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Ghanshyam on 01/27/2017.
 */
public class ApiClient {

    static WebApiService webApiService;

    /**
     * this return the webservice instance initialized through retrofit
     * @return
     */
    public static WebApiService getWebApiService() {

        if (webApiService == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = httpClient.addInterceptor(interceptor).connectTimeout(60, TimeUnit.SECONDS).
                    readTimeout(60, TimeUnit.SECONDS).
                    writeTimeout(60, TimeUnit.SECONDS)
                    .build();

            String baseUrl = ""+WebApiService.BASEURL;
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            webApiService = retrofit.create(WebApiService.class);
        }
        return webApiService;
    }
}


package com.gs.rxjavaexample.remote;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ghanshyam on 01/27/2017.
 */
public interface WebApiService {

    public static final String BASEURL = "your_base_url";

    @GET("test_api_name")
    Observable<Response<ResponseBody>> testApi();
}
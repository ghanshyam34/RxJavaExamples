package com.gs.rxjavaexample.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by Ghanshyam.
 */
public class CommonUtils {
    public static String getResponseBody(Response<ResponseBody> response) {

        BufferedReader reader = null;
        String output = null;
        try {

            if (response.body() != null) {
                reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
            } else if (response.errorBody() != null) {
                reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            output = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }
}

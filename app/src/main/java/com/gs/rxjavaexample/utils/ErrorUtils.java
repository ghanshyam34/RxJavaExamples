package com.gs.rxjavaexample.utils;

/**
 * Created by Ghanshyam.
 */
public class ErrorUtils {
    public static String getHtttpCodeError(int statusCode) {
        String error = "";
        switch (statusCode) {
            case 400:
                error = "bad request";
                break;
            case 401:
                error = "access denied";
                break;
        }
        return error;
    }
}

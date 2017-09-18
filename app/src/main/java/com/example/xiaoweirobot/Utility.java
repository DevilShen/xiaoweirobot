package com.example.xiaoweirobot;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/9/18.
 */

public class Utility {
    public static Responsee handleResponse(String jsonData){
        Gson gson = new Gson();
        return gson.fromJson(jsonData,Responsee.class);
    }

}

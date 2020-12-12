package com.example.retrofitdata.Api_Interface;

import com.example.retrofitdata.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonApiInterface {

    @GET("/showusers/")
    Call<List<User>> getUser();

    @POST("/register/")
    Call<User> createUser(@Body User user);
}

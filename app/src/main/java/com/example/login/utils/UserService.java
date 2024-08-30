package com.example.login.utils;

import com.example.login.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;

import java.util.List;

public interface UserService {

    @GET("/api/users")
    Call<List<User>> getUsers();

    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @POST("/api/users/validate")
    Call<User> validateLogin(@Body User user );
}

package com.iesvirgendelcarmen.rvargas.touchremote.rest;

import com.iesvirgendelcarmen.rvargas.touchremote.model.Notification;
import com.iesvirgendelcarmen.rvargas.touchremote.model.Registration;
import com.iesvirgendelcarmen.rvargas.touchremote.model.User;
import java.util.List;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

interface UserService {
    @GET("/login/{email}/{password}")
    void getUser(@Path("email") String email, @Path("password") String password, Callback<User> callback);

    @GET("/notifications/{userId}/{employeeid}")
    void getNotificationsByUser(@Path("userId") String userId, @Path("employeeid") String employeeId, Callback<List<Notification>> callback);

    @PUT("/changePassword/{employeeId}/{userId}/{old}/{new}")
    void changePassword(@Path("employeeId") String employeeId, @Path("userId") String userId, @Path("old") String oldPassword, @Path("new") String newPassword, Callback<String> callback);

    @POST("/registration/add")
    void postRegistration(@Body Registration registration, Callback<Boolean> callback);
}
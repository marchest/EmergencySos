package API;

import baseClasses.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by marchest on 30.11.2016.
 */
public interface ServiceInterface {
    @FormUrlEncoded
    @POST("/users")
    void postUser(@Field("userName") String name,
                  @Field("userSurname") String surName,
                  @Field("userEmail") String email,
                  @Field("userGender")String gender,
                  @Field("userPhone") String userPhone,
                  Call<UserModel> serverResponseCallback);
}
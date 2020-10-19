package com.avorobyev174.mec_winet.classes.house;

import com.avorobyev174.mec_winet.classes.api.house.ResponseCreateHouse;
import com.avorobyev174.mec_winet.classes.api.house.ResponseHouseInfo;

import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.DELETE;
import retrofit2.Call;
import retrofit2.http.Query;

public interface HousesApi {
    @GET("house/?485kf9056kjwj3=04jf75h@hjfks")
    Call<ResponseHouseInfo> getHouse();

    @POST("house/?485kf9056kjwj3=04jf75h@hjfks")
    Call<ResponseCreateHouse> createHouse(@Query("street") String street,
                                          @Query("house_number") String houseNumber);

    @DELETE("house/5?485kf9056kjwj3=04jf75h@hjfks")
    Call<ResponseHouseInfo> deleteHouse();
}

package com.ograper.runwith.network;

import com.ograper.runwith.Format.DataResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAPI {
    @POST("/user/add")
    Call<DataResponse> userAdd(@Query("id") String id);
}

package com.ograper.runwith.network;

import com.ograper.runwith.Format.DataResponse;
import com.ograper.runwith.Format.TokenEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TokenAPI {
    @POST("/message/newToken")
    Call<DataResponse> sendToken(@Query("id") String id, @Query("newToken") String newToken);
}

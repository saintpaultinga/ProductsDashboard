package com.icc.commerce.productsdashboard.net.service;


import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface IProductService {
    @Headers({
            "Accept: application/json",
            "User-Agent: Mozilla/5.0"
    })
    @GET("https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json")
    Call<JsonElement> loadProductCatalog();
}

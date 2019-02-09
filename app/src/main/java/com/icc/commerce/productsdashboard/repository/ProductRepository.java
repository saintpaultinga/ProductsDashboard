package com.icc.commerce.productsdashboard.repository;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.icc.commerce.productsdashboard.model.Product;
import com.icc.commerce.productsdashboard.net.service.IProductService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import timber.log.Timber;

public class ProductRepository {

    final private IProductService productService;
    @Inject
    public ProductRepository(IProductService productService) {
        this.productService = productService;
    }

    public LiveData<List<Product>> getRemoteProducts() {
        final MutableLiveData<List<Product>> MutableProductList = new MutableLiveData<>();
        if (productService == null) {
            Timber.e("productService is null!!");
            return MutableProductList;
        }
        Call<JsonElement> call = productService.loadProductCatalog();
        if (call == null) {
            Timber.d("API call failed, something wrong happen!!");
            return MutableProductList;
        }
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, retrofit2.Response<JsonElement> response) {
                if (response.body() == null) {
                    Timber.d("The response was null!!");
                } else {
                    Gson gson = new Gson();
                    Type productListType = new TypeToken<ArrayList<Product>>(){}.getType();
                    List<Product> list = gson.fromJson(response.body(), productListType);
                    MutableProductList .setValue(list);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Timber.d("he request failed!!");
                // TODO: may need to add an empty product list to the MutableProductList to avoid
                // TODO: a possible NullPointerException when the list is used
            }
        });

        return MutableProductList;
    }
}

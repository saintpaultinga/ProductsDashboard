package com.icc.commerce.productsdashboard.di.modules;

import android.arch.lifecycle.ViewModelProvider;

import com.icc.commerce.productsdashboard.di.util.ViewModelFactory;
import com.icc.commerce.productsdashboard.net.service.IProductService;
import com.icc.commerce.productsdashboard.net.util.Urls;
import com.icc.commerce.productsdashboard.repository.ProductRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {


    @Provides
    @Singleton
    OkHttpClient getRequestHeader() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .build();
            return chain.proceed(request);
        })
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS);

        return httpClient.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    IProductService getApiCallInterface(Retrofit retrofit) {
        return retrofit.create(IProductService.class);
    }

    @Provides
    @Singleton
    ProductRepository getRepository(IProductService apiCallInterface) {
        return new ProductRepository(apiCallInterface);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelFactory(ProductRepository myRepository) {
        return new ViewModelFactory(myRepository);
    }

}

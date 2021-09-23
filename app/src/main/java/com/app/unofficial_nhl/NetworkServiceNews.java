package com.app.unofficial_nhl;

import io.reactivex.schedulers.Schedulers;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.Executors;



public class NetworkServiceNews {
    private static NetworkServiceNews mInstance;
    private static final String BASE_URL = "https://api.nytimes.com/";
    private Retrofit mRetrofit;



    private NetworkServiceNews() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       // interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

//        OkHttpClient.Builder client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor);

        RxJava2CallAdapterFactory rxAdapter =
                RxJava2CallAdapterFactory
                        .createWithScheduler(Schedulers.io());

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.callbackExecutor(Executors.newFixedThreadPool(20))
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
              //  .client(client.build())
                .build();
    }

    public static NetworkServiceNews getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkServiceNews();
        }
        return mInstance;
    }


    public JSONPlaceHolderApiNews getJSONApi() {
        return mRetrofit.create(JSONPlaceHolderApiNews.class);
    }
}

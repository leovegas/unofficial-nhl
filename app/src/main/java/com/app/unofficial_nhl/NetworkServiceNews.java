package com.app.unofficial_nhl;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkServiceNews {
    private static NetworkServiceNews mInstance;
    private static final String BASE_URL = "https://api.nytimes.com/";
    private Retrofit mRetrofit;

    private NetworkServiceNews() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       // interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

//        OkHttpClient.Builder client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
             //   .callbackExecutor(Executors.newSingleThreadExecutor())
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

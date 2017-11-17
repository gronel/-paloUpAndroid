package biz.fastgroup.apps.paloup.rest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gronel on 26/03/2017.
 */

public class ApiClient {

    private static Retrofit retrofit =null;


    public static Retrofit getClient(){
         String BASE_URL=  "http://192.168.10.3/core/api/";
        if(retrofit==null){
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new ServiceInterceptor());//calling customize Interceptor
            OkHttpClient client = httpClient.build();//build new client

            retrofit =new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}

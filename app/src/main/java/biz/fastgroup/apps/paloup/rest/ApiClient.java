package biz.fastgroup.apps.paloup.rest;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static biz.fastgroup.apps.paloup.helper.Constant.CORE_URI;


/**
 * Created by gronel on 26/03/2017.
 */

public class ApiClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {


    /* register realm object to gson builder*/
        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaringClass().equals(RealmObject.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();

        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new ServiceInterceptor())
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();//build new client

            retrofit = new Retrofit.Builder()
                    .baseUrl(CORE_URI)
                    .client(client)
                    .addConverterFactory(
                            GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }
}

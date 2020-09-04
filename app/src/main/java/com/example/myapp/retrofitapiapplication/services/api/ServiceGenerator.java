package com.example.myapp.retrofitapiapplication.services.api;

import android.content.Context;

import com.example.myapp.retrofitapiapplication.helpers.Const;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private Context mContext;

    public ServiceGenerator(Context context) {
        mContext = context;
    }

    public <T> T CreateService(Class<T> serviceClass) {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .setPrettyPrinting()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .client(LogInspector.client())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

    public APIInterface getApiInstance() {
        return new ServiceGenerator(mContext).CreateService(APIInterface.class);
    }
}

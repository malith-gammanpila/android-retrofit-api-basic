package com.example.myapp.retrofitapiapplication.services.api;

import com.example.myapp.retrofitapiapplication.services.api.response_models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("posts")
    Call<List<Post>> getPosts();
}

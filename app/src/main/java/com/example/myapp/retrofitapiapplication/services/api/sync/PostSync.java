package com.example.myapp.retrofitapiapplication.services.api.sync;

import android.content.Context;

import com.example.myapp.retrofitapiapplication.helpers.Const;
import com.example.myapp.retrofitapiapplication.services.api.ServiceGenerator;
import com.example.myapp.retrofitapiapplication.services.api.response_models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostSync {
    private Context context;
    private PostCallback callback;

    public PostSync(Context context, PostCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void movies() {
        new ServiceGenerator(context).getApiInstance().getPosts()
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (response.isSuccessful()) {
                            callback.onSuccess(true, response.body());
                        } else {
                            callback.onSuccess(false, null);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        callback.onSuccess(false, null);
                    }
                });
    }

    public interface PostCallback {
        void onSuccess(boolean status, List<Post> response);
    }
}

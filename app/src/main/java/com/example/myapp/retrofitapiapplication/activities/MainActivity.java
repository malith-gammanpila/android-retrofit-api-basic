package com.example.myapp.retrofitapiapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapp.retrofitapiapplication.R;
import com.example.myapp.retrofitapiapplication.helpers.Const;
import com.example.myapp.retrofitapiapplication.services.api.JsonPlaceholderApi;
import com.example.myapp.retrofitapiapplication.services.api.response_models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResults;
    private Button buttonLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResults = findViewById(R.id.textViewResults);

        buttonLoad = findViewById(R.id.buttonLoadData);
        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
    }

    /**
     * Call API: Get all posts
     */
    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderApi jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);

        Call<List<Post>> call = jsonPlaceholderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                textViewResults.setText(null);

                if (!response.isSuccessful()) {
                    textViewResults.setText(("Code: " + response.code()));
                    return;
                } else {
                    List<Post> posts = response.body();

                    for (Post post : posts) {
                        String content = "";
                        content += "User ID: " + post.getUserId()
                                + "\nID: " + post.getId()
                                + "\nTitle: " + post.getTitle()
                                + "\nText: " + post.getText() + "\n\n";

                        textViewResults.append(content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResults.setText(t.getMessage());
            }
        });
    }
}
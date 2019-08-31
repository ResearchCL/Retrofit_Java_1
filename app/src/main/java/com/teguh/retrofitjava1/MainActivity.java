package com.teguh.retrofitjava1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private JsonPlaceHolderApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_result);

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsonPlaceHolderApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(JsonPlaceHolderApi.class);

//        getPosts();
//        getComments();
//        createPost();
//        updatePost();
        deletePost();
    }

    private void deletePost() {
        Call<Void> call = api.deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textView.setText("Code : " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }

    private void updatePost() {
        Post post = new Post(12, null, "New Text Body");

        Call<Post> call = api.patchPost(5, post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()) {
                    textView.setText("Code : " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += " Code : " + response.code() + "\n";
                content += " ID : " + postResponse.getId() + "\n";
                content += " User ID : " + postResponse.getUserId() + "\n";
                content += " Title : " + postResponse.getTitle() + "\n";
                content += " Body : " + postResponse.getText() + "\n";

                textView.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }

    private void createPost() {
        Post post = new Post(23, "Title Title Little Star", "Penekno Blimbing kuwi");

        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "25");
        fields.put("title", "aku anak paud");
        fields.put("body", "3333333333333 tiga");

        Call<Post> call = api.createPost(fields);
//        Call<Post> call = api.createPost(24, "Yang Ke-2", "DUaaaa");

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code : " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += " Code : " + response.code() + "\n";
                content += " ID : " + postResponse.getId() + "\n";
                content += " User ID : " + postResponse.getUserId() + "\n";
                content += " Title : " + postResponse.getTitle() + "\n";
                content += " Body : " + postResponse.getText() + "\n";

                textView.setText(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }

    private void getComments() {
        Call<List<Comment>> call = api.getComments("posts/1/comments");

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                List<Comment> comments = response.body();

                for (Comment comment : comments) {
                    String content = "";
                    content += " Post ID : " + comment.getPostId() + "\n";
                    content += " ID : " + comment.getId() + "\n";
                    content += " Name : " + comment.getName() + "\n";
                    content += " Email : " + comment.getEmail() + "\n";
                    content += " Body : " + comment.getText() + "\n";

                    textView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }

    private void getPosts() {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "4");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

//        Call<List<Post>> call = api.getPost(new Integer[]{2, 3, 5}, null, null);
        Call<List<Post>> call = api.getPost(parameters);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code : " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += " ID : " + post.getId() + "\n";
                    content += " User ID : " + post.getUserId() + "\n";
                    content += " Title : " + post.getTitle() + "\n";
                    content += " Body : " + post.getText() + "\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}

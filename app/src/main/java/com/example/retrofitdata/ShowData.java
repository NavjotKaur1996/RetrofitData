package com.example.retrofitdata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.retrofitdata.Api_Interface.JsonApiInterface;
import com.example.retrofitdata.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowData extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        textView = findViewById(R.id.textView1);
        getPost();
    }

    public void getPost(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApiInterface jsonPlaceholderApi = retrofit.create(JsonApiInterface.class);
        Call<List<User>> call = jsonPlaceholderApi.getUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code :"+response.code());
                    return;
                }

                List<User> users = response.body();
                for(User user:users){
                    String content ="";
                    content += "Id: " +user.getId()+ "\n";
                    content += "Email: " +user.getEmail()+ "\n";
                    content += "Password: " +user.getPassword()+ "\n";
                    content += "Name: " +user.getName()+ "\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}
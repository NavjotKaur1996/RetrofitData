package com.example.retrofitdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdata.Api_Interface.JsonApiInterface;
import com.example.retrofitdata.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.showData);
        createPost();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ShowData.class);
                startActivity(intent);
            }
        });

    }

    public void createPost(){
        User user = new User("","newuser@gmail.com", 112233,"New User");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApiInterface jsonPlaceholderApi = retrofit.create(JsonApiInterface.class);
        Call<User> call = jsonPlaceholderApi.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code :"+response.code());
                    return;
                }
                User postResponse =  response.body();

                String content ="";
                content += "Code :" +response.code() + "\n";
                content += "Email: " +user.getEmail()+ "\n";
                content += "Password: " +user.getPassword()+ "\n";
                content += "Name: " +user.getName()+ "\n";
                textView.append(content);
                Toast.makeText(MainActivity.this,"User has been created",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

  }
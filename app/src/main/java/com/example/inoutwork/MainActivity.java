package com.example.inoutwork;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, 
    // button, textview and progressbar.
    private EditText GetDateAndTime;
    private Button postDataBtn, postDataBtn2;
    private ProgressBar loadingPB;

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our views
        GetDateAndTime = findViewById(R.id.idEdtName);
        //jobEdt = findViewById(R.id.idEdtJob);
        postDataBtn = findViewById(R.id.idBtnPost);
        postDataBtn2 = findViewById(R.id.idBtnPost2);
        loadingPB = findViewById(R.id.idLoadingPB);

        calendar = Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date = simpleDateFormat.format(calendar.getTime());

        GetDateAndTime.setText(Date);


        // adding on click listener to our button.
        postDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to post the data and passing our name and job.
                postData("5001", "EUA-510518", GetDateAndTime.getText().toString(),"1","060540001442");
                Toast.makeText(MainActivity.this, "Вход сделан удачно 777", Toast.LENGTH_SHORT).show();
            }
        });

        // adding on click listener to our button.
        postDataBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to post the data and passing our name and job.
                postData("5001", "EUA-510518",GetDateAndTime.getText().toString(),"2","060540001442");
                Toast.makeText(MainActivity.this, "Выход сделан удачно", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postData(String object_code, String table_number, String event_datetime, String event, String object_bin) {

        // below line is for displaying our progress bar.
        loadingPB.setVisibility(View.VISIBLE);

        // on below line we are creating a retrofit 
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("https://reqres.in/api/")
                .baseUrl("http://tco.aqnietgroup.com:5555/v1/")
                // as we are sending data in json format so 
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // passing data from our text fields to our modal class.
        DataModal modal = new DataModal(object_code, table_number, event_datetime, event, object_bin);

        // calling a method to create a post and passing our modal class.
        Call<DataModal> call = retrofitAPI.createPost(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {
                // this method is called when we get response from our api.
                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.
                loadingPB.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                // below line is for hiding our progress bar.
                loadingPB.setVisibility(View.GONE);
            }
        });
    }
}

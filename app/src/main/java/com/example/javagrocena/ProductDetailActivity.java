package com.example.javagrocena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.javagrocena.adapters.Constants;

public class ProductDetailActivity extends AppCompatActivity {

    String userId;
    boolean isRunning;
    //MyProgressDialog progressDialog;
    NetworkImageView imgGame;
    int image;
    TextView txt_name;
    String name;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        userIdFromPreference();
        //progressDialog = new MyProgressDialog();

        Intent intent= getIntent();
        int price = intent.getIntExtra(Constants.PRODUCT_PRICE_KEY,0);
        String id = intent.getStringExtra(Constants.PRODUCT_ID_KEY);
        name = intent.getStringExtra(Constants.PRODUCT_NAME_KEY);
        image = intent.getIntExtra(Constants.PRODUCT_IMAGE_KEY,R.drawable.coca1);
        String status = intent.getStringExtra(Constants.PRODUCT_STATUS_KEY);






        findByIds();


    }
    private void userIdFromPreference() {
        userId = "1053";

    }

    private void findByIds() {

        imgGame = findViewById(R.id.img_game);
        imgGame.setBackgroundResource(image);
        txt_name = findViewById(R.id.txt_name);
        txt_name.setText(name);

    }
}
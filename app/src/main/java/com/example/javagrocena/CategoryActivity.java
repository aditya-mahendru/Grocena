package com.example.javagrocena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.javagrocena.adapters.ProductAdapter;
import com.example.javagrocena.models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class CategoryActivity extends AppCompatActivity {


    FirebaseFirestore db;

    RecyclerView categoryRec;
    List<ProductModel> productModelList;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent = getIntent();
        String type = intent.getExtras().getString("data");
        System.out.println(type);

        db = FirebaseFirestore.getInstance();
        categoryRec = findViewById(R.id.recycler_view_category);

        categoryRec.setLayoutManager(new GridLayoutManager(this,2));
        productModelList = new ArrayList<>();
        productAdapter = new ProductAdapter(this,productModelList);
        categoryRec.setAdapter(productAdapter);

        db.collection(type)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document: task.getResult())
                            {
                                ProductModel productModel = document.toObject(ProductModel.class);
                                productModelList.add(productModel);
                                System.out.println(productModel.getName());
                                productAdapter.notifyDataSetChanged();
                            }
                        }

                    }
                });

    }
}
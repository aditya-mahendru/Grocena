package com.example.javagrocena.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javagrocena.CategoryActivity;
import com.example.javagrocena.R;
import com.example.javagrocena.adapters.CategoryAdapter;
import com.example.javagrocena.adapters.ShopNamesAdapter;
import com.example.javagrocena.databinding.FragmentHomeBinding;
import com.example.javagrocena.models.CategoryModel;
import com.example.javagrocena.models.ShopModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements CategoryAdapter.OnChooseListener {

    private FragmentHomeBinding binding;

    RecyclerView shopsRec, categoryRec;
    FirebaseFirestore db;
    //ShopNames

    List<ShopModel> shopModelList;
    ShopNamesAdapter shopNamesAdapter;

    List<CategoryModel> categoryModelList;
    CategoryAdapter categoryAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        db = FirebaseFirestore.getInstance();

        shopsRec = root.findViewById(R.id.recycler_view_slider);

        shopsRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        shopModelList = new ArrayList<>();
        shopNamesAdapter = new ShopNamesAdapter(getActivity(),shopModelList);
        shopsRec.setAdapter(shopNamesAdapter);

        db.collection("Shops")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                ShopModel shopModel = document.toObject(ShopModel.class);
                                shopModelList.add(shopModel);
                                shopNamesAdapter.notifyDataSetChanged();
                            }
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        categoryRec = root.findViewById(R.id.recycler_view_categories);

        categoryRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getActivity(),categoryModelList, this);
        categoryRec.setAdapter(categoryAdapter);

        db.collection("Categories")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                            }
                        }
                        else
                        {
                            Toast.makeText(getActivity(),"Error"+task.getException(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCategoryClick(String type) {
        //categoryModelList.get(position);
        Intent intent = new Intent(HomeFragment.this.getActivity(),CategoryActivity.class);
        intent.putExtra("data",type );
        startActivity(intent);
    }
}
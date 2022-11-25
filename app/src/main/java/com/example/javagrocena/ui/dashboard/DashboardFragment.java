package com.example.javagrocena.ui.dashboard;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.javagrocena.ProductDetailActivity;
import com.example.javagrocena.R;
import com.example.javagrocena.adapters.Constants;
import com.example.javagrocena.adapters.SearchViewAdapter;
import com.example.javagrocena.databinding.FragmentDashboardBinding;
import com.example.javagrocena.models.SearchModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardFragment extends Fragment {

    String userId;
    boolean isRunning;
    //MyProgressDialog progressDialog;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;


    ArrayList<String> gameIdList = new ArrayList<String>();
    ArrayList<String> gameNameList = new ArrayList<String>();
    //ArrayList<SliderModel> sliderList = new ArrayList<SliderModel>();

    HashMap<String, String> Hash_file_maps;
    private RecyclerView.Adapter mAdapterGamesList;

    EditText etSearch;
    ArrayList<SearchModel> challengeList = new ArrayList<SearchModel>();

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //progressDialog = new MyProgressDialog();

        findByIds(root);
        recyclerWork(root);

        Hash_file_maps = new HashMap<String, String>();

        return root;
    }

    private void findByIds(View V) {

        etSearch = (EditText)V.findViewById(R.id.et_search);

        ImageView img_search_by = V.findViewById(R.id.img_search_by);
        img_search_by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchString = etSearch.getText().toString();
                if(searchString.equals(""))
                {
                    Toast.makeText(getActivity(),"Please enter search item",Toast.LENGTH_SHORT).show();
                }
                else {
                    getData();
                }
            }
        });

    }

    private void recyclerWork(View V) {

//        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_challenge);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mSwipeRefreshLayout.setRefreshing(false);
//                getJsonRequestGames();
//            }
//        });

        mRecyclerView = (RecyclerView) V.findViewById(R.id.recycler_view_challenge);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setNestedScrollingEnabled(false);


        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLayoutManager = new GridLayoutManager(getActivity(), 2);
            mRecyclerView.setLayoutManager(mLayoutManager);
        } else {
//            mRecycler.setLayoutManager(new GridLayoutManager(mContext, 4));
            mLayoutManager = new GridLayoutManager(getActivity(), 2);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        float yInches = metrics.heightPixels / metrics.ydpi;
        float xInches = metrics.widthPixels / metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
        if (diagonalInches >= 6.5) {
            // 6.5inch device or bigger
            mLayoutManager = new GridLayoutManager(getActivity(), 2);
            mRecyclerView.setLayoutManager(mLayoutManager);
        } else {
            // smaller device

            if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mLayoutManager = new GridLayoutManager(getActivity(), 2);
                mRecyclerView.setLayoutManager(mLayoutManager);
            } else {
//            mRecycler.setLayoutManager(new GridLayoutManager(mContext, 4));
                mLayoutManager = new GridLayoutManager(getActivity(), 2);
                mRecyclerView.setLayoutManager(mLayoutManager);
            }


        }
    }

    private void getData()
    {
        int idTEMP = 1000;
        String id = idTEMP+"";
        int image = R.drawable.coca1;
        String name = "Coca cola Soft drink";
        String status = "";
        int price = 25;
        challengeList.add(new SearchModel(id, name, image, status, price));


        int idTEMP2 = 1002;
        String id2 = idTEMP2+"";
        int image2 = R.drawable.coca2;
        String name2 = "Coca cola Soft";
        String status2 = "";
        int price2 = 35;
        challengeList.add(new SearchModel(id2, name2, image2, status2, price2));


        int idTEMP3 = 1003;
        String id3 = idTEMP3+"";
        int image3 = R.drawable.coca3;
        String name3 = "Coca cola drink";
        String status3 = "";
        int price3 = 25;
        challengeList.add(new SearchModel(id3, name3, image3, status3, price3));


        int idTEMP4 = 1004;
        String id4 = idTEMP4+"";
        int image4 = R.drawable.coca4;
        String name4 = "Coca cola";
        String status4 = "";
        int price4 = 20;
        challengeList.add(new SearchModel(id4, name4, image4, status4, price4));



        int idTEMP5 = 1005;
        String id5 = idTEMP5+"";
        int image5 = R.drawable.coca5;
        String name5 = "Coca cola Soft drink";
        String status5 = "";
        int price5 = 25;
        challengeList.add(new SearchModel(id5, name5, image5, status5, price5));



        int idTEMP6 = 1006;
        String id6 = idTEMP6+"";
        int image6 = R.drawable.coca6;
        String name6 = "Coca cola Soft drink";
        String status6 = "";
        int price6 = 45;
        challengeList.add(new SearchModel(id6, name6, image6, status6, price6));


        RecyclerView.Adapter mAdapter = new SearchViewAdapter(challengeList, getActivity());
        mRecyclerView.setAdapter(mAdapter);



        ((SearchViewAdapter) mAdapter).setOnItemClickListener(new SearchViewAdapter.ChallengeClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.e("check", "click position " + position);

                String id = challengeList.get(position).getId();
                String name = challengeList.get(position).getName();
                String status = challengeList.get(position).getStatus();
                int image = challengeList.get(position).getImage();
                int price = challengeList.get(position).getPrice();

                Intent intentProduct = new Intent(getActivity(), ProductDetailActivity.class);
                intentProduct.putExtra(Constants.PRODUCT_ID_KEY,id);
                intentProduct.putExtra(Constants.PRODUCT_NAME_KEY,name);
                intentProduct.putExtra(Constants.PRODUCT_STATUS_KEY,status);
                intentProduct.putExtra(Constants.PRODUCT_IMAGE_KEY,image);
                intentProduct.putExtra(Constants.PRODUCT_PRICE_KEY,price);
                startActivity(intentProduct);

            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
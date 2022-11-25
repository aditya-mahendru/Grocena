package com.example.javagrocena.adapters;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.javagrocena.R;
import com.example.javagrocena.models.SearchModel;

import java.util.ArrayList;


/**
 * Created by Administrator on 7/22/2016.
 */
public class SearchViewAdapter extends RecyclerView
        .Adapter<SearchViewAdapter
        .DataObjectHolder> {
    private static final String LOG_TAG = "MyRecyclerViewAdapter";
    private static ChallengeClickListener myClickListener;
    Activity mContext;
    private final ArrayList<SearchModel> tournamentList;

    public SearchViewAdapter(ArrayList<SearchModel> myDataset, Activity context) {
        tournamentList = myDataset;
        mContext = context;
    }

    public void setOnItemClickListener(ChallengeClickListener myClickListener) {
        SearchViewAdapter.myClickListener = myClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_unit_view, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        holder.txt_price.setText("Rs. "+tournamentList.get(position).getPrice() + " /-");

        holder.imgGame.setBackgroundResource(tournamentList.get(position).getImage());

        holder.txt_name.setText(tournamentList.get(position).getName() + "");


//        DisplayMetrics displaymetrics = new DisplayMetrics();
//        mContext.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//
//        int width = displaymetrics.widthPixels;
//
//        holder.imgGame.getLayoutParams().height = width / 2 + 20;
//        holder.imgGame.getLayoutParams().width = width / 2;
//
//        holder.rlContainer.getLayoutParams().height = width / 2 + 20;
//        holder.rlContainer.getLayoutParams().width = width / 2;



        holder.imgGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }


        });




    }

    public void addItem(SearchModel dataObj, int index) {
        tournamentList.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        tournamentList.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return tournamentList.size();
    }

//    private void loadImage(NetworkImageView imageView, String url) {
//        if (url.equals("")) {
//
//            return;
//        }
//        ImageLoader imageLoader;
//        imageLoader = CustomVolleyRequest.getInstance(mContext)
//                .getImageLoader();
//        imageLoader.get(url, ImageLoader.getImageListener(imageView,
//                R.color.white, R.color.white));
//        imageView.setImageUrl(url, imageLoader);
//    }

    public interface ChallengeClickListener {
        void onItemClick(int position, View v);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        ImageView imgGame;
        ImageView imgLogo;
        TextView txt_name;
        TextView txt_price;
        LinearLayout rlContainer;

        public DataObjectHolder(View itemView) {
            super(itemView);
            imgGame = (ImageView) itemView.findViewById(R.id.img_game);
            imgLogo = (ImageView) itemView.findViewById(R.id.img_logo);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            txt_price = (TextView) itemView.findViewById(R.id.txt_price);

            rlContainer = (LinearLayout) itemView.findViewById(R.id.rl_container);


            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }
}

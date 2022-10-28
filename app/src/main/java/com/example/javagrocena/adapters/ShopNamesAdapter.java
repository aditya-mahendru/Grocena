package com.example.javagrocena.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.javagrocena.R;
import com.example.javagrocena.models.ShopModel;

import java.util.List;

public class ShopNamesAdapter extends RecyclerView.Adapter<ShopNamesAdapter.ViewHolder> {

    private Context context;
    private List<ShopModel> shopModelList;

    public ShopNamesAdapter(Context context, List<ShopModel> shopModelList) {
        this.context = context;
        this.shopModelList = shopModelList;
    }

    @NonNull
    @Override
    public ShopNamesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_names,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopNamesAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(shopModelList.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(shopModelList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return shopModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popImg = itemView.findViewById(R.id.pop_img);
            name = itemView.findViewById(R.id.shop_name);
        }
    }
}

package com.example.javagrocena.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.javagrocena.CategoryActivity;
import com.example.javagrocena.R;
import com.example.javagrocena.models.ProductModel;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    
    private CategoryActivity context;
    private List<ProductModel> productModelList;
    
    public ProductAdapter (CategoryActivity context, List<ProductModel> productModelList){
        this.context = context;
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_unit_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position)
    {
        Glide.with(context).load(productModelList.get(position).getURL()).into(holder.imgGame);
        holder.name.setText(productModelList.get(position).getName());
        holder.price.setText(productModelList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgGame;//img_game
        TextView price; //txt_price
        TextView name; //txt_name
        public ViewHolder(View itemView) {
            super(itemView);
            imgGame = itemView.findViewById(R.id.img_game);
            price = itemView.findViewById(R.id.txt_price);
            name = itemView.findViewById(R.id.txt_name);
            
        }
    }
}

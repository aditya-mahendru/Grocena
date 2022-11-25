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
import com.example.javagrocena.models.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<CategoryModel> categoryModelList;
    private OnChooseListener monChooseListener;

    public CategoryAdapter(Context context, List<CategoryModel> categoryModelList, OnChooseListener onChooseListener) {
        this.context = context;
        this.categoryModelList = categoryModelList;
        this.monChooseListener = onChooseListener;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_names,parent,false),monChooseListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(categoryModelList.get(position).getUrl()).into(holder.backImg);
        holder.type.setText(categoryModelList.get(position).getType());


    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView backImg;
        TextView type;

        OnChooseListener onChooseListener;

        public ViewHolder(@NonNull View itemView, OnChooseListener onChooseListener){
            super(itemView);
            backImg = itemView.findViewById(R.id.back_img);
            type = itemView.findViewById(R.id.category_type);
            this.onChooseListener = onChooseListener;

            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {

            onChooseListener.onCategoryClick(categoryModelList.get(getAdapterPosition()).getType());

        }
    }
    public interface OnChooseListener {
        void onCategoryClick (String type);
    }
}

package com.dimsen.pasaronline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dimsen.pasaronline.R;
import com.dimsen.pasaronline.data.Category;
import com.dimsen.pasaronline.utils.OnItemClickListener;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHoler> {

    private ArrayList<Category> dataCategories;
    private Context context;

    private OnItemClickListener categoryOnItemClick;

    private ItemAdapter itemAdapter;

    public CategoryAdapter(ArrayList<Category> dataCategories, Context context, OnItemClickListener categoryOnItemClick) {
        this.dataCategories = dataCategories;
        this.context = context;
        this.categoryOnItemClick = categoryOnItemClick;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_category, parent, false);
        return new CategoryViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHoler holder, int position) {
        Category dataCategory = dataCategories.get(position);
        holder.textViewCategoryName.setText(dataCategory.getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryOnItemClick.onItemClicked(dataCategory);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataCategories.size();
    }

    public class CategoryViewHoler extends RecyclerView.ViewHolder {
        TextView textViewCategoryName;

        public CategoryViewHoler(@NonNull View itemView) {
            super(itemView);

            textViewCategoryName = itemView.findViewById(R.id.txtCategoryName);
        }
    }

    public void setFilter(ArrayList<Category> dataCategoryArrayList) {
        dataCategories = new ArrayList<>();
        dataCategories.addAll(dataCategoryArrayList);
        notifyDataSetChanged();
    }
}

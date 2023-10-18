package com.dimsen.pasaronline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dimsen.pasaronline.R;
import com.dimsen.pasaronline.data.DataItem;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHoler> {

    private ArrayList<DataItem> dataCategories;
    private Context context;

    private CategoryItemClickListener categoryItemClickListener;

    public CategoryAdapter(ArrayList<DataItem> dataCategories, Context context, CategoryItemClickListener categoryItemClickListener) {
        this.dataCategories = dataCategories;
        this.context = context;
        this.categoryItemClickListener = categoryItemClickListener;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_category, parent, false);
        return new CategoryViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHoler holder, int position) {
        DataItem dataCategory = dataCategories.get(position);
        holder.textViewCategoryName.setText(dataCategory.getItemName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryItemClickListener.categoryOnItemClick(dataCategory);
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

    public interface CategoryItemClickListener {
        void categoryOnItemClick(DataItem dataItem);
    }
}

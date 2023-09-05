package com.dimsen.pasaronline.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dimsen.pasaronline.data.DataItem;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    private ArrayList<DataItem> dataItems;
    private Context context;

    public ItemsAdapter(ArrayList<DataItem> dataItems, Context context) {
        this.dataItems = dataItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemsAdapter.ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setFilter(ArrayList<DataItem> dataItemArrayList) {
        dataItems = new ArrayList<>();
        dataItems.addAll(dataItemArrayList);
        notifyDataSetChanged();
    }
}

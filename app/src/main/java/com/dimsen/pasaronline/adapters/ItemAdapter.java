package com.dimsen.pasaronline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dimsen.pasaronline.R;
import com.dimsen.pasaronline.data.Item;
import com.dimsen.pasaronline.utils.OnItemClickListener;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemsViewHolder> {

    private ArrayList<Item> dataItems;
    private Context context;

    private OnItemClickListener itemsOnItemClick;

    private ArrayList<Item> originalDataItems;

    public ItemAdapter(ArrayList<Item> dataItems, Context context, OnItemClickListener itemsOnItemClick) {
        this.dataItems = dataItems;
        this.context = context;
        this.itemsOnItemClick = itemsOnItemClick;
        this.originalDataItems = new ArrayList<>(dataItems);
    }

    @NonNull
    @Override
    public ItemAdapter.ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemsViewHolder holder, int position) {
        Item dataItem = dataItems.get(position);
        Glide.with(holder.imageViewItemImage.getContext())
                .load("data:image/jpeg;base64," + dataItem.getItemImage())
                .centerCrop()
                .into(holder.imageViewItemImage);
        holder.textViewItemName.setText(dataItem.getItemName());
        holder.textViewItemPrice.setText("Rp " + dataItem.getItemPrice());
        holder.textViewItemWeight.setText(dataItem.getItemWeight() + " gram");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsOnItemClick.onItemClicked(dataItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItemName, textViewItemPrice, textViewItemWeight;
        ImageView imageViewItemImage;
        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewItemName = itemView.findViewById(R.id.txtItemName);
            textViewItemPrice = itemView.findViewById(R.id.txtItemPrice);
            textViewItemWeight = itemView.findViewById(R.id.txtItemWeight);
            imageViewItemImage = itemView.findViewById(R.id.imgItemImage);
        }
    }

    public void setFilter(ArrayList<Item> dataItemArrayList) {
        dataItems = new ArrayList<>();
        dataItems.addAll(dataItemArrayList);
        notifyDataSetChanged();
    }

    public void showAllItems() {
        dataItems.clear();
        dataItems.addAll(originalDataItems);
        notifyDataSetChanged();
    }
}

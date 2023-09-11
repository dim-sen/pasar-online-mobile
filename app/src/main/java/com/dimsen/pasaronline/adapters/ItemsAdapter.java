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
import com.dimsen.pasaronline.data.DataItem;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    private ArrayList<DataItem> dataItems;
    private Context context;

    private ItemsItemClickListener itemsItemClickListener;

    public ItemsAdapter(ArrayList<DataItem> dataItems, Context context, ItemsItemClickListener itemsItemClickListener) {
        this.dataItems = dataItems;
        this.context = context;
        this.itemsItemClickListener = itemsItemClickListener;
    }

    @NonNull
    @Override
    public ItemsAdapter.ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemsViewHolder holder, int position) {
        DataItem dataItem = dataItems.get(position);
        Glide.with(holder.imageViewItemImage.getContext())
                .load("data:image/jpeg;base64," + dataItem.getItemImage())
                .centerCrop()
                .into(holder.imageViewItemImage);
        holder.textViewItemName.setText(dataItem.getItemName());
        holder.textViewItemPrice.setText(String.valueOf(dataItem.getItemPrice()));
        holder.textViewItemWeight.setText(String.valueOf(dataItem.getItemWeight()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsItemClickListener.itemsOnItemClick(dataItem);
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
            imageViewItemImage = itemView.findViewById(R.id.imgItems);
        }
    }

    public void setFilter(ArrayList<DataItem> dataItemArrayList) {
        dataItems = new ArrayList<>();
        dataItems.addAll(dataItemArrayList);
        notifyDataSetChanged();
    }

    public interface ItemsItemClickListener {
        void itemsOnItemClick(DataItem dataItem);
    }
}

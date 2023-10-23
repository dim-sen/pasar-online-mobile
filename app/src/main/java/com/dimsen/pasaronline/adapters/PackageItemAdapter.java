package com.dimsen.pasaronline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dimsen.pasaronline.R;
import com.dimsen.pasaronline.data.PackageItem;

import java.util.ArrayList;

public class PackageItemAdapter extends RecyclerView.Adapter<PackageItemAdapter.PackageItemsViewHolder> {

    private ArrayList<PackageItem> dataPackageItems;
    private Context context;

    public PackageItemAdapter(ArrayList<PackageItem> dataPackageItems, Context context) {
        this.dataPackageItems = dataPackageItems;
        this.context = context;
    }

    @NonNull
    @Override
    public PackageItemAdapter.PackageItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_detail, parent, false);
        return new PackageItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageItemAdapter.PackageItemsViewHolder holder, int position) {
        PackageItem packageItem = dataPackageItems.get(position);
        holder.textViewPackageItemDetail.setText(packageItem.getItemDao().getItemName());
    }

    @Override
    public int getItemCount() {
        return dataPackageItems.size();
    }

    public class PackageItemsViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPackageItemDetail;
        public PackageItemsViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewPackageItemDetail = itemView.findViewById(R.id.txtPackageItemDetail);
        }
    }
}

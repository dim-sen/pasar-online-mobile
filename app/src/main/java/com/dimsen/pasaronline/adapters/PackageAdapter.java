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
import com.dimsen.pasaronline.data.Package;
import com.dimsen.pasaronline.utils.OnItemClickListener;

import java.util.ArrayList;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.PackageViewHolder> {

    private ArrayList<Package> dataPackages;

    private Context context;

    private OnItemClickListener packageOnItemClick;

    public PackageAdapter(ArrayList<Package> dataPackages, Context context, OnItemClickListener packageOnItemClick) {
        this.dataPackages = dataPackages;
        this.context = context;
        this.packageOnItemClick = packageOnItemClick;
    }

    @NonNull
    @Override
    public PackageAdapter.PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_packages, parent, false);
        return new PackageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageAdapter.PackageViewHolder holder, int position) {
        Package dataPackage = dataPackages.get(position);
        Glide.with(holder.imageViewPackageImage.getContext())
                .load("data:image/jpeg;base64," + dataPackage.getPackageImage())
                .centerCrop()
                .into(holder.imageViewPackageImage);
        holder.textViewPackageName.setText(dataPackage.getPackageName());
        holder.textViewPackagePrice.setText("Rp " + dataPackage.getPackagePrice());
        holder.textViewPackageWeight.setText(dataPackage.getPackageWeight() + " gram");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageOnItemClick.onItemClicked(dataPackage);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataPackages.size();
    }

    public class PackageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPackageName, textViewPackageWeight, textViewPackagePrice;
        ImageView imageViewPackageImage;
        public PackageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewPackageName = itemView.findViewById(R.id.txtItemName);
            textViewPackageWeight = itemView.findViewById(R.id.txtItemWeight);
            textViewPackagePrice = itemView.findViewById(R.id.txtItemPrice);
            imageViewPackageImage = itemView.findViewById(R.id.imgItemImage);
        }
    }

    public void setFilter(ArrayList<Package> dataPackageArrayList) {
        dataPackages = new ArrayList<>();
        dataPackages.addAll(dataPackageArrayList);
        notifyDataSetChanged();
    }
}

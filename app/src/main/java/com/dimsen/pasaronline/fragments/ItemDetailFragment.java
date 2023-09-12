package com.dimsen.pasaronline.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.dimsen.pasaronline.R;
import com.dimsen.pasaronline.data.DataItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDetailFragment extends Fragment {
    private TextView itemNameTextView, itemPriceTextView, itemWeightTextView, itemStockTextView;
    private ImageView itemImageImageView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ItemDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemDetailFragment newInstance(String param1, String param2) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        Toolbar toolbarItemDetail = view.findViewById(R.id.toolbarItemDetail);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbarItemDetail);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("");

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarItemDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        itemNameTextView = view.findViewById(R.id.txtDetailItemName);
        itemPriceTextView = view.findViewById(R.id.txtDetailItemPrice);
        itemWeightTextView = view.findViewById(R.id.txtDetailItemWeight);
        itemStockTextView = view.findViewById(R.id.txtDetailItemStock);
        itemImageImageView = view.findViewById(R.id.imgDetailItemImage);

        getArgumentsParcelable();

        return view;
    }

    public void getArgumentsParcelable() {
        Bundle bundle = getArguments();
        Log.d("Bundle Content", String.valueOf(bundle));
        if (bundle != null) {
            ArrayList<DataItem> detailDataItemArrayList = bundle.getParcelableArrayList("items");
            if (detailDataItemArrayList != null) {
                DataItem detailDataItem = detailDataItemArrayList.get(0);
                Glide.with(getContext())
                                .load("data:image/jpeg;base64," + detailDataItem.getItemImage())
                                        .centerCrop()
                                                .into(itemImageImageView);
                itemNameTextView.setText(detailDataItem.getItemName());
                itemPriceTextView.setText(String.valueOf(detailDataItem.getItemPrice()));
                itemWeightTextView.setText(String.valueOf(detailDataItem.getItemWeight()));
                Log.d("Parcel Items", String.valueOf(detailDataItemArrayList.size()));
            }
        }
    }
}
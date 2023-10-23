package com.dimsen.pasaronline.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dimsen.pasaronline.R;
import com.dimsen.pasaronline.adapters.PackageItemAdapter;
import com.dimsen.pasaronline.data.Item;
import com.dimsen.pasaronline.data.Package;
import com.dimsen.pasaronline.data.PackageItem;
import com.dimsen.pasaronline.requests.ApiService;
import com.dimsen.pasaronline.responses.PackageItemResponse;
import com.dimsen.pasaronline.utils.PackageItemApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PackageDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PackageDetailFragment extends Fragment {

    private TextView textViewPackageName, textViewPackageWeight, textViewPackagePrice, textViewPackageDescription;
    private ImageView imageViewPackageImage;
    private ArrayList<PackageItem> packageItemArrayList = new ArrayList<>();

    private RecyclerView recyclerViewPackageItem;
    private PackageItemAdapter packageItemAdapter;
    private Long packageId;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PackageDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PackageDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PackageDetailFragment newInstance(String param1, String param2) {
        PackageDetailFragment fragment = new PackageDetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_package_detail, container, false);

        Toolbar toolbarPackageDetail = view.findViewById(R.id.toolbarPackageDetail);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbarPackageDetail);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("");

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPackageDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        textViewPackageName = view.findViewById(R.id.txtPackageName);
        textViewPackageWeight = view.findViewById(R.id.txtPackageWeight);
        textViewPackagePrice = view.findViewById(R.id.txtPackagePrice);
        textViewPackageDescription = view.findViewById(R.id.txtPackageDescription);
        imageViewPackageImage = view.findViewById(R.id.imgPackageImage);

        getArgumentsParcelable();
        initRecyclerView(view);
        getPackageItemResponse();

        return view;
    }

    private void initRecyclerView(View view) {
        recyclerViewPackageItem = view.findViewById(R.id.recyclerItemDetail);
        recyclerViewPackageItem.setHasFixedSize(true);
        recyclerViewPackageItem.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,
                false));
    }

    private void getPackageItemResponse() {
        PackageItemApi packageItemApi = ApiService.packageItemApi();
        Call<PackageItemResponse> packageItemResponseCall = packageItemApi.PACKAGE_ITEM_RESPONSE_CALL(packageId);

        packageItemResponseCall.enqueue(new Callback<PackageItemResponse>() {
            @Override
            public void onResponse(Call<PackageItemResponse> call, Response<PackageItemResponse> response) {
                packageItemArrayList.clear();
                Log.d("PACKAGE ITEM", "onResponse: " + response.code());
                Log.d("RESPONSE", "onResponse: " + response.body());
                if (response.body() != null) {
                    packageItemArrayList.addAll(response.body().getDataPackageItem());
                }
                packageItemAdapter = new PackageItemAdapter(packageItemArrayList, getContext());
                recyclerViewPackageItem.setAdapter(packageItemAdapter);
            }

            @Override
            public void onFailure(Call<PackageItemResponse> call, Throwable t) {
                Log.d("RESULT", "FAILURE", t);
            }
        });
    }

    private void getArgumentsParcelable() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            Package packages = bundle.getParcelable("packages");
            if (packages != null) {
                Glide.with(getContext())
                        .load("data:image/jpeg;base64," + packages.getPackageImage())
                        .centerCrop()
                        .into(imageViewPackageImage);
                textViewPackageName.setText(packages.getPackageName());
                textViewPackageWeight.setText(packages.getPackageWeight() + " gram");
                textViewPackagePrice.setText("Rp " + packages.getPackagePrice());
                textViewPackageDescription.setText(packages.getPackageDescription());

                packageId = packages.getId();
            }
        }
    }
}
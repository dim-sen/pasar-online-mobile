package com.dimsen.pasaronline.fragments;

import android.app.SearchManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dimsen.pasaronline.R;
import com.dimsen.pasaronline.adapters.ItemsAdapter;
import com.dimsen.pasaronline.data.DataItem;
import com.dimsen.pasaronline.requests.ApiService;
import com.dimsen.pasaronline.responses.ItemsResponse;
import com.dimsen.pasaronline.utils.ItemsApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements ItemsAdapter.ItemsItemClickListener{

    private ArrayList<DataItem> dataItemArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemsAdapter itemsAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbarHome = view.findViewById(R.id.toolbarHome);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarHome);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        setHasOptionsMenu(true);

        initRecyclerView(view);
        getApiResponse();
        return view;
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),
                2,
                LinearLayoutManager.VERTICAL,
                false));
    }

    private void getApiResponse() {
        ItemsApi itemsApi = ApiService.getItemsApi();

        Call<ItemsResponse> itemsResponseCall = itemsApi.DATA_ITEM_CALL();

        itemsResponseCall.enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                dataItemArrayList.clear();
                Log.d("RESULT", "RESPONSE" + response.code());
                if (response.body() != null) {
                    dataItemArrayList.addAll(response.body().getDataItems());
                }
                itemsAdapter = new ItemsAdapter(dataItemArrayList, getActivity().getApplicationContext(), HomeFragment.this::itemsOnItemClick);
                recyclerView.setAdapter(itemsAdapter);
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                Log.d("RESULT", "FAILURE", t);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search_menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(AppCompatActivity.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search here");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(false);

        SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<DataItem> dataItems = new ArrayList<>();

                for (DataItem dataItem : dataItemArrayList) {
                    String itemName = dataItem.getItemName().toLowerCase();
                    if (itemName.contains(newText.toLowerCase())) {
                        dataItems.add(dataItem);
                    }
                }

                itemsAdapter.setFilter(dataItems);
                return true;
            }
        };

        searchView.setOnQueryTextListener(onQueryTextListener);
    }

    @Override
    public void itemsOnItemClick(DataItem dataItem) {
        Log.d("dataItemArrayList", String.valueOf(dataItemArrayList));
        Bundle itemsBundle = new Bundle();
        itemsBundle.putParcelableArrayList("items", dataItemArrayList);
        Log.d("itemsBundle", String.valueOf(itemsBundle));

        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.homeHostFragment);
        NavController navController = navHostFragment.getNavController();

        navController.navigate(R.id.itemDetailFragment, itemsBundle);
    }
}
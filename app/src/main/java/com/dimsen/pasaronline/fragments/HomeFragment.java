package com.dimsen.pasaronline.fragments;

import android.app.SearchManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
import com.dimsen.pasaronline.adapters.CategoryAdapter;
import com.dimsen.pasaronline.adapters.ItemAdapter;
import com.dimsen.pasaronline.adapters.PackageAdapter;
import com.dimsen.pasaronline.data.Category;
import com.dimsen.pasaronline.data.Item;
import com.dimsen.pasaronline.data.Package;
import com.dimsen.pasaronline.requests.ApiService;
import com.dimsen.pasaronline.responses.CategoryResponse;
import com.dimsen.pasaronline.responses.ItemsResponse;
import com.dimsen.pasaronline.responses.PackageResponse;
import com.dimsen.pasaronline.utils.CategoryApi;
import com.dimsen.pasaronline.utils.ItemsApi;
import com.dimsen.pasaronline.utils.OnItemClickListener;
import com.dimsen.pasaronline.utils.PackageApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements OnItemClickListener {

    private ArrayList<Item> dataItemArrayList = new ArrayList<>();
    private ArrayList<Category> categoryArrayList = new ArrayList<>();
    private ArrayList<Package> packageArrayList = new ArrayList<>();
    private ArrayList<Category> originalCategoryArrayList = new ArrayList<>();
    private ArrayList<Package> originalPackageArrayList = new ArrayList<>();
    private RecyclerView itemRecyclerView;
    private RecyclerView categoryRecyclerView;
    private RecyclerView packageRecyclerView;
    private ItemAdapter itemsAdapter;
    private CategoryAdapter categoryAdapter;
    private PackageAdapter packageAdapter;
    private Category currentCategory = null;

    private TextView textViewCategoryTitle, textViewPackageTitle;

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

        textViewCategoryTitle = view.findViewById(R.id.txtCategoryTitle);
        textViewPackageTitle = view.findViewById(R.id.txtPackageTitle);

        initRecyclerView(view);
        getItemResponse();
        getCategoryResponse();
        getPackageResponse();
        return view;
    }

    private void getPackageResponse() {
        PackageApi packageApi = ApiService.packageApi();
        Call<PackageResponse> packageResponseCall = packageApi.PACKAGE_RESPONSE_CALL();

        packageResponseCall.enqueue(new Callback<PackageResponse>() {
            @Override
            public void onResponse(Call<PackageResponse> call, Response<PackageResponse> response) {
                packageArrayList.clear();
                Log.d("PACKAGE", "onResponse: " + response.code());
                if (response.body() != null) {
                    packageArrayList.addAll(response.body().getDataPackage());
                    originalPackageArrayList.addAll(packageArrayList);
                }
                packageAdapter = new PackageAdapter(packageArrayList, getActivity().getApplicationContext(), HomeFragment.this::onItemClicked);
                packageRecyclerView.setAdapter(packageAdapter);
            }

            @Override
            public void onFailure(Call<PackageResponse> call, Throwable t) {
                Log.d("RESULT", "FAILURE", t);
            }
        });
    }

    private void getCategoryResponse() {
        CategoryApi categoryApi = ApiService.categoryApi();
        Call<CategoryResponse> categoryResponseCall = categoryApi.CATEGORY_RESPONSE_CALL();

        categoryResponseCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                categoryArrayList.clear();
                Log.d("CATEGORY", "onResponse: " + response.code());
                if (response.body() != null) {
                    categoryArrayList.addAll(response.body().getDataCategory());
                    originalCategoryArrayList.addAll(categoryArrayList);
                }
                categoryAdapter = new CategoryAdapter(categoryArrayList, getActivity().getApplicationContext(), HomeFragment.this::onItemClicked);
                categoryRecyclerView.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.d("RESULT", "FAILURE", t);
            }
        });
    }

    private void initRecyclerView(View view) {
        itemRecyclerView = view.findViewById(R.id.recyclerItems);
        itemRecyclerView.setHasFixedSize(true);
        itemRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(),
                2,
                LinearLayoutManager.VERTICAL,
                false));

        categoryRecyclerView = view.findViewById(R.id.recyclerCategories);
        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false));

        packageRecyclerView = view.findViewById(R.id.recyclerPackages);
        packageRecyclerView.setHasFixedSize(true);
        packageRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
    }

    private void getItemResponse() {
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
                itemsAdapter = new ItemAdapter(dataItemArrayList, getActivity().getApplicationContext(), HomeFragment.this::onItemClicked);
                itemRecyclerView.setAdapter(itemsAdapter);
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
                ArrayList<Item> dataItems = new ArrayList<>();

                for (Item dataItem : dataItemArrayList) {
                    String itemName = dataItem.getItemName().toLowerCase();
                    if (itemName.contains(newText.toLowerCase())) {
                        dataItems.add(dataItem);
                    }
                }
                itemsAdapter.setFilter(dataItems);

                ArrayList<Category> categories = new ArrayList<>();
                for (Category category : categoryArrayList) {
                    String categoryName = category.getCategoryName().toLowerCase();
                    if (categoryName.contains(newText.toLowerCase())) {
                        categories.add(category);
                    }
                }
                categoryAdapter.setFilter(categories);

                ArrayList<Package> packages = new ArrayList<>();
                for (Package aPackage : packageArrayList) {
                    String packageName = aPackage.getPackageName().toLowerCase();
                    if (packageName.contains(newText.toLowerCase())) {
                        packages.add(aPackage);
                    }
                }
                packageAdapter.setFilter(packages);
                return true;
            }

        };

        searchView.setOnQueryTextListener(onQueryTextListener);
    }

    @Override
    public void onItemClicked(Object model) {
        if (model instanceof Item) {
            Bundle itemsBundle = new Bundle();
            itemsBundle.putParcelable("items", (Parcelable) model);

            NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.homeHostFragment);
            NavController navController = navHostFragment.getNavController();

            navController.navigate(R.id.itemDetailFragment, itemsBundle);
        }

        if (model instanceof Category) {
            Category clickedCategory = (Category) model;

            if (currentCategory != null && currentCategory.equals(clickedCategory)) {
                currentCategory = null;
                itemsAdapter.showAllItems();
            } else {
                currentCategory = clickedCategory;

                ArrayList<Item> dataItems = new ArrayList<>();

                for (Item dataItem : dataItemArrayList) {
                    if (dataItem.getCategoryDao().getCategoryName().equals(clickedCategory.getCategoryName())) {
                        dataItems.add(dataItem);
                    }
                }
                itemsAdapter.setFilter(dataItems);
                Toast.makeText(getActivity().getApplicationContext(), "Tap " + clickedCategory.getCategoryName() + " Kembali Untuk Menampilkan Semua", Toast.LENGTH_SHORT).show();
            }
        }

        if (model instanceof Package) {
            Bundle itemsBundle = new Bundle();
            itemsBundle.putParcelable("packages", (Parcelable) model);

            NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.homeHostFragment);
            NavController navController = navHostFragment.getNavController();

            navController.navigate(R.id.packageDetailFragment, itemsBundle);
        }
    }
}
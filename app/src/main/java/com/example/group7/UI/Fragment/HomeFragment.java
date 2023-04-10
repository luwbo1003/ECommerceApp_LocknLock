package com.example.group7.UI.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.GridView;

import com.example.group7.R;
import com.example.group7.UI.Adapters.BannerApdater;
import com.example.group7.UI.Adapters.BannerViewHolder;
import com.example.group7.UI.Adapters.CategoryAdapter;
import com.example.group7.UI.Adapters.ProductAdapter;
import com.example.group7.ViewModels.BannerViewModel;
import com.example.group7.ViewModels.CategoryViewModel;
import com.example.group7.ViewModels.ProductViewModel;
import com.example.group7.models.Banner;
import com.example.group7.models.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

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

    private BannerViewModel bannerViewModel;
    private CategoryViewModel categoryViewModel;
    private ProductViewModel productViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_home, container, false);

//        ArrayList<Banner> banners = new ArrayList<>();

//        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Banners");
//        dbref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Banner banner = dataSnapshot.getValue(Banner.class);
//                    banners.add(banner);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        //Banners

        ViewPager2 viewPager2 = contentView.findViewById(R.id.viewPager2);
        BannerApdater bannerApdater = new BannerApdater(viewPager2, contentView.getContext());

        viewPager2.setAdapter(bannerApdater);

        //Retrieve banners data
        bannerViewModel = new ViewModelProvider(this).get(BannerViewModel.class);
        bannerViewModel.getBannersLiveData().observe(getViewLifecycleOwner(), banners -> {
            if (banners != null) {
                bannerApdater.setBanners(banners);
            }
        });

        //Set Transformer for ViewPager2
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

        // Category
        RecyclerView recyclerView = contentView.findViewById(R.id.recyclerView);
        CategoryAdapter categoryAdapter = new CategoryAdapter(contentView.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(contentView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(categoryAdapter);

            // retrieve categories data

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.getCategoriesLiveData().observe(getViewLifecycleOwner(), categories -> {
            if (categories != null){
                categoryAdapter.setCategories(categories);
            }
        });

        //Gridview product
        GridView gridView = contentView.findViewById(R.id.gridView);
        ProductAdapter productAdapter = new ProductAdapter(contentView.getContext());
        gridView.setAdapter(productAdapter);


        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProductLiveData().observe(getViewLifecycleOwner(), products -> {
            
        });
        return contentView;
    }
}
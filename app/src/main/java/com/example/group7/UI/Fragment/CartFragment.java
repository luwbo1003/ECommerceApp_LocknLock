package com.example.group7.UI.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.group7.R;
import com.example.group7.UI.Adapters.CartAdapter;
import com.example.group7.ViewModels.CartViewModel;
import com.example.group7.ViewModels.OrderViewModel;
import com.example.group7.ViewModels.ProductViewModel;
import com.example.group7.models.Cart;
import com.example.group7.models.Order;
import com.example.group7.models.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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

    CartViewModel cartViewModel;
    ProductViewModel productViewModel;
    Button btn_checkout;
    TextView tv_subtotal, tv_total, tv_shipping;
    String user_id = "";
    ArrayList<Cart> list;
    LinearLayout linear_checkout, linear_cart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_cart, container, false);

        btn_checkout = contentView.findViewById(R.id.btn_checkout);
        tv_subtotal = contentView.findViewById(R.id.iv_subtotal_price);
        tv_total = contentView.findViewById(R.id.iv_total_price);
        tv_shipping = contentView.findViewById(R.id.iv_delivery_price);
        linear_cart = contentView.findViewById(R.id.linear_cart);
        linear_checkout = contentView.findViewById(R.id.linear_checkout);


        Bundle args = getArguments();
        if (args != null) {
            user_id = args.getString("id");
        }

        //RecyclerView for Carts
        RecyclerView recyclerView = contentView.findViewById(R.id.ryc_cart);
        CartAdapter cartAdapter = new CartAdapter(contentView.getContext());
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(contentView.getContext()));

        //Lấy dữ liêu giỏ hàng
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getCartsLiveData().observe(getViewLifecycleOwner(), carts -> {
            if (carts != null) {
                list = CartViewModel.getCartsByUserId(carts, user_id);
                cartAdapter.setCarts(list);
                if (list.size() < 1){
                    linear_checkout.setVisibility(View.GONE);
                    linear_cart.setVisibility(View.VISIBLE);
                }
            }
        });

        //Lấy dữ liệu sản phẩm
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProductLiveData().observe(getViewLifecycleOwner(), products -> {
            if (products != null) {
                cartAdapter.setProducts(products);
            }
        });

        cartAdapter.getSubtotal().observe(getViewLifecycleOwner(), total -> {
            if (total != null) {
                tv_subtotal.setText(String.valueOf(total));
                int ship = Integer.parseInt(tv_shipping.getText().toString());
                int totals = total + ship;
                tv_total.setText(String.valueOf(totals));
            }
        });

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                Date now = calendar.getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String strDate = formatter.format(now);
                String total = tv_total.getText().toString();

                OrderViewModel orderViewModel = new OrderViewModel();
                String key = orderViewModel.getOrderKey();
                Order order = new Order(key, strDate, "My Purchases", user_id, total);

                orderViewModel.addOrder(order, key, getContext(), "Purchase successfully", "Purchase failed");
                for (Cart cart : list) {
                    CartViewModel.deleteCart(cart.getId());
                }
            }
        });
        return contentView;
    }
}
package com.example.group7.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group7.R;
import com.example.group7.Storage.StorageUtils;
import com.example.group7.ViewModels.ProductViewModel;
import com.example.group7.models.Cart;
import com.example.group7.models.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private ArrayList<Cart> carts;
    private ArrayList<Product> products;
    private Context context;
    private final LayoutInflater mInfalter;
    private MutableLiveData<Integer> subtotal;

    public CartAdapter(Context context) {
        this.context = context;
        this.subtotal = new MutableLiveData<>();
        this.carts = new ArrayList<Cart>();
        this.products = new ArrayList<Product>();
        mInfalter = LayoutInflater.from(context);
    }

    public void setCarts(ArrayList<Cart> carts) {
        this.carts = carts;
        notifyDataSetChanged();
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public void updateSubTotal() {
        int value = 0;
        for (Cart cart : carts) {
            Product product = ProductViewModel.getProductByIdFromList(products, cart.getProd_id());
            value += product.getProduct_price() * cart.getQuantity();
        }
        subtotal.setValue(value);
    }

    public MutableLiveData<Integer> getSubtotal() {
        return subtotal;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View mItemView = mInfalter.inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(mItemView, this, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the contents of the view with that element
        Cart mCurrent = carts.get(position);
        holder.setId(mCurrent.getId());

        if (products.size() > 0) {
            Product product = ProductViewModel.getProductByIdFromList(products, mCurrent.getProd_id());

            holder.getTv_title().setText(product.getProduct_name());
            holder.getTv_price().setText(product.getProduct_price() + "");
            holder.getTv_quantity().setText(mCurrent.getQuantity() + "");
            holder.setMaxQuan(product.getProduct_quantity());
            holder.setTempQuan(mCurrent.getQuantity());

            updateSubTotal();
            StorageUtils.loadStorageImageIntoImageView("product_img", product.getProduct_img(), holder.getIv_image());
        }
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }
}
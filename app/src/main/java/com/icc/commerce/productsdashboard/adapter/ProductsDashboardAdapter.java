package com.icc.commerce.productsdashboard.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icc.commerce.productsdashboard.R;
import com.icc.commerce.productsdashboard.model.Item;
import com.icc.commerce.productsdashboard.model.Product;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class ProductsDashboardAdapter extends ListAdapter<Product, ProductsDashboardAdapter.ProductCardViewHolder> {

    private OnItemClickedListener listener;

    public ProductsDashboardAdapter() {
        super(callback);
    }

    final private static DiffUtil.ItemCallback<Product> callback = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product firstProduct, @NonNull Product secondProduct) {
            return firstProduct.compareTo(secondProduct) == 0;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product firstProduct, @NonNull Product secondProduct) {
            return firstProduct.equals(secondProduct);
        }
    };

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dasboard_item_layout, parent, false);
        return new ProductCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder productCardViewHolder, int position) {
        Product currentProduct = getItem(position);
        if (!TextUtils.isEmpty(currentProduct.getTitle()))
            productCardViewHolder.mTitle.setText(currentProduct.getTitle());
        if (!TextUtils.isEmpty(currentProduct.getBottomDescription()))
            productCardViewHolder.mBottomDescription.setText(Html.fromHtml(currentProduct.getBottomDescription()));
        if (!TextUtils.isEmpty(currentProduct.getPromoMessage()))
            productCardViewHolder.mPromoMessage.setText(currentProduct.getPromoMessage());
        if (!TextUtils.isEmpty(currentProduct.getTopDescription()))
            productCardViewHolder.mTopDescription.setText(currentProduct.getTopDescription());
        Picasso.get()
                .load(currentProduct.getProductImageUrl())
                .error(R.drawable.ic_launcher_background)
                .into(productCardViewHolder.mImage, new Callback() {
                    @Override
                    public void onSuccess() {
                    }
                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
        if (currentProduct.getContent() != null && currentProduct.getContent().size() > 0) {
            populateContent(currentProduct, productCardViewHolder);
        }

    }

    private void populateContent(Product currentProduct, ProductCardViewHolder productCardViewHolder) {
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // make sure the linearLayout is empty
        if (productCardViewHolder.mContent.getChildCount() > 0) {
            productCardViewHolder.mContent.removeAllViews();
        }
        final List<Item> content = currentProduct.getContent();
        for (Item item : content) {
            Button button = new Button(productCardViewHolder.itemView.getContext());
            button.setBackgroundResource(R.drawable.btn_transparent_bg_bordered);
            button.setText(item.getTitle());
            button.setOnClickListener(v -> {
                if (listener != null) {
                    listener.displayItem(item.getUrl());
                } else {
                    Timber.d("The listener was null!!");
                }
            });
            productCardViewHolder.mContent.addView(button, params);
        }
    }

    final class ProductCardViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_image)
        ImageView mImage;
        @BindView(R.id.top_description)
        TextView mTopDescription;
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.promo_message)
        TextView mPromoMessage;
        @BindView(R.id.bottom_description)
        TextView mBottomDescription;
        @BindView(R.id.content)
        LinearLayout mContent;

        ProductCardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setListener(OnItemClickedListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickedListener {
        void displayItem(String url);
    }
}

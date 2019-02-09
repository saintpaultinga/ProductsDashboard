package com.icc.commerce.productsdashboard.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.icc.commerce.productsdashboard.R;
import com.icc.commerce.productsdashboard.adapter.ProductsDashboardAdapter;
import com.icc.commerce.productsdashboard.config.ApplicationConfig;
import com.icc.commerce.productsdashboard.net.util.Utils;
import com.icc.commerce.productsdashboard.viewmodel.ProductViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class DashboardActivity extends AppCompatActivity implements
        ProductsDashboardAdapter.OnItemClickedListener {

    @BindView(R.id.dashboard)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_layout)
    CoordinatorLayout mCoordinatorLayout;
    @Inject
    ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_main);
        ButterKnife.bind(this);
        ((ApplicationConfig) getApplication()).getApplicationComponent().inject(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setHasFixedSize(true);

        final ProductsDashboardAdapter productsDashboardAdapterdapter =
                new ProductsDashboardAdapter();
        mRecyclerView.setAdapter(productsDashboardAdapterdapter);

        productsDashboardAdapterdapter.setListener(this);

        if (!Utils.isConnectedToInternet(this)) {
            Timber.d("No internet connection");
            Snackbar.make(mCoordinatorLayout,getString(R.string.no_internet_connexion),
                    Snackbar.LENGTH_SHORT).show();
            return;
        }

        productViewModel.getProductList().observe(this, productsDashboardAdapterdapter::submitList);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    public void displayItem(String url) {
        Intent intent = new Intent(this, ItemWebViewActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}

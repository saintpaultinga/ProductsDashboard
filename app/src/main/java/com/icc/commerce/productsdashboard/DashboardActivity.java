package com.icc.commerce.productsdashboard;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.icc.commerce.productsdashboard.adapters.ProductsDashboardAdapter;
import com.icc.commerce.productsdashboard.net.util.Utils;
import com.icc.commerce.productsdashboard.viewmodel.ProductViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class DashboardActivity extends AppCompatActivity implements
        ProductsDashboardAdapter.OnItemClickedListener {

    @BindView(R.id.dashboard)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_main);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setHasFixedSize(true);

        final ProductsDashboardAdapter productsDashboardAdapterdapter =
                new ProductsDashboardAdapter();
        mRecyclerView.setAdapter(productsDashboardAdapterdapter);

        // TODO : use dagger for injection
        ProductViewModel productViewModel = ViewModelProviders.of(this)
                .get(ProductViewModel.class);
        productsDashboardAdapterdapter.setListener(this);

        if (!Utils.isConnectedToInternet(this)) {
            Timber.d("No internet connection");
            Snackbar.make(coordinatorLayout,getString(R.string.no_internet_connexion),
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

package com.icc.commerce.productsdashboard.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.icc.commerce.productsdashboard.model.Product;
import com.icc.commerce.productsdashboard.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;

public class ProductViewModel extends ViewModel {

    private ProductRepository repository;
    @Inject
    public ProductViewModel(ProductRepository repository) {
            this.repository = repository;
    }

    // TODO this should be remove, I've added just for unit testing
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Product>> getProductList() {
        return repository.getRemoteProducts();
    }

}

package com.icc.commerce.productsdashboard.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.icc.commerce.productsdashboard.model.Product;
import com.icc.commerce.productsdashboard.net.service.IProductService;
import com.icc.commerce.productsdashboard.net.util.ServiceGenerator;
import com.icc.commerce.productsdashboard.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends ViewModel {

    private ProductRepository repository;
    // TODO: use dagger for injection here instead of letting the viewModel doing the instanciation
    public ProductViewModel() {
            this.repository = ProductRepository.getRepositoryInstance(
                    ServiceGenerator.createService(IProductService.class));
    }

    // TODO this should be remove, I've added just for unit testing
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Product>> getProductList() {
        return repository.getRemoteProducts();
    }

}

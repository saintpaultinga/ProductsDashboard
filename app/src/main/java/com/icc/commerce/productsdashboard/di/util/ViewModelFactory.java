package com.icc.commerce.productsdashboard.di.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.icc.commerce.productsdashboard.repository.ProductRepository;
import com.icc.commerce.productsdashboard.viewmodel.ProductViewModel;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final ProductRepository repository;

    @Inject
    public ViewModelFactory(ProductRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProductViewModel.class)) {
            return (T) new ProductViewModel(repository);
        }
        throw new IllegalArgumentException("Unkown class name");
    }
}

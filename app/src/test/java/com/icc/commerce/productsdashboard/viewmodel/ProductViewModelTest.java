package com.icc.commerce.productsdashboard.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import com.icc.commerce.productsdashboard.model.Product;
import com.icc.commerce.productsdashboard.repository.ProductRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductViewModelTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductViewModel productViewModel;
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Test
    public void test_GetProductList_isEmpty() {
        MutableLiveData<List<Product>> products = new MutableLiveData<>();
        when(productRepository.getRemoteProducts())
                .thenReturn(products);
        products.setValue(new ArrayList<Product>());
        assertEquals(0, productViewModel.getProductList().getValue().size());
    }

    @Test
    public void test_GetProductList_isNotEmpty() {
        MutableLiveData<List<Product>> mlProducts = new MutableLiveData<>();
        when(productRepository.getRemoteProducts())
                .thenReturn(mlProducts);
        // prepare the data
        Product product = new Product();
        product.setTitle("Jean");
        product.setProductImageUrl("https://www.abercrombie.com/anf/nativeapp/test/jean/j1.png");

        Product product1 = new Product();
        product.setTitle("T-Short");
        product.setProductImageUrl("https://www.abercrombie.com/anf/nativeapp/test/jean/j2.png");
        // add the product to the list
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);
        // set the list to the liveData
        mlProducts.setValue(productList);

        assertEquals(2, productViewModel.getProductList().getValue().size());
    }

    @Test
    public void test_GetProductList_isNull() {
        MutableLiveData<List<Product>> products = new MutableLiveData<>();
        when(productRepository.getRemoteProducts())
                .thenReturn(products);
        assertNull(null, productViewModel.getProductList().getValue());
    }
}
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
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductViewModelTest {

    ProductRepository productRepository;
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();


    @Before
    public void setUp() throws Exception {
        productRepository = mock(ProductRepository.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_GetProductList_isEmpty() {
        MutableLiveData<List<Product>> products = new MutableLiveData<>();
        when(productRepository.getRemoteProducts())
                .thenReturn(products);
        products.setValue(new ArrayList<Product>());
        ProductViewModel viewModel = new ProductViewModel();
        viewModel.setRepository(productRepository);
        assertEquals(0, viewModel.getProductList().getValue().size());
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

        ProductViewModel viewModel = new ProductViewModel();
        viewModel.setRepository(productRepository);
        assertEquals(2, viewModel.getProductList().getValue().size());
    }

    @Test
    public void test_GetProductList_isNull() {
        MutableLiveData<List<Product>> products = new MutableLiveData<>();
        when(productRepository.getRemoteProducts())
                .thenReturn(products);
        ProductViewModel viewModel = new ProductViewModel();
        viewModel.setRepository(productRepository);
        assertEquals(null, viewModel.getProductList().getValue());
    }
}
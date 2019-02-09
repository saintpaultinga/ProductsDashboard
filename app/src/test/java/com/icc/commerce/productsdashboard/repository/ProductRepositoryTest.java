package com.icc.commerce.productsdashboard.repository;

import com.icc.commerce.productsdashboard.net.service.IProductService;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class ProductRepositoryTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    IProductService productService;
    @InjectMocks
    ProductRepository repository;

    @Test
    public void test_LoadProductCatalog_isCalled() {
        repository.getRemoteProducts();
        verify(productService, times(1)).loadProductCatalog();
    }
}
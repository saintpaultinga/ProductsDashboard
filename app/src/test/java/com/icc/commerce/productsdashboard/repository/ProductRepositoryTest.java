package com.icc.commerce.productsdashboard.repository;

import com.icc.commerce.productsdashboard.net.service.IProductService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductRepositoryTest {

    IProductService productService;

    @Before
    public void setUp() throws Exception {
        productService = mock(IProductService.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_LoadProductCatalog_isCalled() {
        ProductRepository repository = ProductRepository.getRepositoryInstance(productService);
        repository.getRemoteProducts();
        verify(productService, times(1)).loadProductCatalog();
    }
}
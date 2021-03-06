package com.ecommerce.shop.data.repository;

import com.ecommerce.shop.data.model.Currency;
import com.ecommerce.shop.data.model.Product;
import com.ecommerce.shop.payload.request.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepositoryImpl;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create_product_test(){
        Product product = new Product();

        product.setName("Luxury Sofa");
        product.setPrice(400D);
        product.setCurrency(Currency.NGN);
        product.setDetails("i love you");

        assertThat(product).isNotNull();
        assertThat(product.getId()).isNull();

        log.info("Product before saving -> {}", product);

        productRepositoryImpl.save(product);

        assertThat(product.getId()).isNotNull();
    }

    @Test
    @Transactional
    void returnProductTestUSingFindAllMethod(){
        List<Product> products = productRepositoryImpl.findAll();
        assertThat(products).hasSize(4);
        log.info("Product returned from database -> {}", products);
    }

    @Test
    @Transactional
    void find_existing_product_by_id(){
      Product existingProduct =  productRepositoryImpl.findById(110L).orElse(null);
      assertThat(existingProduct).isNotNull();
      log.info("Product -> {}", existingProduct);
    }

    @Test
    void delete_existing_product_by_id(){
        assertThat(productRepositoryImpl.findById(110L).orElse(null)).isNotNull();
        productRepositoryImpl.deleteById(110L);
        assertThat(productRepositoryImpl.findById(110L).orElse(null)).isNull();
    }

    @Test
    void update_product_by_id(){
//        Product product =  new Product();
//
//        List<String> imageUrl = new ArrayList<>(List.of("image1, image2"));
//
//        product =  new Product();
//        product.setId(1L);
//        product.setName("product 1");
//        product.setDetails("details");
//        product.setPrice(12.00);
//        product.setCurrency(Currency.NGN);
//        product.setImageUrl(imageUrl);
//        productRepositoryImpl.save(product);
//
//
//        ProductRequest productRequest =  new ProductRequest();
//        productRequest.setName("test product");
//
////        System.out.println(productServiceImpl.findById(1L));
//        Product updatedProduct = productRepositoryImpl.updateProduct(1L, productRequest);
//        System.out.println(updatedProduct);

//        assertThat(updatedProduct.getId()).isEqualTo(product.getId());
    }

}
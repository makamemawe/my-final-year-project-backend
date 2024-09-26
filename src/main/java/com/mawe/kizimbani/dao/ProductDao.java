package com.mawe.kizimbani.dao;

import com.mawe.kizimbani.entity.Category;
import com.mawe.kizimbani.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {
    List<Product> findAll(Pageable pageable);

     List<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
             String productName, String searchKey, Pageable pageable);


//    List<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCaseOrCategory_CategoryNameContainingIgnoreCase(
//            String productName, String searchKey, Category categoryName, Pageable pageable
//    );
}

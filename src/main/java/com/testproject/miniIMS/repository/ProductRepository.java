package com.testproject.miniIMS.repository;

import com.testproject.miniIMS.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

package com.testproject.miniIMS.repository;

import com.testproject.miniIMS.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

package com.danangww.onboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danangww.onboarding.model.BookCategoryModel;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategoryModel, Long> {

}

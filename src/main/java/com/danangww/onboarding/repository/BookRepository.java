package com.danangww.onboarding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.danangww.onboarding.model.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
	@Query(value = "SELECT * FROM books WHERE book_category_id=:categoryId", nativeQuery = true)
	List<BookModel> findByCategoryId(long categoryId);
}

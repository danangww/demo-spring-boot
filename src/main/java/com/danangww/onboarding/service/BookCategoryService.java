package com.danangww.onboarding.service;

import java.util.List;
import java.util.Optional;

import com.danangww.onboarding.model.BookCategoryModel;

public interface BookCategoryService {
	List<BookCategoryModel> findAll();
	Optional<BookCategoryModel> findById(long id);
	BookCategoryModel save(BookCategoryModel bookCategoryModel);
	BookCategoryModel update(long id, BookCategoryModel bookCategoryModel);
	void delete(long id);
}

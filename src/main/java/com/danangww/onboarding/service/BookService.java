package com.danangww.onboarding.service;

import java.util.List;
import java.util.Optional;

import com.danangww.onboarding.model.BookModel;

public interface BookService {
	List<BookModel> findAll();
	Optional<BookModel> findById(long id);
	List<BookModel> findByCategoryId(long categoryId);
	BookModel save(BookModel bookModel);
	BookModel update(long id, BookModel bookModel);
	void delete(long id);
}

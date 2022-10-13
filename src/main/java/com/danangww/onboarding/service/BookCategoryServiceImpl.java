package com.danangww.onboarding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danangww.onboarding.model.BookCategoryModel;
import com.danangww.onboarding.repository.BookCategoryRepository;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

	@Autowired
	private BookCategoryRepository bookCategoryRepository;

	@Override
	public List<BookCategoryModel> findAll() {
		return bookCategoryRepository.findAll();
	}

	@Override
	public BookCategoryModel save(BookCategoryModel bookCategoryModel) {
		return bookCategoryRepository.save(bookCategoryModel);
	}

	@Override
	public Optional<BookCategoryModel> findById(long id) {
		return bookCategoryRepository.findById(id);
	}

	@Override
	public BookCategoryModel update(long id, BookCategoryModel bookCategoryModel) {
		Optional<BookCategoryModel> check = findById(id);
		BookCategoryModel resultBookCategoryModel = null;

		if (check.isPresent()) {
			BookCategoryModel tempBookCategoryModel = check.get();
			tempBookCategoryModel.setName(bookCategoryModel.getName());
			resultBookCategoryModel = bookCategoryRepository.save(tempBookCategoryModel);
		}

		return resultBookCategoryModel;
	}

	@Override
	public void delete(long id) {
		bookCategoryRepository.deleteById(id);
	}

}

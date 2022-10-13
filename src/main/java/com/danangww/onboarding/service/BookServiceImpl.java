package com.danangww.onboarding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danangww.onboarding.model.BookModel;
import com.danangww.onboarding.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<BookModel> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public Optional<BookModel> findById(long id) {
		return bookRepository.findById(id);
	}

	@Override
	public BookModel save(BookModel bookModel) {
		return bookRepository.save(bookModel);
	}

	@Override
	public List<BookModel> findByCategoryId(long categoryId) {
		return bookRepository.findByCategoryId(categoryId);
	}

	@Override
	public BookModel update(long id, BookModel bookModel) {
		Optional<BookModel> check = findById(id);
		BookModel resultBookModel = null;

		if (check.isPresent()) {
			BookModel tempBookModel = check.get();
			tempBookModel.setTitle(bookModel.getTitle());
			tempBookModel.setDescription(bookModel.getDescription());
			tempBookModel.setAuthor(bookModel.getAuthor());
			resultBookModel = bookRepository.save(tempBookModel);
		}

		return resultBookModel;
	}

	@Override
	public void delete(long id) {
		bookRepository.deleteById(id);
	}

}

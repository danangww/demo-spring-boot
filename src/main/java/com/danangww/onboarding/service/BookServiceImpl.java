package com.danangww.onboarding.service;

import java.util.List;

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

}

package com.danangww.onboarding.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danangww.onboarding.dto.ResponseDTO;
import com.danangww.onboarding.model.BookCategoryModel;
import com.danangww.onboarding.model.BookModel;
import com.danangww.onboarding.service.BookCategoryServiceImpl;
import com.danangww.onboarding.service.BookServiceImpl;

@RestController
@RequestMapping(value = "/api/book-categories")
public class BookCategoryController {

	@Autowired
	private BookCategoryServiceImpl bookCategoryServiceImpl;

	@Autowired
	private BookServiceImpl bookServiceImpl;

	@GetMapping(value = "/")
	public ResponseDTO findBookCategories() {
		ResponseDTO responseDTO = new ResponseDTO();

		List<BookCategoryModel> list = bookCategoryServiceImpl.findAll();
		responseDTO.setCode(200);
		responseDTO.setData(list);

		return responseDTO;
	}

	@GetMapping(value = "/{bookCategoryId}")
	public ResponseDTO findBookCategoryById(@PathVariable("bookCategoryId") long id) {
		ResponseDTO responseDTO = new ResponseDTO();

		responseDTO.setCode(200);
		responseDTO.setData(bookCategoryServiceImpl.findById(id));

		return responseDTO;
	}

	@PostMapping(value = "/")
	public ResponseDTO saveBookCategory(@RequestBody BookCategoryModel bookCategoryModel) {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			BookCategoryModel insertedBookCategoryModel = bookCategoryServiceImpl.save(bookCategoryModel);
			responseDTO.setCode(200);
			responseDTO.setMessage("book category saved successfully.");
			responseDTO.setData(insertedBookCategoryModel);
		} catch (Exception e) {
			responseDTO.setCode(500);
			responseDTO.setMessage(e.getMessage());
		}

		return responseDTO;
	}

	@PutMapping(value = "/{bookCategoryId}")
	public ResponseDTO updateBookCategory(@PathVariable("bookCategoryId") long id,
			@RequestBody BookCategoryModel bookCategoryModel) {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			responseDTO.setCode(200);
			if (bookCategoryServiceImpl.findById(id).isPresent()) {
				BookCategoryModel updatedBookCategoryModel = bookCategoryServiceImpl.update(id, bookCategoryModel);
				responseDTO.setMessage("book category updated successfully.");
				responseDTO.setData(updatedBookCategoryModel);
			} else {
				responseDTO.setMessage("book category not found.");
			}
		} catch (Exception e) {
			responseDTO.setCode(500);
			responseDTO.setMessage(e.getMessage());
		}

		return responseDTO;
	}

	@DeleteMapping(value = "/{bookCategoryId}")
	public ResponseDTO deleteBookCategory(@PathVariable("bookCategoryId") long id) {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			responseDTO.setCode(200);
			if (bookCategoryServiceImpl.findById(id).isPresent()) {
				bookCategoryServiceImpl.delete(id);
				responseDTO.setMessage("book category deleted successfully.");
			} else {
				responseDTO.setMessage("book category not found.");
			}
		} catch (Exception e) {
			responseDTO.setCode(500);
			responseDTO.setMessage(e.getMessage());
		}

		return responseDTO;
	}

	@GetMapping(value = "/{bookCategoryId}/books")
	public ResponseDTO findBooks(@PathVariable("bookCategoryId") long bookCategoryId) {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			responseDTO.setCode(200);
			Optional<BookCategoryModel> check = bookCategoryServiceImpl.findById(bookCategoryId);

			if (check.isPresent()) {
				responseDTO.setData(bookServiceImpl.findByCategoryId(check.get().getId()));
			} else {
				responseDTO.setMessage("book category not found.");
			}
		} catch (Exception e) {
			responseDTO.setCode(500);
			responseDTO.setMessage(e.getMessage());
		}

		return responseDTO;
	}

	@PostMapping(value = "/{bookCategoryId}/books")
	public ResponseDTO saveBook(@PathVariable("bookCategoryId") long bookCategoryId, @RequestBody BookModel bookModel) {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			responseDTO.setCode(200);
			Optional<BookCategoryModel> check = bookCategoryServiceImpl.findById(bookCategoryId);

			if (check.isPresent()) {
				bookModel.setBookCategoryModel(check.get());
				BookModel insertedBookModel = bookServiceImpl.save(bookModel);
				responseDTO.setData(insertedBookModel);
				responseDTO.setMessage("book saved successfully.");
			} else {
				responseDTO.setMessage("book category not found.");
			}
		} catch (Exception e) {
			responseDTO.setCode(500);
			responseDTO.setMessage(e.getMessage());
		}

		return responseDTO;
	}

}

package com.danangww.onboarding.controller;

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
import com.danangww.onboarding.service.BookCategoryServiceImpl;

@RestController
@RequestMapping(value = "/api/book-categories")
public class BookCategoryController {

	@Autowired
	private BookCategoryServiceImpl bookCategoryServiceImpl;

	@GetMapping(value = "/")
	public ResponseDTO findAll() {
		ResponseDTO responseDTO = new ResponseDTO();

		responseDTO.setCode(200);
		responseDTO.setData(bookCategoryServiceImpl.findAll());

		return responseDTO;
	}

	@GetMapping(value = "/{bookCategoryId}")
	public ResponseDTO findById(@PathVariable("bookCategoryId") long id) {
		ResponseDTO responseDTO = new ResponseDTO();

		responseDTO.setCode(200);
		responseDTO.setData(bookCategoryServiceImpl.findById(id));

		return responseDTO;
	}

	@PostMapping(value = "/")
	public ResponseDTO save(@RequestBody BookCategoryModel bookCategoryModel) {
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
	public ResponseDTO update(@PathVariable("bookCategoryId") long id,
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
	public ResponseDTO delete(@PathVariable("bookCategoryId") long id) {
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

}

package com.danangww.onboarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danangww.onboarding.dto.ResponseDTO;
import com.danangww.onboarding.model.BookModel;
import com.danangww.onboarding.service.BookServiceImpl;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookServiceImpl bookServiceImpl;

	@GetMapping(value = "/")
	public ResponseDTO findAll() {
		ResponseDTO responseDTO = new ResponseDTO();

		responseDTO.setCode(200);
		responseDTO.setData(bookServiceImpl.findAll());

		return responseDTO;
	}

	@GetMapping(value = "/{bookId}")
	public ResponseDTO findById(@PathVariable("bookId") long bookId) {
		ResponseDTO responseDTO = new ResponseDTO();

		responseDTO.setCode(200);
		responseDTO.setData(bookServiceImpl.findById(bookId));

		return responseDTO;
	}

	@PutMapping(value = "/{bookId}")
	public ResponseDTO updateBook(@PathVariable("bookId") long id, @RequestBody BookModel bookModel) {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			responseDTO.setCode(200);
			if (bookServiceImpl.findById(id).isPresent()) {
				BookModel updatedBookModel = bookServiceImpl.update(id, bookModel);
				responseDTO.setMessage("book updated successfully.");
				responseDTO.setData(updatedBookModel);
			} else {
				responseDTO.setMessage("book not found.");
			}
		} catch (Exception e) {
			responseDTO.setCode(500);
			responseDTO.setMessage(e.getMessage());
		}

		return responseDTO;
	}

	@DeleteMapping(value = "/{bookId}")
	public ResponseDTO deleteBook(@PathVariable("bookId") long id) {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			responseDTO.setCode(200);
			if (bookServiceImpl.findById(id).isPresent()) {
				bookServiceImpl.delete(id);
				responseDTO.setMessage("book deleted successfully.");
			} else {
				responseDTO.setMessage("book not found.");
			}
		} catch (Exception e) {
			responseDTO.setCode(500);
			responseDTO.setMessage(e.getMessage());
		}

		return responseDTO;
	}
}

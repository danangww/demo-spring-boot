package com.danangww.onboarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danangww.onboarding.dto.ResponseDTO;
import com.danangww.onboarding.service.BookServiceImpl;

@RestController
@RequestMapping("/api/{bookCategoryId}")
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
}

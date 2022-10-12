package com.danangww.onboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danangww.onboarding.model.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

}
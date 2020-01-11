package com.test.demo.dao.Mongo;

import com.test.demo.dao.po.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTransaction extends MongoRepository<Book, String> {
    List<Book> findAll();

    Book save(Book book1);

    Book findBooksByBookId(String bookId);
}

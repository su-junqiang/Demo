package com.test.demo.service;

import com.test.demo.dao.po.Book;
import com.test.demo.utils.help.ReturnMesg;
import org.springframework.stereotype.Component;


@Component
public interface BookService {
    ReturnMesg selectAllBook();

    ReturnMesg addBook(Book book);
}

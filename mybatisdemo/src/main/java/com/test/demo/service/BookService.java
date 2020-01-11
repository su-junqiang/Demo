package com.test.demo.service;

import com.test.demo.utils.help.ReturnMesg;
import com.test.demo.dao.po.Book;

public interface BookService {
    ReturnMesg selectAllBook();

    ReturnMesg addBook(Book book);
}

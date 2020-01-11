package com.test.demo.service.impl;


import com.test.demo.dao.Mongo.BookTransaction;
import com.test.demo.utils.help.ReturnMesg;
import com.test.demo.dao.po.Book;
import com.test.demo.service.BookService;
import com.test.demo.utils.help.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookTransaction bookTransaction;

    @Override
    public ReturnMesg selectAllBook() {
        List<Book> books = bookTransaction.findAll();
        if (books == null) {
            return new ReturnMesg(ErrorCode.UNDEFIND_BOOK);
        }
        return new ReturnMesg(200, "成功", books);
    }

    @Override
    public ReturnMesg addBook(Book book) {
        Book stat;
        Book cod = bookTransaction.findBooksByBookId(book.getBookId());
        System.out.println(cod);
        if (cod == null) {
            Book book1 = new Book();
            book1.setBookId(book.getBookId());
            book1.setBookMesg(book.getBookMesg());
            book1.setBookName(book.getBookName());
            book1.setImg(book.getImg());
            stat = bookTransaction.save(book1);
        } else {
            return new ReturnMesg(ErrorCode.PARAM_BOOKID);
        }
        if (stat == null) {
            return new ReturnMesg(ErrorCode.BOOK_ADD_FAILED);
        } else {
            return new ReturnMesg();
        }
    }
}

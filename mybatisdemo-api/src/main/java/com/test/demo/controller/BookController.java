package com.test.demo.controller;

import com.test.demo.service.BookService;
import com.test.demo.utils.help.ErrorCode;
import com.test.demo.utils.help.ReturnMesg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.test.demo.dao.po.Book;



@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/selectAllBook")
    public ReturnMesg selectAllBook() {
        return bookService.selectAllBook();
    }

    @PostMapping("/addBook")
    public  ReturnMesg addBook(@RequestBody Book book){
        if (book.getBookId()==null||book.getBookName()==null){
            return new ReturnMesg(ErrorCode.PARAM_NOT_FOUND);
        }
        return bookService.addBook(book);
    }
}

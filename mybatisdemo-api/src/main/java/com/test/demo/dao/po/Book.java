package com.test.demo.dao.po;


import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bookId;
    private String bookName;
    private String img;
    private String bookMesg;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBookMesg() {
        return bookMesg;
    }

    public void setBookMesg(String bookMesg) {
        this.bookMesg = bookMesg;
    }

    @Override
    public String toString() {
        return "{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", img='" + img + '\'' +
                ", bookMesg='" + bookMesg + '\'' +
                '}';
    }
}

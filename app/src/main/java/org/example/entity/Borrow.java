package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_borrow")
public class Borrow {
    @TableId(type=IdType.AUTO)
    private Long id;
    private Long bookId;
    private String borrower;
    private String borrowDate;

    @TableField(exist=false)
    private String bookTitle;

    public Borrow(){
    }

    public Borrow(Long bookId,String borrower,String borrowDate){
        this.bookId=bookId;
        this.borrower=borrower;
        this.borrowDate=borrowDate;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public String getBorrower() { return borrower; }
    public void setBorrower(String borrower) { this.borrower = borrower; }
    public String getBorrowDate() { return borrowDate; }
    public void setBorrowDate(String borrowDate) { this.borrowDate = borrowDate; }
    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    @Override
    public String toString(){
        return "Borrow{id=" + id + ", bookId=" + bookId + ", bookTitle='" + bookTitle + "', borrower='" + borrower + "', borrowDate='" + borrowDate + "'}";
    }
}


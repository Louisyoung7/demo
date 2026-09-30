package org.example.service;
import org.example.entity.Book;
import org.example.mapper.BookMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService{
    private final BookMapper bookMapper;
    public BookService(BookMapper bookMapper){
        this.bookMapper=bookMapper;
    }
    public List<Book>listAll(){
        return bookMapper.selectList(null);
    }

    public Book getById(long id){
        return bookMapper.selectById(id);
    }
    public boolean add(Book book){
        return bookMapper.insert(book)>0;
    }
    public boolean update(Book book){
        return bookMapper.updateById(book)>0;
    }
    public boolean delete(long id){
        return bookMapper.deleteById(id)>0;
    }


}

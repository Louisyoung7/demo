package org.example.service;

import org.example.entity.Book;
import org.example.entity.Borrow;
import org.example.mapper.BookMapper;
import org.example.mapper.BorrowMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BorrowService {
    private final BorrowMapper borrowMapper;
    private final BookMapper bookMapper;//关联查询需要它来查书名

    public BorrowService (BorrowMapper borrowMapper,BookMapper bookMapper){
        this.borrowMapper=borrowMapper;
        this.bookMapper=bookMapper;
    }
    public List<Borrow>listAllWithBookTitle(){
        List<Borrow>list=borrowMapper.selectList(null);
        for(Borrow b:list){
            Book book=bookMapper.selectById(b.getBookId());
            if(book!=null){
                b.setBookTitle(book.getTitle());
            }
        }
        return list;
    }
    public Borrow getById(long id) {
        return borrowMapper.selectById(id);
    }

    public boolean add(Borrow borrow) {
        return borrowMapper.insert(borrow) > 0;
    }

    public boolean update(Borrow borrow) {
        return borrowMapper.updateById(borrow) > 0;
    }

    public boolean delete(long id) {
        return borrowMapper.deleteById(id) > 0;
    }


}

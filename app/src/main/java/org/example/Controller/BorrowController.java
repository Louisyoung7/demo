package org.example.controller;
import org.example.entity.Borrow;
import org.example.service.BorrowService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/borrows")
public class BorrowController {
    private final BorrowService borrowService;
    public BorrowController(BorrowService borrowService){
        this.borrowService=borrowService;
    }
    @GetMapping
    public List<Borrow>list(){
        return borrowService.listAllWithBookTitle();
    }

    @GetMapping("/{id}")
    public Borrow get(@PathVariable Long id){
        return borrowService.getById(id);
    }

    @PostMapping
    public Borrow add(@RequestBody Borrow borrow){
        borrowService.add(borrow);
        return borrow;
    }

    @PutMapping("/{id}")
    public Borrow update(@PathVariable Long id,@RequestBody Borrow borrow){
        borrow.setId(id);
        borrowService.update(borrow);
        return borrow;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return borrowService.delete(id);
    }
}